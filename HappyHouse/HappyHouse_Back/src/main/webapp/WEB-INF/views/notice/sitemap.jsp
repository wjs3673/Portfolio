<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트맵</title>
<%@ include file="/WEB-INF/views/style/header.jsp"%>
</head>
<body>
	<div align="center">
		<br>
		<h2>사이트맵</h2>

		<br>
		<br> <a href="${root}/user/home" style="font-size: 20px">홈</a>
		<br>
		<br>
		<a href="${root}/user/mvlogin" style="font-size: 20px">로그인</a>
		<br>
		<br>
		<a href="${root}/user/mvjoin" style="font-size: 20px">회원가입</a>
		<br>
		<br>
		<a href="${root}/apt/search?pg=1&key=&word=" style="font-size: 20px">거래검색</a>
	</div>
</body>
</html>