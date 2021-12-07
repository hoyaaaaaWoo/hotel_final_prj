package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("chain/") //Controller�� ���Ǵ� ��� �����θ� �ٿ��� �ۼ��� �� 
public class ChainController {

	@RequestMapping(value="chain_a.do", method=GET)
	public String chainA() {
		return "forward:chain_b.do"; //ViewResolver�� ������� �ʰ� do�� �̵�
	}//chainA
	
	
	
	@RequestMapping(value="chain_b.do", method=GET)
	public String chainB() {
		return "chain/chain_result";
	}//chainA
	
	
	
}//ChainController
