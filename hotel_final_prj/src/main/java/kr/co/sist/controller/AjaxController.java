package kr.co.sist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.sist.service.CarService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.json.simple.JSONObject;

@Controller
@RequestMapping("ajax/")
public class AjaxController {

	@Autowired
	private CarService cs;
	
//----------------------------------------------------------------------------------	
	
	
	@RequestMapping(value = "ajax_form.do", method = GET)
	public String ajaxForm() {
		return "ajax/form";
	}//ajaxForm
	

//----------------------------------------------------------------------------------	
	
	
	@RequestMapping(value = "ajax_req.do", method = POST) //GET �̸� form�� ajax�� type�� get���� ���߱�
	public String ajaxReq( String name, Model model) {
		
		JSONObject json = new JSONObject();
		json.put("msg", name + "�� �ȳ��ϼ���?");
		
		model.addAttribute("jsonData", json.toJSONString() );
		
		return "ajax/ajax_result";
	}//ajaxReq
	
	
//-------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "ajax_req2.do", method = POST, produces = "application/json;charset=UTF-8") //GET �̸� form�� ajax�� type�� get���� ���߱�
	@ResponseBody //�ٽ� �������̼�~!~!~!~!��
	public String ajaxReq2( String name ) {
		// JSONObject�� �����ϴ� ���� ���������̹Ƿ� Service���� Ŭ������ ���ǵǾ�� ��
		JSONObject json = new JSONObject();
		json.put("msg", name + "�� �ȳ��ϼ���? 12���� ù ��° �ݿ����Դϴ�.");

		return json.toJSONString();
	}//ajaxReq
	
	
//-------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "search_maker.do", method = POST, produces = "application/json;charset=UTF-8") 
	@ResponseBody //�ٽ� �������̼�~!~!~!~!��
	public String searchMaker( String country ) {
		String jsonObj = cs.searchMakerJson(country);
		
		return jsonObj;
	}//ajaxReq
	
	
//-------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "search_model.do", method = POST, produces = "application/json;charset=UTF-8") 
	@ResponseBody //�ٽ� �������̼�~!~!~!~!��
	public String searchModel( String maker ) {
		String jsonObj = cs.searchModelJson(maker);
		
		return jsonObj;
	}//ajaxReq
	
	
	
	
}//class
