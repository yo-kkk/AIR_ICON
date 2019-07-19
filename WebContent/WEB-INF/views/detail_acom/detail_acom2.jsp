<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ include file="search.jsp" --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Insert title here</title>

<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- CSS file href -->

	<!-- CSS file href -->




</head>
<body>
			<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>

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
	<div class="col-md-6">
		<table class="table"> <!-- table -->
	
			<tbody>
			<c:forEach items="${imagesList}" var="imageDto">
				<tr>
					<th>
						사진
					</th>
					<td><!-- 이미지 크기 줄여야됨.... -->
						<div class="row"><!-- 숙소 이미지 -->
						<c:if test=""></c:if>
						<img src="" class="back_img">
						<img src="${imageDto.route+'.'+imageDto.extension}" class="image_padding" width=200px, height=200px,>
						<!--  <img src="images/ij.jpg" class="image_padding" width=200px, height=200px>
						<img src="images/ys2.jpg" class="image_padding" width=200px, height=200px>
						<img src="images/sg.png" class="image_padding" width=200px, height=200px>-->
						</div>
					</td>
				</tr>
			</c:forEach>
					<tr>
					<th>
						숙소 설명
					</th>
					<td>
						<div class="form-group">
						${acom_infoDTO.intro_cont}
    					</div>
					</td>
				
					</tr>
					<tr>
					<th>
						편의 시설
					</th>
					<td>
						<div class="form-group">
						<div class="row">	
			<div class="col">
				
				<c:if test="${acom_infoDTO.kitchen=='y'}"><h5>주방</h5></c:if>
				<c:if test="${acom_infoDTO.parking=='y'}"><h5>주차장</h5></c:if>
				<c:if test="${acom_infoDTO.toiletries=='y'}"><h5>화장실</h5></c:if>
				<c:if test="${acom_infoDTO.elevator=='y'}"><h5>엘레베이터</h5></c:if>
				<c:if test="${acom_infoDTO.wifi=='y'}"><h5>와이파이</h5></c:if>
			
				<c:if test="${acom_infoDTO.tv=='y'}"><h5>TV</h5></c:if>
				<c:if test="${acom_infoDTO.aircond=='y'}"><h5>에어컨</h5></c:if>
				<c:if test="${acom_infoDTO.hotwater=='y'}"><h5>온수</h5></c:if>
				<c:if test="${acom_infoDTO.washer=='y'}"><h5>세탁기</h5></c:if>
			</div>
		</div>
    				</div>
					</td>
				
					</tr>
					
					<c:forEach items="${list}" var="dto">
				<tr>
					<th>
						후기
					</th>
					
					<td>
						<div class="form-group">
						${dto.user_id} ${dto.content}
   						</div>
					</td>
					<td>
						<div>
							<c:if test="${dto.user_id == sessionScope.sessionID}">
								<a href="#">[수정]</a>
								<a href="#">[삭제]</a>
							</c:if>
						</div>
					</td>
				</tr>
					</c:forEach>
					<tr>
					<th>
						예약 가능 여부
					</th>
					<td>
						<div class="form-group">
						
   					<input type="date" class="form-control" id="birth" name="birth" placeholder="생년월일" required>
 					<button type="button" id="loginbtn" class="btn btn-primary" data-toggle="modal" data-target="#reservModal">예약</button>

 					
       			</div>
					</td>
					</tr>
					
					<tr>
					<th>
						주소
					</th>
					<td>
						<div class="form-group">
    			${acom_infoDTO.address}
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
 

 <!-- RESERV -->
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
          <div class="form-group">
            <label for="checkin_label" class="col-form-label">체크인</label>
   			<input type="date" class="form-control" id="checkin" name="checkin" placeholder="체크인" required>
			<label for="checkout_label" class="col-form-label">체크아웃</label>
   			<input type="date" class="form-control" id="checkout" name="checkout" placeholder="체크아웃" required>
          </div>
          <div class="form-group">
            <label for="a" style="float:left;">인원</label>
  					<select class="form-control" id="person" name="person">
   						<option selected disabled hidden="true">인원</option>
   						<option value="1">1</option>
    					<option value="2">2</option>
    					<option value="3">3</option>
    					<option value="4">4</option>
  					</select>
          </div>
          <div class="form-group">
            <label for="a" style="float:left;">호스트에게 전할 메시지</label>				
			<textarea class="form-control" rows="4" name="etc" placeholder="내용 입력"></textarea>	
          </div>
          <div class="form-group">
          	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">예약하기</button>
      		</div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
 
 
 
</body>
 
</html>

