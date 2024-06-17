package com.pcwk.tvl.review;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.cmn.PLog;

public class ReviewDao implements WorkDiv<ReviewDTO>, PLog {
    private ConnectionMaker connectionMaker;

    public ReviewDao() {
        connectionMaker = new ConnectionMaker();
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
        sb.append("     comments,                \n");
        sb.append("     title,                  \n");
        sb.append("     reg_dt,                 \n");
        sb.append("     mod_dt,                 \n");
        sb.append("     read_cnt               \n");
        sb.append(") VALUES (               \n");
		sb.append("    review_seq.NEXTVAL,   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    SYSDATE,             \n");
		sb.append("    SYSDATE,             \n");
		sb.append("    0            	    \n");
		sb.append(")                        \n");
        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

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

	@Override
	public List<ReviewDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewDTO doSelectOne(ReviewDTO param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
