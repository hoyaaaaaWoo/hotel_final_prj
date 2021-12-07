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
	
	
	@RequestMapping(value = "ajax_req.do", method = POST) //GET 이면 form의 ajax의 type도 get으로 맞추기
	public String ajaxReq( String name, Model model) {
		
		JSONObject json = new JSONObject();
		json.put("msg", name + "님 안녕하세요?");
		
		model.addAttribute("jsonData", json.toJSONString() );
		
		return "ajax/ajax_result";
	}//ajaxReq
	
	
//-------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "ajax_req2.do", method = POST, produces = "application/json;charset=UTF-8") //GET 이면 form의 ajax의 type도 get으로 맞추기
	@ResponseBody //핵심 에노테이션~!~!~!~!★
	public String ajaxReq2( String name ) {
		// JSONObject을 생성하는 것은 업무로직이므로 Service쪽의 클래스에 정의되어야 함
		JSONObject json = new JSONObject();
		json.put("msg", name + "님 안녕하세요? 12월의 첫 번째 금요일입니다.");

		return json.toJSONString();
	}//ajaxReq
	
	
//-------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "search_maker.do", method = POST, produces = "application/json;charset=UTF-8") 
	@ResponseBody //핵심 에노테이션~!~!~!~!★
	public String searchMaker( String country ) {
		String jsonObj = cs.searchMakerJson(country);
		
		return jsonObj;
	}//ajaxReq
	
	
//-------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "search_model.do", method = POST, produces = "application/json;charset=UTF-8") 
	@ResponseBody //핵심 에노테이션~!~!~!~!★
	public String searchModel( String maker ) {
		String jsonObj = cs.searchModelJson(maker);
		
		return jsonObj;
	}//ajaxReq
	
	
	
	
}//class
