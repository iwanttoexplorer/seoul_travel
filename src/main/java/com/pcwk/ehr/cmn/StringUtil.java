package com.pcwk.ehr.cmn;

import java.util.List;
import java.util.UUID;

import com.pcwk.tvl.category.CategoryDTO;
import com.pcwk.tvl.content.ContentDTO;
import com.pcwk.tvl.gucode.GucodeDTO;


public class StringUtil implements PLog{
	
	/**
	 * 페이지 네이션
	 * @param maxNum : 총글수
	 * @param cuurentPageNo : 현재페이지번호
	 * @param rowPerPage : 페이지사이즈 -> 10/20/30/50/100/200
	 * @param bottomCount : 10 / 5
	 * @param url : 서버 호출 url
	 * @param scriptName : 자바스크립트 명
	 * @return
	 */
	public static String renderingPaging(int maxNum, int cuurentPageNo, int rowPerPage, int bottomCount, String url, String scriptName) {
		StringBuilder html = new StringBuilder(2000);
		//전체 모양
		// << < 1 2 3 .. 10 > >>
		// ===================================================================================
		
		//maxNum=21
		//cuurentPageNo=1
		//rowPerPage= 10
		//bottomCount= 10
		
		int maxPageNo = (maxNum -1)/rowPerPage + 1; //3페이지
		int startPageNo = ((cuurentPageNo-1)/bottomCount)* bottomCount +1 ;// 1,11,21,...
		int endPageNo = ((cuurentPageNo-1)/bottomCount+1)* bottomCount; // 10,20,30,...
		
		int nowBlocNo = ((cuurentPageNo-1)/bottomCount) + 1; // 1
		int maxBlocNo = ((maxNum-1)/bottomCount)+1; // 3
		
		// ===================================================================================
		
		if(cuurentPageNo>maxPageNo) {
			return "";
		}
		
		html.append("<ul class=\"pagination justify-content-center\"> \n");
		
		//<<
		
		if(nowBlocNo>1 && nowBlocNo<=maxBlocNo) {
			log.debug("nowBlocNo: {}",nowBlocNo);
			log.debug("maxBlocNo: {}",maxBlocNo);
			
			html.append("<li class=\"page-item\"> \n");             //scriptName(url,1);
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',1);"+"\"> \n");
			html.append("<span aria-hidden=\"true\">&laquo;</span> \n");
			
			html.append("</a> \n");
			html.append("</li> \n");
		}
		
		//<
		if(startPageNo>bottomCount) {
			log.debug("startPageNo: {}",startPageNo);
			log.debug("bottomCount: {}",bottomCount);
			
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+(startPageNo - bottomCount)+");\"> \n");
			html.append("<span aria-hidden=\"true\">&lt;</span> \n");
			
			html.append("</a> \n");
			html.append("</li> \n");
		}
		
		// 1 2 3 ... 10
		int inx = 0;
		for(inx=startPageNo;inx<=maxPageNo && inx <= endPageNo;inx++) {
			if(inx == cuurentPageNo) {
				html.append("<li class=\"page-item\"> \n");
				
				html.append("<a class=\"page-link active\" href=\"#\"> \n");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li> \n");
			}else {
				html.append("<li class=\"page-item\"> \n");
				
				html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+inx+");\"> \n");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li> \n");
			}
		}
		
		// >
		if(maxPageNo>inx) {
			log.debug("nowBlocNo: {}",nowBlocNo);
			log.debug("bottomCount: {}",bottomCount);
			log.debug(">: {}",((nowBlocNo * bottomCount)+1));
			
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+((nowBlocNo * bottomCount)+1)+");\"> \n");
			html.append("<span aria-hidden=\"true\">&gt;</span> \n");
			
			html.append("</a> \n");
			html.append("</li> \n");
		}
		
		// >>
		if(maxPageNo>inx) {
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+maxPageNo+");\"> \n");
			html.append("<span aria-hidden=\"true\">&raquo;</span> \n");
			html.append("</a> \n");
			html.append("</li> \n");
		}
		
		html.append("</ul> \n");
		log.debug(html.toString());
		return html.toString();
	}
	
	/**
	 * 
	 * @param pageCode
	 * @param selecedVal
	 * @return
	 */
	public static String getCategoryList(List<CategoryDTO> pageCode, String selecedVal) {
		StringBuilder sb = new StringBuilder(300);
		
		if(null != pageCode && pageCode.size()>0){
     	   for(CategoryDTO code :pageCode){
     		   String optionValue="";
     		   
     		   if(code.getCategory().equals(selecedVal)) {
     			  optionValue = "<option value='"+code.getCategory()+"' selected>"+code.getCatName()+"</option>";
     		   } else {
     			  optionValue = "<option value='"+code.getCategory()+"'>"+code.getCatName()+"</option>";
     		   }
     		   
     		   sb.append(optionValue+"\n");
     	   }
     	   log.debug(sb.toString());
        }
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param pageCode
	 * @param selecedVal
	 * @return
	 */
	public static String getGuCodeList(List<GucodeDTO> pageCode, String selecedVal) {
		StringBuilder sb = new StringBuilder(300);
		
		if(null != pageCode && pageCode.size()>0){
     	   for(GucodeDTO code :pageCode){
     		   String optionValue="";
     		   
     		   if(code.getGuCode().equals(selecedVal)) {
     			  optionValue = "<option value='"+code.getGuCode()+"' selected>"+code.getGname()+"</option>";
     		   } else {
     			  optionValue = "<option value='"+code.getGuCode()+"'>"+code.getGname()+"</option>";
     		   }
     		   
     		   sb.append(optionValue+"\n");
     	   }
     	   log.debug(sb.toString());
        }
		
		return sb.toString();
	}
	
	/**
	 *  request null처리
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static String 
	nvl(String value, String defaultValue) {
		if(null == value || value.trim().isEmpty()) {
			return defaultValue;
		}
		
		return value;
	}
	
	/**
	 *  32bit UUID 생성
	 * @return String
	 */
	public static String getUUID() {
		String resultUUID = "";
		UUID uuidTemp = UUID.randomUUID();
		
		return uuidTemp.toString().replaceAll("-", "");
	}
	
}
