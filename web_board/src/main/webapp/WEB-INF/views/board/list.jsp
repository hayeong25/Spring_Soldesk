<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board List</h1>
	</div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
			    Board List Page
			    <button id="regBtn" type="button" class="btn btn-xs pull-right">
			    	Register New Board
			    </button>
			</div> <!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>번 호</th>
							<th>제 목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
				  	<!-- 게시판 리스트 반복문 -->
					<tbody>
						<c:forEach var="dto" items="${list }">
							<tr>
								<td>${dto.bno }</td>
								<td><a href="${dto.bno}" class="move">${dto.title}</a></td>
								<td>${dto.writer }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.regdate }" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.updatedate }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row">
				<!-- start search -->
					<div class="col-md-12">
						<div class="col-md-8">
							<!--search Form-->
							<form action="" method="get" id="searchForm">
								<!-- Search일 때도 주소줄은 따라가야 함 -->
								<input type="hidden" name="pageNum" value="${criteria.pageNum }" />
								<input type="hidden" name="amount" value="${criteria.amount }" />
								<select name="type" id="">
									<option value="" <c:out value="${criteria.type == '' ? 'selected' : ''}" />>---------------------</option>
									<option value="T" <c:out value="${criteria.type == 'T' ? 'selected' : ''}" />>제목</option>
									<option value="C" <c:out value="${criteria.type == 'C' ? 'selected' : ''}" />>내용</option>
									<option value="W" <c:out value="${criteria.type == 'W' ? 'selected' : ''}" />>작성자</option>
									<option value="TC" <c:out value="${criteria.type == 'TC' ? 'selected' : ''}" />>제목 or 내용</option>
									<option value="TW" <c:out value="${criteria.type == 'TW' ? 'selected' : ''}" />>제목 or 작성자</option>
									<option value="TCW" <c:out value="${criteria.type == 'TCW' ? 'selected' : ''}" />>제목 or 내용 or 작성자</option>
								</select>
								<input type="text" name="keyword" id="" value="${criteria.keyword}"/>
								<button class="btn btn-default" type="submit">Search</button>
							</form>
						</div>
						<div class="col-md-2 col-md-offset-2">
						<!--페이지 목록 갯수 지정하는 폼-->
						<select name="" id="amount" class="form-control">
							<option value="10" <c:out value="${criteria.amount == 10 ? 'selected' : ''}" />>10</option>
							<option value="20" <c:out value="${criteria.amount == 20 ? 'selected' : ''}" />>20</option>
							<option value="30" <c:out value="${criteria.amount == 30 ? 'selected' : ''}" />>30</option>
							<option value="40" <c:out value="${criteria.amount == 40 ? 'selected' : ''}" />>40</option>
						</select>
					    </div>
					</div>
				</div><!-- end search -->
				<div class="text-center">
				<!-- start Pagination -->
					<ul class="pagination">
						<c:if test="${pageDTO.prev}">
							<li class="paginate_button previous"><a href="${pageDTO.startPage-1}">Previous</a></li>
						</c:if>
						<c:forEach var="index" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">						
							<li class="paginate_button ${pageDTO.criteria.pageNum == index ? 'active' : ''}"><a href="${index}">${index}</a></li>
						</c:forEach>
						<c:if test="${pageDTO.next}">
							<li class="paginate_button next"><a href="${pageDTO.endPage+1}">Next</a></li>
						</c:if>
					</ul>
				</div> <!-- end Pagination -->
			</div> <!-- end panel-body -->
		</div> <!-- end panel -->
	</div>
</div> <!-- /.row -->

<!-- 페이지 링크 처리 form -->
<form action="/board/list" id="actionForm">
	<!--
		pageNum, amount, type, keyword 값을 부를 때
		1) pageDTO 사용 - pageDTO.criteria.pageNum
		2) criteria에서 가져다 사용 - criteria.pageNum
	-->
	<input type="hidden" name="pageNum" value="${criteria.pageNum }" />
	<input type="hidden" name="amount" value="${criteria.amount }" />
	<input type="hidden" name="type" value="${criteria.type }" />
	<input type="hidden" name="keyword" value="${criteria.keyword }" />
</form>

<!-- 모달 -->
<div class="modal" tabindex="-1" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">게시글</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>처리가 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>

<!-- 스크립트 -->
<script>
	//게시글 등록 성공 후 result 확인
	let result = '${result}';
</script>
<script src="/resources/js/list.js"></script>
<%@include file="../includes/footer.jsp" %>