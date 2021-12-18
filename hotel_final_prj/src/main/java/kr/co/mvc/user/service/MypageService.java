package kr.co.mvc.user.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.mvc.user.dao.MypageDAO;
import kr.co.mvc.user.vo.LoginVO;
import kr.co.mvc.user.vo.MemberChgPassVO;
import kr.co.mvc.user.vo.UserMemberVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class MypageService {

	@Autowired
	private MypageDAO myDAO;
	
	
	public String searchLogin( String id, String pass ) {
		String realpass = null;
		realpass = myDAO.selectLogin(id).getPass();
		return realpass;
	}//searchLogin
	
	
	/**
	 * Controller ���� �޾ƿ� form pass�� enPass�� ��ȣȭ��Ŵ
	 * DB���� ������ realpass(�Ϲ����ؽ���ȣȭ��)�� ����
	 * @param realpass
	 * @param pass
	 * @return
	 */
	public boolean passFlag ( String realpass, String pass) {
		boolean flag = false;
		try {
			String enPass = DataEncrypt.messageDigest("SHA-512", pass);
		if( realpass.equals(enPass)) {
			flag = true;
		}//
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//passFlag
	
	
	/**
	 * ȸ���� ���� �̸�, ��ȭ��ȣ, �̸��� ������
	 * @param id
	 * @return
	 */
	public List<UserMemberVO> searchMemPersonalInfo( String id ){
		List<UserMemberVO> umVO = null;
		try {
			umVO = myDAO.DecryptDeleteMemberData(id);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}//end catch
		return umVO;
	}//searchMemPersonalInfo
	
	
	
	public boolean chgPass ( MemberChgPassVO cpVO ) {
		boolean flag = false;
		int cnt = myDAO.updatePass(cpVO);
		if( cnt == 1) {
			flag = true;
		}//end if
		System.out.println("mSer flag : " + flag);
		return flag;
	}//chgPass
	
}//class
