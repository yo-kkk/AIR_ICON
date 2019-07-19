 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">    
   <link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>만족스러운 여행을 원하십니까?·AirIcon</title>
   
   <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

   <!-- Google Fonts -->
   <link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
   
   
	<link rel="stylesheet" href="../css/searchview.css">
</style>
</head>

<style>

*{
	font-family: 'Nanum Gothic', sans-serif;
}

.menubar li ul {
background-color: white;
display:none;  /* 평상시에는 서브메뉴가 안보이게 하기 */
height:auto;
padding:0px;
margin:0px;
border:0px;
position:absolute;
width:200px;
z-index:200;
}

.menubar li:hover ul {
display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
margin-top:0;
}
</style>

<body>

<c:choose>
      <c:when test="${user_id == null}">
         <jsp:include page="../top_banner/top_not_login.jsp" flush="false"/>
         <jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
         <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
      </c:when>
      <c:otherwise>
      	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
         <jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
         <jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
        
      </c:otherwise>
</c:choose>

<hr>
   

   
   
   <div class="dropdown_box" id="conv3">
			<ul class="nav" id="top_with_login_ul">	
				
				<li class="nav-item dropdown top_li">
					<a class="nav-link dropdown-toggle" href="http://example.com" data-toggle="dropdown"> 편의시설</a>
					<div class="dropdown-menu">
						<form action="searchviewfilter" method="post">
						<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc1" name="inlineCeckbox1" value="kitchen"/> 
     			<label class="custom-control-label" for="cc1">
            	주방
     			</label>
     			
					</div>
			
			<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc2" name="inlineCeckbox1" value="parking"/> 
     			<label class="custom-control-label" for="cc2">
            	주차장
     			</label>
     			
					</div>
			
			<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc3" name="inlineCeckbox1" value="toiletries"/> 
     			<label class="custom-control-label" for="cc3">
            	화장실
     			</label>
     			
					</div>
					<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc4" name="inlineCeckbox1" value="elevator"/> 
     			<label class="custom-control-label" for="cc4">
            	엘리베이터
     			</label>
     			
					</div>
					
					<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc5" name="inlineCeckbox1" value="tv"/> 
     			<label class="custom-control-label" for="cc5">
            	TV
     			</label>
     			
					</div>
					
					<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc6" name="inlineCeckbox1" value="aircond"/> 
     			<label class="custom-control-label" for="cc6">
            	에어컨
     			</label>
     			
					</div>
					
					<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc7" name="inlineCeckbox1" value="hotwater"/> 
     			<label class="custom-control-label" for="cc7">
            	온수
     			</label>
     			
					</div>
					
					<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc8" name="inlineCeckbox1" value="washer"/> 
     			<label class="custom-control-label" for="cc8">
            	세탁기
     			</label>
     			
					</div>
					
					<div class="custom-control custom-checkbox">
     			<input type="checkbox"  class="custom-control-input"  id="cc9" name="inlineCeckbox1" value="wifi"/> 
     			<label class="custom-control-label" for="cc9">
            	와이파이
     			</label>
     			
					</div>
					
					<input type="submit" id="btn4" value="적용">
			
					</form>
					</div>
				</li>
			</ul>
		</div>
   
   
  

</table>
</div>


<center>
		<h3 style="letter-spacing: -2px; font-weight:bolder;">검색된 결과가 없습니다</h3>
      <img src="/images/failimg.png" style="margin:0 auto;"/>
</center>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<!--  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>-->	
   
</body>
</html>