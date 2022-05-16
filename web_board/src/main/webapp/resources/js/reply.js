/**
 * read.jsp 댓글 스크립트
 */

// 모듈화 : server쪽으로 ajax 요청을 모아둔 함수
let replyService=(function(){
	function add(reply, callback) { // 자바스크립트는 function 자체를 매개변수로 던질 수 있다
		console.log('add method 실행');
		
		$.ajax({
			url:'/replies/new',
			type:'post',
			beforeSend:function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})
	} /* add 종료 */
		
	function getList(param, callback) {
		let bno = param.bno;
		let page = param.page;
		
		$.getJSON({
			url:'/replies/pages/' + bno + "/" + page,
			success:function(result) {
				if(callback) {
					callback(result.replyCnt, result.list);
				}
			}
		})
	} /* getList 종료 */
	
	function get(rno, callback) {
		$.getJSON({
			url:'/replies/' + rno,
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})
	} /* get 종료 */
	
	function update(reply, callback) {
		$.ajax({
			url:'/replies/' + reply.rno,
			type:'put',
			beforeSend:function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})
	} /* update 종료 */
	
	function remove(rno, replyer, callback) {
		$.ajax({
			url:'/replies/' + rno,
			type:'delete',
			beforeSend:function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			contentType:'application/json',
			data:JSON.stringify({
				replyer:replyer
			}),
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})
	} /* remove 종료 */
	
	function displayTime(timeValue) {
		// ms 단위 변환 => ex) 댓글 단 날짜가 오늘이라면 시:분:초 / 오늘이 아니라면 연:월:일
		let today = new Date();
		let gap = today.getTime() - timeValue;
		let dateObject = new Date(timeValue);
		
		let str = "";
		if(gap < (1000 * 60 * 60 * 24)) {
			let hh = dateObject.getHours();
			let mm = dateObject.getMinutes();
			let ss = dateObject.getSeconds();
			return [(hh > 9 ? '' : '0')+hh, ':', (mm > 9 ? '' : '0')+mm, ':', (ss > 9 ? '' : '0')+ss].join('');
		}else {
			let yy = dateObject.getFullYear();
			let mm = dateObject.getMonth()+1;
			let dd = dateObject.getDate();
			return [yy, '/', (mm > 9 ? '' : '0')+mm, '/', (dd > 9 ? '' : '0')+dd].join('');
		}
	} /* displayTime 종료 */
	
	return {
		add:add,
		getList:getList,
		get:get,
		update:update,
		remove:remove,
		displayTime:displayTime
	}
	
})();

