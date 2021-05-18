<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>검색</title>
<%@ include file="/WEB-INF/views/style/header.jsp"%>
<script type="text/javascript">
	function searchAll() {
		document.location.href = "${root}/hospital.do?act=searchAll&pg=1";
	}
	function search() {
		if (document.getElementById("word").value == "") {
			alert("내용 입력 !!!");
			return;
		}

		var sel = document.getElementById("key");
		var tmp = document.getElementById("word");
		if (sel.options[sel.selectedIndex].value == "aptname") {
			document.location.href = "${root}/hospital.do?act=searchAptName&pg=1&word="
					+ tmp.value;
		} else if (sel.options[sel.selectedIndex].value == "dongname") {
			document.location.href = "${root}/hospital.do?act=searchDong&pg=1&word="
					+ tmp.value;
		}
	}
	function pageMove(pg) {
		document.getElementById("pg").value = pg;
		document.location.href = "${root}/hospital.do?act=searchAll&pg=" + pg;
	}
</script>
</head>

<body>
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="notice"> <input
			type="hidden" name="pg" id="pg" value="">
	</form>
	<div class="container" align="center">
		<div class="col-lg-8" align="center">
			<h2>국민 안심 병원 목록</h2>
			<h3><c:forEach var="corona" items="${hospitals}" begin='1' end ='1' step='1'>(${corona.update_date} 업데이트)</c:forEach></h3>
			<form id="searchform" method="get" class="form-inline" action="">
				<table class="table table-borderless">
					<tr>
						<td align="right">
							<button type="button" class="btn btn-success"
								onclick="javascript:searchAll();">전체 조회</button> <select
							class="form-control" name="key" id="key">
								<option value="aptname" selected="selected">병원 이름</option>
								<option value="dongname">동 이름</option>
						</select> <input type="text" class="form-control" placeholder="검색어 입력."
							name="word" id="word">
							<button type="button" class="btn btn-success"
								onclick="javascript:search();">검색</button>
						</td>
					</tr>
				</table>
			</form>

			<c:forEach var="hospital" items="${hospitals}">
				<table class="table table-active">
					<tbody>
						<tr class="table-secondary">
							<td colspan="2">시도, 시구군 : ${hospital.city} ${hospital.gugun}</td>
						</tr>
						<tr>
							<td class="table-warning" >
							<strong>의료기관명 : ${hospital.hospital_name}</strong>
							</td>
							<td>
							</td>
						</tr>
						<tr class="table-danger">
							<td colspan="2">주소 : ${hospital.address}</td>
						</tr>
						<tr class="table-primary">
							<td>신청유형(A:외래진료, B:외래진료 및 입원) : ${hospital.type}</td>
							<td align="right">대표 전화번호 : ${hospital.phone_number}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			<table>
				<tr>
					<td>${navigation.navigator}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>