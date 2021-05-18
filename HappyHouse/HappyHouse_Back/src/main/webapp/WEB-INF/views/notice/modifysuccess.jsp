<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 완료</title>
<%@ include file="/WEB-INF/views/style/header.jsp"%>
</head>
<body>
	<div align="center">
		<div class="container" align="center">
			<br>
			<h2>공지사항 수정 완료</h2>
			<br>
			<a href="${root}/notice/list?pg=1">공지사항목록</a>
		</div>
	</div>
</body>
</html>