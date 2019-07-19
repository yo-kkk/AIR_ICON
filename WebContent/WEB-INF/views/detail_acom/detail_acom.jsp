<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%--@ include file="search.jsp" --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>${acom_infoDTO.intro_title}·AirIcon</title>

<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:700" rel="stylesheet">

	
	<!-- Css file href -->
		

<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<!-- CSS file href -->
<link rel="stylesheet" href="../css/detail_acom.css">

<!-- DatePicker  -->
 	    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>

	<link rel="stylesheet" href="/css/jquery-ui.min.css">

    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

    <script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>



    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">

    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>

    <script src="/js/bootstrap-datepicker.kr.js" charset="UTF-8"></script>



<script>

$(document).ready(function() {

	
	var floatPosition = parseInt($("#floatMenu").css('top'));
	

	$(window).scroll(function() {
		
		var scrollTop = $(window).scrollTop();
		var newPosition = scrollTop + floatPosition + "px";

		

		$("#floatMenu").stop().animate({
			"top" : newPosition
		}, 500);

	}).scroll();

});

</script>


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
	
	var disabledDays = new Array();
	var disabledDaysout = new Array();
	
    <c:forEach items="${dateAll}" var="d">
    disabledDays.push("${d.resrvDateAll}");
    </c:forEach>
    
   
	
	$(function(){
        $('.input-group.date.in').datepicker({

        	
            calendarWeeks: false,
            todayHighlight: true,
            autoclose: true,
            format: "yyyy-mm-dd",
            language: "kr",
            startDate: new Date(),
            datesDisabled :disabledDays


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
            language: "kr",
            datesDisabled :disabledDays         
        });
        
	 });

	

	</script>


</head>
<body>

			<jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
			<c:choose>
		<c:when test="${user_id == null}">
			<jsp:include page="../top_banner/top_not_login.jsp" flush="false"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
		</c:otherwise>		
	</c:choose>

<div>
<table id="table1">

<tr>
	<c:if test="${file1!=null}">
		<td colspan="2" id="table_td1" >
		<img src="../Download?fileName=${file1}" alt="" width="100%" height="600">
		</td>
	</c:if>
	<c:if test="${file1==null}">
		<td colspan="2" id="table_td1" >
		<img src="../Download?fileName=noImage.png" alt="" width="100%" height="600">
		</td>
	</c:if>
</tr>
<tr>
	<c:if test="${file2!=null}">
		<td id="table_td1">
		<img src="../Download?fileName=${file2}" alt="" width="100%" height="400px">
		</td>
	</c:if>
	<c:if test="${file2==null}">
		<td id="table_td1">
		<img src="../Download?fileName=noImage.png" alt="" width="100%" height="400px">
		</td>
	</c:if>

	<c:if test="${file3!=null}">
		<td id="table_td1">
		<img src="../Download?fileName=${file3}" alt="" width="100%" height="400px">
		</td>
	</c:if>
	<c:if test="${file3==null}">
		<td id="table_td1">
		<img src="../Download?fileName=noImage.png" alt="" width="100%" height="400px">
		</td>
	</c:if>
</tr>

</table>

</div>
<div class="container-fluid"> <!-- 1class -->
	<div class="row"> <!-- 2class -->
		<div class="col-md-12"> <!-- 3class -->
			<div class="tabbable" id="tabs-248108">
			
				
				<div class="tab-content">
					
					<div class="tab-pane active" id="tab1">
						
						<!-- 원래 옆에 탭 위치였으나 잠시 뺐어요 -->
						<div>

						<form action="result.jsp" method="post">
						<div id="div2">
							<div class="container-fluid">
