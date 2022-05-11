/**
 * 	로그인 성공 후 상단 메뉴 변경
 */
$(function(){
	// Register, Login 메뉴 삭제
	$("#2, #3").detach();
	
	// Login 성공 메뉴 추가
	let tag = "<span class='navbar-text'>" + name + " 님 반갑습니다.</span>";
	tag += "&nbsp;&nbsp;";
	tag += "<button type='button' id='modify' class='btn btn-success'>비밀번호수정</button>";
	tag += "&nbsp;&nbsp;";
	tag += "<button type='button' id='logout' class='btn btn-primary'>로그아웃</button>";
	tag += "&nbsp;&nbsp;";
	tag += "<button type='button' id='leave' class='btn btn-danger'>회원탈퇴</button>";
	
	$("#navbarCollapse").append(tag);
})


