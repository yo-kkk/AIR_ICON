<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>만족스러운 여행을 원하십니까?·AirIcon</title>

	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
	
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href= "/css/add_Img.css">
	
	<!-- img_미리보기 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	
	<script type="text/javascript">
        $(function() {
            $("#imgInp1").on('change', function(){
                readURL1(this);
            });
        });
        
        function readURL1(input) {
            if (input.files && input.files[0]) {
            	var reader = new FileReader();

    			reader.onload = function (e) {
                    $('#blah1').attr('src', e.target.result);
            	}
            reader.readAsDataURL(input.files[0]);
            }
        }
   </script>
   
   <script type="text/javascript">
        $(function() {
            $("#imgInp2").on('change', function(){
                readURL2(this);
            });
        });
        
        function readURL2(input) {
            if (input.files && input.files[0]) {
            	var reader = new FileReader();

    			reader.onload = function (e) {
                    $('#blah2').attr('src', e.target.result);
            	}
            reader.readAsDataURL(input.files[0]);
            }
        }
   </script>
   
   <script type="text/javascript">
        $(function() {
            $("#imgInp3").on('change', function(){
                readURL3(this);
            });
        });
        
        function readURL3(input) {
            if (input.files && input.files[0]) {
            	var reader = new FileReader();

    			reader.onload = function (e) {
                    $('#blah3').attr('src', e.target.result);
            	}
            reader.readAsDataURL(input.files[0]);
            }
        }
   </script>
</head>
<body>
	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
	
	<hr class="top_line">
	
	<br/>
	<br/>
	
	<h style="margin-left : 15%;">사진을 변경하시거나 추가하실수 있습니다.</h>
	
	<h4 class="intro_font00">
				숙소 대표사진 입니다 <br/>
				(변경을 원하시면 새로운 사진을 추가해주세요.)
	</h4>
	
	<form name="myform" method="post" action="addImg_action?acom_no=${acom_no }" enctype="multipart/form-data">
	
	<div class="img_01">			
	
    		<div class="img_preview">
    			<input type="file" id="imgInp1" name="img01"/>
    			<br/>
    			<br/>
        		<img id="blah1" src= "<c:if test="${file1!=null}">../Download?fileName=${file1}</c:if>" alt="사진을 선택해주세요" class="img_box" style="width : 580px; max-height : 400px; align-item: center; margin-left:10px;" name="img_01"/>   		        		
    		</div>						
	</div>
	
	<div class="img_02">			
			<h4 class="intro_font">두번째 사진 입니다.</h4>
    		<div class="img_preview">
    			<input type="file" id="imgInp2" name="img02"/>
    			<br/>
    			<br/>
        		<img id="blah2" src="<c:if test="${file2!=null}">../Download?fileName=${file2}</c:if>" alt="사진을 선택해주세요" class="img_box" style="width : 580px; max-height : 400px; align-item: center; margin-left:10px;" name="img_02"/>   		        		
    		</div>						
	</div>
	
	<div class="img_03">			
			<h4 class="intro_font">세번째 사진 입니다.</h4>
    		<div class="img_preview">
    			<input type="file" id="imgInp3" name="img03"/>
    			<br/>
    			<br/>
        		<img id="blah3" src="<c:if test="${file3!=null}">../Download?fileName=${file3}</c:if>" alt="사진을 선택해주세요" class="img_box" style="width : 580px; max-height : 400px; align-item: center; margin-left:10px;" name="img_03"/>   		        		
    		</div>						
	</div>
	<div class="submit_btnn">
		<input type="submit" class="btn btn-success submit_btnn" value="사진변경">
	</div>
	
	</form>
</body>
</html>