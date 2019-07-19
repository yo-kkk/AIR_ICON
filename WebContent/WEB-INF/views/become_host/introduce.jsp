<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>여행의 호스트가 되어보세요·AirIcon</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/acom_host_introduce01.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
	<jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
    
    <hr class="top_line">

    <div class="img_box">
		<img src="../images/introduce.jpg" class="back_img">
		<button type="button" class="btn btn-success front_btn" id="hostbtn" onclick="location.href='step01' ">호스트 등록하기</button>
	</div>
	
	<div class="intro1">
		<h1 class="intro_title">호스팅 3단계</h1>
		
		<hr class="intro_line">
		
		<div class="intro1_01">
			<h4 class="intro_title_font">무료로 숙소 등록하기</h4>
			
			<br/>
			<h6 class="intro_font">
			남는 방 하나부터 별장까지, <br/>
			다양한 형태의 공간을       <br/>
			무료로 등록하고 공유하세요.
			</h6>
		
		</div>
		
		<div class="intro1_02">
			<h4 class="intro_title_font">숙소 세부사항 입력하기</h4>
			
			<br/>
			<h6 class="intro_font">
			
			숙소의 종류,요금 및             <br/>
			숙소 설명과 사진을 등록하세요.  <br/>
			<br/>
			Step-by-Step으로 <br/>
			도와드리겠습니다.
			
			</h6>
		</div>
		
		<div class="intro1_03">
			<h4 class="intro_title_font">첫번째 게스트 맞이하기</h4>
			
			<br/>
			<h6 class="intro_font">
			숙소 등록이 끝나면 조건에 맞는 <br/>
			게스트가 연락을 할거에요! <br/>
			<br/>
			게스트를 맞이하기전 언제든 <br/>
			메시지로 연락할수 있습니다.
			</h6>
		</div>
		
	
	
	</div>
	
	<div class="intro2">
		<h1 class="intro_title">AIR-ICON 소개</h1>
		
		<hr class="intro_line">
		
		<div class="intro2_01">
			<h4 class="intro_title_font">Air-ICON이 무엇인가요?</h4>
			<br/>
			
			<h6 class="intro_font">
			에어아이콘은 국내 곳곳에서 머물 곳을 찾고 <br/>
			새로운 사람들을 만날수 있는 플랫폼 입니다.<br/>
			<br/>
			이러한 커뮤니티의 핵심은 바로 호스트 입니다.<br/>
			호스트는 게스트가 여행을 특별하게 <br/>
			즐길수 있도록 기회를 제공합니다.			
			</h6>
	
		</div>
		
		<div class="intro2_02">
			<h4 class="intro_title_font">호스트란 무엇인가요?</h4>
			
			<br/>
			
			<h6 class="intro_font">
			여행자를 대상으로 남는 집을 숙소로 빌려주거나<br/>
			전문 지식을 공유하여 수입을 올릴수 있습니다.<br/>
			<br/>
			숙소를 본인이 원하는 때에만 직접 빌려줄수 있고<br/>
			상황에 따라 원하지 않는 게스트는 거부할 수 있습니다.			
			</h6>
		</div>
		
		<hr class="end_line">
	</div>
	
	<div class="intro3">
		<img src="../images/welcome.jpg" class="welcome_img">	
		
		<div class="intro3_inner">
			<h2 style="margin-top: 110px" align="center">호스트를 시작할 준비가 되셨나요?</h2>
			<button type="button" class="btn btn-success" id="hostbtn" style="margin-top: 50px; margin-left : 390px;"
			onclick="location.href='step01' ">시작하기</button>
		</div>
	</div>

</body>
</html>