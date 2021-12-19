package kr.co.mvc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.mvc.user.service.MypageService;
import kr.co.mvc.user.vo.MemberChgInfoVO;
import kr.co.mvc.user.vo.MemberChgPassVO;
import kr.co.mvc.user.vo.UserMemberVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Controller
public class MypageController {

	@Autowired
	private MypageService mySer;
	
	@RequestMapping(value = "user/mypage/mypage.do", method = GET)
	public String mypage() {
		return "user/mypage/mypage";
	}//mypage
	
	@RequestMapping(value = "user/mypage/mypage_form.do", method = {GET, POST})
	public String mypage_process(HttpSession session, String pass, Model model) {
		
		String id = (String) session.getAttribute("id");
		String url = "";
			
			String realPass = mySer.searchLogin(id,pass);
			boolean flag = mySer.passFlag(realPass, pass);

			if( flag  ) {
				url = "user/mypage/mypage_form";
			}else {
				url = "user/mypage/mypageNP";
			}//end else
			
		List<UserMemberVO> uVO = mySer.searchMemPersonalInfo(id);	
		model.addAttribute("info", uVO);
		
		return url;
	
	}//mypage_form
	
	/**
	 * 비밀번호 변경하기
	 * @param session
	 * @param pass [form에서 입력된 현재비밀번호]
	 * @param change_pass [form에서 입력된 바꿀 비밀번호]
	 * @param change_pass2 [form에서 입력된 바꿀 비밀번호 확인]
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@ResponseBody
	@RequestMapping(value = "user/mypage/member_pass_process.do", method = POST)
	public String changePass( HttpSession session, String pass, String change_pass, String change_pass2 ) throws NoSuchAlgorithmException {
		String msg = "";
		
		String id = (String) session.getAttribute("id");
		
		System.out.println("Param - id : " + id + " pass : " + pass + " change_pass : " + change_pass + " change_pass2 : " + change_pass2);

		String dPass = mySer.DataEncryptPass(pass);
		String dCPass = mySer.DataEncryptCPass(change_pass);
		
		MemberChgPassVO cpVO = new MemberChgPassVO();
		cpVO.setId(id);
		cpVO.setPass(dPass);
		cpVO.setChange_pass(dCPass);
		cpVO.setChange_pass2(change_pass2);

		System.out.println( "Controller - cpVO : " + cpVO);

		boolean flag = mySer.modifyPass(cpVO);
				
		System.out.println("flag --------------------- " + flag );
		if( flag ) {
			msg = "<script type='text/javascript'> location.href='http://localhost/hotel_final_prj/user/mypage/mypage_form.do'; alert('변경 되었습니다'); </script>";
		}else {
			msg = "<script type='text/javascript'> alert('비밀번호를 확인하여주세요'); history.back(); </script>";
		}
		
		return msg;
	}//changePass
	
	
	
	/**
	 * 회원의 이름/전화번호/이메일 변경하기
	 * @param session
	 * @param kname
	 * @param tel
	 * @param email
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 */
	@ResponseBody
	@RequestMapping(value = "user/mypage/member_update_process.do", method = POST)
	public String changeMemInfo( HttpSession session, String kname, String tel, String email ) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		String msg = "";
		
		String id=(String)session.getAttribute("id");
		
		MemberChgInfoVO ciVO = new MemberChgInfoVO();
		ciVO.setId(id); 
		DataEncrypt de=new DataEncrypt("AbcdEfgHiJkLmnOpQ");
		ciVO.setKname(de.encryption(kname) );//이름
		ciVO.setEmail(de.encryption(email) );//이메일
		ciVO.setTel(de.encryption(tel) );//번호
		
		boolean cFlag = mySer.modifyMemInfo(ciVO);
		if( cFlag ) {
			msg = "<script type='text/javascript'> location.href='http://localhost/hotel_final_prj/user/mypage/mypage_form.do'; alert('변경 되었습니다'); </script>";
		}else {
			msg = "<script type='text/javascript'> alert('다시 확인하여주세요'); history.back();  </script>";
		}
		return msg;
	}//changeMemInfo
	
	
	/**
	 * 회원탈퇴
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "user/mypage/deleteMem.do", method = GET)
	 public String deleteMem( HttpSession session ){
		 String msg = "";
		 String id = (String)session.getAttribute("id");
		 
		 boolean dFlag = mySer.deleteMem(id);
		 System.out.println("Controller -------- dFlag : " + dFlag);
		 if ( dFlag ) {
			 session.invalidate();
			 msg = "<script type='text/javascript'> alert('회원 탈퇴가 완료되었습니다. 그동안 이용해 주셔서 감사합니다'); location.href='http://localhost/hotel_final_prj/user/user_main/Hotel_Ritz_Seoul.do'; </script>";
		 }else {
			 msg = "<script type='text/javascript'> alert('죄송합니다. 잠시 후 다시 시도해주십시오.'); location.href='http://localhost/hotel_final_prj/user/mypage/mypage_form.do'; </script>";
		 }//end else
		 
		 return msg;
	 }//deleteMem
	 
	
	
	
	
}//class
