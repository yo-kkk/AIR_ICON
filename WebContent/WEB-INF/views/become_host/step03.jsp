<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/favicon.png">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>호스트로 등록하는중입니다 3/3·AirIcon</title>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/become_host_step03.css">
		
</head>
<body>

	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
    
    <div class="step_title">
    	<h6 class="title_font"> 3단계 : 숙소의 편의시설 여부를 알려주세요.</h6>
    </div>
	
	<div class="progress_bar">
		<div class="progress_inner">
		</div>
	</div>

	<!-- main box -->
	<form name="myform" method="post" action="step03_action">
	
	<div class="main_box">
	
		<div class="ask">
			<h4 class="ask_font"><li>어떤 편의시설을 제공하시나요?</li></h4>
			
			<h3 class="intro_font">
				일반적으로 게스트가 기대하는 편의시설 목록입니다.<br/>
				숙소를 등록한 후 언제든 편의시설을 추가할 수 있어요.			
			</h3>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="kitchen" 
									<c:if test="${acom_infoDTO.kitchen == 'y'}">checked</c:if> >
				
				<h2 class="form-control">주방</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="parking"
									<c:if test="${acom_infoDTO.parking == 'y'}"> checked </c:if> >
				<h2 class="form-control">주차</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="toiletries"
									<c:if test="${acom_infoDTO.toiletries == 'y'}"> checked </c:if> >
				<h2 class="form-control">세면도구</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="elevator"
									<c:if test="${acom_infoDTO.elevator == 'y'}"> checked </c:if> >
				<h2 class="form-control">엘레베이터</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="tv"
									<c:if test="${acom_infoDTO.tv == 'y'}"> checked </c:if> >
				<h2 class="form-control">TV</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="aircond"
									<c:if test="${acom_infoDTO.aircond == 'y'}"> checked </c:if> >
				<h2 class="form-control">에어콘</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="hotwater"
									<c:if test="${acom_infoDTO.hotwater == 'y'}"> checked </c:if> >
				<h2 class="form-control">온수</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="washer"
									<c:if test="${acom_infoDTO.washer == 'y'}"> checked </c:if> >
				<h2 class="form-control">세탁기</h2>
			</div>
			
			<div class="checkbox_div">				
				<input type="checkbox" class="check_box" name="wifi"
									<c:if test="${acom_infoDTO.wifi == 'y'}"> checked </c:if> >
				<h2 class="form-control">와이파이</h2>
			</div>
								
		</div>		
		
		<div class="back_button">
			<button type="button" class="btn" onclick="javascript:history.go(-1);" > 
				<h4 class="button_font"> < 뒤로 </h4>		
			</button>
		</div>
		
		<input type="submit" class="submit_button" value="다음"/>
		
		
	</div>
	<!-- main box end-->
	
	</form>
	
	
</body>
</html>