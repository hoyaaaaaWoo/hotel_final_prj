package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.sist.vo.SessionVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("session/")
@SessionAttributes( { "data", "id", "namesArr" } )
public class SessionController {

	@RequestMapping(value = "http_session_set.do", method = GET)
	public String setSessionValue( HttpSession session ) {
		
		List<SessionVO> list = new ArrayList<SessionVO>();
		list.add(new SessionVO("naver.com", "이기휘"));
		list.add(new SessionVO("sist.co.kr", "하진수"));
		list.add(new SessionVO("youtube.com", "서지안"));
		list.add(new SessionVO("github.com", "윤현식"));
		list.add(new SessionVO("google.com", "권오재"));
		
		
		//세션에 값 넣기
		session.setAttribute("ses_msg", "오늘은 바람이 많이 부는 화요일입니다.");
		session.setAttribute("ses_link", list);
		
		return "session/get_session_value";
	}//setSessionValue
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping( value = "http_session_get.do", method = GET)
	public String getSessionValue( HttpSession session, Model model ) {
		
		// Session값을 model에 담을 거야
		String resultPage = "session/get_session_value";
		String msg = (String) session.getAttribute("ses_msg");
		
		if( msg == null ) {
			resultPage = "redirect:../index.do?login=nil";
		}else {
			System.out.println( msg );
			System.out.println( session.getAttribute("ses_link") );
			model.addAttribute( "req_msg", msg );
		}//end else

		
		return resultPage;
	}//getSessionValue
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping( value = "http_session_remove.do", method = GET)
	public String removeSession( HttpSession session ) {
		
		session.removeAttribute("ses_msg");
		session.removeAttribute("ses_link");
		session.invalidate();
		return "redirect:../index.do?login=nil";
	}
	

////////////////////////////////////// 12-01-2021 추가 코드 ///////////////////////////////////////////////////////////

	@RequestMapping(value = "session_attribute_set.do", method = GET)
	public String useSessionAttributes(Model model) {
		
		// 세션에 저장하는 값은 중요도가 낮고, 연관성 없는 데이터를 저장한다. 
		// DBMS 조회만 많은 양의 데이터는 저장하지 않는다. 
		model.addAttribute("data", "오늘은 수요일 입니다. ");
		model.addAttribute("id", "test_id"); //세션에 설정
		model.addAttribute("namesArr", new String[] {"우지호", "남주혁", "손현우", "채형원", "이민혁", "이주헌"} );
		
		
		return "session/session_attributes_result";
	}//useSessionAttributes
	
	
	@RequestMapping(value = "session_attribute_get.do", method = GET)
	public String getSessionAttribute( HttpSession session) {//세션에서 값을 가져오는 일
		 String data = (String)session.getAttribute( "data" );
		 String id = (String)session.getAttribute( "id" );
		 String[] names = (String[])session.getAttribute( "namesArr" );
		 
		 System.out.println( data );
		 System.out.println( id );
		 if( names != null ) {
			 for( String name : names ) {
				 System.out.println( name );
			 }//end for
		 }//end if
		 
		return "session/session_attributes_result";
	}//setSessionAttribute
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "session_attribute_remove.do", method = GET)
	public String removeSessionAttributes( SessionStatus ss ) {
		//System.out.println("삭제 전 : " + ss.isComplete());
		
		if( !ss.isComplete() ) {
			// 세션 삭제
			ss.setComplete();
		}//end if
		
		//System.out.println("삭제 후 : " + ss.isComplete());
		
		return "session/session_attributes_result";
	}//removeSessionAttributes
	
	
	
	/*public String removeSessionAttribute( HttpSession session ) { 
	  // HttpSession으로는 세션 삭제가 불가능하다. 
		
		session.removeAttribute("data");
		session.removeAttribute("id");
		session.removeAttribute("namesArr");
		
		session.invalidate();
		
		return "session/session_attributes_result";
	}//removesessionAttribute
	*/
	
	
	
	
	
}//class
