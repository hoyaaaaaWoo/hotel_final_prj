<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost/spring_mvc_prj/common/css/main_v20211012.css">

<style type="text/css">

</style>
<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

$(function(){
	$("#btn").click(function(){
		$.ajax({
			url:"ajax_req.do", //를 부를 거야
			type:"post",
			data:"name=" + $("#name").val(),
			dataType:"json",
			error: function( xhr ){
				console.log( xhr.status + " / " + xhr.statusText);
			},
			success:function( jsonObj ){
				$("#view").html( jsonObj.msg );
			}
		});//ajax
	});//click
	
	//------------------------------------------12-03-2021 코드추가------------------------------------------------
	$("#btn2").click(function(){
		$.ajax({
			url: "ajax_req2.do",
			type: "post",
			data: { name : $("#name2").val() },
			dataType: "json", //찍어보고 싶으면 "text"로 찍어보기
			error: function( xhr ){
				console.log( xhr.status + " / " + xhr.statusText);
			},
			success: function( jsonObj ){
				//응답되는 데이터로 View를 만들어서 보여줌
				$("#view").html( "<strong>" + jsonObj.msg + "</strong>");
			}
			
		});//ajax
	});//click
	
	$("#btn3").click(function(){
		//let select = $("#sel"); // 
		let select = document.getElementById("sel");
		let data = ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];

		for( var i = 0 ; i< data.length ; i++ ){ 
			select.options[i+1] = new Option ( data[i], data[i] );
		}//end for
	});//click
	
	$("#country").change(function(){
		
		if( $("country").val() != "none" ){
			$.ajax({
				url: "search_maker.do",
				data: {country : $("#country").val() }, //파라메터 명이 오타여서 Controller에서 웹 파라메터로 받지 못했어요
				type: "post",
				dataType: "json",
				error: function( xhr ){
					console.log(xhr.status + " / " + xhr.statusText);
				},
				success: function( jsonObj ){
					// select를 얻고 
					let makerSel = document.getElementById("maker");
					// option의 갯수 설정
					makerSel.length = 1;
					
					// 제조사가 조회되면 모델을 초기화한다. 
					let modelSel = document.getElementById("model");
					modelSel.length = 1;
					
					if( jsonObj.searchResultFlag ){ //검색결과가 존재하면
						let jsonArr = jsonObj.data; //JSONArray를 받고
						
						for( var i = 0 ; i < jsonArr.length ; i++ ){
							makerSel.options[i+1] = new Option( jsonArr[i].maker, jsonArr[i].maker ); //JSONArray로 Option 생성
						}//end for
						
					}//end if
				}//success
			});//ajax
		}//end if
		
	});//change
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
	
	// 제조사가 클릭되면 
	$("#maker").change(function(){
		
		if( $("maker").val() != "none" ){
			$.ajax({
				url: "search_model.do",
				data: {maker : $("#maker").val() }, 
				type: "post",
				dataType: "json",
				error: function( xhr ){
					console.log(xhr.status + " / " + xhr.statusText);
				},
				success: function( jsonObj ){
					// select를 얻고 
					let modelSel = document.getElementById("model");
					// option의 갯수 설정
					modelSel.length = 1;
					if( jsonObj.searchResultFlag ){ //검색결과가 존재하면
						let jsonArr = jsonObj.data; //JSONArray를 받고
						
						for( var i = 0 ; i < jsonArr.length ; i++ ){
							modelSel.options[i+1] = new Option( jsonArr[i].model, jsonArr[i].model ); //JSONArray로 Option 생성
						}//end for
						
					}//end if
				}//success
			});//ajax
		}//end if
		
	});//change
});//ready

</script>
</head>
<body></body>


<form>
<label>이름</label>
<input type = "text" name = "name" id = "name" />
<input type = "button" value = "입력" id = "btn" />
<label>이름2</label>
<input type = "text" name = "name2" id = "name2" />
<input type = "button" value = "입력" id = "btn2" />
</form>

<div id = "view"></div>

<div>
<select id = "sel">
	<option value = "none">선택</option>
</select>
<input type = "button" value = "옵션설정" id = "btn3" />
</div>

<div>
<select id = "country">
	<option value = "none">---제조국---</option>
	<option value = "국산">국산</option>
	<option value = "수입">수입</option>
</select>

<select id = "maker">
	<option value = "none">---제조사---</option>
</select>

<select id = "model">
	<option value = "none">---모델---</option>
</select>

<input type = "button" value = "검색" />

</div>

</body>
</html>