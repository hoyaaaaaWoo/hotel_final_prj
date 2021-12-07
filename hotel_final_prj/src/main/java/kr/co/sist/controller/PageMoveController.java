package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * forward�� redirect�� ���� ������ �̵�
 * @author user
 *
 */

@Controller
public class PageMoveController {

//	@RequestMapping(value="/forward.do", method=GET)
//	public String forward() {
//		return "day1129/forward"; //URL�� ������� �ʴ´�. 
//	}//forward
	
	//��û URL�� �����ϴ� JSP���� ���� ������ return�� ������� �ʾƵ� �ȴ�. 
//	@RequestMapping(value="/forward.do", method=GET)
	@RequestMapping("/forward.do") //get����� ��û�� ���� value�Ӽ��� method�Ӽ��� �����Ͽ� ����� �� �ִ�. 
	public void forward() {
//		System.out.println("----- forward method -----");
	}//forward
	
	
	// http://localhost/spring_mvc_prj/redirect.do
	@RequestMapping(value="/redirect.do", method=GET)
	public String redirect() {
		System.out.println("----- redirect -----");
		//redirect�� ������ ViewResolver�� ��ġ�� �ʰ� JSP�� ��û�ϰ� �ȴ�. 
		
		///////////////// JSP ��û////////////////////
		return "redirect:day1129/redirect.jsp";
		
		///////////////// html ��û////////////////////
		//return "redirect:http://localhost/spring_mvc_prj/day1129/redirect.html";
		
		///////////////// do ��û////////////////////
		//return "redirect:http://localhost/spring_mvc_prj/index.do";
	}//redirect
	
	@RequestMapping(value="day1129/sub_path.do", method=GET)
	public String subPath() {
		
		
		return "subpath";
	}//subPath
	
	
	@RequestMapping(value="day1129_sub/exam.do", method=GET)
	public String requestExam() {
		return "day1129_sub/exam";//exam.jsp�� day1129_sub���������� �ֱ� ������ exam�� �ҷ��ٶ��� �ٿ��� ȣ���ϼž��ؿ� ��!
	}//requestExam
	
	@RequestMapping(value="day1129/use_resource.do", method=GET)
	public String resource() {
		return "day1129/resource";
	}//requestExam
	
	
}//class
