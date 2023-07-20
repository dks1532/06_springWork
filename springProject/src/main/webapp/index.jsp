<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- include는 web-inf 폴더 접근가능, href 경로 지정은 접근불가 -->
<jsp:include page="/WEB-INF/views/common/header.jsp">	
	<jsp:param value="SPRING" name="title"/>
</jsp:include>

	<img src="${pageContext.request.contextPath}/resources/img/spring.png" alt="spring" width="600" height="500">

<jsp:include page="/WEB-INF/views/common/footer.jsp" />