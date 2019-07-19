<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>호스트 등록 수정중입니다 2/3·AirIcon</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/become_host_step02.css">
	
	<!-- img_미리보기 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

	<script type="text/javascript">
        $(function() {
            $("#imgInp").on('change', function(){
                readURL(this);
            });
        });
        
        function readURL(input) {
            if (input.files && input.files[0]) {
            	var reader = new FileReader();

    			reader.onload = function (e) {
                    $('#blah').attr('src', e.target.result);
            	}
            reader.readAsDataURL(input.files[0]);
            }
        }
   </script>
	
</head>
<body>

	<!-- AirIcon Banner -->
	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>

    
    <div class="step_title">
    	<h6 class="title_font"> 2단계 : 숙소의 상세정보를 등록해주세요.</h6>
    </div>
	
	<div class="progress_bar">
		<div class="progress_inner">
		</div>
	</div>


	<!-- main box -->
	<form name="myform" method="post" action="acom_update2_action">
	
	<div class="main_box">
		
		<div class="ask_02">
			<h4 class="intro_font"><li>숙소 이름 지정</li></h4>
			숙소의 특징과 장점을 강조하는 제목을 정해 게스트의 관심을 끌어보세요.
			
			<input type="text" class="form-control intro_title" name="intro_title" placeholder="숙소 제목" required
			value="${acom_infoUpdateDTO.intro_title}">
			
		</div>
		
		<div class="ask_03">
			<h4 class="intro_font"><li>게스트에게 숙소에 대해 설명해주세요.</li></h4>
			숙소에 대해 간략히 설명해주세요.<br/>
			숙소와 주변 지역에 대한 정보에서 시작해 <br/>
			게스트와 어떻게 소통하고 싶은지 등의 내용을 적어주세요.
			
			<textarea class="form-control intro_content" rows="17" name="intro_cont" placeholder="숙소 설명">${acom_infoUpdateDTO.intro_cont}</textarea>
			
		</div>
		
		<div class="ask_04">
			<h4 class="intro_font"><li>숙소에 대한 참고사항이 있나요</li></h4>
			특별히 게스트에게 부탁하거나 이용에 관한 참고사항이 있다면 이야기해주세요.
			
			<textarea class="form-control intro_content" rows="8" name="intro_etc" placeholder="참고사항">${acom_infoUpdateDTO.intro_etc}</textarea>		
		</div>
		
		<div class="ask_05">
			<h4 class="intro_font"><li>숙소와 가까운 대중교통에 대해 설명해주세요</li></h4>
			
			<textarea class="form-control intro_content" rows="4" name="intro_pubtrans" placeholder="대중교통">${acom_infoUpdateDTO.intro_pubtrans}</textarea>		
		</div>
		
		
		<div class="back_button">
			<button type="button" class="btn" onclick="javascript:history.go(-1);" > 
				<h4 class="button_font"> < 뒤로 </h4>		
			</button>
		</div>
		
		
			<input type="submit" class="submit_button" value="다음"/>
		
		
		
	</div>
	<!-- main box end-->
	
	</form>
	
	
</body>
</html>