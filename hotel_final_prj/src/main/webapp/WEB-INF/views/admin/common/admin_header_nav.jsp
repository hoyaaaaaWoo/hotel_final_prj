<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" info="관리자 공통 디자인 - header, navi"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 

		<div id="header">
		Hotel Ritz Seoul  &nbsp; &nbsp;
		<span class="glyphicon glyphicon-home cursor" aria-hidden="true" onclick="javascript:location.href='search_today_res_list.do'"></span>
		<span class="glyphicon glyphicon-user" aria-hidden="true" id="adminImg" ></span>
		<span style="position: absolute; right: 3px; top: 7px;"><strong>   </strong></span>
		<span id="logOut" class="cursor" onclick="javascript:location.href=''">로그아웃</span>
		</div>

		<nav id="naviBar">
			<ul>
				<li class="navLi"><a class="navA" href=""><span
						class="glyphicon glyphicon-triangle-right"></span> 회원관리</a></li>
				<li class="navLi"><a class="navA" href="search_res_list.do"><span
						class="glyphicon glyphicon-triangle-right"></span> 예약관리</a></li>
				<li class="navLi"><a class="navA" href=""><span
						class="glyphicon glyphicon-triangle-right"></span> 객실관리</a></li>
			</ul>
		</nav>
		
