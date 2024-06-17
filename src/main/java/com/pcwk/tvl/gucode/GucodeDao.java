package com.pcwk.tvl.gucode;

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
import com.pcwk.tvl.category.CategoryDTO;

public class GucodeDao implements WorkDiv<GucodeDTO>, PLog {
	
	private ConnectionMaker connectionMaker;
	
	public GucodeDao() {
		connectionMaker = new ConnectionMaker(); //생성자 객체 만들기
		
	}

	@Override
	public List<GucodeDTO> doRetrieve(DTO search) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		
		SearchDTO  searchVO = (SearchDTO) search;
		
		//return값
		List<GucodeDTO> list = new ArrayList<GucodeDTO>();
		Connection conn = connectionMaker.getConnection(); //DB연결
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과	
		
		StringBuilder sb=new StringBuilder(300);
		sb.append(" SELECT           \n");
		sb.append("     gucode,      \n");
		sb.append("     gname        \n");
		sb.append(" FROM             \n");
		sb.append("     v_gucode     \n");
		
		
		log.debug("1.sql:\n {}", sb.toString());
		log.debug("2.conn: {}", conn);
		log.debug("3.search: {} ", search);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			//PARAM설정
			
			
			//SELECT SQL실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}", rs);
			
			while(rs.next()) {
				GucodeDTO outVO = new GucodeDTO();
				
				outVO.setGuCode(rs.getString("gucode"));
				outVO.setGname(rs.getString("gname"));

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

	@Override
	public int doSave(GucodeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(GucodeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(GucodeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GucodeDTO doSelectOne(GucodeDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		
		GucodeDTO outVO = null;//단건조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과	/결과 받을꺼	
		StringBuilder sb=new StringBuilder(500);
		sb.append(" SELECT           \n");
		sb.append("     gucode,      \n");
		sb.append("     gname        \n");
		sb.append(" FROM             \n");
		sb.append("     v_gucode     \n");
		sb.append(" WHERE gucode = ? \n");
		
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.param: {} ", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			
			//param설정
			pstmt.setString(1, param.getGuCode());
			
			//SELECT실행
			rs=pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			if(rs.next()) {
				outVO = new GucodeDTO();
				
				outVO.setGuCode(rs.getString("gucode"));
				outVO.setGname(rs.getString("gname"));
				
				
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
