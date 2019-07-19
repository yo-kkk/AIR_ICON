<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>이전 여행·AirIcon</title>
   <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	

	
	<!-- Css file href -->
	
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<!-- CSS file href -->
	<link rel="stylesheet" href="../css/profilecss.css">
	
<jsp:include page="menutrip.jsp" flush="false"/>
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
<div class="container-fluid"> <!-- 1class -->
	<div class="row"> <!-- 2class -->
		<div class="col-md-12"> <!-- 3class -->
			<div class="tabbable" id="tabs-248108">
			
				
				<div class="tab-content">
					
					<div class="tab-pane active" id="tab1">
						
						

						
						<div id="div5">
						
						
							<div class="container-fluid">
	<div class="row"> <!-- row -->
		<div class="col-md-5">
			<table class="table" style="width:900px;"> <!-- table -->
			<thead>
			<tr align="center">	
						<th>숙소 이름</th>
						<th>호스트 이름</th>
						<th>사용 날짜</th>
						<th>가격</th>
						<th>후기</th>
					</tr>
			</thead>
				<tbody>
	<c:forEach items="${list}" var="dto">
	<tr align="center">
	 
		<input style="display:none;" id="send_acom_no" value="${dto.acom_no}"/>
		<input style="display:none;" id="send_acom_reserv_no" value="${dto.acom_reserv_no}"/>
		<td><a href="../detail/detail_acom?acom_no=${dto.acom_no}" style="color:#f78181">${dto.intro_title}</a></td>
		<td>${dto.host_id}</td>
		<td>${dto.reserv_date_start} ~ ${dto.reserv_date_end}</td>
		<td><fmt:formatNumber value="${dto.price}" pattern="\#,###"/></td>
		
		
	<td>
	
	<c:choose>
		<c:when test="${dto.reviewCheck==1}">
			<font color="#65bd81"><b>작성 완료</b></font>
		</c:when>
		<c:otherwise>
			<button type="button" id="reviewbtn" class="reviewbtn" style="font-weight:bolder; color:#F78181; background-color: rgba(255, 0, 0, 0); border:0;"  data-toggle="modal" data-target="#reviewModal" onclick="nosend('${dto.acom_no}','${dto.acom_reserv_no}')">작성하기</button>
		</c:otherwise>		
	</c:choose>
		
	</td>
	
	</tr>
	</c:forEach>
	
	<tr align="center" >
		<td colspan="5">
		  <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		
		    <li class="page-item<c:if test="${startBlock==1}"> disabled</c:if>">
		      <a class="page-link" href="trip_2?pg=${startBlock - 1}" tabindex="-1" aria-disabled="true">Previous</a>
		    </li>
		    
			<c:forEach var="p" begin="${startBlock}" end="${endBlock}">
			    <li class="page-item <c:if test="${p == pg}"> active</c:if>">
			    	<a <c:if test="${p != pg}"> href="trip_2?pg=${p}" </c:if> class="page-link">${p}</a>
			    	   <c:if test="${p == pg}"><span class="sr-only">(current)</span></c:if>
			    </li>
			</c:forEach>
		    
		    <li class="page-item<c:if test="${endBlock == totalPage}"> disabled</c:if>">
		      <a class="page-link" href="trip_2?pg=${endBlock + 1}">Next</a>
		    </li>
		  </ul>
		  </nav>
		</td>
	</tr>


				</tbody>
			</table><!-- table -->
</table><!-- table -->
		</div><!--  -->
	</div><!-- col-md-12 -->
</div><!-- row -->
	</div> <!-- container-fluid -->
						
					</div> <!-- div2 -->
					</div>
				

					
				</div>
			</div>
		</div>
	</div> <!-- 2class -->
 <!-- 1class -->

<div>
<hr/>
</div>
 
 <!-- review modal -->
 <div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="header" style="padding-left:230px;">REVIEW</h2>

      </div>
      <div class="modal-body">
        <form method="post" action="review">
          <div class="form-group" >
          		<input type="text" style="display:none;" id="modal_acom_no" name="modal_acom_no" />
          		<input type="text" style="display:none;" id="modal_acom_reserv_no" name="modal_acom_reserv_no" />
          </div>
          <div class="form-group">
            <label for="rate_label" class="col-form-label">별점</label>

  			<select class="form-control" id="rate" name="rate">
    			<option selected disabled hidden="true">별점</option>
    			<option value="1">1</option>
    			<option value="1.5">1.5</option>
    			<option value="2">2</option>
    			<option value="2.5">2.5</option>
    			<option value="3">3</option>
    			<option value="3.5">3.5</option>
    			<option value="4">4</option>
    			<option value="4.5">4.5</option>
    			<option value="5">5</option>
  			</select>
		</div>
		<div class="form-group">
            <label for="content_label" class="col-form-label">후기 내용</label>
			<textarea class="form-control" rows="4" name="content" placeholder="내용 입력"></textarea>	
        </div>
          	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">작성하기</button>
      		</div>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
 
 <script type="text/javascript">

 function nosend(no,no2){

	 $('#modal_acom_no').val(no);
	 $('#modal_acom_reserv_no').val(no2);

} 

 
 
 </script>
 
</body>
 
</html>

