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
			// 첨부파일 목록 가져가기
			let str = "";
		
			// li 태그 정보 수집하기
			$(".uploadResult ul li").each(function(idx, obj) {
				var job = $(obj);
				
				str += "<input type='hidden' name='attachList[" + idx + "].uuid' value='" + job.data("uuid") + "'>";
				str += "<input type='hidden' name='attachList[" + idx + "].uploadPath' value='" + job.data("path") + "'>";
				str += "<input type='hidden' name='attachList[" + idx + "].fileName' value='" + job.data("filename") + "'>";
				str += "<input type='hidden' name='attachList[" + idx + "].fileType' value='" + job.data("type") + "'>";
			})
			
			console.log("form 태그 삽입 전 : " + str);
			
			form.append(str);
		}else if(operation == "remove") {
			form.attr('action', '/board/remove');
		}else if(operation == "list") {
			form.find("[name='bno']").remove();
			form.attr('action', '/board/list');
		}
		
		form.submit();
	})
	
	// 첨부파일 가져오기 - 무조건 실행
	$.getJSON({
		url:'getAttachList',
		data:{
			bno: bno
		},
		success:function(data) {
			console.log(data);
			showUploadFile(data);
		}
	})
	
	// x 버튼 클릭 시 화면에서만 첨부 파일 삭제 - 최종 삭제는 modify 버튼 누른 후
	$(".uploadResult").on("click", "button", function() {
		// button 태그가 속해 있는 li 태그 가져오기
		let targetLi = $(this).closest("li");		
		
		if(confirm('파일을 삭제하시겠습니까?')) {
			// li 태그 제거
			targetLi.remove();
		}
	})
})