<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

 						<div id="div1">
 						
 						<b>프로필 관리</b>
 						<hr/>
							<ul class="nav flex-column">
							
						<li class="nav-item" id="profile_ul" >
			<a class="nav-link active" href="profilelist" >프로필 수정</a>
				</li>
				<li class="nav-item">
					<a class="nav-link " href="profilelist2">결제 수단</a>
				</li>
				<li class="nav-item">
					<a class="nav-link " href="profilelist3">즐겨찾기</a>
				</li>
				<li class="nav-item">
					<a class="nav-link " href="profilelist4">후기</a>
				</li>
				</ul>
				<br/>
				
			<c:if test="${host_yn=='y'}">
				
				<b>호스트 메뉴</b>
				
				<hr/>
				
				<ul class="nav flex-column">
				<li class="nav-item">
					<a class="nav-link " href="profilelist6" >나의 숙소 관리</a>
				</li>
				<li class="nav-item">
					<a class="nav-link " href="../profile/profilelist7">예약현황</a>
				</li>
					<li class="nav-item">
					<a class="nav-link " href="../profile/profilelist5">나의 숙소 후기</a>
				</li>
				</ul>
				
				<hr/>
				
			</c:if>
				
				
						</div><!-- div1 -->

 


