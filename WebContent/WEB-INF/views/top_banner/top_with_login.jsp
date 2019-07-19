<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/top_with_login.css">
	<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
	
	<!-- 전체 Top 1024x100 -->
	<div class="top_with_login">
	
	<!-- AirIcon Banner -->
	<div class="airicon_button">
		<h2>
     		<a class="airicon_font font-weight-light" href="../home/home">AirIcon</a>
    	</h2>
    </div>
    
    <!-- Right Dropdown button -->
    <div class="dropdown_box">
			<ul class="nav" id="top_with_login_ul">
			
				<li class="nav-item top_li">
					<a class="nav-link" href="../become_host/introduce">호스트가 되어보세요</a>
						
				</li>
				
				<li class="nav-item dropdown top_li">
					<a class="nav-link" href="../message/msg_main">메시지</a>
				</li>
				<li class="nav-item dropdown top_li">
					<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"> <%= session.getAttribute("user_id") %>  님</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="../profile/profilelist">내 프로필</a>
						<a class="dropdown-item" href="../trip/trip_1">내 예약 현황</a>						 
						<a class="dropdown-item" href="../userInfo/logout">로그아웃</a>
					</div>
				</li>
			</ul>
		</div>
		<!-- DropdownButton End -->
	
	</div>
	<!-- 전체 Top End -->
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


