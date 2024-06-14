package com.pcwk.tvl.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class MemberDao implements WorkDiv<MemberDTO> {
	private ConnectionMaker connectionMaker;
	
	
	public MemberDao() {
		connectionMaker = new ConnectionMaker();
	}

	@Override
	public List<MemberDTO> doRetrieve(DTO search) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		MemberDTO outVO = null;
		SearchDTO searchVO = (SearchDTO) search;
		StringBuilder sbWhere = new StringBuilder(100);
		
		if(null!=searchVO.getSearchDiv()){
			switch(searchVO.getSearchDiv()) {
			case "":
				break;
			case "10": 
				sbWhere.append("WHERE user_id LIKE ? || '%' \n");
				break;
			
			case "20": 
				sbWhere.append("WHERE grades_seq LIKE ? ||'%' \n");
				break;
			
			case "30": 
				sbWhere.append("WHERE user_name = ? \n");
				break;
				
			case "40": 
				sbWhere.append("WHERE user_email LIKE ? ||'%' \n");
				break;
				
			case "50": 
				sbWhere.append("WHERE to_char(reg_dt,'yyyy-mm-dd') = ? \n");
				break; 
				
			default:
				log.debug("잘못된 조건입니다.");
				break;
			}
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet rs = null; // SQL문 결과
		StringBuilder sb = new StringBuilder(400);
		
		sb.append(" SELECT *                                                       \n");
		sb.append("   FROM(                                                        \n");
		sb.append("     SELECT tt1.rnum num,                                       \n");
		sb.append("            tt1.user_id,                                        \n");
		sb.append("            tt1.grades_seq,                                     \n");
		sb.append("            tt1.user_name,                                      \n");
		sb.append("            tt1.user_pw,                                        \n");
		sb.append("            tt1.user_email,                                     \n");
		sb.append("            to_char(tt1.reg_dt,'yyyy-mm-dd hh24:mi:ss') reg_dt  \n");
		sb.append("       FROM(                                                    \n");
		sb.append("         SELECT ROWNUM rnum, t1.*                               \n");
		sb.append("           FROM(                                                \n");
		sb.append("             SELECT *                                           \n");
		sb.append("               FROM v_user                                      \n");
		//--where------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where------------------------------------------------------------------------------------------
				
		sb.append("              ORDER BY reg_dt DESC                              \n");
		sb.append("         )t1                                                    \n");
		sb.append("          WHERE ROWNUM <= ( ? * (? -1)) + ?                     \n");
		sb.append("     )tt1                                                       \n");
		sb.append("      WHERE rnum >= ( ? * (? -1) + 1)                           \n");
		sb.append(" )A,(                                                           \n");
		sb.append("     SELECT COUNT(*) totalCnt                                   \n");
		sb.append("       FROM v_user                                              \n");
		//--where------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where------------------------------------------------------------------------------------------
		sb.append(" )B                                                             \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",search);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			if(null!=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				//paging ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
			}else if(null!=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")){
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				//paging ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
			}else if(null!=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				//paging ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
			}else if(null!=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				//paging ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
				
			}else if(null!=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				//paging ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
				
			}else {
			
				
				//paging ROWNUM
				pstmt.setInt(1, searchVO.getPageSize());
				pstmt.setInt(2, searchVO.getPageNo());
				pstmt.setInt(3, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());
				
			}
			
			
			//select실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			while(rs.next()) {
				outVO = new MemberDTO();
				
				outVO.setUserId(rs.getString("user_id"));
				outVO.setGradesSeq(rs.getInt("grades_seq"));
				outVO.setUserName(rs.getString("user_name"));
				outVO.setUserPw(rs.getString("user_pw"));
				outVO.setUserEmail(rs.getString("user_email"));
				outVO.setRegDt(rs.getString("reg_dt"));
				
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
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
	
	/*
	 * 아이디 영문+숫자만 기입가능 4~16자
	 * 비번 영문,숫자,특문 필수 10~20자
	 * 이름최대글자수 한글 30자
	 * 이메일 320자 로컬64 @ 도메인 255
	 * 
	 */
	@Override
	public int doSave(MemberDTO param) {
		String checkId = checkId(param.getUserId());
		String checkPW = checkPassword(param.getUserPw());
		String checkName = checkName(param.getUserName());
		String checkEmail = checkEmail(param.getUserEmail());
		
		if(!checkId.equals("이상무")) {
			log.debug("checkId: {} , 아이디자리수 : {}",checkId,param.getUserId().length());
			return 0;
		}
		if(!checkPW.equals("이상무")) {
			log.debug("checkPW: {} , 비밀번호자리수 : {}",checkPW,param.getUserPw().length());
			return 0;
		}
		if(!checkName.equals("이상무")) {
			log.debug("checkName: {}",checkName);
			return 0;
		}
		if(!checkEmail.equals("이상무")) {
			log.debug("checkEmail: {}",checkEmail);
			return 0;
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder(200);
		
		sb.append(" INSERT INTO v_user (  \n");
		sb.append("     user_id,          \n");
		sb.append("     grades_seq,       \n");
		sb.append("     user_name,        \n");
		sb.append("     user_pw,          \n");
		sb.append("     user_email,       \n");
		sb.append("     reg_dt            \n");
		sb.append(" ) VALUES (            \n");
		sb.append("     ?,                \n");
		sb.append("     1,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     SYSDATE           \n");
		sb.append(" )                     \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			pstmt.setString(1, param.getUserId());
			pstmt.setString(2, param.getUserName());
			pstmt.setString(3, param.getUserPw());
			pstmt.setString(4, param.getUserEmail());
			
			param.setFlag(pstmt.executeUpdate());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}
		log.debug("6.param.getFlag():{}",param.getFlag());
		
		
		return param.getFlag();
	}

	@Override
	public int doDelete(MemberDTO param) {
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		StringBuilder sb = new StringBuilder(100);
		
		sb.append(" DELETE FROM v_user  \n");
		sb.append(" WHERE user_id = ?   \n");

		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			
			pstmt = conn.prepareCall(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			pstmt.setString(1, param.getUserId());
			
			param.setFlag(pstmt.executeUpdate());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}
		log.debug("6.param.getFlag():{}",param.getFlag());
		
		return param.getFlag();
	}
	
	
	@Override
	public int doUpdate(MemberDTO param) {
		String checkPW = checkPassword(param.getUserPw());
		String checkName = checkName(param.getUserName());
		String checkEmail = checkEmail(param.getUserEmail());
		
		if(!checkPW.equals("이상무")) {
			log.debug("checkPW: {} , 비밀번호자리수 : {}",checkPW,param.getUserPw().length());
			return 0;
		}
		if(!checkName.equals("이상무")) {
			log.debug("checkName: {}",checkName);
			return 0;
		}
		if(!checkEmail.equals("이상무")) {
			log.debug("checkEmail: {}",checkEmail);
			return 0;
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder(200);
		
		sb.append(" UPDATE v_user          \n");
		sb.append(" SET                    \n");
		sb.append(" 	grades_seq  = ?,   \n");
		sb.append(" 	user_name   = ?,   \n");
		sb.append(" 	user_pw     = ?,   \n");
		sb.append(" 	user_email  = ?    \n");
		sb.append(" WHERE                  \n");
		sb.append("         user_id = ?    \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			pstmt.setInt(1, param.getGradesSeq());
			pstmt.setString(2, param.getUserName());
			pstmt.setString(3, param.getUserPw());
			pstmt.setString(4, param.getUserEmail());
			pstmt.setString(5, param.getUserId());
			
			param.setFlag(pstmt.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}
		log.debug("6.param.getFlag():{}",param.getFlag());
		
		return param.getFlag();
	}


	@Override
	public MemberDTO doSelectOne(MemberDTO param) {
		MemberDTO outVO = null; 
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder(200);
		
		sb.append(" SELECT                                  \n");
		sb.append("     user_id,                            \n");
		sb.append("     grades_seq,                         \n");
		sb.append("     user_name,                          \n");
		sb.append("     user_pw,                            \n");
		sb.append("     user_email,                         \n");
		sb.append("     to_char(reg_dt,'yyyy-mm-dd') reg_dt \n");
		sb.append(" FROM v_user                             \n");
		sb.append(" WHERE user_id = ?                       \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt = conn.prepareCall(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			pstmt.setString(1, param.getUserId());
			
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			if(rs.next()) {
				outVO = new MemberDTO();
				outVO.setUserId(rs.getString("user_id"));
				outVO.setGradesSeq(rs.getInt("grades_seq"));
				outVO.setUserName(rs.getString("user_name"));
				outVO.setUserPw(rs.getString("user_pw"));
				outVO.setUserEmail(rs.getString("user_email"));
				outVO.setRegDt(rs.getString("reg_dt"));
				
				log.debug("6.outVO:{}",outVO);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt, rs);
			log.debug("7. conn:{} pstmt:{} rs:{}",conn,pstmt,rs);
		}
		
		return outVO;
	}
	
	public String checkPassword(String pwd) {
		String patternPW = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()])(?=.*\\d)[a-zA-Z!@#$%^&*()\\d]+$";
		
		Pattern pattern = Pattern.compile(patternPW);
		Matcher matcher = pattern.matcher(pwd);
		
		if(!matcher.matches()) {
			return "하나이상의 영문,숫자,특수문자가 포함되어야 합니다.";
		}
		
		if( !(10 <=pwd.length() && pwd.length() <= 20) ) {
			return "비밀번호는 10~20자리를 입력하세요.";
		}
		
		Pattern pattern2 = Pattern.compile("^\\S*$");
		Matcher matcher2 = pattern2.matcher(pwd);
		
		if(!matcher2.matches()) {
			return "비밀번호에 공백은 불가능합니다.";
		}
		
		return "이상무";
	}
	
	public String checkId(String id) {
		String patternId = "^[a-zA-Z0-9]+$";
		
		Pattern pattern = Pattern.compile(patternId);
		Matcher matcher = pattern.matcher(id.trim());
		
		if(!matcher.matches()) {
			return "아이디는 영문과 숫자만 가능합니다.";
		}
		
		if( !(4 <=id.length() && id.length() <= 16) ) {
			return "아이디는 4~16자리를 입력하세요.";
		}
		
		int flag = checkUsedId(id);
		if(1<=flag) {
			return "중복된 아이디 입니다.";
		}
		
		return "이상무";
	}
	
	/**
	 * 아이디 중복체크 여부
	 * @param id
	 * @return 0: 미중복 , 1: 중복
	 */
	public int checkUsedId(String id) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder(100);
		
		sb.append(" SELECT COUNT(*) cnt    \n");
		sb.append("   FROM v_user          \n");
		sb.append("  WHERE user_id = ?     \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.id:{}",id);
		
		try {
			pstmt = conn.prepareCall(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			if(rs.next()) {
				flag = rs.getInt("cnt");
				log.debug("6.result: {}",flag);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt, rs);
			log.debug("6.finally conn:{} pstmt:{} rs:{}",conn,pstmt,rs);
		}
		
		return flag;
	}
	
	
	
	public String checkName(String name) {
		String patternName = "^[가-힣]+$";
		
		Pattern pattern = Pattern.compile(patternName);
		Matcher matcher = pattern.matcher(name.trim());
		
		if(!matcher.matches()) {
			return "이름을 확인하세요.";
		}
		
		if( name.length()>30 ) {
			return "이름의 글자수가 너무 많습니다.";
		}
		
		return "이상무";
	}
	
	public String checkEmail(String email) {
		String patternEmail = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
		
		Pattern pattern = Pattern.compile(patternEmail);
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches()) {
			return "이메일 형식을 다시 확인해주세요.";
		}
		
		String[] array = email.split("@");
//		for(String a :array)
//		log.debug(a);
		if(!(array[0].length()<=64 && array[1].length()<=255)) {
			return "이메일 길이를 확인해주세요.";
		}
		
	
		return "이상무";
	}

}
