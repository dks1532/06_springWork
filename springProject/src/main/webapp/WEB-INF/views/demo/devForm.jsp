<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	#demo-container{width:550px; margin:auto;}
</style>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev등록" name="title"/>
</jsp:include>
<br>

<div id="demo-container" class="form-floating">
	<form name="frm">
		<div class="mb-3 row">
			<label for="name" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name" name="name" value="아무개" required>
			</div>
		</div>
		<div class="mb-3 row">
			<label for="career" class="col-sm-2 col-form-label">개발경력</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="career" name="career" value="10" required>
			</div>
		</div>
		<div class="mb-3 row">
			<label for="email" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="email" name="email" value="kh@google.com" required>
			</div>
		</div>
		 
		<label class="col-sm-2 col-form-label">성별</label>
		<div class="form-check form-check-inline">	
			<input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="M" checked>
			<label class="form-check-label" for="inlineRadio1">남</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="F">
			<label class="form-check-label" for="inlineRadio2">여</label>
		</div>
		<br>
		
		<label class="col-sm-2 col-form-label">개발언어</label>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="java" checked>
			<label class="form-check-label" for="inlineCheckbox1">JAVA</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="c" checked>
			<label class="form-check-label" for="inlineCheckbox2">C</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="JavaScript">
			<label class="form-check-label" for="inlineCheckbox3">JavaScript</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="python">
			<label class="form-check-label" for="inlineCheckbox3">Python</label>
		</div>
	</form>
	<button type="button" class="btn btn-outline-secondary" onclick="submit('dev1');">제출(HttpServletRequest)</button><br><br>
	<button type="button" class="btn btn-outline-secondary" onclick="submit('dev2');">제출(@RequestParam)</button><br><br>
	<button type="button" class="btn btn-outline-secondary" onclick="submit('dev3');">제출(커맨드객체)</button><br><br>
	<button type="button" class="btn btn-outline-secondary" onclick="insertDev();">제출(DB저장)</button><br><br>
</div>

<script type="text/javascript">
	const submit = (id) => {
		frm.action = `${pageContext.request.contextPath}/demo/\${id}.do`;	// \ 안넣으면 jquery로 인식
		frm.submit();
	}
	const insertDev = () => {
		frm.method = 'POST';
		frm.action = '${pageContext.request.contextPath}/demo/insertDev.do';
		frm.submit();
	}
	
</script>

