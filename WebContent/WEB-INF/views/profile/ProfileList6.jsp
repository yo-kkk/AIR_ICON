<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>나의 숙소 관리·AirIcon</title>
  <!-- Bootstrap CSS -->
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
	<!-- Css file href -->
	
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
<!-- CSS file href -->
	<link rel="stylesheet" href="../css/profilecss.css">
</head>
 <script>
<!-- 숫자만 입력받기 -->
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


function KeyInput(mode){ 
	if(mode==0){ 
	if(document.fo.location1.value.length ==4) 
	document.fo.location2.focus(); 
	}else if(mode==1){ 
	if(document.fo.test2.value.length ==4) 
	document.fo.test3.focus(); 
	} 
	} 
</script> 
<body>
 <jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
<jsp:include page="menu.jsp" flush="false"/>
<div class="container-fluid"> <!-- 1class -->
	<div class="row"> <!-- 2class -->
		<div class="col-md-12"> <!-- 3class -->
			<div class="tabbable" id="tabs-248108">
			
				
				<div class="tab-content">
					
					<div class="tab-pane active" id="tab1">
						
						
	
						
						<div id="div5">
							<div class="container-fluid">
	<div class="row"> <!-- row -->
		<div class="col-md-7">
		
			<table class="table" style="width:1000px;"> <!-- table -->
			<thead>
			<tr align="center">
						<th>
							#
						</th>
						
						<th>
							숙소명
						</th>
						<th>
							주소
						</th>
						
						<th>
							타입
						</th>
						
							<th>
							가격
						</th>
					
						<th>
							수정
						</th>
					</tr>
			</thead>
				<tbody>
				<c:forEach items="${acom_infoList}" var="list"  >
					<tr align="center">
						<td>
							${list.acom_no}
						</td>
						
						<td>
					<a href="../detail/detail_acom?acom_no=${list.acom_no}" style="color:#f78181">${list.intro_title}</a>
						</td>
						<td>
							${list.address}
						</td>
						
						<td>
							${list.type}
						</td>
						
						<td>
						<fmt:formatNumber value="${list.price}" pattern="\#,###"/>/박
						</td>
							
						<td>
							<a href="acom_update?acom_no=${list.acom_no}" >수정</a>
							
							<button type="button" id="btn3"  data-toggle="modal" data-target="#acom_deleteModal" onclick="nosend('${list.acom_no}')">삭제</button>
							<a href="addImg?acom_no=${list.acom_no}" >사진 추가</a>
						</td>
					</tr>
					
					<!-- modal login -->
<div class="modal fade" id="acom_deleteModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="header" style="padding-left:180px; ">숙소 삭제</h2>      
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>      
      </div>
      <div class="modal-body" >
        <form method="post" action="../profile/acom_delete" style="width:400px; margin:0 auto;">
       	<div class="form-group" >
          		<input type="text" style="display:none;" id="modal_acom_no" name="modal_acom_no" />
          </div>
          <div class="form-group" align="center">
            <label for="passwd_label" class="col-form-label"  ><b>PASSWORD</b></label>
            <input type="password" class="form-control" id="passwd" name="passwd" >
          </div>
     	<div style="margin-bottom:30px;" align="center">
			<button type="submit" id="btn4">삭제하기</button>
			
      		</div>
        </form>
      </div>

    </div>
  </div>
</div>
				</c:forEach>
	<tr>
		<td colspan="7" align="center">
		  <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		
		    <li class="page-item<c:if test="${startBlock==1}"> disabled</c:if>">
		      <a class="page-link" href="profilelist6?pg=${startBlock - 1}" tabindex="-1" aria-disabled="true">Previous</a>
		    </li>
		    
			<c:forEach var="p" begin="${startBlock}" end="${endBlock}">
			    <li class="page-item <c:if test="${p == pg}"> active</c:if>">
			    	<a <c:if test="${p != pg}"> href="profilelist6?pg=${p}" </c:if> class="page-link">${p}</a>
			    	   <c:if test="${p == pg}"><span class="sr-only">(current)</span></c:if>
			    </li>
			</c:forEach>
		    
		    <li class="page-item<c:if test="${endBlock == totalPage}"> disabled</c:if>">
		      <a class="page-link" href="profilelist6?pg=${endBlock + 1}">Next</a>
		    </li>
		  </ul>
		  </nav>
		</td>
	</tr>

				</tbody>
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
 
  <script type="text/javascript">

 function nosend(no){

	 $('#modal_acom_no').val(no);

} 
 
 
 </script>
 
 
 
 
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
 
</html>

