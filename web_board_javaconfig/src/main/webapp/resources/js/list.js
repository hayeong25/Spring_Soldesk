/**
 * list.jsp 스크립트
 */
$(function() {
	// regBtn 클릭 시 /board/register로 움직이기
	$("#regBtn").click(function() {
		location.href="/board/register";
	})
	
	// 게시물 등록 시 Modal 창 띄우기
	checkModal(result);
	
	history.replaceState({}, null, null);
	
	function checkModal(result) {
		if(result == '' || history.state) {
			return;
		}
		if(parseInt(result) > 0) {
			$(".modal-body").html(result + "번 게시물이 등록되었습니다.");
		}
		$("#myModal").modal("show"); // modal 감출 때는 "hide"
	}
	
	// 페이지 이동 버튼 클릭
	let actionForm = $("#actionForm");
	$(".paginate_button a").click(function(e) {
		e.preventDefault(); // a 태그 속성 중지
		// bno가 있는 경우 제거
		actionForm.find("[name='bno']").remove();
		
		// list로 경로 수정
		actionForm.attr("action", "/board/list");
		
		// 사용자가 선택한 페이지 번호 가져오기
		let pageNum = $(this).attr('href');
		
		// 가져온 번호를 actionForm 안의 pageNum 값으로 대체
		actionForm.find("[name='pageNum']").val(pageNum);
		
		// actionForm 보내기
		actionForm.submit();
	})
	
	// 페이지 목록 개수 클릭
	$(".form-control").change(function() {
		// actionForm 안의 amount값 변경
		actionForm.find("[name='amount']").val($(this).val());
	
		// actionForm 보내기
		actionForm.submit();
	})
	
	// title 클릭시
	$(".move").click(function(e) {
		e.preventDefault(); // a 태그 기능 중지		
		// a의 href 가져오기
		let href = $(this).attr('href');
		
		// actionForm 안에 bno 태그 추가 (값은 href가 가지고 있는 값)
		if(actionForm.find("[name='bno']").length != 0) { // 뒤로 가기 했다가 다시 게시물로 돌아왔을 때 bno가 계속 append 되지 않게
			actionForm.find("[name='bno']").val(href);
		}else {
			actionForm.append("<input type='hidden' name='bno' value='" + href + "'>");			
		}
		
		// actionForm의 action(경로) 지정
		actionForm.attr("action", "/board/read");
		
		// submit();
		actionForm.submit();
	})
	
	// 검색 버튼 클릭 시
	$(".btn-default").click(function(e) {
		let searchForm = $("#searchForm");
		e.preventDefault(); // submit 버튼 기능 중지
		
		// type=""일 경우 alert
		if(searchForm.find("select[name='type']").val() == '') {
			alert('검색 조건을 선택하세요.');
			return false;
		}		
		
		// keyword=null일 경우 alert
		if(searchForm.find("input[name='keyword']").val() == "") {
			alert('검색어를 입력하세요.');
			return false;
		}
		
		// searchForm 안의 pageNum은 1로 변경
		searchForm.find("input[name='pageNum']").val("1");
		
		// submit
		searchForm.submit();
	})
	
})