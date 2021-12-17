package kr.co.mvc.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.dao.MemberDAO;
import kr.co.mvc.admin.vo.MemberVO;

@Component
public class MemberService {
	
	@Autowired
	private MemberDAO memDAO;
	
/**
 * 정상회원 조회
 * @param id
 * @return
 */
public List<MemberVO> searchActiveMember(String id, int startNum, int endNum) {
	List<MemberVO> list =null;
	try {
		list = memDAO.selectActiveMember(id, startNum, endNum); 
	}catch(DataAccessException dae) {
		dae.printStackTrace();
	}//end catch
	return list;
}//searchActiveMember

/**
 * 페이지네이션에 사용할 전체 레코드 수 조최
 * @param id
 * @param status
 * @return
 */
public int searchAllMemberCnt(String id, String status) {
	int allMemCnt = 0;
	try {
		allMemCnt = memDAO.selectAllMemberCnt(id, status); 
	}catch(DataAccessException dae) {
		dae.printStackTrace();
	}//end catch
	return allMemCnt;
}//selectAllMemberCnt

/**
 * 탈퇴회원 조회
 * @param id
 * @return
 */
public List<MemberVO> searchInactiveMember(String id, int startNum, int endNum) {
	List<MemberVO> list =null;
	try {
		list = memDAO.selectInactiveMember(id, startNum, endNum); 
	}catch(DataAccessException dae) {
		dae.printStackTrace();
	}//end catch
	return list;
}//searchInactiveMember


public int changeMemberProcess(String id) {
	int cnt=0;
	
	
	return cnt;
}
	
}//class
