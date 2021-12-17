<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	info="회원조회"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hotel Ritz - 회원관리</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost/hotel_final_prj/common/css/main_v20211012.css">

<!-- jQuery CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- 관리자 메인 CSS -->
<link rel="stylesheet" type="text/css"
	href="http://localhost/hotel_final_prj/admin/css/admin_main.css">
	
	
<style type="text/css">

#idSearch{
	font-size: 15px;
	margin: 20px;
	padding :45px;
	border-bottom: 1px solid #454D55;
	width:100%;
	hegiht:200px;
}

#memberList{
   width:100%;
   padding-left:25px;
}

#id{
	width:140px;
	margin-right: 5px;
	font-size: 15px;
	color:#000000;
	position: absolute;
	top: 145px;
	left: 75px;	
}
	
#searchBtn{
	position: absolute;
	top: 140px;
	left: 230px;	
}
	
 
.table{
	width:1300px;
	margin-top: 40px;
	}

th{
	height:40px;
	font-size: 16px;
	text-align: center;
	vertical-align: middle;
	background-color: #dfdfdf;
	}
	
td{
	font-size: 16px;
	text-align: center;
	vertical-align: middel;
	color:#000000;
	background-color: #FFFFFF;
}
tr:hover td {
	background-color: #F1F3F4;
	cursor:pointer;
}
.btn{
	font-weight: bold;
}

#page {
	margin-top: 50px;
	padding-left: 550px;
}

.pagination>li>a {
	color: #343A40
}

#navMember{
	background-color: #454D55;
	text-decoration: none;
}
</style>

<script type="text/javascript">
$(function(){
$("#searchBtn").click(function(){
	$("#frm_search").submit();
})//click

/* 페이지네이션 클릭시 현재페이지 전송 */
$(".pagination li").click(function () {
	let page = $(this).text();
	let currentPage = ${currentPage};
	let totalPage = ${totalPage};

	//이전 버튼 클릭
	if(page == '<<') {
		if(currentPage == 1) { //현재 1 페이지면 1 반환
			page = 1; 
		} else {
			page = currentPage-1;
		}//end else
	}//end if  
	
	//다음 버튼 클릭
	if(page == ">>") {
		if(currentPage == totalPage) {//현재 끝 페이지면 끝 반환
			page = totalPage; 
		} else {
			page = currentPage+1;
		}//end else
	}//end if  
 	
	location.href="search_active_member.do?page="+page;
});//페이지네이션 click


$(".delBtn").click(function(){
	//선택된 버튼 할당
	var delBtn = $(this);
	//선택된 버튼에 해당하는 행과 각 td
	let tr = delBtn.parent().parent(); 
	let td = tr.children();
	//삭제번호 얻기
	let id = td.eq(0).text();
	if(confirm("["+id+"] 회원을 삭제하시겠습니까?")){
		$("#delId").val(id);
		$("#delFrm").submit();
	}else{
		alert("회원 삭제를 취소합니다.");
	}//end else
});//click


})//ready

</script>
</head>
<body>
	<div id="wrap">
		<!-- header/navibar import -->
		<jsp:include page="/admin/common/admin_header_nav.jsp"/>
			
		<!-- 컨테이너 시작 -->
		<div id="container">
	 		<span id="mainMenu" onclick="location.href='search_active_member.do'">일반회원 조회</span> &nbsp; 
			<span id="mainMenu" style="text-decoration: none" onclick="location.href='search_inactive_member.do'">탈퇴회원 조회</span>
			<div id="idSearch">
				
				<form id="frm_search" action="search_active_member.do" method="post">
				<c:choose>
					<c:when test="${not empty id}">
						<input type="text" name="id" value="${id}"  class="form-control" id="id" maxlength="10"/>&nbsp;
					</c:when>
					<c:otherwise>
						<input type="text" name="id" placeholder="ID 조회"  class="form-control"  id="id"maxlength="10"/>&nbsp;
					</c:otherwise>
				</c:choose>
					<input type="button" value="검색" name="search" class="btn btn-default" id="searchBtn" />
				</form>	
				
			</div>
			
			<div id="memberList"> 
			<table class="table table-bordered"  id="table">
				<tr>
					<th>ID</th>
					<th>예약자명</th>
					<th>영문이름</th>
					<th>생년월일</th>
					<th>연락처</th>
					<th>이메일</th>
					<th>가입일자</th>
					<th>회원관리</th>
				</tr>	
			 <c:if test="${ empty memberList }">
	   			<tr> 
	     		 <td colspan="7">회원정보가 존재하지 않습니다.</td>
	   			</tr>
   			</c:if>
				<c:forEach var="member" items="${memberList}">
				<tr>
					<td><c:out value="${member.id }"/></td>
					<td><c:out value="${member.kname }"/></td>
					<td><c:out value="${member.ename_fst }${member.ename_lst }"/></td>
					<td><c:out value="${member.birth_year }"/></td>
					<td><c:out value="${member.tel }"/></td>
					<td><c:out value="${member.email }"/></td>
					<td><c:out value="${member.join_date }"/></td>
					<td><input type="button" id="delBtn" name="delBtn" class="delBtn btn btn-danger" value="회원삭제"></td>
				</tr>
				</c:forEach>
			</table>
			</div>			
			
		<c:if test="${totalPage ne 0}">
		<ul class="pagination" id="page">
		    <li><a href="#void">&lt;&lt;</a></li>
		    <c:forEach var="num" begin="1" end="${totalPage}" step="1">
		    	<c:if test="${num eq currentPage}">
		    		<c:set var="active" value="style='background:#dfdfdf'"/>
			    </c:if>
			    <li><a href="#void" ${active}><c:out value="${num}"/></a></li>
		    	<c:set var="active" value=""/>
		    </c:forEach>
		    <li><a href="#void">&gt;&gt;</a></li>
		 </ul>
		 </c:if>
		
		</div> <!-- 컨테이너 div  -->
		
		<!-- 삭제버튼 클릭시 hidden값 설정 및 페이지 이동 -->
		 <form name="delFrm" id="delFrm" action="http://localhost/hotel_final_prj/admin/admin_member/admin_member_del_process.jsp" method="post">
		 	<input type="hidden" name="delId" id="delId"/>
		 </form>
		 
		<!-- footer import -->
		<jsp:include page="/admin/common/admin_footer.jsp"/>
		
	</div>
</body>
</html>