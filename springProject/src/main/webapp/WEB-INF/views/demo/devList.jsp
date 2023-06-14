<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev List" name="title"/>
</jsp:include>

<table class="table">
	<tr>
		<th scope="col">번호</th>
		<th scope="col">이름</th>
		<th scope="col">경력</th>
		<th scope="col">이메일</th>
		<th scope="col">성별</th>
		<th scope="col">개발가능언어</th>
		<th scope="col">등록일시</th>
		<th scope="col">수정</th>
	</tr>
	<c:forEach items="${devList}" var="dev">
		<tr>
			<td>${dev.no}</td>
			<td>${dev.name}</td>
			<td>${dev.career}</td>
			<td>${dev.email}</td>
			<td>${dev.gender}</td>
			<td>
				<c:forEach items="${dev.lang}" var="lang" varStatus="s">
					${lang}${not s.last ? "," : ""} 
				</c:forEach>
			</td>
			<td>
				<fmt:parseDate value="${dev.createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createDate" />
				<fmt:formatDate value="${createDate}" pattern="yy-MM-dd HH:mm" />
			</td>
			<td>
				<button type="button" class="btn btn-outline-success" onclick="updateDev(${dev.no})">수정</button>
				<%-- <button type="button" class="btn btn-outline-success" onclick="location.href='${pageContext.request.contextPath}/demo/updateDev.do?no=${dev.no}'">수정</button> --%>
			</td>
		</tr>
	</c:forEach>
</table>
<script>
	function updateDev(no) {
		location.href='${pageContext.request.contextPath}/demo/updateDev.do?no='+no;
	}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />