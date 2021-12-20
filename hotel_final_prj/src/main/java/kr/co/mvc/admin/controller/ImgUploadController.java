package kr.co.mvc.admin.controller;

import javax.servlet.http.HttpServletRequest;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mvc.admin.service.ImgUploadService;

@Controller
public class ImgUploadController {

	@Autowired
	private ImgUploadService imgSev;
	
	/**
	 * 이미지 파일 업로드 -AJAX
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add_img_file.do", method=POST, produces = "applicaton/json;charset=UTF-8")
	@ResponseBody
	public String addImgFileProcess(HttpServletRequest request) {
		String imgJson = imgSev.addImgFileProcess(request);
		return imgJson;
	}//addImgFileProcess
	
	
	/**
	 * 이미지 파일 삭제 - AJAX
	 * @param imgName
	 * @return
	 */
	@RequestMapping(value = "remove_img_file.do",method=POST, produces = "applicaton/json;charset=UTF-8")
	@ResponseBody
	public String removeImgFileProcess(String imgName) {
		String imgJson = imgSev.removeImgFileProcess(imgName);
		return imgJson;
	}//removeImgFileProcess

}//class
