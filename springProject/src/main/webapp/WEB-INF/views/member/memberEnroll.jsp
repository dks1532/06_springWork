<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	#container{width: 500px; margin: auto;}
	th, td{height: 30px;}
	input {width: 250px;}
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원가입" name="title"/>
</jsp:include>
<br><br>
	<div id="container">
		<form action="${pageContext.request.contextPath}/member/memberEnroll.me" method="post" name="enrollfrm">
			<table class="table">
				<tr>
					<th scope="col">아이디</th>
					<td scope="col">
						<input name="userId" placeholder="4글자이상" required>
					</td>
				</tr>
				<tr>
					<th scope="col">비밀번호</th>
					<td scope="col">
						<input type="password" name="userPwd" required>
					</td>
				</tr>
				<tr>
					<th scope="col">이름</th>
					<td scope="col">
						<input name="userName" required>
					</td>
				</tr>
				<tr>
					<th scope="col">EMAIL</th>
					<td scope="col">
						<input type="email" name="email" placeholder="abc@naver.com">
					</td>
				</tr>
				<tr>
					<th scope="col">생년월일</th>
					<td scope="col">
						<input type="date" name="birthday">
					</td>
				</tr>
				<tr>
					<th scope="col">전화번호</th>
					<td scope="col">
						<input name="phone">
					</td>
				</tr>
			</table>
			<button type="submit" class="btn btn-outline-success">회원가입</button>&emsp;
			<button type="reset" class="btn btn-outline-success">초기화</button>
		</form>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />