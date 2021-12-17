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
	 * �α��� ����
	 * ���̵�� ��й�ȣ�� �Է¹޾� �̸��� ��ȸ�ϴ� ��.
	 * @param mVO
	 * @return
	 * @throws SQLException
	 */
	public String selectLogin(UserMemberVO mVO)throws DataAccessException{
		String kname="";//�̸��� AES ��ȣȭ�Ǿ��ִ�.
		
		String selectId="select kname from member where id=? and pass=? and m_status='Y'";
		kname=
			jt.queryForObject(selectId, new Object[] {mVO.getId(),mVO.getPass()},String.class );
		
		
		return kname;
	}
	
}//class
