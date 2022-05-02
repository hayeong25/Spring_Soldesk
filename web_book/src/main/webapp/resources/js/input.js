/*
	insert.jsp, update.jsp 유효성 검사
*/
$(function() {
	// 입력 버튼 클릭하면 submit 기능 중지
	// code, price 값 가져와서 숫자인지 확인 - 숫자가 아니라면 경고창 띄우고 되돌아가기
	$(".btn-primary").click(function(e) {
		e.preventDefault(); // submit 버튼은 일단 가버리는 기능을 갖고 있기 때문에 그 기능 중지 시키고 유효성 검사
		let code = $("#code");
		let price = $("#price");
		if(!/^[0-9]{4}$/.test(code.val())) { // test() : boolean return
			alert('도서 코드를 확인해주세요');
			code.select(); // code 요소 안에 있는 값을 블럭으로 선택
			return;
		}
		if(!/^[0-9]+$/.test(price.val())) { // test() : boolean return
			alert('가격을 확인해주세요');
			price.select();
			return;
		}
		
		// 입력이 올바르게 되었다면 form submit
		$("form").submit();
	})
})