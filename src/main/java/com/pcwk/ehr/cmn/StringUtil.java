package com.pcwk.ehr.cmn;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	
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
	 * 32bit UUID 생성
	 * @return
	 */
	public static String getUUID() {
		UUID uuidTemp = UUID.randomUUID();
		
		return uuidTemp.toString().replaceAll("-", "");
	}

}
