<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

<title>Insert title here</title>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- CSS file href -->
	<link rel="stylesheet" href="css/profilecss.css">
	<!-- CSS file href -->
</head>





<title>로그인</title>



<div class="container-fluid"> <!-- 1class -->
	<div class="row"> <!-- 2class -->
		<div class="col-md-12"> <!-- 3class -->
			<div class="tabbable" id="tabs-248108">
			
				
				<div class="tab-content">
					
					<div class="tab-pane active" id="tab1">
						
						로그인
						<div>

						<form action="result.jsp" method="post">
						<div id="div2">
							<div class="container-fluid">
	<div class="row"> <!-- row -->
		<div class="col-md-4">
			<table class="table"> <!-- table -->
	<tbody>







<body>
<form action="login_action.jsp" method="post">
<!-- <table border="1"> -->







<tr>
	<th>아이디</th>
	<div class="form-group">
	<td><input type="text" class="form-control" name="user_id" placeholder="아이디"  required autofocus></td>
</td>
</tr>







<tr>
	<th>비밀번호</th>
	<div class="form-group">
	<td><input type="password" class="form-control" name="password" placeholder="비밀번호" required></td>
</tr>











<tr>
<td colspan="2" align="center">
<br/>
    <input type="submit" id="btn2" name="btn2" value="회원가입" onclick="location='regist.jsp'" > 
    <input type="button" id="btn2" name="btn2" value="로그인">
</td>
</tr>   





</table>

</form>
		
				</tbody>
			</table><!-- table -->
		</div><!--  -->
	</div><!-- col-md-12 -->
</div><!-- row -->
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
 
</body>
 
</html>