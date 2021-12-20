package kr.co.mvc.user.dao;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.mvc.user.vo.LoginVO;
import kr.co.mvc.user.vo.UserFindVO;
import kr.co.mvc.user.vo.UserMemberVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class UserLoginDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
		/**
		 * �α��μ���
		 * @param lVO
		 * @return
		 */
		public String loginProc(LoginVO lVO)  {
		String selectpass="select kname from member where id=? and pass=? and m_status='Y'";
		//���̵�� ��й�ȣ�� �־ �̸�����ȸ�Ѵ�.
		String kname="";
		try {
			kname=jt.queryForObject(selectpass.toString(), new Object[] {lVO.getId(),lVO.getPass()},new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String kname=rs.getString("kname");
				return kname;
			}
		});
		}catch(EmptyResultDataAccessException erda) {
		}
		return kname;
	
	}
	
	
	
	/**
	 * �̸��� �̸��Ϸ� ���̵� ã��
	 * @param ufVO
	 * @return
	 * @throws DataAccessException
	 */
	public String selectUserId(UserFindVO ufVO) throws DataAccessException {
		String enKname = null;
		String enEmail = null;
		String id = null;
		
		try {
			DataEncrypt de = new DataEncrypt("AbcdEfgHiJkLmnOpQ");
			enKname = de.encryption(ufVO.getId_kname());
			enEmail = de.encryption(ufVO.getId_email());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}//end catch

		String selectId = "select id from member where kname=? and email=?";
		id = jt.queryForObject(selectId, new Object[] { enKname, enEmail }, String.class);

		return id;
	}//selectUserId

	/**
	 * ��й�ȣ ã�� ��, �̸��� ���̵�� �̸��Ϸ� ����� ����
	 * @param ufVO
	 * @return
	 * @throws DataAccessException
	 */
	public String selectUserRecord(UserFindVO ufVO) throws DataAccessException {
		String joinDate = null;
		String enKname = null;
		String enEmail = null;
		try {
			DataEncrypt de = new DataEncrypt("AbcdEfgHiJkLmnOpQ");
			enKname = de.encryption(ufVO.getPw_kname());
			enEmail = de.encryption(ufVO.getPw_email());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}//end catch

		String selectJoin = "select join_date from member where id=? and kname=? and email=?";
		
		joinDate 
			 = jt.queryForObject(selectJoin, new Object[] {ufVO.getPw_id(), enKname, enEmail }, String.class);
		
		return joinDate;
	}//selectUserRecord

	
	/**
	 * �ӽú�й�ȣ�� table update
	 * @param ufVO
	 * @return
	 * @throws DataAccessException
	 */
	public int updateUserPassword(UserFindVO ufVO) throws DataAccessException {
		int cnt = 0;
		
		String id = ufVO.getPw_id();
		String enPass = null;
		try {
			enPass = DataEncrypt.messageDigest("SHA-512", ufVO.getTemp_pw());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}//end catch
		
		String updatePass = "update member set pass=? where id=?";
		
		cnt = jt.update(updatePass,enPass,id);
		
		return cnt;
	}//updateUserPassword

}//class
