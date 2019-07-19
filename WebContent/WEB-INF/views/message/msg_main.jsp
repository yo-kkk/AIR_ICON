<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>메세지·AirIcon</title>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
	
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href= "/css/msg_main.css">
</head>
<body>
	<jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
	
	<hr class="top_line">
	
	<div class="message_fullbox">
	
		<h5 class="intro_ment">
			상세보기를 통해 메시지를 작성해보세요.
		</h5>
		
		<c:forEach items="${list}" var="dto">
		<div class="msg_inner">
			<br/>
			<br/>
			<h5 class="sender_font">${dto.other_id}</h5>			
			<h5	class="date_font">${dto.reg_date}</h5>			
			<textarea class="form-control msg_font" disabled rows="5" >${dto.content }</textarea>			
			
			<button type="button" class="btn btn-secondary detail_button" onclick="location.href='msg_detail?other_id=${dto.other_id}'">상세보기</button>
		</div>
		</c:forEach>	
		
	</div>
	
</body>
</html>