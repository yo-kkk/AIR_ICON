<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/favicon.png">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>호스트로 등록하는중입니다 3/3·AirIcon</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/become_host_total.css">
	
	<script type="text/javascript">
	function button_event(){
		if (confirm("작성한대로 제출하시겠습니까?") == true){    //확인
    		document.myform.submit();
		}else{   //취소
    		return;
		}
	}
	</script>
	
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

	<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>
    
    <div class="step_title">
    	<h6 class="title_font"> 지금까지 작성한 숙소가 맞는지 확인해주세요</h6>
    </div>
	
	<div class="progress_bar">
		<div class="progress_inner">
		</div>
	</div>


	<!-- main box -->
	<form name="myform" method="post" action="total_action" enctype="multipart/form-data">
	
	<div class="main_box">
	
		<div class="ask">
			<h4 class="ask_font"><li>작성한 숙소의 정보를 다시한번 확인해주세요</li></h4>
			

			<h5 class="contain_title"> 
				- 숙소 종류  : 
				<input type="text" class="form-control mid_length" name="type" placeholder="${acom_infoDTO.type}" disabled> 
			</h5>
			
			<h5 class="contain_title"> 
				- 최대 인원  : 
				<input type="text" class="form-control short_length" name="maxperson" placeholder="${acom_infoDTO.maxperson}" disabled> 
			</h5>
			
			<h5 class="contain_title"> 
				- 주소  : 
				<input type="text" class="form-control max_length" name="address" placeholder="${acom_infoDTO.address}" disabled> 
			</h5>
			
			<h5 class="contain_title"> 
				- 희망 가격  : 
				<input type="text" class="form-control quater_length" name="price" placeholder="${acom_infoDTO.price} 원" disabled> 
			</h5>
				<br/>
				<br/>
				
			<h5 class="contain_title"> 
				- 숙소 소개 제목  : 
			</h5>
			<input type="text" class="form-control intro_title" name="intro_title" placeholder="${acom_infoDTO.intro_title}" disabled>
			
			<h5 class="contain_title"> 
				- 소개 내용  : 
			</h5>
			<textarea class="form-control intro_cont" name="intro_cont" rows="17" disabled>${acom_infoDTO.intro_cont}</textarea>
			
			<h5 class="contain_title"> 
				- 숙소이용 참고사항  : 
			</h5>
			<textarea class="form-control intro_cont" name="intro_etc" rows="8" disabled>${acom_infoDTO.intro_etc}</textarea>
			
			<h5 class="contain_title"> 
				- 가까운 대중교통  : 
			</h5>
			<textarea class="form-control intro_cont" name="intro_pubtrans" rows="4" disabled>${acom_infoDTO.intro_pubtrans}</textarea>
				<br/>
			<h5 class="contain_title"> 
				- 편의시설 유무  : 
			</h5>
				
			<table class="util_table">
				<tr>
					<td> <c:if test="${acom_infoDTO.kitchen=='y'}">
						 <input type="text" class="form-control util_box" name="kitchen" placeholder="주방" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoDTO.parking=='y'}">
						 <input type="text" class="form-control util_box" name="parking" placeholder="주차" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoDTO.toiletries=='y'}">
						 <input type="text" class="form-control util_box" name="toiletries" placeholder="세면도구" disabled>
						 </c:if> 
					</td>
				</tr>
			
				<tr>
					<td> <c:if test="${acom_infoDTO.elevator=='y'}">
						 <input type="text" class="form-control util_box" name="elevator" placeholder="엘레베이터" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoDTO.tv=='y'}">
						 <input type="text" class="form-control util_box" name="tv" placeholder="TV" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoDTO.aircond=='y'}">
						 <input type="text" class="form-control util_box" name="aircond" placeholder="에어콘" disabled>
						 </c:if> 
					</td>
				</tr>
				
				<tr>
					<td> <c:if test="${acom_infoDTO.hotwater=='y'}">
						 <input type="text" class="form-control util_box" name="hotwater" placeholder="온수" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoDTO.washer=='y'}">
						 <input type="text" class="form-control util_box" name="washer" placeholder="세탁기" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoDTO.wifi=='y'}">
						 <input type="text" class="form-control util_box" name="wifi" placeholder="와이파이" disabled>
						 </c:if>
					</td>
				</tr>
			
			</table>
										
		</div>
		
		<div class="img_upload" style="border : 0.2px solid">
			<h4 class="intro_font"><li>마지막으로! 숙소 사진을 올려주세요</li></h4>
			게스트가 사진을 보고 숙소의 느낌을 생생히 떠올려볼 수 있도록 해주세요.
			
    		<div class="img_preview">
    			
    			<input type="file" id="imgInp" name="img_up" style="margin-left: 20px" required/>
    			<br/>

        		<img id="blah" src="#" alt="사진을 선택해주세요" class="img_box"/>   		        		
    		</div>	
		</div>
					
		
		<div class="back_button">
			<button type="button" class="btn" onclick="javascript:history.go(-1);" > 
				<h4 class="button_font"> < 뒤로 </h4>		
			</button>
		</div>
		
		
		<input type="submit" class="submit_button" value="제출" onclick="Javascript:button_event();"/>
		
	</div>
	<!-- main box end-->
	
	</form>
	
	
</body>
</html>