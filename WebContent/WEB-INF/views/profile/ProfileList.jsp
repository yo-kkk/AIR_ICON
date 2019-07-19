<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>프로필 수정·AirIcon</title>
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
</script> 
<body>
 <jsp:include page="../top_banner/search_bar.jsp" flush="false"/>
<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
<jsp:include page="menu.jsp" flush="false"/>

<form  method="post" action="profilelistaction">
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
		<div class="col-md-4">
		
			<table  style="width:400px ;height:700px;"> <!-- table -->
		
				<tbody>
				
						<tr>
						<th width="100px">
							아이디
						</th>
						<td width="300">
						
							<div class="form-group">
        			<input type="text" class="form-control" id="id" name="id" maxlength="20" placeholder="${usersDTO.user_id}" disabled="disabled" >
        			
       			</div>
						</td>
					</tr>
					
					
					<tr>
						<th>
							이름
						</th>
						<td>
							<div class="form-group">
        			<input type="text" class="form-control" id="name" name="name" maxlength="50" value="${usersDTO.name}" placeholder="이름" required autofocus>
       			</div>
						</td>
					</tr>
						<tr>
						<th>
							성별
						</th>
						<td>
							
							<div class="form-group">
        			<input type="text" class="form-control" id="gender" name="gender" placeholder="${usersDTO.gender}" disabled="disabled" >
        			
       			</div>
						</td>
					</tr>
						<tr>
						<th>
							비밀번호
						</th>
						<td>
							<div class="form-group">
        			<input type="password" class="form-control" id="password" name="password" maxlength="30" placeholder="비밀번호" required >
       			</div>
						</td>
					
						</tr>
						<tr>
						<th >
							비밀번호확인
						</th>
						<td>
							<div class="form-group">
        			<input type="password" class="form-control" id="passwordcheck" name="passwordcheck" maxlength="30" placeholder="비밀번호 확인" required >
       			</div>
						</td>
					
						</tr>
						<tr>
						<th>
							이메일
						</th>
						<td>
							<div class="form-group">
        			<input type="email" class="form-control" id="email" name ="email" maxlength="100" value="${usersDTO.email}" placeholder="이메일" required >
       			</div>
						</td>
					
						</tr>
						<tr>
						<th>
							전화번호
						</th>
						<td>
							<div class="form-group">
        			
       				<input type="text" class="form-control" id="tel" name="tel" maxlength="30" value="${usersDTO.tel}" placeholder="전화번호" required="required" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' " />
       			</div>
						</td>
					
						</tr>
						<tr>
						<th>
							주소
						</th>
						<td>
							<div class="form-group">
        			<input type="text" class="form-control" id="address" name="address" maxlength="320" value="${usersDTO.address}" placeholder="주소" required >
       			</div>
						</td>
					
						</tr>
						<tr>
						<th>
							생년월일
						</th>
						<td >
							<div class="form-group">
        			<input type="date" class="form-control" id="birth" name="birth" value="${usersDTO.birth}" placeholder="생년월일" required >
       			</div>
						</td>
					
						</tr>
						<tr>
						<td colspan="2" align="right">
						<br/>
					<input type="submit" id="btn1" name="btn1"  value="저장" />
							
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

