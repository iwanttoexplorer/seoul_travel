package com.pcwk.tvl.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class ReviewDao implements WorkDiv<ReviewDTO>, PLog {
    private ConnectionMaker connectionMaker;
    private List<ReviewDTO> reviewList;
    
    public ReviewDao() {
        connectionMaker = new ConnectionMaker();
        reviewList = new ArrayList<>();
    }

    // 리뷰 등록 메서드
    public int doSave(ReviewDTO param) {
        int flag = 0;
        Connection conn = connectionMaker.getConnection();
        PreparedStatement pstmt = null;

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO v_review (      \n");
        sb.append("     aboard_seq,             \n");
        sb.append("     contentId,              \n");
        sb.append("     user_id,                \n");
        sb.append("     img_link,               \n");
        sb.append("     comments,               \n");
        sb.append("     title,                  \n");
        sb.append("     reg_dt,                 \n");
        sb.append("     mod_dt,                 \n");
        sb.append("     read_cnt               \n");
        sb.append(") VALUES (                   \n");
        sb.append("    review_seq.NEXTVAL,      \n");
        sb.append("    ?,                       \n");
        sb.append("    ?,                       \n");
        sb.append("    ?,                       \n");
        sb.append("    ?,                       \n");
        sb.append("    ?,                       \n");
        sb.append("    SYSDATE,                 \n");
        sb.append("    SYSDATE,                 \n");
        sb.append("    0                        \n");
        sb.append(")                            \n");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, param.getContentId());
            pstmt.setString(2, param.getUserId());
            pstmt.setString(3, param.getImgLink());
            pstmt.setString(4, param.getComments());
            pstmt.setString(5, param.getTitle());

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt);
            log.debug("5.conn:{} pstmt:{}", conn, pstmt);
        }
        log.debug("6.flag:{}", flag);

        return flag;
    }

    // 리뷰 수정 메서드
    public int doUpdate(ReviewDTO param) {
        int flag = 0;
        Connection conn = connectionMaker.getConnection();
        PreparedStatement pstmt = null;

        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE v_review SET       \n");
        sb.append("     aboard_seq = ?,       \n");
        sb.append("     title = ?,            \n");
        sb.append("     comments = ?          \n");
        sb.append(" WHERE aboard_seq = ?      \n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, param.getAboardSeq());
            pstmt.setString(2, param.getTitle());
            pstmt.setString(3, param.getComments());
            pstmt.setInt(4, param.getAboardSeq());
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt);
            log.debug("5.conn:{} pstmt:{}", conn, pstmt);
        }
        log.debug("6.flag:{}", flag);

        return flag;
    }

    // 리뷰 삭제 메서드
    public int doDelete(ReviewDTO param) {
        int flag = 0;
        Connection conn = connectionMaker.getConnection();
        PreparedStatement pstmt = null;

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM v_review   \n");
        sb.append("WHERE aboard_seq = ?  \n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, param.getAboardSeq());

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt);
            log.debug("5.conn:{} pstmt:{}", conn, pstmt);
        }
        log.debug("6.flag:{}", flag);

        return flag;
    }

    // 리뷰 조회수 증가 메서드
    public int doReadCnt(int aboard_seq) throws SQLException {
        int flag = 0;
        Connection conn = connectionMaker.getConnection();
        PreparedStatement pstmt = null;

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE v_review             \n");
        sb.append("SET readCnt = readCnt + 1  \n");
        sb.append("WHERE aboard_seq = ?       \n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.aboard_seq:{}", aboard_seq);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, aboard_seq);

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt);
            log.debug("5.conn:{} pstmt:{}", conn, pstmt);
        }
        log.debug("6.flag:{}", flag);

        return flag;
    }

    // 리뷰 목록 조회 메서드
    @Override
    public List<ReviewDTO> doRetrieve(DTO search) {
        List<ReviewDTO> list = new ArrayList<>();
        Connection conn = connectionMaker.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM v_review      \n");
        sb.append("ORDER BY aboard_seq DESC    \n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ReviewDTO review = new ReviewDTO();
                review.setAboardSeq(rs.getInt("aboard_seq"));
                review.setContentId(rs.getString("contentId"));
                review.setUserId(rs.getString("user_id"));
                review.setImgLink(rs.getString("img_link"));
                review.setComments(rs.getString("comments"));
                review.setTitle(rs.getString("title"));
                review.setRegDt(rs.getString("reg_dt"));
                review.setModDt(rs.getString("mod_dt"));
                review.setReadCnt(rs.getInt("read_cnt"));
                list.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt, rs);
            log.debug("5.conn:{} pstmt:{} rs:{}", conn, pstmt, rs);
        }
        log.debug("6.list.size:{}", list.size());

        return list;
    }
    
 // 특정 페이지의 리뷰 목록을 가져오는 메서드
    public List<ReviewDTO> getReviews(int pageNumber, int pageSize) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ( ");
        sb.append("SELECT a.*, ROWNUM rnum FROM ( ");
        sb.append("SELECT * FROM v_review ORDER BY ABOARD_SEQ DESC ");
        sb.append(") a WHERE ROWNUM <= ? ");
        sb.append(") WHERE rnum > ?");

        List<ReviewDTO> reviews = new ArrayList<>();

        try (Connection conn = connectionMaker.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sb.toString())) {
            int endRow = pageNumber * pageSize;
            int startRow = (pageNumber - 1) * pageSize;
            pstmt.setInt(1, endRow);
            pstmt.setInt(2, startRow);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ReviewDTO review = new ReviewDTO();
                    review.setAboardSeq(rs.getInt("ABOARD_SEQ"));
                    review.setContentId(rs.getString("CONTENTID"));
                    review.setUserId(rs.getString("USER_ID"));
                    review.setImgLink(rs.getString("IMG_LINK"));
                    review.setComments(rs.getString("COMMENTS"));
                    review.setTitle(rs.getString("TITLE"));
                    review.setRegDt(rs.getString("REG_DT"));
                    review.setModDt(rs.getString("MOD_DT"));
                    review.setReadCnt(rs.getInt("READ_CNT"));
                    reviews.add(review);
                }
            }
        }
        return reviews;
    }

    // 전체 리뷰 수를 가져오는 메서드
    public int getTotalReviews() throws SQLException {
        String sql = "SELECT COUNT(*) FROM v_review";
        try (Connection conn = connectionMaker.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    

    
    // 추천 함수임
    public int like(int aboardSeq) {
        String SQL = "Update V_REVIEW set ";
        return -1;
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewList;
    }

    public ReviewDTO getReviewById(String id) {
        for (ReviewDTO review : reviewList) {
            if (review.getUserId() == id) {
                return review;
            }
        }
        return null;
    }

	@Override
	public ReviewDTO doSelectOne(ReviewDTO inVO) {
        ReviewDTO outVO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connectionMaker.getConnection();
            String sql = "SELECT * FROM v_review WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inVO.getUserId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                outVO = new ReviewDTO();
                outVO.setTitle(rs.getString("title"));
                outVO.setComments(rs.getString("comments"));
                outVO.setUserId(rs.getString("userId"));
                outVO.setRegDt(rs.getString("regDt"));
                outVO.setReadCnt(rs.getInt("readCnt"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }

        return outVO;
    }
}
