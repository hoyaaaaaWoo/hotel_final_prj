package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DataController {

	
	
	@RequestMapping(value="send/use_request.do", method = GET)
	public String uesRequest(HttpServletRequest request) {
		//발생된 데이터를 HttpServletRequest에 저장하여 View로 전달
		
		request.setAttribute("name", "<u>우지호</u>");
		// Controller에서 작선된 태그가 View로 전달되어 <c:out>을 통해 출력되면 <c:out>을 타지 않고 그대로 출력된다.  
		request.setAttribute("subjects", new String[] {"Java SE", "Oracle", "JDBC", "HTML", "CSS", "JavaScript"});
		
		return "data/use_request";
	}//uesRequest
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	@RequestMapping(value="send/use_model.do", method = GET)
	public String uesModel( Model model ) {
		//발생된 데이터를 Model interface에 저장하여 View로 전달
		
		model.addAttribute("name", "<u>남주혁</u>");
		model.addAttribute("subjects", new String[] {"Model사용", "Java SE", "Oracle", "JDBC", "HTML", "CSS", "JavaScript"});
		
		return "data/use_request";
	}//uesRequest
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	
	@RequestMapping(value="send/use_model_and_view.do", method = GET)
	// 반환형을  ModelAndView 로 설정
	public ModelAndView uesModelAndView( ) {
		//발생된 데이터를 ModelAndView에 저장하여 View로 전달
		
		// ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		
		// view명 설정
		mav.setViewName("data/use_request");
		
		// mav 값 설정 : 설정된 값은 requestScope에 저장되어 전달된다. 
		mav.addObject("name", "<u>우지호</u>");
		mav.addObject("subjects", new String[] {"<strong>ModelAndView 사용</strong>", "Java SE", "Oracle", "JDBC", "HTML", "CSS", "JavaScript"});
		
		return mav;
	}//uesModelAndView
	
	
}//class
