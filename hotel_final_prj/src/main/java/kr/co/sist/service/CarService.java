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
			for( int i = 0 ; i < makerList.size() ; i++ ) { // ��ȸ�� ����� �ݺ�
				temp = new JSONObject(); //��ȸ�� ����� �����ϱ� ���� JSONObject ����
				temp.put("maker", makerList.get(i)); // ������ JSONObject�� ��ȸ ����� ����
				jsonArr.add(temp); // ���� ���� JSONObject�� JSONArray�� �Ҵ�
			}//end for
			// �� for�� ������ �����Ͱ� �ִ� �Ŵ�
			// ���� ���� ���� ���� JSONArray�� JSONObject�� ����
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
			for( int i = 0 ; i < modelList.size() ; i++ ) { // ��ȸ�� ����� �ݺ�
				temp = new JSONObject(); //��ȸ�� ����� �����ϱ� ���� JSONObject ����
				temp.put("model", modelList.get(i)); // ������ JSONObject�� ��ȸ ����� ����
				jsonArr.add(temp); // ���� ���� JSONObject�� JSONArray�� �Ҵ�
			}//end for
			// �� for�� ������ �����Ͱ� �ִ� �Ŵ�
			// ���� ���� ���� ���� JSONArray�� JSONObject�� ����
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