<div class="row"> <!-- row -->
	<div class="col-md-12">
		<table class="table"> <!-- table -->
	
			<tbody>
				<tr>
					<td>
					
					<h1><b>${acom_infoDTO.intro_title}</b>
					<c:choose>
      	<c:when test="${user_id != null}"><a href="../search/bookmark?acom_no=${acom_infoDTO.acom_no}" style="color:#f2c921">★</a> </c:when></c:choose></h1>
					<h6><b>호스트 ${acom_infoDTO.host_id}님</b></h6>
					</td>
				</tr>
			
					<tr>
					
					<td>
						<div class="form-group">
							<div>
							<h5><b>숙소 설명</b></h5>
								${acom_infoDTO.intro_cont}
							</div>
    					</div>
					</td>
				
					</tr>
					
					<tr>
					
					<td>
						
				<%int i=1;%>
				<h5><b>편의 시설</b></h5>
				<table>
				<tr>
				<c:if test="${acom_infoDTO.kitchen=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/kitchen.png" alt="" ><br/>주방</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.parking=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/parking.png" alt="" ><br/>주차장</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.toiletries=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/essentials.png" alt="" ><br/>화장실</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.elevator=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/elevator.png" alt="" ><br/>엘레베이터</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.wifi=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/wireless-internet.png" alt="" ><br/>와이파이</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
			
				<c:if test="${acom_infoDTO.tv=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/tv.png" alt="" ><br/>TV</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.aircond=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/air-conditioning.png" alt="" ><br/>에어컨</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.hotwater=='y'}">	<td align="center"><div id=cdiv><img src="../detail_images/hotwater.PNG" alt="" ><br/>온수</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
				<c:if test="${acom_infoDTO.washer=='y'}"><td align="center"><div id=cdiv><img src="../detail_images/washer.png" alt="" ><br/>세탁기</div></td><%if(i%4==0){%></tr><tr><%}i++;%></c:if>
		</tr></table>
					</td>
				
					</tr>
					
					<c:set var="doneLoop" value="false"/>
				
				<tr >
					<td>
					<h5><b>후기</b></h5>
					<c:forEach items="${list}" var="dto" varStatus="status">
						  <c:if test="${not doneLoop}">
						 
						<h6><b>${dto.user_id}님</b></h6>
							 <font color="#5c5c5c" size="2">${dto.reg_date}</font><br/>
						${dto.content}<br/><br/>
						 <c:if test="${status.count==2}"> <button type="button" id="reviewbtn"  data-toggle="modal" data-target="#reviewModal">후기 ${reviewCount}개모두보기</button></c:if>
						  <c:if test="${status.count ==2}">
						<c:set var="doneLoop" value="true"/>
						</c:if>
						</c:if>
   						 
					</c:forEach>
					</td>
				</tr>
				
			
					<tr>
				
					<td>
						<h5><b>참고사항</b></h5>
						<div class="form-group">
    					${acom_infoDTO.intro_etc}
   						</div>
					</td>
					</tr>
					
					
					<tr>
					<td>
					<h5><b>주소</b></h5>
						<div class="form-group">
    						${acom_infoDTO.address}
   						</div>
					</td>
					</tr>
					
					<tr>
					<td>
					<h5><b>교통편</b></h5>
						<div class="form-group">
    						${acom_infoDTO.intro_pubtrans}
   						</div>
					</td>
					</tr>
					

			</tbody>
		</table><!-- table -->
	</div><!--  -->
</div><!-- col-md-12 -->
<!-- row -->
</div> <!-- container-fluid -->
					
				</div> <!-- div2 -->
				</form>
				</div>
				
			

				
			</div>
		</div>
	</div>
	</div> <!-- 2class -->
</div> <!-- 1class -->

<div>
<hr/>
</div>
 


 <!-- RESERV modal 
