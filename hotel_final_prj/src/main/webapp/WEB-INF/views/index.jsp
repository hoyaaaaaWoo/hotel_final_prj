<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function chgMethod(){
	var obj = document.frm;
	let type = "GET";
	
	if( obj.reqMethod[1].checked ){//선택된 요청방식의 비교
		type = "POST";
	}//end if
	
	obj.method = type; //요청방식의 변경
	obj.submit();
	//alert( obj.method );
	
}//chgMethod

<c:if test = "${ param.login eq 'nil'}">
alert("로그인을 하지 않으셨습니다.");
//세션을 설정한 다음, 삭제하면 redirect:../index.do?login=nil"; 로 메인으로 이동하게 되는데
//이때 login=nil이 있어서 메인으로 이동하면서 if를 타게 됩니다.
</c:if>

</script>

</head>
<body>

<ul>
<li><a href = "hello.do">안녕하세요? 요청</a></li>
<li><a href = "get.do">GET 요청</a></li>
<li>
	<form method = "post" action = "post.do">
	<input type = "submit" value = "POST요청" />
	</form>
</li>

<li>
	<form action = "post_get.do" name = "frm">
	<input type = "radio" value = "get" name = "reqMethod" checked = "checked" /> GET
	<input type = "radio" value = "post" name = "reqMethod"  /> POST
	<br/>
	<input type = "button" value = "요청" onclick = "chgMethod()" />
	</form>
</li>

<li><a href = "forward.do">view이동(forward)</a></li>
<li><a href = "redirect.do">view이동(redirect)</a></li>
<li><a href = "day1129/sub_path.do">경로구분(하위 경로가 존재)</a></li>
<li><a href = "day1129_sub/exam.do">요청경로에 대한 응답연습-이그잼</a></li>

<li><a href = "day1129/use_resource.do">Views안의 JSP가  webapp 안의 자원 사용</a></li>


<li><a href = "param/param_form.do">web parameter 처리(request)</a></li>
<li><a href = "param/param_form2.do">web parameter 처리(단일형 변수)로 값받기</a></li>
<li><a href = "param/param_form3.do">web parameter 처리(VO)</a></li>

<li><a href = "param/param_form_test.do">이름나이전화번호~</a></li>

<li><a href = "send/use_request.do">View로 데이터보내기(HttpRequestRequest)</a></li>
<li><a href = "send/use_model.do">View로 데이터보내기(Model)</a></li>
<li><a href = "send/use_model_and_view.do">View로 데이터보내기(ModelAndView)</a></li>

<li><a href = "chain/chain_a.do">view이동_a (forward chain)</a></li>
<li><a href = "chain/chain_b.do">view이동_b (forward chain)</a></li>

<li><a href = "session/http_session_set.do">session 값 설정(HttpSession)</a></li>
<li><a href = "session/http_session_get.do">session 값 얻기(HttpSession)</a></li>
<li><a href = "session/http_session_remove.do">session 값 삭제(HttpSession)</a></li>

<li><a href = "session/session_attribute_set.do">session 값 설정(@SessionAttribute)</a></li>
<li><a href = "session/session_attribute_get.do">session 값 얻기(@SessionAttribute)</a></li>
<li><a href = "session/session_attribute_remove.do">session 값 삭제 (@SessionAttribute)</a></li>

<li><a href = "cookie/cookie_set.do">cookie 심기</a></li>
<li><a href = "cookie/cookie_get.do">cookie 클래스 읽기</a></li>
<li><a href = "cookie/cookie_value_get.do">cookie @CookieValue 읽기</a></li>

<li><a href = "include/include.do">include</a></li>

<li><a href = "exception/exception.do">예외처리(Exception Handling)</a></li>
<li><a href = "exception/exception2.do">예외처리(Exception Handling)</a></li>

<li><a href = "fileup/file_upload_frm.do">fileupload 폼</a></li>

<li><a href = "di/use_applicationContext.do">DI 사용</a></li>
<li><a href = "di/use_webApplicationContext.do">DI 사용</a></li>
<li><a href = "di/jdbc_template.do">DI 사용</a></li>

<li><a href = "ajax/ajax_form.do">ajax</a></li>

</ul>

</body>
</html>