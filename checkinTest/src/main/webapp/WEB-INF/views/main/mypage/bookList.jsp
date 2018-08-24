<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>check in! 예약내역</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="http://gsgd.co.uk/sandbox/jquery/easing/"></script>
<link rel="stylesheet" href="/webapp/css/default.css">
<!-- body 구성에 적용 되는 스타일 시트 -->

<style>
#headerIng {
	width: 100%;
	height: 90px;
	margin-bottom: 10px
}

.row {
	margin-right: 2px
}
/*=========================================================  */
#left {
	width: 15%
}

#center {
	padding: 20px 0px;
	margin: 0px;
	weight: 400px
}
</style>
<script>
	function whenclickwritemodal(b,a,r){
		$(".b").val(b);
		$(".a").val(a);
		$(".r").val(r);
	}

</script>
</head>
<body>
	<%@ include file="../topnav_member.jspf"%>
	<!-- 
   본문은 left center right으로 나뉜다.
-->
	<div id="main" class="row main">
		<!-- 빈 영역 확보 -->
		<aside class="left" id="left"></aside>

		<!-- side Nav -->
		<aside class="left" id="sideNav">
			<%@ include file="rightside_nav.jspf"%>
		</aside>

		<!-- main -->
		<div class="col-sm-7 center container" id="center">
			<!--  check box 추가 -->
			<!-- 홈 > 회원가입 -->
			<span class="font1-small">홈>마이페이지>예약내역</span>
			<hr />
			<c:forEach var="list" items="${lst }">
				<!-- 예약 현황 리스트 시작 -->
	<div>
		<!-- 예약 현황 1 시작 -->
		<div class="input-group">
			<div class="input-group-item col-lg-3 border">
				<c:if test="${list.aimg1 != null}">
				<img src='${list.aimg1}' style="width:100%;height:100%"/>
				</c:if>
			</div>
			<div class="container input-group-item col-lg-9">
				<table class="table">
					<thead>
						<tr>
							<th>예약날짜</th>
							<th>체크인</th>
							<th>체크아웃</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${list.writedate }</td>
							<td>${list.bcheckin }</td>
							<td>${list.bcheckout }</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<thead>
						<tr>
							<th>숙박업소</th>
							<th>룸번호(이름)</th>
							<th>연락처</th>
							<th>인원수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${list.aname }</td>
							<td>${list.rname }</td>
							<td>${list.atel }</td>
							<td>${list.bcount }</td>
						</tr>
					</tbody>
				</table>
				<div class="row" id="review_btns">
					<span class="col-sm-5"></span>
					
					
					<c:if test="${list.v != 0}">
					<button id='editBtn' data-toggle="modal" data-target="#reviewModal_edit" class="btn col-sm-2 showreviewModal_edit" style="background-color:#5284FF;color:#fff;font-weight:bold">리뷰수정</button>
					</c:if>
					<c:if test="${list.v == 0}">
					<button id="writeBtn" onclick="whenclickwritemodal(${list.b},${list.a},${list.r})" data-toggle="modal" data-target="#reviewModal" class="btn col-sm-2 showreviewModal" style="background-color:#5284FF;color:#fff;font-weight:bold">리뷰작성</button>
					</c:if>
					<button id="cancleBtn" class="btn btn-danger col-sm-2">예약취소</button>
				</div>
			</div>
		</div>
		<!-- 예약 현황 1 끝 -->
	</div>
	<!-- 예약 현황 리스트 끝 -->

				<br/>
			</c:forEach>			
		</div>
		<aside class="left" id="left"></aside>
		<!-- footer -->
	<footer id="footer" class="footer" style="width: 100%; font-size: 0.8em; margin-top: 150px;">
		<%@ include file="../companyInfo.jspf"%>
	</footer>
	</div>

	
	<%@ include file="../review/writeReviewModal.jspf"%>
	<%@ include file="../review/editReviewModal.jspf"%>
</body>
</html>