<div class="modal fade" id="reservModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="header" style="padding-left:200px;">예약</h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="post" action="acom_resrv_action">
        <div class="form-group" >
          		<input type="text" style="display:none;" id="modal_acom_no" name="modal_acom_no" />
          </div>
          <div class="form-group">
            <label for="checkin_label" class="col-form-label">체크인</label>
   			<input type="date" class="form-control" id="checkin" name="checkin" placeholder="체크인" required>
			<label for="checkout_label" class="col-form-label">체크아웃</label>
   			<input type="date" class="form-control" id="checkout" name="checkout" placeholder="체크아웃" required>
          </div>
          <div class="form-group">
            <label for="a" style="float:left;">인원</label>
  						<input type="number" class="form-control" id="person" name="person" min=1 max=${acom_infoDTO.maxperson} placeholder="인원 (명)" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'>
          </div>
          <div class="form-group">
            <label for="a" style="float:left;">호스트에게 전할 메시지</label>				
			<textarea class="form-control" rows="4" name="etc" placeholder="내용 입력"></textarea>	
          </div>
          <div class="form-group">
          	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="submit" class="resrvbtn">예약하기</button>
      		</div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
 
 -->
 
 <c:choose>
		<c:when test="${user_id != null&&!(acom_infoDTO.host_id eq user_id)}">
		
		<div id="floatMenu" >
		<div id="resrv2">
		<form name="myform" method="post" action="acom_resrv_action" autocomplete="off">
		<center>
		<h3><b>예약 가능 여부</b></h3>
		<h4><b><fmt:formatNumber value="${acom_infoDTO.price}" pattern="\#,###"/>/박</b></h4>
		</center>
		<hr/>
		
	
		  <input type="text" class="form-control" id="acom_no" name="acom_no" style="display:none;" value="${acom_infoDTO.acom_no}"/>
		
		<div class="form-group bottom_halfmargin">
  			<input type="number" class="form-control" id="person" min="1"  max="${acom_infoDTO.maxperson}" name="person" style="border-radius: 2rem;" placeholder="인원 (명)" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' required>
		</div>
		
		
		<h6>체크인</h6>
      	<div class="input-group date in">
            <input type="text" class="form-control" name="checkin" style="border-radius: 2rem;" placeholder="연도/월/일"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
        </div>
      	     		
        <h6 style="padding-top:10px;">체크아웃</h6>
        <div class="input-group date out">
            <input type="text" class="form-control" name="checkout" style="margin-bottom:30px; border-radius: 2rem;" placeholder="연도/월/일"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
        </div>						
			<button type="submit" id="resrvbtn" class="resrvbtn">예약하기</button>
		</form>
		
		<br/>
		<div class="input-group">
			<button type="button" id="msgbtn" class="resrvbtn" data-toggle="modal" data-target="#msgmodal">호스트에게 문의하기</button>
		</div>
	</div>
	
	<!-- modal 메시지보내기 -->
<div class="modal fade" id="msgmodal" tabindex="-1" role="dialog" style="margin-top: 120px">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="header" style="margin-left:40px;">메시지 내용을 작성해보세요</h2>      
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>      
      </div>
      <div class="modal-body" >
        <form method="post" action="detail_msg_action?acom_no=${acom_no}" style="width:360px; margin:0 auto;">
          <div class="form-group">
            <label for="user_id_label" class="col-form-label" style=" font-family: 'Roboto Slab', serif;">숙소 이름이나 주소등을 꼭 기입해서 문의해주세요</label>
            <textarea class="form-control" id="msg_content" name="msg_content" rows="10" required></textarea>
            
            <br/>
            <button type="submit" id="send_msg_btn" class="resrvbtn" style="margin-left:30px;">호스트에게 문의하기 </button>
          </div>      	
        </form>
      </div>

    </div>
  </div>
</div>

 	
		
		
		
		</div>
		</div>

	
		</c:when>
			
	</c:choose>
	
	 <!--review -->
	 
<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="header" >후기${reviewCount}개</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
          <div class="form-group">
           <c:forEach items="${list}" var="dto" varStatus="status">
						  
						<h6>${dto.user_id}님</h6>
							${dto.reg_date}<br/>
						${dto.content}<br/><hr/>
						 
   						 
					</c:forEach>
			
          </div>
          
      </div>
    </div>
  </div>
</div>
	
 <!--  
 <script type="text/javascript">

 function nosend(no){

	 $('#modal_acom_no').val(no);

} 
 
 
 </script>
 
 -->
 
</body>
 
</html>

