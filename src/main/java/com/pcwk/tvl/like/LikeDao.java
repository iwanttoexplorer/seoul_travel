package com.pcwk.tvl.like;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.PLog;

public class LikeDao implements PLog{
	
	private ConnectionMaker connectionMaker;
	public LikeDao() {
		connectionMaker=new ConnectionMaker();
	}
	
	public int like(String userId, int aboardSeq, String crtDt) {
		
		Connection conn = connectionMaker.getConnection();
		String SQL="INSERT INTO v_like values(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userId);
			pstmt.setInt(2, aboardSeq);
			pstmt.setString(3, crtDt);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
