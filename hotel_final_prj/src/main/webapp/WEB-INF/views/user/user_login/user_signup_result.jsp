<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="회원가입 처리"
    %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
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
	font-size: 30px
}	

	.gohome {
	border: 1px solid #E9E9E9;
	font-size : 14px;
	font-weight: bold;
	background-color: #000;
	color: #F5DF3C;
	width: 130px;
	height: 40px;
	cursor: pointer;
	text-align: center;
	border-radius: 7px;
}

.gohome:hover {
	background-color: #F5dF4D;
	color: #000000;
	cursor: pointer;
}
			/* div {margin: 0px auto; width: 1130px;} */
	</style>

    <!-- Bootstrap core CSS -->
	<!-- jQuery CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	

	<!-- Bootstrap CDN -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
    <script src="http://211.63.89.141/common/bootstrap/holder.js"></script>

    <script src="http://211.63.89.141/common/bootstrap/ie-emulation-modes-warning.js"></script>

    <link href="http://211.63.89.141/common/bootstrap/carousel.css" rel="stylesheet">
  </head>
  
<!-- NAVBAR
================================================== -->
  <body>
 <div class="wrapper">
 
<jsp:include page="/user/common/main_header_nav.jsp"/>	


	<br/><br/><br/>
	<div class="container marketing">
  <br/><br/>
<div style="text-align: center">
		<p class="MyTitle">가입 완료</p><br/>
 <hr class="hr1">
	</div>

  <div style="width: 300px;text-align: center; margin: 0px auto; font-size: 15px">


<c:catch var="e">

<c:out value="${ param.kname }"/>님 <strong><c:out value="${ param.id }"/></strong>로
회원 가입되셨습니다.<br/>회원가입을 축하드립니다.<br/>

</c:catch>
<c:if test="${ not empty e }">
죄송합니다. 회원정보가 입력되지 않았습니다.
잠시 후 다시 시도해주세요.
</c:if>
</div>
</div>

  <br/><br/>
 <div style="width:150px; text-align: center; margin: 0px auto;">
  <input type="button" class="gohome" style="width: 100px;" value="홈으로" onclick="location.href='http://211.63.89.141/user/user_main/Hotel_Ritz_Seoul.do'">
  </div>
</div>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
      <!-- FOOTER -->
		<jsp:include page="/user/common/main_footer.jsp" />

    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="http://211.63.89.141/common/bootstrap/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
