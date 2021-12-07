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
		list.add(new SessionVO("naver.com", "�̱���"));
		list.add(new SessionVO("sist.co.kr", "������"));
		list.add(new SessionVO("youtube.com", "������"));
		list.add(new SessionVO("github.com", "������"));
		list.add(new SessionVO("google.com", "�ǿ���"));
		
		
		//���ǿ� �� �ֱ�
		session.setAttribute("ses_msg", "������ �ٶ��� ���� �δ� ȭ�����Դϴ�.");
		session.setAttribute("ses_link", list);
		
		return "session/get_session_value";
	}//setSessionValue
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping( value = "http_session_get.do", method = GET)
	public String getSessionValue( HttpSession session, Model model ) {
		
		// Session���� model�� ���� �ž�
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
	

////////////////////////////////////// 12-01-2021 �߰� �ڵ� ///////////////////////////////////////////////////////////

	@RequestMapping(value = "session_attribute_set.do", method = GET)
	public String useSessionAttributes(Model model) {
		
		// ���ǿ� �����ϴ� ���� �߿䵵�� ����, ������ ���� �����͸� �����Ѵ�. 
		// DBMS ��ȸ�� ���� ���� �����ʹ� �������� �ʴ´�. 
		model.addAttribute("data", "������ ������ �Դϴ�. ");
		model.addAttribute("id", "test_id"); //���ǿ� ����
		model.addAttribute("namesArr", new String[] {"����ȣ", "������", "������", "ä����", "�̹���", "������"} );
		
		
		return "session/session_attributes_result";
	}//useSessionAttributes
	
	
	@RequestMapping(value = "session_attribute_get.do", method = GET)
	public String getSessionAttribute( HttpSession session) {//���ǿ��� ���� �������� ��
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
		//System.out.println("���� �� : " + ss.isComplete());
		
		if( !ss.isComplete() ) {
			// ���� ����
			ss.setComplete();
		}//end if
		
		//System.out.println("���� �� : " + ss.isComplete());
		
		return "session/session_attributes_result";
	}//removeSessionAttributes
	
	
	
	/*public String removeSessionAttribute( HttpSession session ) { 
	  // HttpSession���δ� ���� ������ �Ұ����ϴ�. 
		
		session.removeAttribute("data");
		session.removeAttribute("id");
		session.removeAttribute("namesArr");
		
		session.invalidate();
		
		return "session/session_attributes_result";
	}//removesessionAttribute
	*/
	
	
	
	
	
}//class
