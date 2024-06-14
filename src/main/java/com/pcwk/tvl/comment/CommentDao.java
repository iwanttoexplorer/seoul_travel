package com.pcwk.tvl.comment;
import com.pcwk.tvl.comment.*;
import com.pcwk.ehr.cmn.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CommentDao implements WorkDiv<CommentDTO>, PLog{
	private ConnectionMaker connectionMaker;

	public CommentDao() {
		connectionMaker=new ConnectionMaker();
	}

	@Override
	public List<CommentDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(CommentDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO v_comment (      \n");
		sb.append("    comseq,                 \n");
		sb.append("    aboard_seq,               \n");
		sb.append("    user_id,            \n");
		sb.append("    content,            \n");
		sb.append("    reg_dt,              \n");
		sb.append("    mod_dt              \n");
		sb.append(") VALUES (               \n");
		sb.append("    comment_seq.NEXTVAL, \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    SYSDATE,             \n");
		sb.append("    SYSDATE              \n");
		sb.append(")                        \n");
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		try {
//			conn.setAutoCommit(true); 기본 값이 true 자동 커밋
			
			//preparestatement << 사용 금지!!! 해킹가능성!!!
			pstmt = conn.prepareCall(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			//param 설정
			
			pstmt.setInt(1, param.getAboardSeq());
			pstmt.setString(2, param.getUserId());
			pstmt.setString(3, param.getContent());
			
			//DML
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}
		log.debug("6.flag:{}",flag);

		return flag;
	}

	@Override
	public int doUpdate(CommentDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE v_comment          \n");
		sb.append(" SET                   \n");
		sb.append("     content= ?  ,      \n");
		sb.append("     mod_dt = SYSDATE  \n");
		sb.append(" WHERE                 \n");
		sb.append("     comseq = ?       \n");
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			pstmt.setString(1, param.getContent());
			pstmt.setInt(2, param.getComSeq());
			
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}
		log.debug("6.flag:{}",flag);
		
		return flag;
	}

	@Override
	public int doDelete(CommentDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM

		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM v_comment \n");
		sb.append("where comseq=?       \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		try {
//			conn.setAutoCommit(true); 기본 값이 true 자동 커밋
			
			//preparestatement << 사용 금지!!! 해킹가능성!!!
			pstmt = conn.prepareCall(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			//param 설정
			pstmt.setInt(1, param.getComSeq());
			
			
			//DML
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}
		log.debug("6.flag:{}",flag);
		return flag;
	}

	@Override
	public CommentDTO doSelectOne(CommentDTO param) {
		// TODO Auto-generated method stub
		return null;
	}



}
