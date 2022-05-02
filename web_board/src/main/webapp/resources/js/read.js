/**
 * read.jsp 스크립트
 */
$(function() {
	let operForm = $("#operForm");
	
	// list 버튼 클릭 시 목록 보여주기
	$(".btn-info").click(function() {
		// location.href="/board/list";
		
		// operForm 안의 bno 태그 제거
		operForm.find("input[name='bno']").remove();
		
		// operForm action 수정
		operForm.attr("action", "/board/list");
		
		// submit
		operForm.submit();
	})
	
	// modify 버튼 클릭 시 operForm으로 이동
	$(".btn-default").click(function() {
		operForm.submit();
	})
})