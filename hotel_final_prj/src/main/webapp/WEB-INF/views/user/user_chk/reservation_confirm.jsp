<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="kr.co.sist.util.cipher.DataDecrypt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" info="Hotel Ritz Seoul"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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

<style type="text/css">
.hr1 {
	border-bottom: 1px solid #d3d3d3;
}

#resConf {
	width: 1000px;
	height: 60px;
	font-size: 20px;
	font-weight: bold
}

.resChk {
	width: 1000px;
	height: 1900px;
	color: #5A5A5A;
	margin: 0px auto;
	text-align: center;
}

.chkDiv {
	width: 1000px;
	color: #5A5A5A;
	margin: 0px auto;
	text-align: center;
}

.chkTab {
	width: 1000px;
	height: 450px;
	color: #5A5A5A;
	text-align: left;
	margin: 0px auto;
	table-layout: fixed;
}

.guide {
	font-size: 17px;
	font-weight: bold;
	padding-left: 20px;
}

.guideText {
	font-size: 15px;
}

.cancel {
	width: 800px;
	height: 300px;
	font-size: 13px;
	font-weight: bold;
}

.creditcard {
	font-size: 17px;
	font-weight: bold;
	padding-left: 20px;
}

.creditnumber {
	font-size: 15px;
}

.creditname {
	font-size: 15px;
}

.creditday {
	font-size: 15px;
}

#chkSubTab {
	width: 500px;
	border: 1px solid #d3d3d3;
}

.guideTextP {
	font-size: 15px;
	padding-left: 20px;
	padding-top: 10px;
	padding-bottom: 10px
}

.guideTextPR {
	font-size: 15px;
	padding-right: 20px;
	padding-top: 10px;
	padding-bottom: 10px;
	float: right;
	font-weight: bold
}

.notice {
	width: 1000px;
	height: 70px;
	font-size: 16px;
	font-weight: bold;
	color: #333;
	table-layout: fixed;
	padding: 5px;
	text-align: left;
}

.total {
	width: 1000px;
	height: 70px;
	font-size: 20px;
	font-weight: bold;
	color: #333;
	table-layout: fixed;
	padding: 5px;
}

.guideDiv {
	width: 1000px;
	height: 300px; /* border: 10px solid #FFFF00 */
}

.guideTitle {
	width: 1000px;
	height: 40px;
	color: #333;;
	font-size: 20px;
	font-weight: bold;
	text-align: left;
}

.addBack {
	width: 1000px;
	height: 100px;
	background-color: #FAFAFA;
	padding: 50px
}

.back {
	width: 1000px;
	height: 200px;
	background-color: #FAFAFA;
	padding: 50px
}

.backTab {
	width: 900px;
	height: 100px;
	margin: 0px auto;
	table-layout: fixed;
}

.button {
	border: 1px solid #E9E9E9;
	font-weight: bold;
	font-size: 15px;
	background-color: #000;
	color: #F5DF3C;
	width: 130px;
	height: 40px;
	cursor: pointer;
	text-align: center;
	border-radius: 7px;
}

.button:hover{
	background-color: #F5dF4D;
	color: #000000;
	cursor: pointer;
}

</style>

<!-- Bootstrap core CSS -->
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

<link href="http://211.63.89.141/common/bootstrap/carousel.css"
	rel="stylesheet">


<!-- daum map -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4770be2574c85940d843f0c412764fd2"></script>

<script type="text/javascript">

	function main() {
		location.href = "http://211.63.89.141/user/user_main/Hotel_Ritz_Seoul.do"
	}
	function print() {
		alert("인쇄를 시작하겠습니다.");
	}
	
	function cancelRes(){
		
		if(confirm("예약을 취소하시겠습니까?")){
			$("#cancelFrm").submit();
		}
	}//cancelRes
	

	$(function(){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(37.51271451389996, 127.10252419812018), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };

	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(37.51271451389996, 127.10252419812018); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	var iwContent = '<div style="padding:5px;"><strong>Hotel Ritz Seoul</strong> <br><a href="https://map.kakao.com/link/map/Hotel Ritz Seoul,37.51271451389996, 127.10252419812018" style="color:#333" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hotel Ritz Seoul,37.51271451389996, 127.10252419812018" style="color:#333" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwPosition = new kakao.maps.LatLng(37.51271451389996, 127.10252419812018); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker); 

	});//end ready
	
	function confirmCenter(msg) {
	      let flag = confirm(msg);
	      return flag;
	   }//confirmCenter
	   
