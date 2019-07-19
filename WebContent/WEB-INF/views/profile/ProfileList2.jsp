<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
 
<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>결제 정보·AirIcon</title>
  <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
	<!-- Css file href -->
	
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
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
<form name="myform" method="post" action="profilelist2action">
<div class="container-fluid"> <!-- 1class -->
	<div class="row"> <!-- 2class -->
		<div class="col-md-12"> <!-- 3class -->
			<div class="tabbable" id="tabs-248108">
			
				
				<div class="tab-content">
					
					<div class="tab-pane active" id="tab1">
						
						
						<div>
						
						<div id="div2">
							<div class="container-fluid">
	<div class="row"> <!-- row -->
		<div class="col-md-5">
			<table class="table" style="width:600px;"> <!-- table -->
		
				<tbody>
				
					<tr>
						<th>
							카드 번호
						</th>
						


				<td  align="left">
        		<input type="text" class="form-control" maxlength="4"  style="width:80px; ime-mode:disabled;" id="location1" name="location1" value="${usersDTO.card_num_sub[0]}" placeholder="4자리" required autofocus="autofocus" onkeydown='return onlyNumber(event)' onkeyup="removeChar(event);" />
        		</td>
        		<td align="left">
        		<input type="text" class="form-control" maxlength="4" style="width:80px; ime-mode:disabled;" id="location2" name="location2" value="${usersDTO.card_num_sub[1]}"   placeholder="4자리" required onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/>
       			</td>
       			<td align="left">
        		<input type="text" class="form-control" maxlength="4" style="width:80px; ime-mode:disabled;" id="location3" name="location3" value="${usersDTO.card_num_sub[2]}"  placeholder="4자리" required onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/>
       			</td>
       			<td align="left">
        		<input type="text" class="form-control" maxlength="4" style="width:80px; ime-mode:disabled;" id="location4" name="location4" value="${usersDTO.card_num_sub[3]}"  placeholder="4자리" required onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/>
       			</td>
       			
       			
						</tr>
					
						
						<tr>
						<th>
							비밀번호
						</th>
						<td align="left">
							
        			<input type="password" class="form-control" style="width:80px; ime-mode:disabled;"maxlength="2" id="password" value="${usersDTO.card_password}" name="password" placeholder="2자리" required onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' />
       		
						</td>
						<td colspan="3" align="left">
						●●
						</td>
						</tr>
						<tr>
						<th>
							유효기간
						</th>
						
							<td colspan="4" align="left">
        			
						<input type="month" class="form-control" style="width:180px; ime-mode:disabled; "  "  id="card_date" value="${usersDTO.card_date}" name="card_date" placeholder="2" required onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' />
						</td>

						</tr>
						<tr>
						<th>
							CVC
						</th>
					
						<td colspan="4" align="left">
        			<input type="password" class="form-control" maxlength="3" style="width:80px; ime-mode:disabled;" id="card_cvc" name="card_cvc"  value="${usersDTO.card_cvc}" placeholder="cvc" required onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' />
       			
						</td>
					
						</tr>
						<tr>
						<td colspan="5" align="right">
						<input type="submit" id="btn1" name="btn2"  value="저장" />
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
</div> <!-- 1class -->

<div>
<hr/>
</div>
 </form>
</body>
 
</html>

