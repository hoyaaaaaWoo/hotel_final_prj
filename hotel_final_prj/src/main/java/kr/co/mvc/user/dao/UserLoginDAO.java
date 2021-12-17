package kr.co.mvc.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.co.mvc.user.vo.UserMemberVO;

@Component
public class UserLoginDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	/**
	 * 로그인 수행
	 * 아이디와 비밀번호를 입력받아 이름을 조회하는 일.
	 * @param mVO
	 * @return
	 * @throws SQLException
	 */
	public String selectLogin(UserMemberVO mVO)throws DataAccessException{
		String kname="";//이름은 AES 암호화되어있다.
		
		String selectId="select kname from member where id=? and pass=? and m_status='Y'";
		kname=
			jt.queryForObject(selectId, new Object[] {mVO.getId(),mVO.getPass()},String.class );
		
		
		return kname;
	}
	
}//class