$(function() {
	// 댓글 리스트 영역 가져오기
	let replyUl = $(".chat");
	
	// 댓글 페이지 영역 가져오기
	let pageFooter = $(".panel-footer");
	
	// 현재 페이지 정보
	let pageNum = 1;
	
	// 댓글 전체 가져오기 호출
	showList(1);
	
	// modal 영역 가져오기
	let modal = $(".modal");
	
	// modal 안에 있는 input 요소 찾기
	let modalInputReply = modal.find("input[name='reply']");
	let modalInputReplyer = modal.find("input[name='replyer']");
	let modalInputReplyDate = modal.find("input[name='replydate']");
	
	// New Reply 클릭 시
	$("#addReplyBtn").click(function() {
		// input 태그가 가지고 있는 val 지우기
		modal.find("input").val("");
		
		// login 사용자 보여주기
		modalInputReplyer.val(replyer);
		
		// 날짜 숨기기
		modalInputReplyDate.closest("div").hide();
		
		// 등록, 닫기 버튼만 보여주기
		// 닫기 버튼을 제외한 모든 버튼 숨기기
		modal.find("button[id!='modalCloseBtn']").hide();
		// 등록 버튼 보여주기
		modal.find("#modalRegisterBtn").show();
		
		// modal 창 보여주기
		modal.modal("show");
		
	})
	
	// 댓글 modal 등록 버튼 클릭 시
	$("#modalRegisterBtn").click(function() {
		let reply = {
			bno:bno,
			reply:modalInputReply.val(),
			replyer:modalInputReplyer.val()
		};
		
		replyService.add(reply, function(result) {
			if(result) {
				// 댓글 등록 성공 시 modal input에 있는 내용 지우기
				modal.find("input").val("");
				
				// modal 숨기기
				modal.modal("hide");
				
				// list 호출
				showList(-1);
			}
		});
	})
		
	function showList(page) {
		// page:page||1 => page 변수 값이 들어오면 그 값을 사용하고, 없으면 1
		replyService.getList({bno:bno, page:page||1}, function(total, list){
			// 새 댓글 등록 시
			if(page == -1) {
				pageNum = Math.ceil(total / 10.0);
				showList(pageNum);
				return;
			}
			
			if(list == null || list.length == 0) {
				replyUl.html("");
				return;
			}
			
			let str = "";
			for(var i = 0; i < list.length; i++) {
				str += '<li class="left clearfix" data-rno="'+ list[i].rno +'">';
				str += '<div>';
				str += '<div class="header">';
				str += '<strong class="primary-font">'+ list[i].replyer +'</strong>';
				str += '<small class="pull-right text-muted">'+ replyService.displayTime(list[i].replydate) +'</small>';
				str += '</div>';
				str += '<p>'+ list[i].reply +'</p>';
				str += '</div></li>';
			}
			
			replyUl.html(str);
			
			// 페이지 나누기를 위한 함수 호출
			showReplyPage(total);
		});
	}
	
	// 댓글 페이지 나누기
	// 제일 처음 쓴 댓글이 먼저 보임
	function showReplyPage(total) {
		let endPage = Math.ceil(pageNum / 10.0) * 10;
		let startPage = endPage - 9;
		let prev = startPage != 1;
		let next = false;
				
		if(endPage * 10 >= total) {
			endPage = Math.ceil(total / 10.0);
		}
		
		if(endPage * 10 < total) {
			next = true;
		}
		
		let str = '<ul class="pagination pull-right">';
		if(prev) {
			str += '<li class="paginate_button previous">';
			str += '<a href="' + (startPage-1) + '">Previous</a></li>';
		}
		
		for(let i = startPage; i <= endPage; i++) {
			let active = pageNum == i ? 'active' : '';
			str +='<li class="paginate_button '+ active +'">';
			str += '<a href="' + i + '">' + i + '</a></li>';
		}
		
		if(next) {
			str += '<li class="paginate_button next">';
			str += '<a href="' + (endPage+1) + '">Next</a></li>';
		}
		
		str += '</ul>';
		
		pageFooter.html(str);
	}
	
	// 댓글 페이지 나누기 클릭 시
	pageFooter.on("click", "li a", function(e) {
		e.preventDefault(); // a 태그 기능 중지
		
		pageNum = $(this).attr("href");
		showList(pageNum);
	})
	
	// 댓글 클릭 이벤트
	// 이벤트 위임 : chat을 이용해 이벤트를 걸고, 실제 이벤트 대상은 li 태그가 됨
	$(".chat").on("click", "li", function() {
		// 사용자가 클릭한 li 안의 rno 값 가져오기
		let rno = $(this).data("rno");
		
		replyService.get(rno, function(data) {
			// 도착한 댓글을 modal에 보여주기
			modalInputReply.val(data.reply);
			modalInputReplyer.val(data.replyer);
			modalInputReplyDate.val(replyService.displayTime(data.replydate)).attr("readonly", "readonly");
			
			// 수정이나 삭제 시 rno가 따라가야 하기 때문에 rno 값 추가
			modal.data("rno", data.rno);
			
			// 날짜 보여주기
			modalInputReplyDate.closest("div").show();
			
			// 닫기 버튼을 제외한 모든 버튼 숨기기
			modal.find("button[id!='modalCloseBtn']").hide();
			
			// 수정 버튼 보여주기
			modal.find("#modalModBtn").show();
			
			// 삭제 버튼 보여주기
			modal.find("#modalRemoveBtn").show();
			
			modal.modal("show");
		})
	})
	
	// modal이 가지고 있는 rno에 해당하는 댓글 수정하기
	$("#modalModBtn").click(function() {
		// replyer 값을 가지고 있는지 확인
		if(!replyer) {
			alert('로그인 후 수정 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		// 로그인 사용자(replyer)와 댓글 작성자(modalInputReplyer)가 같은 사람인지 확인
		if(modalInputReplyer.val() != replyer) {
			alert('본인 댓글만 수정 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		let reply = {
			rno:modal.data("rno"),
			reply:modalInputReply.val(),
			replyer:replyer
		};
		
		replyService.update(reply, function(result) {
			if(result) {
				alert(result);
				// 수정 성공 시 modal창 닫기
				modal.modal("hide");
				// 리스트 보여주기
				// showList(1);
				
				// 페이지 나누기 후 
				showList(pageNum);
			}
		})
	})
	
	// modal이 가지고 있는 rno에 해당하는 댓글 삭제하기
	$("#modalRemoveBtn").click(function(){
		// replyer 값을 가지고 있는지 확인
		if(!replyer) {
			alert('로그인 후 삭제가 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		// 로그인 사용자(replyer)와 댓글 작성자(modalInputReplyer)가 같은 사람인지 확인
		if(modalInputReplyer.val() != replyer) {
			alert('본인 댓글만 삭제 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		replyService.remove(modal.data("rno"), replyer, function(result) {
			if(result) {
				alert(result);
				// 삭제 성공 시 modal창 닫기
				modal.modal("hide");
				// 리스트 보여주기
				// showList(1);
				
				// 페이지 나누기 후 
				showList(pageNum);
			}
		})
	})
})