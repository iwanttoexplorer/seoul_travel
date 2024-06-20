package com.pcwk.tvl.like;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.tvl.review.ReviewDTO;

public class LikeDao implements PLog,WorkDiv<LikeDTO>{
	
	private ConnectionMaker connectionMaker;
	public LikeDao() {
		connectionMaker=new ConnectionMaker();
	}
	
    public int doSave(LikeDTO param) {
		int flag=0;
		Connection conn = connectionMaker.getConnection();
        PreparedStatement pstmt = null;
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO v_like (          \n");
        sb.append("    user_id,                 \n");
        sb.append("    aboard_seq,              \n");
        sb.append("    crt_dt                   \n");
        sb.append(") VALUES (                   \n");
        sb.append("    ?,                       \n");
        sb.append("    ?,                       \n");
        sb.append("    SYSDATE                  \n");
        sb.append(")                            \n");
        
        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);

        try {
            // preparestatement << 사용 금지!!! 해킹가능성!!!
            pstmt = conn.prepareCall(sb.toString());
            log.debug("4.pstmt:{}", pstmt);
            
            // param 설정
            pstmt.setString(1, param.getUserId());
            pstmt.setInt(2, param.getAboardSeq());

            // DML
            flag = pstmt.executeUpdate();


           

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt);
            log.debug("5.finally conn:{} pstmt:{}", conn, pstmt);
        }

        log.debug("6.flag:{}", flag);

        return flag;
    }
	
    public int doLike(LikeDTO param) {
    	int likeCount=0;
    	Connection conn = connectionMaker.getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT COUNT(*)               \n");
        sb.append("FROM v_like                   \n");
        sb.append("WHERE aboard_seq = ?          \n");
        log.debug("1.sql:{}", sb.toString());
        log.debug("2.conn:{}", conn);
        log.debug("3.param:{}", param);
        try {
            pstmt = conn.prepareCall(sb.toString());
            log.debug("4.pstmt:{}", pstmt);

            // param 설정
            pstmt.setInt(1, param.getAboardSeq());

            rs = pstmt.executeQuery();
            if (rs.next()) {
                likeCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(conn, pstmt, rs);
            log.debug("5.finally conn:{} pstmt:{} rs:{}", conn, pstmt, rs);
        }

        log.debug("6.likeCount:{}", likeCount);

        return likeCount;
    }

	@Override
	public List<LikeDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;

}

	@Override
	public int doUpdate(LikeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(LikeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LikeDTO doSelectOne(LikeDTO param) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
