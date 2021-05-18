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
		document.location.href = "${root}/apt/searchRes?pg=1&key=&word=";
	}
	function searchBuy() {
		document.location.href = "${root}/apt/searchRes?pg=1&key=&word=";
	}
	function searchRent() {
		document.location.href = "${root}/apt/searchRent?pg=1&key=&word=";
	}
	
	function search() {
		if (document.getElementById("word").value == "") {
			alert("내용 입력 !!!");
			return;
		}

		var sel = document.getElementById("key");
		var tmp = document.getElementById("word");
		if (sel.options[sel.selectedIndex].value == "residencename") {
			document.location.href = "${root}/apt/searchResName?pg=1&word="
					+ tmp.value;
		} else if (sel.options[sel.selectedIndex].value == "dongname") {
			document.location.href = "${root}/apt/searchResDongName?pg=1&word="
					+ tmp.value;
		}
	}
	function pageMove(pg) { 
		if(document.getElementById("nalaoldde").value == "buy") {
			document.getElementById("pg").value=pg;
			document.location.href = "${root}/apt/searchRes?pg=" + pg;	
		}
		else {
			document.getElementById("pg").value=pg;
			document.location.href = "${root}/apt/searchRent?pg=" + pg;	
		}
	}
</script>
</head>

<body>
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="notice"> 
		<input type="hidden" name="pg" id="pg" value="">
	</form>
	<div class="container" align="center">
		<div class="col-lg-8" align="center">
			<h2>주택 실거래가 목록</h2>
			<form id="searchform" method="get" class="form-inline" action="">
				<input type="hidden" id="nalaoldde" value="${type}"/>
				<table class="table table-borderless">
					<tr>
						<td align="right">
							<button type="button" class="btn btn-success"
								onclick="javascript:searchAll();">전체 조회</button>
							<button type="button" class="btn btn-success"
								onclick="javascript:searchBuy();">매매</button>
							<button type="button" class="btn btn-success"
								onclick="javascript:searchRent();">전월세</button>   
							<select class="form-control" name="key" id="key">
								<option value="base" selected="selected">선택</option>
								<option value="residencename">주택 이름</option>
								<option value="dongname">동 이름</option>
							</select>
							<input type="text" class="form-control" placeholder="검색어 입력."
							name="word" id="word">
							<button type="button" class="btn btn-success"
								onclick="javascript:search();">검색</button>
						</td>
					</tr>
				</table>
			</form>


			<c:forEach var="deal" items="${deals}">
				<table class="table table-active">
					<tbody>
						<tr
							onclick="document.location.href='${root}/apt/show?no=${deal.no}'"
							style="cursor: pointer;">
							<td><strong>${deal.no}. 매매</strong></td>
						</tr><tr class="table-info">
							<%-- <td colspan="2"><strong>이미지추가</strong></td> --%>
						</tr>
						<tr class="table-info">
							<td>동 : ${deal.dong}</td>
							<td align="right">주택 이름 : ${deal.buildingName}</td>
						</tr>
						<tr>
							<td class="table-danger"><strong>거래액 : ${deal.dealAmount} 
							거래일자 : ${deal.dealYear}-${deal.dealMonth}-${deal.dealDay}</strong></td>
							<td>건축년도 : ${deal.buildYear} 면적 : ${deal.area} 층 :
								${deal.floor} 지번 : ${deal.bungi}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			
			<c:forEach var="rent" items="${rents}">
				<table class="table table-active">
					<tbody>
						<tr
							onclick="document.location.href='${root}/residence.do?act=showRent&no=${rent.no}'"
							style="cursor: pointer;">
							<td><strong>${rent.no}. ${rent.type}</strong></td>
						</tr><tr class="table-info">
							<%-- <td colspan="2"><strong>이미지추가</strong></td> --%>
						</tr>
						<tr class="table-info">
							<td>동 : ${rent.dong}</td>
							<td align="right">주택 이름 : ${rent.buildingName}</td>
						</tr>
						<tr>
							<td class="table-danger"><strong>보증금 : ${rent.rentMoney}, 거래액 : ${rent.bondFee} <br/>
							거래일자 : ${rent.contractYear}-${rent.contractMonth}-${rent.contractDay}</strong></td>
							<td>건축년도 : ${rent.buildYear} 면적 : ${rent.area} 층 :
								${rent.floor} 지번 : ${rent.bunji}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			
			<c:if test="${type eq 'buy'}">
				<div id="navigationOfBuy"></div>
				<table>
					<tr>
						<td>${navigation.navigator}</td>
					</tr>
				</table>	
			</c:if>
			
			<c:if test="${type eq 'rent'}">
				<div id="navigationOfRent"></div>
				<table>
					<tr>
						<td>${navigationRent.navigator}</td>
					</tr>
				</table>	
			</c:if>
		</div>
	</div>
</body>
</html>