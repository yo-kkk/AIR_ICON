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
	<title>호스트로 등록하는중입니다 1/3·AirIcon</title>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/become_host_step01.css">
	
	<script type="text/javascript">
	function OnlyNumber() { 
		var code = window.event.keyCode; 
		if ((code > 34 && code < 41)  || (code > 47 && code < 58) || (code > 95 && code < 106) || 
			 code == 8 || code == 9 || code == 13 || code == 46) { 
			window.event.returnValue = true; 
			return; 
		} 
		window.event.returnValue = false; 
	}

	</script>

</head>
<body>

	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
    
    <div class="step_title">
    	<h6 class="title_font"> 1단계 : 기본 사항을 입력해주세요.</h6>
    </div>
	
	<div class="progress_bar">
		<div class="progress_inner">
		</div>
	</div>

	<!-- main box -->
	<form name="myform" method="post" action="step01_action">
	
	<div class="main_box">
		<div class="main_intro">
		
			<h3 class="intro_font">
				현재는 집 전체를 이용할수 있는 경우에만 <br/>
				호스트로 등록이 가능합니다.					
			</h3>
		</div>
	
		<div class="ask_01">
			<h4 class="ask_font"><li>등록하실 숙소의 종류는 무엇인가요?</li></h4>
			
			<select class="form-control" name="type">
    			<option selected disabled required hidden="true">하나를 선택하세요</option>
   				<option <c:if test="${acom_infoDTO.type=='아파트'}">selected</c:if> >아파트</option>
    			<option <c:if test="${acom_infoDTO.type=='주택'}">selected</c:if> >주택</option>
    			<option <c:if test="${acom_infoDTO.type=='빌라'}">selected</c:if> >빌라</option>
    			<option <c:if test="${acom_infoDTO.type=='독채'}">selected</c:if> >독채</option>
    			<option <c:if test="${acom_infoDTO.type=='숙박 업소'}">selected</c:if> >숙박 업소</option>
    			<option <c:if test="${acom_infoDTO.type=='독특한 숙소'}">selected</c:if> >독특한 숙소</option>    			
 			</select>			
		</div>
		
		<div class="ask_02">
			<h4 class="ask_font"><li>숙소에 얼마나 많은 인원이 숙박할 수 있나요?</li></h4>
			
 			<input type="text" class="form-control" name="maxperson" placeholder="숙박 가능 인원"  maxlength="4"  onKeyDown="OnlyNumber()" required
 			<c:if test="${acom_infoDTO.maxperson != 0}">value="${acom_infoDTO.maxperson}"</c:if> >
		</div>
		
		<div class="ask_03">
			<h4 class="ask_font"><li>숙소의 위치를 알려주세요.</li></h4>
			
			<input type="text" class="form-control" name="address" placeholder="주소를 입력해주세요" required
			<c:if test="${acom_infoDTO.address != null}">value="${acom_infoDTO.address}"</c:if> >
		</div>
		
		<div class="ask_04">
			<h4 class="ask_font"><li>희망하는 가격을 알려주세요.</li></h4>
			정확한 가격은 심사를 통해서 고지될 예정입니다.
			
			<input type="text" class="form-control" name="price" placeholder="(원) 단위로 입력해주세요." maxlength="10"  onKeyDown="OnlyNumber()" required
			<c:if test="${acom_infoDTO.price != 0}">value="${acom_infoDTO.price}"</c:if> >
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