<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="16x16" href="WebContent/images/favicon.png">
	<title>Insert title here</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- Css file href -->
	<link rel="stylesheet" href="css/search.css">	
	
</head>
<body>


<div class="container" style="width:1500px;">
	<div class="row" >
		<div class="col-md-6">
			<h2 class="top_margin">
     			<a class="airicon_button font-weight-light" href="main_intro.jsp">AirIcon</a>
     		</h2>  
		</div>
		<div class="col-md-6" style="padding-top:20px;">
			<ul class="nav">
				<li class="nav-item dropdown ml-md-auto">
					 <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">호스트가 되어보세요</a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						 <a class="dropdown-item" href="#">숙소 호스트</a>
						 <a class="dropdown-item" href="#">트립 호스트</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">여행</a>
				</li>
				<li class="nav-item dropdown">
					 <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">메시지</a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						 <a class="dropdown-item" href="#">알림(0) 모두보기</a>
						 <div class="dropdown-divider"></div>
						 <a class="dropdown-item" href="#">아이콘 : 지각하지마라</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					 <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">ABC님</a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						 <a class="dropdown-item" href="http://localhost/profile/profilelist">프로필</a> <!-- 임시 -->
						 <a class="dropdown-item" href="#">로그아웃</a>
					</div>
				</li>
			</ul>
		</div>
	</div>
	
	
	
<div class="row" style="padding-top:20px; margin: 0 auto;">

	<form class="form-inline" style=" margin:0 auto;">
    	<input type="s-box" class="form-control" id="s-box" placeholder="Search for.." style="margin-right:15px; border-radius: 1rem; width:300px;">
  		<button type="submit" class="btn btn-primary mb-2" style="border-radius: 1rem; margin-top:5px;">Search</button>
	</form>
</div>

</div>




	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	</div>
	
	</div>
	
</body>
</html>
