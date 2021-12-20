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
	 * Controller 에서 받아온 form pass를 enPass로 암호화시킴
	 * DB에서 가져온 realpass(일방향해쉬암호화됨)와 비교함
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
	 * 회원의 기존 이름, 전화번호, 이메일 가져옴
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
	 * 비밀번호 변경
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
	 * form의 pass 암호화
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
	 * form의 change_pass 암호화
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
	 * 회원의 개인정보 수정
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
	 * 회원탈퇴하기
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
