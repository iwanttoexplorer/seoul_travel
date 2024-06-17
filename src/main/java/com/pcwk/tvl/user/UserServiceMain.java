package com.pcwk.tvl.user;

public class UserServiceMain {
	UserService service;
	UserDTO user;
	
	public UserServiceMain() {
		service = new UserService();
		user = new UserDTO("1kqD993", 1, "이름", "pw123213#2", "emailamc1@na3231ver.kr", "디폴트");
	}

	public static void main(String[] args) {

	}

}
