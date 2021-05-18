<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>메인 화면</title>
<%@ include file="/WEB-INF/views/style/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
	<section id="index_section">
		<div class="card col-sm-12 mt-1" style="min-height: 850px;">
			<div class="card-body">
			<script>
			let colorArr = ['table-primary','table-success','table-danger'];
			$(document).ready(function(){
				$.get("${pageContext.request.contextPath}/map"
					,{act:"sido"}
					,function(data, status){
						$.each(data, function(index, vo) {
							$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
						});//each
					}//function
					, "json"
				);//get
			});//ready
			$(document).ready(function(){
				$("#sido").change(function() {
					$.get("${pageContext.request.contextPath}/map"
							,{act:"gugun", sido:$("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
								});//each
							}//function
							, "json"
					);//get
				});//change
				$("#gugun").change(function() {
					$.get("${pageContext.request.contextPath}/map"
							,{act:"dong", gugun:$("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
								});//each
							}//function
							, "json"
					);//get
				});//change
				$("#dong").change(function() {
					$.get("${pageContext.request.contextPath}/map"
							,{act:"apt", dong:$("#dong").val()}
							,function(data, status){
								$("#searchResult").empty();
								$.each(data, function(index, vo) {
									let str = "<tr class="+colorArr[index%3]+">"
									+ "<td>" + vo.no + "</td>"
									+ "<td>" + vo.dong + "</td>"
									+ "<td>" + vo.aptName + "</td>"
									+ "<td>" + vo.jibun + "</td>"
									+ "<td>" + vo.code
									+ "</td><td id='lat_"+index+"'></td><td id='lng_"+index+"'></td></tr>";
									$("tbody").append(str);
									$("#searchResult").append(vo.dong+" "+vo.aptName+" "+vo.jibun+"<br>");
								});//each
								clearOverlays();
								geocode(data);
							}//function
							, "json"
					);//get
				});//change
			});//ready
			function geocode(jsonData) {
				let idx = 0;
				$.each(jsonData, function(index, vo) {
					let tmpLat;
					let tmpLng;
					$.get("https://maps.googleapis.com/maps/api/geocode/json"
							,{	key:'AIzaSyBVoe776OoO1lo7aaDe9DrZI0-zt5IkhWA'
								, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
							}
							, function(data, status) {
								//alert(data.results[0].geometry.location.lat);
								tmpLat = data.results[0].geometry.location.lat;
								tmpLng = data.results[0].geometry.location.lng;
								$("#lat_"+index).text(tmpLat);
								$("#lng_"+index).text(tmpLng);
								addMarker(tmpLat, tmpLng, vo.aptName);
							}
							, "json"
					);//get
				});//each
			}
			</script>
			시도 : <select id="sido">
				<option value="0">선택</option>
			</select>
			구군 : <select id="gugun">
				<option value="0">선택</option>
			</select>
			읍면동 : <select id="dong">
				<option value="0">선택</option>
			</select>
			<table class="table mt-2">
				<thead>
					<tr>
						<th>번호</th>
						<th>법정동</th>
						<th>아파트이름</th>
						<th>지번</th>
						<th>지역코드</th>
						<th>위도</th>
						<th>경도</th>
					</tr>
				</thead>
				<tbody id="searchResult">
				</tbody>
			</table>

			<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
			<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
			<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVoe776OoO1lo7aaDe9DrZI0-zt5IkhWA&callback=initMap"></script>
			<script>
				var multi = {lat: 37.5665734, lng: 126.978179};
				var map;
				var markersArray = [];
				
				function initMap() {
					map = new google.maps.Map(document.getElementById('map'), {
						center: multi, zoom: 12
					});
					var marker = new google.maps.Marker({position: multi, map: map});
					markersArray.push(marker);
				}
				function addMarker(tmpLat, tmpLng, aptName) {
					var marker = new google.maps.Marker({
						position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
						label: aptName,
						title: aptName
					});
					marker.addListener('click', function() {
						map.setZoom(17);
						map.setCenter(marker.getPosition());
						callHouseDealInfo();
					});			
					markersArray.push(marker);
					var initialLocation = new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng));
			        map.setCenter(initialLocation);
			        map.setZoom(17);
					marker.setMap(map);
				}
				function clearOverlays() {
					  for (var i = 0; i < markersArray.length; i++ ) {
					    markersArray[i].setMap(null);
					  }
					  markersArray.length = 0;
				}
				function callHouseDealInfo() {
					alert("you can call HouseDealInfo");
				}
			</script>
			</div>
		</div>
	</section>
	<div class="row">
		<div class="leftcolumn">
			<div class="card">
				<h1>웹사이트 소개</h1>
				<h4>10월 28일, 2020년</h4>
				<br />
				<h2>
					안녕하세요! 해피<span>하우스</span>에 오신 것을 환영합니다.
				</h2>
				<p>상식이 통하고 서로 신뢰할 수 있는 경험을 통해, 더욱 편리하고 멋진 거래를 성사시키는 것이 우리의
					미션입니다.</p>
				<h4>추구하는 가치</h4>
				<p>합리적인 사고/능동적인 태도/빠른 소통/성장 욕구/정직함. 이러한 것들을 중요하게 생각하는 사람들입니다.</p>
				<h4>프로의식</h4>
				<p>우리는 가족이나 동아리가 아닌, 프로팀입니다. 각자의 역할에 충실하고, 약속한 것은 책임있게 실천합니다.</p>
				<h4>팀워크</h4>
				<p>하나의 목표를 위해 서로 다른 역할의 다양한 사람이 함께 한다는 것을 인정합니다. 서로가 최고가 될 수 있도록
					서로를 돕습니다.</p>
			</div>
		</div>
		<div class="rightcolumn">
			<div class="card">
				<h2>Find Us</h2>
				<h4>광주SSAFY 4기 2반</h4>
				<h4>1544-9001</h4>
				
			</div>
		</div>
	</div>

	
</body>
</html>