<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" info="사용자 화면 헤더와 네비바"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="navbar-wrapper"
	style="width: 100%; margin: 0px auto; position: fixed; z-index: 999">
	<div style="height: 60px; background-color: #FFFFFF">
	<div style="height: 20px;  background-color: #FFFFFF"></div>
		<span id="logo"
			onclick="location.href = 'http://211.63.89.141/user/user_main/Hotel_Ritz_Seoul.do'">
			<strong>Hotel Ritz Seoul</strong>
			<% 
			String id=(String)session.getAttribute("id");
			
					if( id == null){
			%>
		</span> <span> <a onclick="location.href='http://211.63.89.141/user/user_login/login.do'" id="linkLogin">로그인</a></span>
			<% }else{ %>
		 <span> <a onclick="location.href='http://211.63.89.141/user/user_login/logout.do'" id="linkLogin">로그아웃</a></span>
			<% }//end if %>
		 <span> <a href="http://211.63.89.141/user/user_chk/reservation_inq.do" id="linkResView">예약조회</a>
		</span> <span> <a href="http://211.63.89.141/user/mypage/mypage.do" id="linkMyPage">마이페이지</a></span>
	</div>
	<div style = "background-color: #000000">
		<nav>
			<table id="navTab">
				<tr>
					<td class="navTd" 
						onclick="location.href = 'http://211.63.89.141/user/user_room/room_date.do'">객실 예약</td>
					<td class="navTd"
						onclick="location.href = 'http://211.63.89.141/user/user_main/dining.do'">다이닝
						예약</td>
					<td class="navTd"
						onclick="location.href = 'http://211.63.89.141/user/user_main/drive.do'">드라이브
						스루</td>
					<td class="navTd"
						onclick="location.href = 'http://211.63.89.141/user/user_main/specialOffer.do'">스페셜
						오퍼</td>
					<td class="navTd"
						onclick="location.href = 'http://211.63.89.141/user/user_main/wedding.do'">웨딩&컨벤션</td>
				</tr>
			</table>
		</nav>
	</div>
</div>

<br />
<br />
<br />
<br />
<br />