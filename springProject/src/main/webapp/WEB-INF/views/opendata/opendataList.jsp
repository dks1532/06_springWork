<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="공공데이터활용" name="title"/>
</jsp:include>

<div id="open-container" class="form-floating">
	<h2>실시간 대기 오염 정보</h2>
	
	지역 :
	<select id="location">
		<option>서울</option>
		<option>경기</option>
		<option>인천</option>
		<option>부산</option>
		<option>대구</option>
	</select>
	
	<button type="button" class="btn btn-outline-secondary" id="btn1">해당지역 대기오염정보</button>
	
	<table class="table" id="result1">
		<thead>
		<tr>
			<th scope="col">측정소명</th>
			<th scope="col">측정일시</th>
			<th scope="col">통합대기환경수치</th>
			<th scope="col">미세먼지농도</th>
			<th scope="col">일산화탄소농도</th>
			<th scope="col">이산화질소농도</th>
			<th scope="col">아황산가스농도</th>
			<th scope="col">오존농도</th>
		</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

	<script>
		document.querySelector("#btn1").addEventListener('click', (e) => {
			// json형식을 받았을 때
			/*
			$.ajax({
				url:"air.do",
				data: {location: $("#location").val()},
				success(data) {
					// console.log(data);
					const itemArr = data.response.body.items;
					let value = "";
					for(let i in itemArr) {
						console.log(itemArr[i]);
						let item = itemArr[i];
						value += "<tr>"
							  + 	"<td>" + item.stationName + "</td>"
							  + 	"<td>" + item.dataTime + "</td>"
							  + 	"<td>" + item.khaiValue + "</td>"
							  + 	"<td>" + item.pm10Value + "</td>"
							  + 	"<td>" + item.coValue + "</td>"
							  + 	"<td>" + item.no2Value + "</td>"
							  + 	"<td>" + item.so2Value + "</td>"
							  + 	"<td>" + item.o3Value + "</td>"
							  + "</tr>"
					}
					$("#result1 tbody").html(value);
				},
				error: console.log
			});
			*/
			
			// xml 형식으로 받을 때(json으로 하는게 더 쉬움)
			$.ajax({
				url:"air.do",
				data: {location: $("#location").val()},
				success(data) {
					const itemArr = $(data).find("item");
					let value = "";
					itemArr.each(function(i, item) {
						value += "<tr>"
							  + 	"<td>" + $(item).find("stationName").text() + "</td>"
							  + 	"<td>" + $(item).find("dataTime").text() + "</td>"
							  + 	"<td>" + $(item).find("khaiValue").text() + "</td>"
							  + 	"<td>" + $(item).find("pm10Value").text() + "</td>"
							  + 	"<td>" + $(item).find("coValue").text() + "</td>"
							  + 	"<td>" + $(item).find("no2Value").text() + "</td>"
							  + 	"<td>" + $(item).find("so2Value").text() + "</td>"
							  + 	"<td>" + $(item).find("o3Value").text() + "</td>"
							  + "</tr>"
					})
					$("#result1 tbody").html(value);
				},
				error: console.log
			});
		});
	</script>

	<h2>지진해일 대피소 정보</h2>
	<button type="button" class="btn btn-outline-secondary" id="btn2">대피소 정보 보기</button>
	<div id="result2"></div>
	<script type="text/javascript">
		document.querySelector("#btn2").addEventListener('click', (e) => {
			$.ajax({
				url:"disaster.do",
				success(data) {
					let $table = $("<table class='table'></table>");
					let $thead = $("<thead></thead>");
					let headTr = `<tr>
									<th>일련번호</th>
									<th>시도명</th>
									<th>시군구명</th>
									<th>대피장소명</th>
									<th>주소</th>
									<th>수용가능인원(명)</th>
									<th>해변으로부터거리</th>
									<th>대피소분류명</th>
								  </tr>`;
								  
					$thead.html(headTr);
					let $tbody = $("<tbody></tbody>");
					
					let bodyTr = "";
					$(data).find("row").each((i, row) => {
						bodyTr += "<tr>"
							  + 	"<td>" + $(row).find("id").text() + "</td>"
							  + 	"<td>" + $(row).find("sido_name").text() + "</td>"
							  + 	"<td>" + $(row).find("sigungu_name").text() + "</td>"
							  + 	"<td>" + $(row).find("shel_nm").text() + "</td>"
							  + 	"<td>" + $(row).find("address").text() + "</td>"
							  + 	"<td>" + $(row).find("shel_av").text() + "</td>"
							  + 	"<td>" + $(row).find("lenth").text() + "</td>"
							  + 	"<td>" + $(row).find("shel_div_type").text() + "</td>"
							  + "</tr>";
					})
					
					$tbody.html(bodyTr);
					$table.append($thead, $tbody).appendTo("#result2");
				},
				error: console.log
			});
		});
	</script>
</div>




<jsp:include page="/WEB-INF/views/common/footer.jsp" />