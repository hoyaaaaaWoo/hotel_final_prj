package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;
import java.util.Random;

@Controller
@RequestMapping("exception/")
public class ExceptionController {

	@RequestMapping(value = "exception.do", method = GET)
	public String classNot() throws ClassNotFoundException{
		
		if( new Random().nextBoolean() ) {
			throw new ClassNotFoundException("Ŭ������ �������� �ʴ� ����");
		}//end if
		
	return "exception/result";
	}//classNot
	
	
	@RequestMapping(value = "exception2.do", method = GET)
	public String query() throws SQLException{
			
		if( new Random().nextBoolean() ) {
			throw new SQLException("�����ͺ��̽��� ����ϴ� �� ���� �߻�");
		}//end if
			
		return "exception/cnfe_result";
	}//query
	
	
	@ExceptionHandler( ClassNotFoundException.class )
	public ModelAndView classNotExceptionHandling( ClassNotFoundException cnfe ) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName( "exception/cnfe_result" );
		mav.addObject( "cnfe", cnfe );
		
		return mav;
	}//classNotExceptionHandling
	
	@ExceptionHandler( SQLException.class )
	public ModelAndView sqlExceptionHandling( SQLException se ) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName( "exception/cnfe_result" );
		mav.addObject( "cnfe", se );
		
		return mav;
	}//classNotExceptionHandling
	
	
}//class
