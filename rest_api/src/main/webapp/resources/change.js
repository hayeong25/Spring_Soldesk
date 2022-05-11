/**
 * step2.jsp 스크립트
 */
$(function() {
	
	// 변경 버튼 클릭 시
	$(".btn-primary").click(function(e) {
		e.preventDefault(); // submit 기능 중지
		
		// 입력 데이터 javascript 객체로 생성
		let param = {
			userid:'gom',
			new_password:$("#confirm_password").val()
		}
		
		// ajax 통신
		$.ajax({
			url:'gom',
			type:'put',
			contentType:'application/json',
			data:JSON.stringify(param),
			success:function(data) {
				alert(data);
			},
			error:function(xhr, status, error){
				alert(xhr.responseText);
			}
		})
	})
	
})