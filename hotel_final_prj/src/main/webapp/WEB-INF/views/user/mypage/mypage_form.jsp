<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Hotel_Ritz_Seoul</title>
     <!-- 메인 CSS -->
	<link rel="stylesheet" type="text/css"
	href="http://211.63.89.141/user/css/main.css">
	<style type = "text/css">
	
.hr1 {border-bottom: 1px solid #d3d3d3;}

.MyTitle {
	color: #333;
	font-weight: bold;
	font-size: 30px;
}	

.MyCTitle {
	color: #333;
	font-weight: bold;
	font-size: 22px;
}	
	
#btn:hover {
	background-color: #FCF4C0  ;
	color: #333;
	cursor: pointer;
}

#btn {
	border: 1px solid #E9E9E9;
	font-size : 15px;
	font-weight: bold;
	background-color: #FAFAFA;
	color: #333;
	width: 250px;
	height : 40px;
	cursor: pointer;
	text-align: center;
	border-radius: 7px;
}

#btn1:hover {
	background-color: #FCF4C0  ;
	color: #333;
	cursor: pointer;
}

#btn1 {
	border: 1px solid #E9E9E9;
	font-size : 15px;
	font-weight: bold;
	background-color: #FAFAFA;
	color: #333;
	width: 250px;
	height : 40px;
	cursor: pointer;
	text-align: center;
	border-radius: 7px;
}

#delBtn:hover {
	background-color: #FCF4C0  ;
	color: #333;
	cursor: pointer;
}

#delBtn {
	border: 1px solid #E9E9E9;
	font-size : 15px;
	font-weight: bold;
	background-color: #FAFAFA;
	color: #333;
	width: 100px;
	height : 40px;
	cursor: pointer;
	text-align: center;
	border-radius: 7px;
}

.myDiv{
		width: 700px; 
		text-align: center;
		margin: 0px auto;
		font-size:14px;
	}
	
#passDiv{
		border: 1px solid #d3d3d3;
		width:500px;
		height:500px;
		padding:30px;
		border-radius: 30px;
		padding-top:40px;
	}
	
	.myCTitle {
		color: #333;
		font-weight: bold;
		font-size: 21px
	}
	
	.myCsTitle {
		color: #333;
		font-weight: bold;
		font-size: 16px;
	}
	
	#infoDiv{
		border: 1px solid #d3d3d3;
		width:500px;
		height:500px;
		padding:30px;
		border-radius: 30px;
		padding-top:40px;
	}
/*
div { border: 1px solid #0000FF}
td { border: 1px solid #FF0000}
tr { border: 1px solid #FF0000}
span { border: 1px solid #FF00FF}
p { border: 1px solid #FF00FF}
*/
	</style>
	
<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
<c:if test="${ not empty chgPassFlag }">
let msg="비밀번호를 다시 확인해주세요";
if(${ chgPassFlag } ){
	msg="비밀번호 변경이 완료되었습니다.";
}//end if
alert( msg );
</c:if>


<c:if test="${ not empty infoChgFlag }">
let msg="개인정보를 다시 확인해주세요.";
if(${ infoChgFlag } ){
	msg="개인정보 변경이 완료되었습니다.";
}//end if
alert( msg );
</c:if>


<c:if test="${ not empty delFlag }">
let msg="죄송합니다. 잠시 후 다시 시도해 주세요.";
if(${ delFlag } ){
	msg="회원 탈퇴가 완료되었습니다. 그동안 이용해 주셔서 감사합니다.";
}//end if
alert( msg );
</c:if>



