<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev등록결과" name="title"/>
</jsp:include>

<table border="1" align="center">
	<tr>
		<th>이름</th>
		<td>${dev.name}</td>
	</tr>
	<tr>
		<th>경력</th>
		<td>${dev.career}</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${dev.email}</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${dev.gender}</td>
	</tr>
	<tr>
		<th>개발언어</th>
		<%-- <td>${dev.lang[0]}, ${dev.lang[1]}</td> --%>
		<td>
			<c:forEach var="lang" items="${dev.lang}" varStatus="s">
				<%-- ${lang} --%>
				${lang}${not s.last ? ',' : ''}
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th>등록일</th>
		<%-- <td>${dev.createDate}</td> --%>
		<td>
			<fmt:parseDate value="${dev.createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createDate" />
			<fmt:formatDate value="${createDate}" pattern="yy-MM-dd HH:mm" />
		</td>
	</tr>
</table>







