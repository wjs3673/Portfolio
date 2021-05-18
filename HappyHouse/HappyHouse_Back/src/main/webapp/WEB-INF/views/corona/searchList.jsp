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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>		
<script type="text/javascript">

	$(document).ready(function() {
		
	})
	
    function searchAll() {
        document.location.href = "${root}/corona.do?act=searchAll&pg=1";
    }
    function search() {
        if (document.getElementById("word").value == "") {
            alert("내용 입력 !!!");
            return;
        }

        var sel = document.getElementById("key");
        var tmp = document.getElementById("word");
        if (sel.options[sel.selectedIndex].value == "aptname") {
            document.location.href = "${root}/apt.do?act=searchAptName&pg=1&word="
                    + tmp.value;
        } else if (sel.options[sel.selectedIndex].value == "dongname") {
            document.location.href = "${root}/apt.do?act=searchDong&pg=1&word="
                    + tmp.value;
        }
    }
    function pageMove(pg) {
        document.getElementById("pg").value = pg;
        document.location.href = "${root}/corona.do?act=searchAll&pg=" + pg;
    }
</script>
</head>

<body>
    <form name="pageform" id="pageform" method="GET" action="">
        <input type="hidden" name="act" id="act" value="notice"> <input
            type="hidden" name="pg" id="pg" value="">
    </form>
    <br>
    <div class="container" align="center">
        <div class="col-lg-8" align="center">
            <h2>코로나 선별 진료소 정보</h2>
            <h3>
                <c:forEach var="corona" items="${coronas}" begin='1' end='1'
                    step='1'>(${corona.update_date} 업데이트)</c:forEach>
            </h3>
            <form id="searchform" method="get" class="form-inline" action="">
            <input type="hidden" name="pg" id="pg" value="1">
                <table class="table table-borderless">
                    <tr>
                        <td align="center">
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

            <c:forEach var="corona" items="${coronas}">
                <table class="table table-active">
                    <tbody>
                        <tr class="table-info">
                            <td>검체체취 가능 여부 : ${corona.testable}</td>
                            <td align="right">시도 : ${corona.city}</td>
                        </tr>
                        <tr>
                            <td class="dayday"><strong> 평일 운영시간 :
                                    ${corona.opening_hours_weekday}</strong></td>
                            <td align="right" class="address">주소 : ${corona.address}</td>
                        </tr>
                        <tr>
                            <td class="sat">토요일 운영시간 : ${corona.opening_hours_sat}</td>
                            <td align="right" class="address">대표전화번호 : ${corona.phone}</td>
                        </tr>
                        <tr>
                            <td colspan="2" class="sun">일요일/공휴일 운영시간 :
                                ${corona.opening_hours_sun_ph}</td>
                        </tr>
                    </tbody>
                    <br>
                </table>
            </c:forEach>
            <br>
            <table>
                <tr>
                    <td>${navigation.navigator}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>