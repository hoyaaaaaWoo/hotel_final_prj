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

</script>
</head>
<body>
<!-- JSP가 저장된 폴더가 아닌 JSP를 요청할 때의 do의 URL을 기준으로 이동한다. -->
webapp에 의 자원 사용<br/>
<img src = "dice_1.png" title = "이미지를  JSP가 있는 폴더에 존재하는 것처럼 사용 가능"/><br/>
<img src = "http://localhost/spring_mvc_prj/day1129/dice_1.png" /><br/>

<a href = "redirect.html">링크</a>
<a href = "http://localhost/spring_mvc_prj/day1129/redirect.html">링크</a>
<a href="../index.do">메인</a>

</body>
</html>