package com.pcwk.tvl.content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.tvl.content.ContentDTO;

public class ContentDao implements WorkDiv<ContentDTO>, PLog{
	
	private ConnectionMaker connectionMaker;

	public ContentDao() {
		connectionMaker = new ConnectionMaker(); //생성자 객체 만들기
	}
	
	@Override
	public int doSave(ContentDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. 연결종료	
		
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null;//SQL+PARAM
		
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO v_content ( \n");
		sb.append("     contentid,          \n");
		sb.append("     category,           \n");
		sb.append("     gucode,             \n");
		sb.append("     tel,                \n");
		sb.append("     addr,               \n");
		sb.append("     img_link,           \n");
		sb.append("     title,              \n");
		sb.append("     reg_dt,             \n");
		sb.append("     mod_dt              \n");
		sb.append(" ) VALUES (              \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?                   \n");
		sb.append(" )                       \n");
		
	
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			conn.setAutoCommit(true);
			
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			//param설정
			pstmt.setString(1, param.getContentId());
			pstmt.setString(2, param.getCategory());
			pstmt.setString(3, param.getGucode());
			pstmt.setString(4, param.getTel());
			pstmt.setString(5, param.getAddr());
			pstmt.setString(6, param.getImgLink());
			pstmt.setString(7, param.getTitle());
			pstmt.setString(8, param.getRegDt());
			pstmt.setString(9, param.getModDt());
			
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
	public int doUpdate(ContentDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(ContentDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 다건 조회
	 */
	@Override
	public List<ContentDTO> doRetrieve(DTO search) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet : SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		
		SearchDTO  searchVO = (SearchDTO) search;
		
		StringBuilder sbWhere = new StringBuilder(300);
//		--WHERE title    LIKE :searchWord||'%' "10"
		
		if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
			sbWhere.append("WHERE title LIKE ?||'%' \n");
		}
		
		List<ContentDTO> list = new ArrayList<ContentDTO>();
		
		Connection conn = connectionMaker.getConnection(); //DB연결
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과
		
		StringBuilder sb=new StringBuilder(300);
		sb.append(" SELECT           \n");
		sb.append("     contentid,   \n");
		sb.append("     category,    \n");
		sb.append("     gucode,      \n");
		sb.append("     tel,         \n");
		sb.append("     addr,        \n");
		sb.append("     img_link,    \n");
		sb.append("     title,       \n");
		sb.append("     reg_dt,      \n");
		sb.append("     mod_dt       \n");
		sb.append(" FROM             \n");
		sb.append("     v_content    \n");
		sb.append(" WHERE gucode = ? \n");
		sb.append(" AND category = ? \n");
		sb.append(" AND title = ?    \n");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.search: {} ", search);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);
			
			
			
			//SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			while(rs.next()) {
				//건수 최대값만 정해짐 
				ContentDTO outVO=new ContentDTO();
				
				outVO.setContentId(rs.getString("contentId"));
				outVO.setCategory(rs.getString("category"));
				outVO.setGucode(rs.getString("gucode"));
				outVO.setTel(rs.getString("tel"));
				outVO.setAddr(rs.getString("addr"));
				outVO.setImgLink(rs.getString("img_link"));
				outVO.setTitle(rs.getString("title"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				
				
				list.add(outVO);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt, rs);
			log.debug("6.finally conn:{} pstmt:{} rs:{}",conn,pstmt,rs);
		}
		
		
		return list;
	}

	/**
	 * 단건 조회
	 */
	@Override
	public ContentDTO doSelectOne(ContentDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		
		ContentDTO outVO = null;//단건조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과	/결과 받을꺼	
		StringBuilder sb=new StringBuilder(500);
		sb.append(" SELECT              \n");
		sb.append("     contentid,      \n");
		sb.append("     category,       \n");
		sb.append("     gucode,         \n");
		sb.append("     tel,            \n");
		sb.append("     addr,           \n");
		sb.append("     img_link,       \n");
		sb.append("     title,          \n");
		sb.append("     reg_dt,         \n");
		sb.append("     mod_dt          \n");
		sb.append(" FROM                \n");
		sb.append("     v_content       \n");
		sb.append(" WHERE contentid = ? \n");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.param: {} ", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			
			//param설정
			pstmt.setString(1, param.getContentId());
			
			//SELECT실행
			rs=pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			if(rs.next()) {
				outVO = new ContentDTO();
				
				outVO.setContentId(rs.getString("contentid"));
				outVO.setCategory(rs.getString("category"));
				outVO.setGucode(rs.getString("gucode"));
				outVO.setTel(rs.getString("tel"));
				outVO.setImgLink(rs.getString("img_link"));
				outVO.setTitle(rs.getString("title"));
				
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				
				log.debug("6.outVO: {}",outVO);
			}
			
		}catch(SQLException e) {
			log.debug("────────────────────────");
			log.debug("SQLException:"+e.getMessage());
			log.debug("────────────────────────");
		}finally {
			DBUtill.close(conn, pstmt, rs);
		}
		return outVO;
	}
	
	
}