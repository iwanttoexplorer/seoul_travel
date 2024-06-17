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
	 * 단건 삭제
	 * @param param
	 * @return 성공(1) 실패(0)
	 */
	public int doDelete(UserDTO param) {
		return dao.doDelete(param);
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
	
	
}
