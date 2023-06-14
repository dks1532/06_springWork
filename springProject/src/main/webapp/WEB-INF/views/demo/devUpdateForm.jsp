<%@page import="com.kh.spring.demo.model.vo.Dev, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	Dev dev = (Dev)request.getAttribute("dev");
	List<String> devLangList = dev.getLang() != null ? Arrays.asList(dev.getLang()) : null;
	pageContext.setAttribute("devLangList", devLangList);
%>
<style>
	#demo-container{width:550px; margin:auto;}
</style>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev등록" name="title"/>
</jsp:include>
<br>

<div id="demo-container" class="form-floating">
	<form name="frm" method="post" action="${pageContext.request.contextPath}/demo/updateDev.do">
		<div class="mb-3 row">
			<label for="name" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name" name="name" value="${dev.name}" readonly>
			</div>
		</div>
		<div class="mb-3 row">
			<label for="career" class="col-sm-2 col-form-label">개발경력</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="career" name="career" value="${dev.career}">
			</div>
		</div>
		<div class="mb-3 row">
			<label for="email" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="email" name="email" value="${dev.email}">
			</div>
		</div>
		 
		<label class="col-sm-2 col-form-label">성별</label>
		<div class="form-check form-check-inline">	
			<input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="M"
				${dev.gender eq 'M' ? 'checked' : ''}>
			<label class="form-check-label" for="inlineRadio1">남</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="F"
				${dev.gender eq 'F' ? 'checked' : ''}>
			<label class="form-check-label" for="inlineRadio2">여</label>
		</div>
		<br>
		
		<label class="col-sm-2 col-form-label">개발언어</label>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="java"
				${devLangList.contains('java') ? 'checked' : ''}>	<!-- contains()는 ()안의 요소가 들어있는가 체크하는 것  -->
			<label class="form-check-label" for="inlineCheckbox1">JAVA</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="c"
				${devLangList.contains('c') ? 'checked' : ''}>
			<label class="form-check-label" for="inlineCheckbox2">C</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="JavaScript"
				${devLangList.contains('JavaScript') ? 'checked' : ''}>
			<label class="form-check-label" for="inlineCheckbox3">JavaScript</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" name="lang" value="python"
				${devLangList.contains('python') ? 'checked' : ''}>
			<label class="form-check-label" for="inlineCheckbox3">Python</label>
		</div>
		<input type="hidden" name="no" value="${dev.no}">
		<button type="submit" class="btn btn-outline-secondary">dev 수정</button>
	</form>
</div>
<script type="text/javascript">
	document.frm.onsubmit = (e) => {
		/* // 이름 한글 2글자 확인하는 절차
		const name = e.target.name;
		if(/^[가-힣]{2,}$/.test(name.value) == false) {
			alert("한글로 2글자 이상 작성하세요");
			return false;
		}
		 */
		const langs = e.target.querySelectorAll("[name=lang]:checked");
		if(langs.length == 0) {
			alert("하나 이상의 개발언어를 선택하세요");
			return false;
		}
	}
</script>

