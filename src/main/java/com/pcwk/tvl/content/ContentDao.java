package com.pcwk.tvl.content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.ContentsSearchDTO;
import com.pcwk.ehr.cmn.DBUtill;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class ContentDao implements WorkDiv<ContentDTO>,PLog{
	
	private ConnectionMaker connectionMaker;
	
	public ContentDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	@Override
	public ContentDTO doSelectOne(ContentDTO param) {
//		1. Drivermanager로 데이터 베이스와 연결
//		2. Connection : ID/PW 데이터 베이스와 연결 인터페이스
//		3. Statement/PreparedStatement : SQL문을 실행 인터페이스
//		4. ResultSet : SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결 종료!
		
		ContentDTO outVO = null; //단건 조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; //SQL+PARAM
		ResultSet rs = null; // SQL문 결과
		StringBuilder sb = new StringBuilder(300);
		
		sb.append(" SELECT                                                                        \n");
		sb.append("     t1.contentid,                                                             \n");
		sb.append("     t1.category,                                                              \n");
		sb.append("     t1.gucode,                                                                \n");
		sb.append("     t1.tel,                                                                   \n");
		sb.append("     t1.addr,                                                                  \n");
		sb.append("     t1.img_link,                                                              \n");
		sb.append("     t1.title,                                                                 \n");
		sb.append("     to_char(to_date(t1.reg_dt,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') reg_dt,  \n");
		sb.append("     to_char(to_date(t1.mod_dt,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') mod_dt,  \n");
		sb.append("     t2.gname,                                                                 \n");
		sb.append("     t3.cat_name                                                               \n");
		sb.append(" FROM v_content t1, v_gucode t2 , v_category t3                                \n");
		sb.append(" WHERE contentid = ?                                                           \n");
		sb.append(" and t2.gucode = t1.gucode                                                     \n");
		sb.append(" and t3.category = t1.category                                                 \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt = conn.prepareCall(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			//param
			pstmt.setString(1, param.getContentId());
			
			//SELECT 실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			if(rs.next()) {
				outVO = new ContentDTO();
				outVO.setContentId(rs.getString("contentid"));
				outVO.setCategory(rs.getString("category"));
				outVO.setGucode(rs.getString("gucode"));
				outVO.setTel(rs.getString("tel"));
				outVO.setAddr(rs.getString("addr"));
				outVO.setImgLink(rs.getString("img_link"));
				outVO.setTitle(rs.getString("title"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setgName(rs.getString("gname"));
				outVO.setCatName(rs.getString("cat_name"));
				
				log.debug("6.outVO:{}",outVO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtill.close(conn, pstmt, rs);
		}
		
		return outVO;
	}
	
	/**
	 * 관광지만
	 */
	@Override
	public List<ContentDTO> doRetrieve(DTO search) {
//		1. Drivermanager로 데이터 베이스와 연결
//		2. Connection : ID/PW 데이터 베이스와 연결 인터페이스
//		3. Statement/PreparedStatement : SQL문을 실행 인터페이스
//		4. ResultSet : SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결 종료!
		List<ContentDTO> list = new ArrayList<ContentDTO>(); //결과 데이터
		ContentDTO outVO;
		
		ContentsSearchDTO searchVO = (ContentsSearchDTO) search;   //검색 조건
		StringBuilder sbWhere = new StringBuilder(200); //검색조건 sql
		
		/*
		 *  검색어로만        "10"
			카테고리만        "20"
			구 별로만         "30"
			검색어+구         "40"
			검색어+카테고리   "50"
			구+카테고리       "60"
			구+카테고리+검색어"70"
		 */
		if(null!=searchVO.getSearchDiv()) {
			switch(searchVO.getSearchDiv()) {
			case "10":
				sbWhere.append("AND t1.title LIKE ? || '%' \n");
				break;
			case "20":
				sbWhere.append("AND t3.cat_name = ? \n");
				break;
			case "30":
				sbWhere.append("AND t2.gname = ? \n");
				break;
			case "40":
				sbWhere.append("AND t1.title LIKE ? || '%' AND t2.gname = ? \n");
				break;
			case "50":
				sbWhere.append("AND t1.title LIKE ? || '%' AND t3.cat_name = ? \n");
				break;
			case "60":
				sbWhere.append("AND t2.gname = ? AND t3.cat_name = ? \n");
			break;
			case "70":
				sbWhere.append("AND t2.gname = ? AND t3.cat_name = ? \n");
				sbWhere.append("AND t1.title LIKE ? || '%' \n");
				break;
			default:
				log.debug("seach_div 값을 확인하세요.");
				break;
			}//switch
			
			
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet rs = null; // SQL문 결과
		
		StringBuilder sb = new StringBuilder(500);
		
		sb.append(" SELECT *                                                                               \n");
		sb.append(" FROM(                                                                                  \n");
		sb.append("     SELECT ttt1.rnum,                                                                  \n");
		sb.append("     	   ttt1.contentid,                                                             \n");
		sb.append("            ttt1.category,                                                              \n");
		sb.append("            ttt1.gucode,                                                                \n");
		sb.append("            ttt1.tel,                                                                   \n");
		sb.append("            ttt1.addr,                                                                  \n");
		sb.append("            ttt1.img_link,                                                              \n");
		sb.append("            ttt1.title,                                                                 \n");
		sb.append("            to_char(to_date(ttt1.reg_dt,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') reg_dt,  \n");
		sb.append("            to_char(to_date(ttt1.mod_dt,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') mod_dt,  \n");
		sb.append("            ttt1.gname,                                                                 \n");
		sb.append("            ttt1.cat_name                                                               \n");
		sb.append("     FROM(                                                                              \n");
		sb.append("         SELECT ROWNUM rnum, tt1.*                                                      \n");
		sb.append("         FROM(                                                                          \n");
		sb.append("             SELECT t1.*,t2.gname,t3.cat_name                                           \n");
		sb.append("             FROM v_content t1 JOIN v_gucode t2 ON t1.gucode = t2.gucode                \n");
		sb.append(" 			JOIN v_category t3 ON t1.category = t3.category                            \n");
		sb.append(" 			WHERE LENGTH(t1.category) = 5                                               \n");
		//--where------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where------------------------------------------------------------------------------------------
		sb.append("             ORDER BY mod_dt DESC                                                       \n");
		sb.append("         )tt1                                                                           \n");
		sb.append("         WHERE ROWNUM <= ( ? * (? -1)) + ?                                              \n");
		sb.append("     )ttt1                                                                              \n");
		sb.append("     WHERE rnum >= ( ? * (? -1) + 1)                                                    \n");
		sb.append(" )A,(SELECT COUNT(*) totalCnt                                                           \n");
		sb.append("             FROM v_content t1 JOIN v_gucode t2 ON t1.gucode = t2.gucode                \n");
		sb.append(" 			JOIN v_category t3 ON t1.category = t3.category                            \n");
		sb.append(" 			WHERE LENGTH(t1.category) = 5                                               \n");
		//--where------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where------------------------------------------------------------------------------------------
		sb.append("    )B                                                                                  \n");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("10")) {
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
				
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("20")){
				pstmt.setString(1, searchVO.getCategoryWord());
				
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getCategoryWord());
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("30")) {
				pstmt.setString(1, searchVO.getgNameWord());
				
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getgNameWord());
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("40")) {
				pstmt.setString(1, searchVO.getSearchWord());
				pstmt.setString(2, searchVO.getgNameWord());
				
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				pstmt.setString(8, searchVO.getSearchWord());
				pstmt.setString(9, searchVO.getgNameWord());
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("50")) {
				pstmt.setString(1, searchVO.getSearchWord());
				pstmt.setString(2, searchVO.getCategoryWord());
				
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				pstmt.setString(8, searchVO.getSearchWord());
				pstmt.setString(9, searchVO.getCategoryWord());
			} else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("60")){
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getgNameWord());
				pstmt.setString(2, searchVO.getCategoryWord());
				
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				pstmt.setString(8, searchVO.getgNameWord());
				pstmt.setString(9, searchVO.getCategoryWord());
				
				
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("70")) {
				pstmt.setString(1, searchVO.getgNameWord());
				pstmt.setString(2, searchVO.getCategoryWord());
				pstmt.setString(3, searchVO.getSearchWord());
				
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());
				pstmt.setInt(6, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(7, searchVO.getPageSize());
				pstmt.setInt(8, searchVO.getPageNo());
				
				pstmt.setString(9, searchVO.getgNameWord());
				pstmt.setString(10, searchVO.getCategoryWord());
				pstmt.setString(11, searchVO.getSearchWord());
			} else {
				pstmt.setInt(1, searchVO.getPageSize());
				pstmt.setInt(2, searchVO.getPageNo());
				pstmt.setInt(3, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());
			}
			
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			while(rs.next()) {
				
				outVO = new ContentDTO();
				
				outVO.setNum(rs.getInt("rnum"));
				outVO.setContentId(rs.getString("contentid"));
				outVO.setCategory(rs.getString("category"));
				outVO.setGucode(rs.getString("gucode"));
				outVO.setTel(rs.getString("tel"));
				outVO.setAddr(rs.getString("addr"));
				outVO.setImgLink(rs.getString("img_link"));
				outVO.setTitle(rs.getString("title"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setgName(rs.getString("gname"));
				outVO.setCatName(rs.getString("cat_name"));
				
				
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
	
	/**
	 * 식당 조회
	 */
	public List<ContentDTO> doRetrieve2(DTO search) {
//		1. Drivermanager로 데이터 베이스와 연결
//		2. Connection : ID/PW 데이터 베이스와 연결 인터페이스
//		3. Statement/PreparedStatement : SQL문을 실행 인터페이스
//		4. ResultSet : SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결 종료!
		List<ContentDTO> list = new ArrayList<ContentDTO>(); //결과 데이터
		ContentDTO outVO;
		
		ContentsSearchDTO searchVO = (ContentsSearchDTO) search;   //검색 조건
		StringBuilder sbWhere = new StringBuilder(200); //검색조건 sql
		
		/*
		 *  검색어로만        "10"
			카테고리만        "20"
			구 별로만         "30"
			검색어+구         "40"
			검색어+카테고리   "50"
			구+카테고리       "60"
			구+카테고리+검색어"70"
		 */
		if(null!=searchVO.getSearchDiv()) {
			switch(searchVO.getSearchDiv()) {
			case "10":
				sbWhere.append("AND t1.title LIKE ? || '%' \n");
				break;
			case "20":
				sbWhere.append("AND t3.cat_name = ? \n");
				break;
			case "30":
				sbWhere.append("AND t2.gname = ? \n");
				break;
			case "40":
				sbWhere.append("AND t1.title LIKE ? || '%' AND t2.gname = ? \n");
				break;
			case "50":
				sbWhere.append("AND t1.title LIKE ? || '%' AND t3.cat_name = ? \n");
				break;
			case "60":
				sbWhere.append("AND t2.gname = ? AND t3.cat_name = ? \n");
			break;
			case "70":
				sbWhere.append("AND t2.gname = ? AND t3.cat_name = ? \n");
				sbWhere.append("AND t1.title LIKE ? || '%' \n");
				break;
			default:
				log.debug("seach_div 값을 확인하세요.");
				break;
			}//switch
			
			
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet rs = null; // SQL문 결과
		
		StringBuilder sb = new StringBuilder(500);
		
		sb.append(" SELECT *                                                                               \n");
		sb.append(" FROM(                                                                                  \n");
		sb.append("     SELECT ttt1.rnum,                                                                  \n");
		sb.append("     	   ttt1.contentid,                                                             \n");
		sb.append("            ttt1.category,                                                              \n");
		sb.append("            ttt1.gucode,                                                                \n");
		sb.append("            ttt1.tel,                                                                   \n");
		sb.append("            ttt1.addr,                                                                  \n");
		sb.append("            ttt1.img_link,                                                              \n");
		sb.append("            ttt1.title,                                                                 \n");
		sb.append("            to_char(to_date(ttt1.reg_dt,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') reg_dt,  \n");
		sb.append("            to_char(to_date(ttt1.mod_dt,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') mod_dt,  \n");
		sb.append("            ttt1.gname,                                                                 \n");
		sb.append("            ttt1.cat_name                                                               \n");
		sb.append("     FROM(                                                                              \n");
		sb.append("         SELECT ROWNUM rnum, tt1.*                                                      \n");
		sb.append("         FROM(                                                                          \n");
		sb.append("             SELECT t1.*,t2.gname,t3.cat_name                                           \n");
		sb.append("             FROM v_content t1 JOIN v_gucode t2 ON t1.gucode = t2.gucode                \n");
		sb.append(" 			JOIN v_category t3 ON t1.category = t3.category                            \n");
		sb.append(" 			WHERE LENGTH(t1.category) = 9                                               \n");
		//--where------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where------------------------------------------------------------------------------------------
		sb.append("             ORDER BY mod_dt DESC                                                       \n");
		sb.append("         )tt1                                                                           \n");
		sb.append("         WHERE ROWNUM <= ( ? * (? -1)) + ?                                              \n");
		sb.append("     )ttt1                                                                              \n");
		sb.append("     WHERE rnum >= ( ? * (? -1) + 1)                                                    \n");
		sb.append(" )A,(SELECT COUNT(*) totalCnt                                                           \n");
		sb.append("             FROM v_content t1 JOIN v_gucode t2 ON t1.gucode = t2.gucode                \n");
		sb.append(" 			JOIN v_category t3 ON t1.category = t3.category                            \n");
		sb.append(" 			WHERE LENGTH(t1.category) = 5                                               \n");
		//--where------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where------------------------------------------------------------------------------------------
		sb.append("    )B                                                                                  \n");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("10")) {
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getSearchWord());
				
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getSearchWord());
				
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("20")){
				pstmt.setString(1, searchVO.getCategoryWord());
				
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getCategoryWord());
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("30")) {
				pstmt.setString(1, searchVO.getgNameWord());
				
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				pstmt.setString(7, searchVO.getgNameWord());
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("40")) {
				pstmt.setString(1, searchVO.getSearchWord());
				pstmt.setString(2, searchVO.getgNameWord());
				
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				pstmt.setString(8, searchVO.getSearchWord());
				pstmt.setString(9, searchVO.getgNameWord());
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("50")) {
				pstmt.setString(1, searchVO.getSearchWord());
				pstmt.setString(2, searchVO.getCategoryWord());
				
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				pstmt.setString(8, searchVO.getSearchWord());
				pstmt.setString(9, searchVO.getCategoryWord());
			} else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("60")){
				log.debug("4.1 searchDiv:{}",searchVO.getSearchDiv());
				pstmt.setString(1, searchVO.getgNameWord());
				pstmt.setString(2, searchVO.getCategoryWord());
				
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				pstmt.setString(8, searchVO.getgNameWord());
				pstmt.setString(9, searchVO.getCategoryWord());
				
				
			}else if(null!=searchVO.getSearchDiv()&& searchVO.getSearchDiv().equals("70")) {
				pstmt.setString(1, searchVO.getgNameWord());
				pstmt.setString(2, searchVO.getCategoryWord());
				pstmt.setString(3, searchVO.getSearchWord());
				
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());
				pstmt.setInt(6, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(7, searchVO.getPageSize());
				pstmt.setInt(8, searchVO.getPageNo());
				
				pstmt.setString(9, searchVO.getgNameWord());
				pstmt.setString(10, searchVO.getCategoryWord());
				pstmt.setString(11, searchVO.getSearchWord());
			} else {
				pstmt.setInt(1, searchVO.getPageSize());
				pstmt.setInt(2, searchVO.getPageNo());
				pstmt.setInt(3, searchVO.getPageSize());
				
				//paging rnum
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());
			}
			
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			while(rs.next()) {
				
				outVO = new ContentDTO();
				
				outVO.setNum(rs.getInt("rnum"));
				outVO.setContentId(rs.getString("contentid"));
				outVO.setCategory(rs.getString("category"));
				outVO.setGucode(rs.getString("gucode"));
				outVO.setTel(rs.getString("tel"));
				outVO.setAddr(rs.getString("addr"));
				outVO.setImgLink(rs.getString("img_link"));
				outVO.setTitle(rs.getString("title"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setgName(rs.getString("gname"));
				outVO.setCatName(rs.getString("cat_name"));
				
				
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

	@Override
	public int doSave(ContentDTO param) {
		// TODO Auto-generated method stub
		return 0;
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


	
	
}
