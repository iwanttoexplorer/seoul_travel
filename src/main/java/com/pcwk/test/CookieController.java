package com.pcwk.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

public class CookieController implements ControllerV,PLog{

	public CookieController() {
		log.debug("---------------------");
        log.debug("CookieController()");
        log.debug("---------------------");
	}
	
	/**
	 * Servlet Cookie 설정
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public JView setCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
    	log.debug("setCookie()");
    	log.debug("---------------------");
    	
    	//쿠키 생성
    	Cookie userCookie = new Cookie("username","seoul_travel");
		
    	//도메인 생성 : .seoul_travel 으로 한정
    	userCookie.setDomain("seoul_travel.com");
    	
    	//경로 설정  "/" -> .seoul_travel 의 모든경로
    	//C:\Windows\System32\drivers\etc hosts파일로 강제설정가능
    	userCookie.setPath("/");
    	
    	//유효기한 설정(1일) : 분,초,24시
    	userCookie.setMaxAge(60*60*24);
    	
    	//쿠키를 응답에 추가
    	response.addCookie(userCookie);
    	
    	//응답 작성
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter pOut = response.getWriter();
    	
    	pOut.print("Cookie 'username set to seoul_travel' ");
    	
    	
		return null;
	}
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
        log.debug("doWork()");
        log.debug("---------------------");
        
        JView viewName = null;

        String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	log.debug("workDiv : {}",workDiv);
        
    	switch (workDiv) {
		case "setCookie":
			viewName = setCookie(request,response);
			break;
    	}
        
		return viewName;
	}

}
