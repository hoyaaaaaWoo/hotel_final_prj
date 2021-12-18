package kr.co.mvc.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.mvc.user.service.UserLoginService;
import kr.co.mvc.user.vo.LoginVO;
import kr.co.mvc.user.vo.UserFindVO;
import kr.co.mvc.user.vo.UserLoginVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.classfmt.ExternalAnnotationProvider.IMethodAnnotationWalker;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class UserLoginController {

	@Autowired(required=false)
	private UserLoginService uLogService;
	
	@RequestMapping(value="user/user_login/login.do",method =GET)
	public String login() {
		return "user/user_login/user_login";
	}
	@RequestMapping(value="user/user_login/loginProcess.do",method= {RequestMethod.POST})
	public String loginProcess(HttpSession session, LoginVO lVO, Model model, HttpServletRequest req) {
	
		boolean flag=uLogService.userMember(lVO); 
		String url="";
		if(flag) {
		
			session.setAttribute("id", lVO.getId() ); 
			url="forward:/user/user_main/Hotel_Ritz_Seoul.do";
			
		}else {
			
			url="forward:/user/user_login/loginFail.do"; 
		}
		return url;	
	}
	
	@RequestMapping(value ="user/user_login/logout.do",method =GET)
	public String logOutProcess(HttpSession session) {
		session.invalidate();
		
		return "redirect:/user/user_main/Hotel_Ritz_Seoul.jsp";
	}
	@RequestMapping(value="user/user_login/loginFail.do",method= {GET, RequestMethod.POST,})
	public String logOut() {
	return  "user/user_login/login_process";
	}
	
	
	/**
	 * 아이디, 비밀번호 찾기 요청
	 * @return
	 */
	@RequestMapping(value = "user/user_login/user_find_form.do", method = GET)
	public String userFindForm() {
		return "/user/user_login/user_find_main";
	}//userFindForm
	
	/**
	 * 아이디 찾기
	 * @param ufVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/user_login/user_find_id.do", method = POST)
	public String userFindId(UserFindVO ufVO, Model model) {
		model.addAttribute("kname", ufVO.getId_kname());
		model.addAttribute("userId", uLogService.searchUserId(ufVO));
		return "/user/user_login/user_find_id_result";
	}//userFindForm
	
	/**
	 * 비밀번호 찾기
	 * @param ufVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/user_login/user_find_password.do", method = POST)
	public String userFindPW(UserFindVO ufVO, Model model) {
			String tempPw = uLogService.setTempPassword(ufVO);
			ufVO.setTemp_pw(tempPw);
			
			int updateCnt = 0;
			if(tempPw != null ) {
				updateCnt = uLogService.changeUserPassword(ufVO);
				if(updateCnt == 1) {
					model.addAttribute("tempPass", tempPw);
				}
			}//end if
		
		return "/user/user_login/user_find_pw_result";
	}//userFindPW
	
	
}//
