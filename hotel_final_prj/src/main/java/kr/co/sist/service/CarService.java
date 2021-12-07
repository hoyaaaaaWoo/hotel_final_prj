package kr.co.sist.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import kr.co.sist.dao.carDAO;

@Component
public class CarService {

	@Autowired
	private carDAO cDAO;
	
	public String searchMakerJson( String country ) {
		
		List<String> makerList = null;
		JSONObject json = new JSONObject();
		
		boolean searchFlag = false;
		int dataSize = 0;
		
		try {
			JSONArray jsonArr = new JSONArray();
			makerList = cDAO.selectMaker(country);
			
			JSONObject temp = null;
			for( int i = 0 ; i < makerList.size() ; i++ ) { // 조회된 결과로 반복
				temp = new JSONObject(); //조회된 결과를 저장하기 위한 JSONObject 생성
				temp.put("maker", makerList.get(i)); // 생성된 JSONObject에 조회 결과를 저장
				jsonArr.add(temp); // 값을 가진 JSONObject를 JSONArray에 할당
			}//end for
			// 이 for를 지나면 데이터가 있는 거다
			// 여러 개의 값을 가진 JSONArray를 JSONObject에 저장
			json.put("data", jsonArr);
			searchFlag = true;
			dataSize = makerList.size();
		}catch( DataAccessException dae ){
			dae.printStackTrace();
		}//end catch
		
		json.put("searchResultFlag", searchFlag);
		json.put("dataSize", dataSize);
		
		
		return json.toJSONString();
		
	}//searchMakerJson
	
	
	
	
	public String searchModelJson( String maker ) {
		
		List<String> modelList = null;
		JSONObject json = new JSONObject();
		
		boolean searchFlag = false;
		int dataSize = 0;
		
		try {
			JSONArray jsonArr = new JSONArray();
			modelList = cDAO.selectModel(maker);
			
			JSONObject temp = null;
			for( int i = 0 ; i < modelList.size() ; i++ ) { // 조회된 결과로 반복
				temp = new JSONObject(); //조회된 결과를 저장하기 위한 JSONObject 생성
				temp.put("model", modelList.get(i)); // 생성된 JSONObject에 조회 결과를 저장
				jsonArr.add(temp); // 값을 가진 JSONObject를 JSONArray에 할당
			}//end for
			// 이 for를 지나면 데이터가 있는 거다
			// 여러 개의 값을 가진 JSONArray를 JSONObject에 저장
			json.put("data", jsonArr);
			searchFlag = true;
			dataSize = modelList.size();
		}catch( DataAccessException dae ){
			dae.printStackTrace();
		}//end catch
		
		json.put("searchResultFlag", searchFlag);
		json.put("dataSize", dataSize);
		
		
		return json.toJSONString();
		
	}//searchModelJson
	
}//class
