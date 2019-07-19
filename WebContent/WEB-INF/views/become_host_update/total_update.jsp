<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="icon" type="image/png" sizes="16x16" href="/images/icon_favicon.png">	
	<title>호스트 등록 수정중입니다 3/3·AirIcon</title>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Playfair+Display" rel="stylesheet">
	
	<!-- CSS file href -->
	<link rel="stylesheet" href="/css/become_host_total.css">
	
	<script type="text/javascript">
	function button_event(){
		if (confirm("작성한대로 제출하시겠습니까?")  ==  true){    //확인
    		document.form.submit();
		}else{   //취소
    		return;
		}
	}
	</script>

</head>
<body>

	<!-- AirIcon Banner -->
		<jsp:include page="../top_banner/top_with_login.jsp" flush="false"/>

    
    <div class="step_title">
    	<h6 class="title_font"> 지금까지 작성한 숙소가 맞는지 확인해주세요</h6>
    </div>
	
	<div class="progress_bar">
		<div class="progress_inner">
		</div>
	</div>


	<!-- main box -->
	<form name="myform" method="post" action="acom_total_action">
	
	<div class="main_box">
	
		<div class="ask">
			<h4 class="ask_font"><li>작성한 숙소의 정보를 다시한번 확인해주세요</li></h4>
			

			<h5 class="contain_title"> 
				- 숙소 종류  : 
				<input type="text" class="form-control short_length" name="type" placeholder="${acom_infoUpdateDTO.type}" disabled> 
			</h5>
			
			<h5 class="contain_title"> 
				- 최대 인원  : 
				<input type="text" class="form-control short_length" name="maxperson" placeholder="${acom_infoUpdateDTO.maxperson}" disabled> 
			</h5>
			
			<h5 class="contain_title"> 
				- 주소  : 
				<input type="text" class="form-control max_length" name="address" placeholder="${acom_infoUpdateDTO.address}" disabled> 
			</h5>
			
			<h5 class="contain_title"> 
				- 희망 가격  : 
				<input type="text" class="form-control quater_length" name="price" placeholder="${acom_infoUpdateDTO.price}" disabled> 
			</h5>
				<br/>
				<br/>
				
			<h5 class="contain_title"> 
				- 숙소 소개 제목  : 
			</h5>
			<input type="text" class="form-control intro_title" name="intro_title" placeholder="${acom_infoUpdateDTO.intro_title}" disabled>
			
			<h5 class="contain_title"> 
				- 소개 내용  : 
			</h5>
			<textarea class="form-control intro_cont" name="intro_cont" rows="17" disabled>${acom_infoUpdateDTO.intro_cont}</textarea>
			
			<h5 class="contain_title"> 
				- 숙소이용 참고사항  : 
			</h5>
			<textarea class="form-control intro_cont" name="intro_etc" rows="8" disabled>${acom_infoUpdateDTO.intro_etc}</textarea>
			
			<h5 class="contain_title"> 
				- 가까운 대중교통  : 
			</h5>
			<textarea class="form-control intro_cont" name="intro_pubtrans" rows="4" disabled>${acom_infoUpdateDTO.intro_pubtrans}</textarea>
				<br/>
			<h5 class="contain_title"> 
				- 편의시설 유무  : 
			</h5>
				
			<table class="util_table">
				<tr>
					<td> <c:if test="${acom_infoUpdateDTO.kitchen eq 'y'}">
						 <input type="text" class="form-control util_box" name="kitchen" placeholder="주방" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoUpdateDTO.parking eq 'y'}">
						 <input type="text" class="form-control util_box" name="parking" placeholder="주차" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoUpdateDTO.toiletries eq 'y'}">
						 <input type="text" class="form-control util_box" name="toiletries" placeholder="세면도구" disabled>
						 </c:if> 
					</td>
				</tr>
			
				<tr>
					<td> <c:if test="${acom_infoUpdateDTO.elevator eq 'y'}">
						 <input type="text" class="form-control util_box" name="elevator" placeholder="엘레베이터" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoUpdateDTO.tv eq 'y'}">
						 <input type="text" class="form-control util_box" name="tv" placeholder="TV" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoUpdateDTO.aircond eq 'y'}">
						 <input type="text" class="form-control util_box" name="aircond" placeholder="에어콘" disabled>
						 </c:if> 
					</td>
				</tr>
				
				<tr>
					<td> <c:if test="${acom_infoUpdateDTO.hotwater eq 'y'}">
						 <input type="text" class="form-control util_box" name="hotwater" placeholder="온수" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoUpdateDTO.washer eq 'y'}">
						 <input type="text" class="form-control util_box" name="washer" placeholder="세탁기" disabled>
						 </c:if> 
					</td>
					<td> <c:if test="${acom_infoUpdateDTO.wifi eq 'y'}">
						 <input type="text" class="form-control util_box" name="wifi" placeholder="와이파이" disabled>
						 </c:if> 
					</td>
				</tr>
			
			</table>
										
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