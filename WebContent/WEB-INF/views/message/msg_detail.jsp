<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>메세지·AirIcon</title>

	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
	
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- CSS file href -->
	<link rel="stylesheet" href= "/css/msg_detail.css">
</head>
<body>
	<jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
	
	<hr class="top_line">
	
	<div class="user_info">	
		<img src="../images/msg.jpg" class="msg_img">
	</div>
	
	
	<form method="post" action="msg_send">
	<div class="message_write">	
		<h5 class="sender_font"><span id="other_id">${other_id}</span>님에게 메시지 보내기</h5>
		
		<textarea class="form-control write_content" rows="6" name="msg_content"
			<c:if test="${other_id=='admin'}"> disabled placeholder="관리자에게는 메시지를 보낼수 없습니다"</c:if> ></textarea>
		 
		<input type="submit" class="btn btn-outline-secondary send_btn" value="메시지 보내기">
		<input type="hidden" name="other_id" value="${other_id}">
	</div>	
	</form>	
	
	
	<div class="detail_fullbox">
	<c:forEach items="${list}" var="dto">
	
	<c:choose>
		<c:when test="${dto.sender_id==user_id }">
			<hr class="msg_line">
		
			<div class="msg_box">
				<textarea class="form-control send_content" rows="6" disabled>${dto.content}</textarea>
			
				<h4 class="sender_id" >${dto.sender_id}</h4>
			
				<h5 class="sender_date">${dto.reg_date}</h5>
			</div>
		</c:when>
		
		<c:otherwise>
			<hr class="msg_line">
			
			<div class="msg_box">
				<textarea class="form-control getter_content" rows="6" disabled>${dto.content}</textarea>
			
				<h4 class="getter_id">${dto.sender_id}</h4>
			
				<h5 class="getter_date">${dto.reg_date}</h5>
		</div>
		</c:otherwise>
	</c:choose>
		
	</c:forEach>
	
	
		
		
	</div>
	
</body>
</html>