<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	#container{width:500px; margin:auto;}
	th, td{height:30px;}
	input {width:250px;}
	.guide {display:none;}
	.ok {color:green;}
	.error {color:red;}
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원가입" name="title"/>
</jsp:include>
<br><br>
	<div id="container">
		<form action="${pageContext.request.contextPath}/member/memberEnroll.me" method="post" name="enrollfrm">
			<table class="table">
				<tr>
					<th scope="col" >아이디</th>
					<td scope="col" >
						<input name="userId" placeholder="4글자이상" id="userId" required>
						<span class="guide ok">아이디 사용가능</span>
						<span class="guide error">아이디 사용불가</span>
					</td>
				</tr>
				<tr>
					<th scope="col">비밀번호</th>
					<td scope="col">
						<input type="password" name="userPwd" required>
					</td>
				</tr>
				<tr>
					<th scope="col">비밀번호확인</th>
					<td scope="col">
						<input type="password" name="pwdCheck" required>
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
	<script type="text/javascript">
		document.querySelector("#userId").addEventListener("keyup", (e) => {
			const ok = document.querySelector(".ok");
			const error = document.querySelector(".error");
			const userId = e.target;
			
			if(userId.value.length < 4) {
				ok.style.display = "none";
				error.style.display = "none";
				return;
			}
			
			$.ajax({
				url : "${pageContext.request.contextPath}/member/checkId.me",
				data : {userId : userId.value},	// url에 넘겨줄 값
				dataType : "json",	// dataType은 안쓰면 json이 기본값
				success(result) {
					console.log(result);
					const {userId, available} = result;
					
					if(available) {
						ok.style.display = "inline";
						error.style.display = "none";
					} else {
						ok.style.display = "none";
						error.style.display = "inline";
					}
				},
				error : console.log
			});
		});
	</script>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />



