$(function(){
	$("#tel").focusout(function(){
		   telCheck($(this).val()); 
		   });//focusout 
		   $("#email").focusout(function(){
				emailCheck($(this).val()); 	    
			 });//focusout 
	$("#btn").click(function(){
		var regPass = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;     // 비밀번호 정규식
		var change_pass =$("#change_pass").val();
		if(confirm("변경하시겠습니까?") == true) {
			if( $("#pass").val() =="" || $("#change_pass").val() =="" || $("#change_pass2").val() ==""   ){
					alert("비밀번호는 공백없이 입력해주세요.");
					$("#kname").focus();
					return;	
			}else if( $("#change_pass").val() != $("#change_pass2").val()   ){
					alert("변경할 비밀번호가 서로 일치하지 않습니다. ");
					return;
			}else if(  ($("#pass").val()) == ($("#change_pass").val()|| $("#change_pass2").val()) ){
						alert("현재 비밀번호와 변경할 비밀번호가 동일합니다");
						return;
			}else if(	!regPass.test($("#change_pass").val()) ){
				alert("비밀번호는 숫자와 문자를 조합하여 8~16글자로 설정해 주세요.");
				return;
			}
		}//end if
		$("#passFrm").submit();	
	})//click
	
	
	////////////////////////////////////////////////////
	$("#btn1").click(function(){
		if(confirm("변경하시겠습니까?") == true) {
			
			$("#frm").submit();	
		}//end if
	});//click
			function telCheck(args) {
			    	   var flag=false;
			    	    
			    	    if (/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(args)) {
			    	        flag= true;
			    	    }
			    	    if(flag==false){
			    	       alert("전화번호를 확인해 주세요");
			    	       $("#tel").val('');
			    	    }
			      }
    function emailCheck() {		

  		var email = document.getElementById("email").value;

  		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

  				if(exptext.test(email)==false){

  			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			

  			alert("이메일 형식이 올바르지 않습니다.");

  			document.addjoin.email.focus();

  			return false;

  		}
  	}
	$("#delBtn").click(function(){
		if (confirm("정말 탈퇴를 하시겠습니까?") == true) {
			alert("회원 탈퇴가 완료되었습니다. \n그동안 이용해 주셔서 감사합니다");
		$("#delfrm").submit();	
		}
		return;
	})//click
	
});//ready

</script>
</head>

<!-- NAVBAR
================================================== -->
  <body>
 	<jsp:include page="/user/common/main_header_nav.jsp"/>
  <div class="navbar-wrapper">
   </div>
<div>
<br/>
</div>
<br/><br/><br/><br/><br/><br/>
<div class = "container" style = "width: 1100px">	
<div style="text-align: center">
	<p class="MyTitle">마이페이지</p><br/>
 	<hr class="hr1">
</div>

<!-- <div style = "width:450px; border-bottom:2px solid #d3d3d3; text-align: center; margin: 0px auto;"> -->
<br/><br/><br/>
<div style="text-align: center; height: 550px" >


	<div class="myDiv" id="passDiv" style = "float: left;">
	<p class = "myCTitle" ">비밀번호 변경하기<p>

	<form  id="passFrm" action="member_pass_process.do" method="post">
	<br/><br/>
	<input type="password" style="width:250px;height:40px" placeholder="현재 비밀번호를 입력하세요"id="pass"  name ="pass">
	<br/><br/>
	<input type="password" style="width:250px;height:40px" placeholder="변경할 비밀번호를 입력하세요" id="change_pass" name="change_pass">
	<br/><br/>
	<input type="password" style="width:250px;height:40px" placeholder="변경할 비밀번호를 한 번 더 입력하세요."  id="change_pass2" name="change_pass2">
	<br/><br/><br/>
	<button type="button" id="btn">수정</button>
</form>


</div>


<c:if test="${empty info }">
<% response.sendRedirect("http://211.63.89.141/user/user_main/Hotel_Ritz_Seoul.do"); %>
</c:if>

<!-- <div style = "width:450px; border-bottom:2px solid #d3d3d3; text-align: center; margin: 0px auto;">
 -->
<div class="myDiv" id="infoDiv" style = "float: right;">
<c:forEach var="info" items="${info}">
	<form id="frm" action="member_update_process.do" method="post">

	<p class="myCTitle">이름 변경하기</p>
	<input type="text" style="width:250px;height:40px" value="${ info.kname }" id="kname" name="kname" >
	<br/><br/>

	<p class="myCTitle">전화번호 변경하기<br/><p class="myCsTitle" >("-"를 포함해주세요)</p></p>
	<input type="text" style="width:250px;height:40px" value="${info.tel }"name="tel" id="tel">
	<br/><br/>

	<p class="myCTitle">이메일 변경하기</p>
	<input type="text" style="width:250px;height:40px" value="${info.email }" name="email" id="email">
	<br/><br/><br/>
	<button type="button" value="수정"  id="btn1">수정</button>
	</form>
</c:forEach>
<br/><br/><br/><br/><br/><br/>
</div >
</div>

<div style = "width:450px; text-align: center; margin: 0px auto;">
	<form action="deleteMem.do" id="delfrm" name="delfrm">
	<button style = "text-align: center; margin: 0px auto;" type="button" id="delBtn">회원탈퇴</button>
	</form>
<br/><br/><br/>
</div>



</div>
  <br/><br/><br/><br/><br/><br/> 

 <!-- FOOTER -->


<jsp:include page="/user/common/main_footer.jsp" />

    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="http://211.63.89.141/common/bootstrap/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>