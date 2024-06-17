package com.pcwk.tvl.content;

import com.pcwk.ehr.cmn.PLog;

public class ContentServiceMain implements PLog{
	
	ContentService service;
	ContentDTO content01;

public ContentServiceMain() {
	service = new ContentService();
	
	content01 = new ContentDTO("2638475", "A0203", "21", "02-749-4500", "서울특별시 용산구 양녕로 445", "http://tong.visitkorea.or.kr/cms/resource/65/2638965_image2_1.JPG", "노들섬", "20240417173555", "20191125183515");
}

public static void main(String[] args) {
	ContentServiceMain mService = new ContentServiceMain();

		

	}

}
