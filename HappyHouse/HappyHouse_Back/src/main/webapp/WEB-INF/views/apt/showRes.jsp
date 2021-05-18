<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/style/header.jsp"%>
<title>거래 상세정보 조회</title>
</head>
<body>
	<c:if test="${check eq 'buy'}">
		<div class="container" align="center">
			<div class="col-lg-6" align="center">
				<form id="loginform" method="post" action="">
					<input type="hidden" name="act" value="login"> <br>
					<div class="form-group" align="left">
						<label for=""><strong>거래번호 : </strong> ${deal.no}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>동 : </strong> ${deal.dong}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>아파트 이름 : </strong> ${deal.buildingName}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>거래액 : </strong>${deal.dealAmount}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>거래일자 : </strong>${deal.dealYear}-${deal.dealMonth}-${deal.dealDay}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>건축년도 : </strong>${deal.buildYear} </label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>면적 : </strong>${deal.area} </label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>층 : </strong>${deal.floor} </label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>지번 : </strong>${deal.bungi} </label>
					</div>
				</form>
			</div>
		</div>
	</c:if>
	
	<c:if test="${check eq 'rent'}">
		<div class="container" align="center">
			<div class="col-lg-6" align="center">
				<form id="loginform" method="post" action="">
					<input type="hidden" name="act" value="login"> <br>
					<div class="form-group" align="left">
						<label for=""><strong>거래번호 : </strong> ${deal.no}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>종류 : </strong> ${deal.type}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>동 : </strong> ${deal.dong}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>주택 이름 : </strong> ${deal.buildingName}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>보증액 : </strong>${deal.rentMoney}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>거래액 : </strong>${deal.bondFee}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>거래일자 : </strong>${deal.contractYear}-${deal.contractMonth}-${deal.contractDay}</label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>건축년도 : </strong>${deal.buildYear} </label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>면적 : </strong>${deal.area} </label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>층 : </strong>${deal.floor} </label>
					</div>
					<div class="form-group" align="left">
						<label for=""><strong>지번 : </strong>${deal.bunji} </label>
					</div>
				</form>
			</div>
		</div>
	</c:if>
</body>
</html>