<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css2?family=Gaegu:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<style>
* {
  box-sizing: border-box;
  font-family: 'Gaegu', cursive;
}

body {
  font-family: Arial;
  padding: 10px;
  background: #dff2f6;
}

/*아파트 검색*/
.numberis{
background-color:#004e66;
}

.dealmoney{
background-color:#fcbe32;
}

.yearof{
background-color:#004e66;
}

/*코로나 검색*/
.table-info{
background-color:#274555;
}
.address{
background-color:#e1eef6;
}
.dayday{
background-color:#fdc23e;
}
.sat{
background-color:#ff7761;
color:white;
}
.sun{
background-color:#274555;
color:white;
}

/* Header/Blog Title */
.header {
  padding: 30px;
  text-align: center;
  background: #dff2f6;
}

.header h1 {
  font-size: 50px;
}

/* Style the top navigation bar */
.topnav {
  overflow: hidden;
  background-color: #2b38ca;
  border-radius: 10px;
}

/* Style the topnav links */
.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change color on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Create two unequal columns that floats next to each other */
/* Left column */
.leftcolumn {   
  float: left;
  width: 75%;
}

/* Right column */
.rightcolumn {
  float: left;
  width: 25%;
  background-color: #f1f1f1;
  padding-left: 20px;
}

/* Fake image */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

/* Add a card effect for articles */
.card {
  background-color: white;
  padding: 20px;
  margin-top: 20px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Footer */
.footer {
  padding: 20px;
  text-align: center;
  background: #ddd;
  margin-top: 20px;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 800px) {
  .leftcolumn, .rightcolumn {   
    width: 100%;
    padding: 0;
  }
}

/* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
@media screen and (max-width: 400px) {
  .topnav a {
    float: none;
    width: 100%;
  }
}

</style>
</head>
<body>
<div class="header">
  <h1><img src="${root}/a.png"/></h1>
</div>
<div class="topnav">
	 <a href="${root}/user/home">홈</a>
	 <c:if test="${userinfo == null}">
	 <a href="${root}/user/mvlogin">로그인</a>
	 </c:if> 
	 <c:if test="${userinfo != null}">
	 <a href="${root}/user/logout">로그아웃</a>
	 </c:if>
	 <a href="${root}/user/mvjoin">회원가입</a>
	 <a href="${root}/apt/search?pg=1&key=&word=">아파트검색</a>
	 <a href="${root}/apt/searchRes?pg=1&key=&word=">주택검색</a>
	 <a href="${root}/corona/searchAll?pg=1&key=&word=">코로나검색</a>
	 <a href="${root}/hospital/searchAll?pg=1&key=&word=">병원검색</a>
	 <a href="${root}/notice/list?pg=1">공지사항</a>
	 <a href="${root}/notice/qnalist?pg=1">QnA</a>
	 <a href="${root}/notice/sitemap">사이트맵</a>
	 <c:if test="${userinfo == null}">
	 	<a href="${root}/user/mvlogin" style="float:right">로그인</a>
	 </c:if>
	 <c:if test="${userinfo != null}">
	 	<a href="${root}/user/info" style="float:right" >${userinfo.id}(${userinfo.name})님 환영합니다!</a>
	 </c:if>
</div>
</body>
</html>