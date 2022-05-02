/**
 * modify.jsp 스크립트
 */
$(function() {
	let form = $("#operForm");
	
	$("button").click(function(e) {
		e.preventDefault(); // submit 동작 막기
		
		// 현재 클릭된 버튼의 data-oper 값 가져오기
		let operation = $(this).data("oper");
		
		if(operation == 'modify') {
			form = $("[role='form']");
		}else if(operation == "remove") {
			form.attr('action', '/board/remove');
		}else if(operation == "list") {
			form.find("[name='bno']").remove();
			form.attr('action', '/board/list');
		}
		
		form.submit();
	})
})