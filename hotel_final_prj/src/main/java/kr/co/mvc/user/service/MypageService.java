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
import kr.co.mvc.user.vo.MemberChgInfoVO;
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
	
	
	
	/**
	 * ��й�ȣ ����
	 * @param DcpVO
	 * @return
	 */
	public boolean modifyPass ( MemberChgPassVO DcpVO ) {
		boolean flag = false;
		
		int cnt = myDAO.updatePass(DcpVO);
		if( cnt == 1) {
			flag = true;
		}//end if
		return flag;
	}//chgPass
	
	
	/**
	 * form�� pass ��ȣȭ
	 * @param pass
	 * @return
	 */
	public String DataEncryptPass ( String pass) {
		String dPass = "";
		try {
			dPass = DataEncrypt.messageDigest("SHA-512", pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}//end catch
		return dPass;
	}//DataEncryptPass
	
	/**
	 * form�� change_pass ��ȣȭ
	 * @param change_pass
	 * @return
	 */
	public String DataEncryptCPass ( String change_pass) {
		String dCPass = "";
		try {
			dCPass = DataEncrypt.messageDigest("SHA-512", change_pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}//end catch
		return dCPass;
	}//DataEncryptPass
	
	
	/**
	 * ȸ���� �������� ����
	 * @param ciVO
	 * @return
	 */
	public boolean modifyMemInfo( MemberChgInfoVO ciVO ) {
		boolean iFlag = false;
		int cnt = myDAO.updateMemInfo(ciVO);
		if( cnt == 1) {
			iFlag = true;
		}//end if
		return iFlag;
	}//modifyMemInfo
	
	
	/**
	 * ȸ��Ż���ϱ�
	 * @param id
	 * @return
	 */
	public boolean deleteMem( String id ) {
		boolean dFlag = false;
		try {
			int cnt = myDAO.delmember(id);
			if( cnt ==1 ){
				dFlag = true;
			}//end if
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return dFlag;
	}//deleteMem
}//class
