<%@page import="org.springframework.dao.DataAccessException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	/* 		div {margin: 0px auto; width: 1130px;} */
.hr1 {border-bottom: 1px solid #d3d3d3;}

.loginTitle {
	color: #333;
	font-weight: bold;
	font-size: 25px
}

#gohome {
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

#gohome:hover {
	background-color: #F5dF4D;
	color: #000000;
	cursor: pointer;
}


#goLogin:hover {
	background-color: #FCF4C0  ;
	color: #333;
	cursor: pointer;
}

#goLogin {
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
/*
div { border: 1px solid #0000FF}
td { border: 1px solid #FF0000}
tr { border: 1px solid #FF0000}
span { border: 1px solid #FF00FF}
p { border: 1px solid #FF00FF}
*/

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

  </head>

<!-- NAVBAR
================================================== -->
  <body>


</div>

  <br/><br/>
 <div style="width:150px; text-align: center;margin:0px auto;">
  <input type="button" style="width: 100px;" id = "gohome" value="홈으로" onclick="location.href='http://team3.aws.sist.co.kr/main/Hotel_Ritz_Seoul.jsp'">

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