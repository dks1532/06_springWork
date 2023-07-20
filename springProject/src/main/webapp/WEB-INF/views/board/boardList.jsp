<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	#container {
	width:1000px;
	margin:auto;
	}
	.table a {text-decoration: none; color:black; cursor: pointer;}
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>

<div id="container">	<!-- 부트스트랩 사용시에는 table 크기 스타일 조정이 힘들어서 div의 id를 지정하여 그 안에 넣어준다. -->
	<table class="table">
		<tr>
			<td colspan="6" align="right">
				<button type="button" class="btn btn-outline-secondary" id="enrollBtn">글쓰기</button>
			</td>
		</tr>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">첨부파일</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.boardNo}</td>
				<td><a href="${pageContext.request.contextPath}/board/boardDetail.bo?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
				<td>${board.boardWriter}</td>
				<td>
					<c:if test="${not empty board.originalFilename}">
						<img src="${pageContext.request.contextPath}/resources/img/file.png" alt="파일" width="20px">
					</c:if>
				</td>
				<td>${board.createDate}</td>
				<td>${board.count}</td>
			</tr>
		</c:forEach>	
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:if test="${pi.nowPage ne 1}">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/board/boardList.bo?nowPage=${pi.nowPage-1}" >Previous</a>
				</li>
			</c:if>
			<c:forEach var="p" begin="${pi.startPage}" end="${pi.endPage}">
				<c:choose>
					<c:when test="${p eq pi.nowPage}">
						<li class="page-item active">
							<a class="page-link" href="${pageContext.request.contextPath}/board/boardList.bo?nowPage=${p}">${p}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath}/board/boardList.bo?nowPage=${p}">${p}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${ pi.nowPage ne pi.totalPage }">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/board/boardList.bo?nowPage=${pi.nowPage+1}">Next</a>
				</li>
			</c:if>
		</ul>
	</nav>
</div>
<script>
	document.querySelector("#enrollBtn").addEventListener('click', (e) => {
		location.href='${pageContext.request.contextPath}/board/boardForm.bo';
	});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />


