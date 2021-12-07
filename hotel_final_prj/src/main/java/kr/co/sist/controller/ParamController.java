package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.vo.ParamVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

@Controller //Handler �� ã������~
public class ParamController {
	
	@RequestMapping(value="param/param_form.do", method=GET)
	public String paramForm() {
		return "param/param_form";
	}//paramForm
	
	
	@RequestMapping(value="param/param_form_process.do", method={GET, POST})
	public String paramFormProcess( HttpServletRequest request) {
//		//post����� �ѱ�ó�� : request�� ���Ǳ� �� ����Ǿ�� ��
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		//�Ķ���� �ޱ�
		System.out.println("��û��� : " + request.getMethod());
		
		//�Ķ���� �� �ޱ�
		// ����
		System.out.println("�̸� : " + request.getParameter("name"));
		//�ߺ� : �迭�� ó��
		String[] hobby=request.getParameterValues("hobby");
		if( hobby != null ) {
			for( String value : hobby ) {
				System.out.println(value + " " );
			}//end for
			System.out.println();
		}else {
			System.out.println("���� ��� ����");
		}//end else
		
		
		return "param/param_form_process";
	}//paramFormProcess
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	@RequestMapping(value="param/param_form2.do", method=GET)
	public String paramForm2() {
		return "param/param_form2";
	}//paramForm // form�ҷ�����
	
	@RequestMapping(value="param/param_form_process2.do", method={GET, POST})
	//public String paramFormProcess2( String name, String[] hobby, String age ) {
		// web parameter�� String�����θ� ���۵�����, int�� �Ű������� �����ϸ� 
		// Spring���� parseInt method�� ����Ͽ� ��ȯ �� �Է����ش�. 
	public String paramFormProcess2( String name, String[] hobby, 
			@RequestParam(required = false, defaultValue = "0")int age ) {
		
		System.out.println("������ ���������� ����� �Ķ���� �� �ޱ�");
		System.out.println("�̸� : " + name);
		//System.out.println("���� : " + Integer.parseInt(age));
		System.out.println("���� : " + age);
		
		if( hobby != null ) {
			for( String value : hobby ) {
				System.out.println(value + " " );
			}//end for
			System.out.println();
		}else {
			System.out.println("���� ��� ����");
		}//end else
		
		return "param/param_form_process2";
	}//paramFormProcess2
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="param/param_form_test.do", method=GET)
	public String paramForm_test() {
		return "param/param_form_test";
	}//paramForm // form�ҷ�����
	
	@RequestMapping(value="param/param_form_process_test.do", method=GET)
	public String paramFormProcess_test( String name, String age, String phoneNum ) {
		
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + Integer.parseInt(age));
		System.out.println("��ȭ��ȣ : " +  phoneNum);
		
		return "param/param_form_process_test";
	}//paramFormProcess3
	
	
///////////////////////////////////////////////  11-30-2021 �ڵ�  ///////////////////////////////////////	

	@RequestMapping(value="param/param_form3.do", method=GET)
	public String paramForm3() {
		return "param/param_form3";
	}//paramForm // form�ҷ�����
	
	@RequestMapping(value="param/param_form_process3.do", method={GET, POST})
	public String paramFormProcess3( HttpServletRequest request, ParamVO pVO ) {
		
		pVO.setIp(request.getRemoteAddr()); // ������ ������ �Ұ���
		
		System.out.println("VO�� ����� �Ķ���� �� �ޱ�");
		System.out.println("�̸� : " + pVO.getName());
		//System.out.println("���� : " + Integer.parseInt(age));
		System.out.println("���� : " + pVO.getAge());
		System.out.println("�������� ip address : " + pVO.getIp());
		
		String[] hobby = pVO.getHobby();
				
		if( hobby != null ) {
			for( String value : hobby ) {
				System.out.println(value + " " );
			}//end for
			System.out.println();
		}else {
			System.out.println("���� ��� ����");
		}//end else
		
		return "param/param_form_process3";
	}//paramFormProcess2
	
}//class
