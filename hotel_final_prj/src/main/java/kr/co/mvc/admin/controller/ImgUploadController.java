package kr.co.mvc.admin.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mvc.admin.service.ImgUploadService;

@Controller
public class ImgUploadController {

	@Autowired
	private ImgUploadService imgSev;
	
	@RequestMapping(value = "add_img_file.do", method = POST)
	@ResponseBody
	public String addImgFileProcess(HttpServletRequest request, String fileName) {
		
		return "";
	}//addImgFileProcess
	
	
	@RequestMapping(value = "remove_img_file.do", method = POST)
	@ResponseBody
	public String removeImgFileProcess(String imgName) {
		
		return "";
	}//removeImgFileProcess

	
}//class
