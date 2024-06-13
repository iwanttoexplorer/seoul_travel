package com.pcwk.ehr.travel;

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

public class ReviewDAO implements WorkDiv<ReviewDTO>, PLog {
    private ConnectionMaker connectionMaker;

    public ReviewDAO() {
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
        sb.append("     comment,                \n");
        sb.append("     title,                  \n");
        sb.append("     reg_dt,                 \n");
        sb.append("     mod_dt)                 \n");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, param.getAboard_seq());
            pstmt.setString(2, param.getComment());
            pstmt.setString(3, param.getImgLink());
            pstmt.setString(4, param.getContentId());
            pstmt.setString(5, param.getUser_id());
            pstmt.setString(6, param.getTitle());
            pstmt.setInt(7, param.getReadCnt());
            pstmt.setDate(8, new java.sql.Date(param.getRegDt().getTime()));
            pstmt.setDate(9, new java.sql.Date(param.getModDt().getTime()));

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
        sb.append("     contents = ?          \n");
        sb.append(" WHERE aboard_seq = ?      \n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, param.getComment());
            pstmt.setString(2, param.getTitle());
            pstmt.setInt(3, param.getAboard_seq());

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
        sb.append("DELETE FROM reviews   \n");
        sb.append("WHERE aboard_seq = ?  \n");

        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, param.getAboard_seq());

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
        sb.append("UPDATE reviews             \n");
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
