<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	#container{width:500px; margin:auto;}
	th, td{height:30px;}
	input {width:250px;}
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원정보" name="title"/>
</jsp:include>
<br><br>
	<div id="container">
		<form action="${pageContext.request.contextPath}/member/memberUpdate.me" method="post" name="memInfofrm">
			<table class="table">
				<tr>
					<th scope="col" >아이디</th>
					<td scope="col" >
						<input name="userId" value="${loginMember.userId}" readonly>
					</td>
				</tr>
				<tr>
					<th scope="col">비밀번호</th>
					<td scope="col">
						<input type="password" name="userPwd" placeholder="*****" required>
						
					</td>
				</tr>
				<tr>
					<th scope="col">이름</th>
					<td scope="col">
						<input name="userName" value="${loginMember.userName}" readonly>
					</td>
				</tr>
				<tr>
					<th scope="col">EMAIL</th>
					<td scope="col">
						<input type="email" value="${loginMember.email}" name="email" >
					</td>
				</tr>
				<tr>
					<th scope="col">생년월일</th>
					<td scope="col">
						<input type="date" name="birthday"  value="${loginMember.birthday}" readonly>
					</td>
				</tr>
				<tr>
					<th scope="col">전화번호</th>
					<td scope="col">
						<input name="phone" value="${loginMember.phone}" >
					</td>
				</tr>
			</table>
			<button type="submit" class="btn btn-outline-success">수정</button>
		</form>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />



