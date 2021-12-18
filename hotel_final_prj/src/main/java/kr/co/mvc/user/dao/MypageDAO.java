package kr.co.mvc.user.dao;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.mvc.user.vo.LoginVO;
import kr.co.mvc.user.vo.MemberChgPassVO;
import kr.co.mvc.user.vo.UserMemberVO;
import kr.co.sist.util.cipher.DataDecrypt;

@Component
public class MypageDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	/**
	 * 아이디를 받아 비밀번호를 조회하는 일
	 * @param mVO
	 * @return
	 * @throws SQLException
	 */
	public LoginVO selectLogin( String id )throws DataAccessException, NullPointerException{
		LoginVO lVO= null;
		
		String selectId="select pass from member where id=? and m_status='Y'";
		lVO = jt.queryForObject(selectId, new Object[] {id},
				new RowMapper<LoginVO>() {
					@Override
					public LoginVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						LoginVO lv = new LoginVO();
						
							lv.setPass(rs.getString("pass"));
						
						return lv;
					}
			});
		System.out.println(lVO);
		return lVO;
	}//selectLogin
	
	
	/**
	 * 회원정보 이름, 전화번호,이메일 조회
	 * @param mVO
	 * @return
	 * @throws SQLException
	 */
	
	public List<UserMemberVO>selectInfo(String id) throws SQLException{
		List<UserMemberVO> list =null;
		String selectpass="select kname,tel,email from member where id=?";
		
		list=	jt.query(selectpass.toString(), new Object[] {id}, new RowMapper<UserMemberVO>() {
			public UserMemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserMemberVO mVO = new UserMemberVO();
				mVO.setKname(rs.getString("kname"));
				mVO.setTel(rs.getString("tel"));
				mVO.setEmail(rs.getString("email"));
				return mVO;
			}
		});
		return list;
	}
	
	/**
	 *  이름,전화번호,이메일 복호화
	 * @param kname
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 * @throws SQLException
	 */
	public List<UserMemberVO>  DecryptDeleteMemberData(String id) throws SQLException,UnsupportedEncodingException,GeneralSecurityException {
		List<UserMemberVO> list = null;
		list=selectInfo(id);
		DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
		for(UserMemberVO mVO: list) {
			mVO.setKname(dd.decryption(mVO.getKname()));
			mVO.setTel(dd.decryption(mVO.getTel()));
			mVO.setEmail(dd.decryption(mVO.getEmail()));
		}
		return list;
	}//DecryptDeleteMemberData
	
	
	/**
	 * 비밀번호 변경
	 * @param mVO
	 * @return
	 * @throws 
	 */
	public int updatePass( MemberChgPassVO cpVO)throws DataAccessException{
		String updatePass="update member set pass=? where id=? and pass=?";
		int cnt=jt.update(updatePass,cpVO.getChange_pass(), cpVO.getId(), cpVO.getPass() );
		System.out.println("mDAO cnt : " + cnt);
		return cnt;
	}//updatePass
	
	
}//class