</script>
</head>
<body>
<input type="hidden" value="${ res_no }"/>

	<div class="wrap">
	

		<jsp:include page="/user/common/main_header_nav.jsp"/>
		<!--================================================== -->
		<div class="resChk">
		<br><br><br>	
			<div class="chkDiv">
				<div id="resConf">예약 정보 조회</div>
				<div style = "font-size: 18px; font-weight: normal;">예약번호 : ${ rVO.res_no }</div>
				<form name="resChkInfo" id="resChkInfo" action="" method="post">
					<table class="chkTab">
						<tr>
							<td style="width: 500px">
							<img src="http://211.63.89.141/roomImages/${ rVO.main_img }"
								width="480" height="330" /><br /> <br /></td>

							<td>
								<table id="chkSubTab">
											
									<tr>
										<td class="guide">객실</td>
										<td class="guideTextP"><c:out value="${ rVO.r_name }"/></td>
									</tr>
									<tr>
										<td class="guide">투숙 날짜</td>
										<td class="guideTextP"><c:out value="${ rVO.chkin_date }"/> - <c:out value="${ rVO.chkout_date }"/> &nbsp;(${ diffDays }박)</td>
									</tr>
									<tr>
										<td class="guide">인원</td>
										<td class="guideTextP">성인&nbsp;<c:out value="${ rVO.adult }"/>, 어린이&nbsp;<c:out value="${ rVO.child }"/></td>
									</tr>
								</table> <br />

								<table id="chkSubTab">
									<tr>
										<td class="guide">객실요금</td>
										<td class="guideTextPR"><fmt:formatNumber pattern = "#,###,###" value = "${ daysPrice }"/> KRW</td>
									</tr>
									<tr>
										<td class="guide">세금 및 봉사료</td>
										<td class="guideTextPR"><fmt:formatNumber pattern = "#,###,###" value = "${ daysTax }"/> KRW</td>
									</tr>
								</table> <br /> <span class="guide">요금정책</span><br /> <span
								class="guideText"> ㆍ 상기 요금에 세금 및 봉사료가 각 10%가 가산됩니다. (총
									21%) <br /> ㆍ 상기 요금에 조식은 포함되어 있지 않습니다.
							</span>
							</td>
						</tr>
					</table>

					<hr class="hr1">
					<table class="total">
						<tr>
							<td></td>
							<td>총 요금</td>
							<td><fmt:formatNumber pattern = "#,###,###" value = "${ daysTotal }"/> KRW</td>
						</tr>
					</table>
				</form>
				<hr class="hr1">
			</div>
			<!-- chkDiv -->
			<br /> <br /> <br />


			<div class="guideDiv">
				<div class="guideTitle">예약자 정보</div>
				<div class="back">
					<form name="bookerInfo" id="bookerInfo" action="" method="post">
						<table class="backTab">
							<tr>
								<td class="guide">성(영문)</td>
								<td class="guideText"><c:out value="${ rVO.ename_lst }"/></td>
								<td class="guide">연락처</td>
								<td class="guideText"><c:out value="${ rVO.tel }"/></td>

							</tr>
							<tr>
								<td class="guide">이름(영문)</td>
								<td class="guideText"><c:out value="${ rVO.ename_fst }"/></td>
								<td class="guide">이메일</td>
								<td class="guideText"><c:out value="${ rVO.email }"/></td>
								<td></td>
								<td><button type="button" class="btn btn-primary"
										onclick="print()">인쇄하기</button></td>
							</tr>
							<tr>
								<td class="creditcard">신용카드 종류</td>
								<td class="creditname"><c:out value="${ rVO.company }"/></td>
								<td class="creditcard">신용카드번호</td>
								<td class="creditnum"><c:out value="${ rVO.card_no }"/></td>
							</tr>

						</table>
					</form>

				</div>
						<br><br><br><br>
						<div class="guideTitle">추가요청 사항</div>
						<div class="addBack">
						<table>
							<tr>
								<td style="font-size: 17px;"><c:out value="${ rVO.add_req }"/></td>
							</tr>
						</table>
						</div>
			</div>
			<!-- guideDiv -->
			 <br /><br /> <br /> <br /><br /> <br /> <br /><br /> <br /> <br /><br /> <br /> <br />


				<div>
					<table class="notice">
						<td>
						● 상기 홈페이지 요금은 정상가에서 할인된 금액으로 중복 할인은 불가능합니다. <br />
						● 예약 취소 및 변경은 체크인 하루 전 18:00(한국시간)까지 가능합니다. <br />
						● 예약하신 일자에 체크인 되지 않거나 또는 위의 지정 시간 이후에 예약을 취소했을 경우에는 예약 사항에 대해 노·쇼(No show)처리되며, 이에 따른 위약금으로 예약 첫날에 해당하는 1박
							객실 요금이 청구되오니 유의하시기 바랍니다. <br />
						● 일부 프로모션 특가 상품은 별도의 취소 규정이 적용되며, 취소 첫날 1박 요금의 100%가 위약금으로 부과됩니다.<br /></td>
					</table>
				</div>
				<br /> <br /> <br />
				<form name="cancelFrm" id="cancelFrm" action="res_cancel.do" method="post">
				<c:choose>
		<c:when test="${ rVO.res_status eq 'Y' }">
				<div style="width: 1000px; text-align: center;">
					<button type="button" class="button" id="cancelBtn"
						style="width: 400px; height: 40px;" onclick="cancelRes()">예약취소</button>
					<input type="hidden" name="res_no" value="${ res_no }" />
					<input type="hidden" name="res_status" id="res_status" />
				</div>
		</c:when>
		
		<c:when test="${ rVO.res_status eq 'N' }">
				<div style="width: 1000px; text-align: center;">
					<h4>취소된 예약</h4>
				</div>
		</c:when>
		
		</c:choose>
				</form>
		


			<br /> <br />
			<div id="map" style="width: 1000px; height: 500px; magin: 0px auto; "></div>


			<br /> <br /> <br /> <br />
			<div style="width: 1000px; text-align: center;">
				<button type="button" class="button"
					style="width: 100px; height: 40px" onclick="main()">홈으로</button>
			</div>
			
			

		</div>
		<!-- resChk -->
		<br /> <br /><br /> <br /><br /> <br /><br /> <br /><br /> <br /><br /> <br /> <br /> <br /> <br /> <br /> <br /><br /> <br /><br /> <br /><br /> <br />

		<!-- FOOTER -->
		<jsp:include page="/user/common/main_footer.jsp" />


	</div>
	<!-- wrap -->



	<!-- ================================================== -->

	<script
		src="http://211.63.89.141/common/bootstrap/ie10-viewport-bug-workaround.js"></script>
	</div>
</body>
</html>