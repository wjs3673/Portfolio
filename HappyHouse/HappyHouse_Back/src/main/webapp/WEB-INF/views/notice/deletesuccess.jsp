<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 완료</title>
<%@ include file="/WEB-INF/views/style/header.jsp"%>
</head>
<body>
	<div align="center">
		<br>
		<h2>공지사항이 성공적으로 삭제되었습니다.</h2>
		<a href="${root}/notice/list?pg=1">돌아가기</a>
	</div>
</body>
</html>