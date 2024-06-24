package com.pcwk.tvl.user;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.tvl.grade.GradeDao;

public class UserService implements PLog {
	private UserDao dao;
	
	public UserService() {
		dao = new UserDao();
	}
	
	/**
	 * 목록 조회
	 * @param search
	 * @return List<BoardDTO>
	 */
	public List<UserDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
	public List<UserDTO> doRetrieveUser() {
		return dao.userDTO();
	}
	
	/**
	 * 저장
	 * @param param
	 * @return 성공(1) 실패(0)
	 */
	public int doSave(UserDTO param) {
				
		return dao.doSave(param);
	}
	
	/**
	 * 단건 수정
	 * @param param
	 * @return 성공(1) 실패(0)
	 */
	public int doUpdate(UserDTO param) {
			
		return dao.doUpdate(param);
	}
	
	/**
	 * 회원 탈퇴
	 * @param param
	 * @return 성공(1) 실패(0)
	 */
	public int doDelete(UserDTO param) {
		return dao.doDelete(param);
	}

	public int doDeleteUser(UserDTO param) {
		return dao.doDeleteUser(param);
	}
	
	
	/**
	 * 단건 조회
	 * @param param
	 * @return UserDTO
	 */
	public UserDTO doSelectOne(UserDTO param) {
		UserDTO outVO = new UserDTO();
		
		outVO = dao.doSelectOne(param);
				
		return outVO;
	}
	
	/**
	 * password 밸리데이션
	 * @param pwd
	 * @return 문자
	 */
	public String checkPassword(String pwd) {
		return dao.checkPassword(pwd);
	}
	
	/**
	 * id 밸리데이션
	 * @param id
	 * @return 문자
	 */
	public String checkId(String id) {
		return dao.checkId(id);
	}
	
	/**
	 * 이름 밸리데이션
	 * @param name
	 * @return 문자
	 */
	public String checkName(String name) {
		
		return dao.checkName(name);
	}
	/**
	 * 이메일 밸리데이션
	 * @param email
	 * @return 문자
	 */
	public String checkEmail(String email) {
		
		return dao.checkEmail(email);
	}
	
	/**
	 * 아이디 찾기 (이메일로)
	 * @param email
	 * @return userId
	 */
	public String findUserId(String email) {
		return dao.findUserId(email);
	}
	
}
