<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/top_not_login.css">


	<!-- 전체 Top 1024x100 -->
	<div class="top_not_login">
	
	<!-- AirIcon Banner -->
	<div class="airicon_button">
		<h2>
     		<a class="airicon_font font-weight-light" href="../home/home">AirIcon</a>
    	</h2>
    </div>
    
    <!-- Right Banner -->
    <div class="banner_button">
    	<button type="button" id="loginbtn" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">Login</button>
    	<button type="button" id="signupbtn" class="btn btn-primary" data-toggle="modal" data-target="#signupModal">Sign up</button>
    </div>
	
	
	</div>
	<!-- 전체 Top End -->

<!-- modal login -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="header" style="padding-left:190px; font-family: 'Roboto Slab', serif;">Login</h2>      
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>      
      </div>
      <div class="modal-body" >
        <form method="post" action="../userInfo/loginAction" style="width:400px; margin:0 auto;" autocomplete="off">
         <input type="hidden" name="_action" value="${_action}"/>
          
          <div class="form-group">
            <label for="user_id_label" class="col-form-label" style=" font-family: 'Roboto Slab', serif;">ID</label>
            <input type="text" class="form-control" id="user_id" name="user_id">
          </div>
          <div class="form-group">
            <label for="passwd_label" class="col-form-label" style=" font-family: 'Roboto Slab', serif;">PASSWORD</label>
            <input type="password" class="form-control" id="passwd" name="passwd" >
          </div>
     	<div style="margin-bottom:30px;">
			<button type="submit" class="submitbtn" style="margin-top:20px;">로그인</button>
      		</div>
      	
        </form>
        <div class="form-group" style="text-align:center;">
      		아직 회원이 아니신가요? <a data-dismiss="modal" data-toggle="modal" data-target="#signupModal" href="#signupModal" style="font-weight:bolder; color:#F78181;">가입하기</a>
         </div>
      </div>

    </div>
  </div>
</div>

<!-- sign up login -->
<div class="modal fade" id="signupModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="header" style="padding-left:170px; font-family: 'Roboto Slab', serif;">Sign Up</h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="post" action="../userInfo/signupaction" style="width:400px; margin:0 auto;" autocomplete="off">
          <div class="form-group">
            <input type="text" class="form-control" id="id" name="id"  maxlength="20" required="required" autofocus="autofocus" placeholder="아이디">
          </div>
          <div class="form-group">
            <input type="text" class="form-control" id="name" name="name" maxlength="50" required="required" placeholder="이름">
          </div>
          <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" maxlength="30" required="required" placeholder="비밀번호">
          </div>
          <div class="form-group">
            <input type="password" class="form-control" id="passwordcheck" name="passwordcheck" maxlength="30" required="required" placeholder="비밀번호 확인">
          </div>
          <div class="form-group">
            <input type="text" class="form-control" id="tel" name="tel" maxlength="30" required="required" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' placeholder="전화번호">
          </div>
          <div class="form-group">
            <input type="text" class="form-control" id="address" name="address" maxlength="320" required="required" placeholder="주소">
          </div>
          <div class="form-group">
            <input type="email" class="form-control" id="email" name="email" maxlength="100" required="required" placeholder="이메일">
          </div>
          <div class="form-group">
            <label for="user_id_label" class="col-form-label">성별&nbsp;&nbsp;</label>
			<input type="radio" name="chk_info" value="m" checked="checked"/>
            <label for="user_id_label" class="col-form-label">남자</label>
            <input type="radio" name="chk_info" value="f"/> 
            <label for="user_id_label" class="col-form-label">여자</label>
			        
          </div>
          <div class="form-group">
            <label for="passwd_label" class="col-form-label">생일</label>
            <input type="date" class="form-control" name="birth" style="border-radius: 2rem;" placeholder="연도/월/일">
          	<div style="margin-top:30px; margin-bottom:30px;">
			<button type="submit" class="submitbtn" style="margin-top:20px;">회원가입</button>
      		</div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<script>

	
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
	</script>
