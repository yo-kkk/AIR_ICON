<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- Css file href -->
	<link rel="stylesheet" href="/css/search_bar.css">	
	<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
	<form class="form-inline" method="post" action="../search/searchviewnew" autocomplete="off">	
	<div class="search_bar">
	<input type="text" class="form-control" style="display:none;" name="new" value="new">
    	<input type="text"   class="form-control" style=" border-radius	 : 2rem;  width:300px; height:37px;"
    	
    	 placeholder="숙소를 검색해보세요" name="location" >
		<button type="submit" id="searchbarbtn" class="btn" style="border-radius  : 2rem; border:0;
	background-color:#F78181; color:white;">검색</button>
	</div>	
	</form>

