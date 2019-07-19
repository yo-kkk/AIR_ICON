<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html style="background-image:url('../images/main.jpg');">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">	
	
	 
<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>만족스러운 여행을 원하십니까?·AirIcon</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/main_intro.css">
	
	<!-- DatePicker  -->
 	    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>

	<link rel="stylesheet" href="/css/jquery-ui.min.css">

    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

    <script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>



    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">

    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>

    <script src="/js/bootstrap-datepicker.kr.js" charset="UTF-8"></script>


    <script type="text/javascript">
    //숫자만 입력받기
	function onlyNumber(event){
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( keyID==9||(keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
			return;
		else
			return false;
	}
	function removeChar(event) {
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ||keyID==9) 
			return;
		else
			event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
	
	 $(function(){

	        $('.input-group.date.in').datepicker({

	        	
	            calendarWeeks: false,
	            todayHighlight: true,
	            autoclose: true,
	            format: "yyyy-mm-dd",
	            language: "kr",
	            startDate: new Date()


	        })
	        .on('changeDate', function(selected){
	        	
	        	var startDate = new Date(selected.date.valueOf());
	        	startDate.setDate(startDate.getDate() + 1);
	        	$('.input-group.date.out').datepicker('setStartDate', startDate);
	        });
	        
	        
	        $('.input-group.date.out').datepicker({

	        	
	            calendarWeeks: false,
	            todayHighlight: true,
	            autoclose: true,
	            format: "yyyy-mm-dd",
	            language: "kr"
	            
	            
	        });


	    });
	 
	 //startDate: new Date(new Date().setDate($('.input-group.date.in').datepicker("getDate")+1))

	</script>


</head>
<body>
<div class="main_all">
	
	<div class="topbar"></div>
	<c:choose>
		<c:when test="${user_id == null}">
			<jsp:include page="../top_banner/top_not_login.jsp" flush="false"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
		</c:otherwise>		
	</c:choose>
	</div>
	<form name="myform" method="post" action="../search/searchview" autocomplete="off">
	
	<!-- Right Box -->
	<div class="form_center">
	<div class="form_box">
		<div class="bottom_margin">
    		<h2 class="intro" style="text-align: center;">에어 아이콘에서 독특한 <br/> 숙소들을 예약해보세요</h2>
    	</div>
    	
    	<div class="form-group">
        	<input type="text" class="form-control" id="location" name="location" placeholder="목적지" required autofocus>
       	</div>
				
		<div class="form-group bottom_halfmargin">
  			<input type="number" class="form-control" id="person" min="1" name="person" required placeholder="인원 (명)" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'>
		</div>
		
		<!-- 
		<h6>체크인</h6>
		<div class="form-group">				
   			<input type="date" class="form-control" id="startday" name="startday" required>
      	</div> 
      	
      	-->
      	<h6>체크인</h6>
      	<div class="input-group date in">
            <input type="text" class="form-control" name="startday" style="border-radius: 2rem;" required placeholder="연도/월/일"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
        </div>
      	       		
        <h6 style="padding-top:10px;">체크아웃</h6>
        <div class="input-group date out">
            <input type="text" class="form-control" name="endday" style="border-radius: 2rem;" required placeholder="연도/월/일"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
        </div>				
				
		<div class ="form-group" style="padding-top:20px;">
			<button class="btn-search" type="submit" >검색</button>
		</div>
	</div>
	
 	</form>

 	 </div>
 	 </div>
 	 
 	 
 

	
</body>
</html>

