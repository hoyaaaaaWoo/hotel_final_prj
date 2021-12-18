package kr.co.mvc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.mvc.user.service.MypageService;
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
			//System.out.println("realPass : " + realPass);

			if( flag  ) {
				//System.out.println("if - pass : " + pass);
				//System.out.println("if - realPass : " + realPass);
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
	 * @param pass
	 * @param change_pass
	 * @param change_pass2
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@ResponseBody
	@RequestMapping(value = "user/mypage/member_pass_process.do", method = {GET, POST})
	public String changePass( HttpSession session, String pass, String change_pass, String change_pass2 ) throws NoSuchAlgorithmException {
		String msg = "";
		
		String id = (String) session.getAttribute("id");
		MemberChgPassVO cpVO = null;
		cpVO.setId(id);
		cpVO.setPass(DataEncrypt.messageDigest("SHA-512", change_pass));
		cpVO.setChange_pass(DataEncrypt.messageDigest("SHA-512", change_pass2));

		boolean flag = mySer.chgPass(cpVO);
				
		System.out.println("flag --------------------- " + flag );
		if( flag ) {
			msg = "<script type='text/javascript'> location.href='mypage_form.do'; alert('변경 되었습니다'); </script>";
		}else {
			msg = "<script type='text/javascript'> alert('비밀번호를 확인하여주세요'); history.back(); </script>";
		}
		
		return msg;
	}//changePass
	
}//class
