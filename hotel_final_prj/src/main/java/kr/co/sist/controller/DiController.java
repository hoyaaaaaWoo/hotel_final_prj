package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sist.service.EmpService;
import kr.co.sist.service.TestService;
import kr.co.sist.service.TestService2;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
@RequestMapping("di/")
public class DiController {

	@Autowired
	private TestService2 ts2;
	@Autowired
	private EmpService es;
	
//---------------------------------------------------------------------------------------------------------------------------------
	 
	@RequestMapping(value = "use_applicationContext.do", method = GET)
	public String useApplicationContext( Model model ) {
		
		//Service 사용
		//TestService ts = new TestService(); => 강결합임
		
		// 약결합해주기~
		ApplicationContext ac = new ClassPathXmlApplicationContext("kr/co/sist/config/applicationContext.xml");
		TestService ts = ac.getBean(TestService.class); //약결합됨
		
		// service가 제공하는 업무 받기
		model.addAttribute("subjectList", ts.subjectList());
		
		((ClassPathXmlApplicationContext)ac).close();
		
		return "di/result";
		
	}//useApplicationContext
	 
	
	
//---------------------------------------------------------------------------------------------------------------------------------

	 
	@RequestMapping(value = "use_webApplicationContext.do", method = GET)
	public String useWebApplicationContext( Model model ) {
		
		model.addAttribute("subjectList", ts2.subjectList() );
		return "di/result";
	}//useWebApplicationContext
	  
	
	
//---------------------------------------------------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "jdbc_template.do", method = GET)
	public String useJdbcTemplate( Model model ) throws SQLException {
		
		model.addAttribute("empList", es.searchAllEmp() );
		
		return "di/emp_result";
	}//useWebApplicationContext
	  
	
	public ModelAndView sqlErr( SQLException se) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("err.sql_err");
		mav.addObject("se", se);
		return mav;
	}//sqlErr
	
	
	
}//class
