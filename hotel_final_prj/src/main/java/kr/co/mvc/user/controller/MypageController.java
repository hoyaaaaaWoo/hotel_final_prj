package kr.co.mvc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.mvc.user.service.MypageService;
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
			System.out.println("realPass : " + realPass);

			if( flag  ) {
				System.out.println("if - pass : " + pass);
				System.out.println("if - realPass : " + realPass);
				url = "user/mypage/mypage_form";
			}else {
				url = "user/mypage/mypageNP";
			}//end else
			
		List<UserMemberVO> uVO = mySer.searchMemPersonalInfo(id);	
		model.addAttribute("info", uVO);
		
		return url;
	
	}//mypage_form
	
}//class
