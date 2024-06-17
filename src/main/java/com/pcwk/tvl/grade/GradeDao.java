package com.pcwk.tvl.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class GradeDao implements WorkDiv<GradeDTO>{
	private ConnectionMaker connectionMaker;
	
	public GradeDao() {
		connectionMaker=new ConnectionMaker();
	}
	
	@Override
	public List<GradeDTO> doRetrieve(DTO search) {
		GradeDTO inVO = (GradeDTO)search;
		List<GradeDTO> list = new ArrayList<GradeDTO>();
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder(300);
		
		sb.append(" SELECT          \n");
		sb.append("     grades_seq, \n");
		sb.append("     grade       \n");
		sb.append(" FROM            \n");
		sb.append("     v_grade     \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.inVO:{}",inVO);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			while(rs.next()) {
				GradeDTO outVO = new GradeDTO();
				outVO.setGrades_seq(rs.getString("grades_seq"));
				outVO.setGrade(rs.getString("grade"));
				
				log.debug("outVO: {}",outVO);
				list.add(outVO);
			}
			
		}catch(SQLException e) {
			log.debug("4.SQLException: {}",e);
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public int doSave(GradeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(GradeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(GradeDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GradeDTO doSelectOne(GradeDTO param) {
		
		
		return null;
	}
	

}
