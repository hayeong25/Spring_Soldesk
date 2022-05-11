/**
 * 
 */
$(function(){
	$("#regist").validate({
		rules: {
			userid: {
				required: true,
				validId: true,
				remote: {
			        url: "/member/checkId",
			        type: "post",
			        data: {
			          	userid: function() {
			          	  	return $("#userid").val();
			          	}
			        }
				}
			},
			password: {
				required: true,
				validPassword: true
			},
			confirm_password: {
				equalTo: "#password"
			},
			name: {
				required: true
			},
			gender: {
				required: true
			},
			email: {
				required: true,
				validEmail: true
			}
		},
		messages: {
			userid: {
				required: "아이디는 필수 입력항목입니다.",
				remote: "이미 사용중인 아이디입니다."
			},
			password: {
				required: "비밀번호는 필수 입력항목입니다."
			},
			confirm_password: {
				equalTo: "비밀번호가 일치하지 않습니다."
			},
			name: {
				required: "이름은 필수 입력항목입니다."
			},
			gender: {
				required: "성별은 필수 선택항목입니다."
			},
			email: {
				required: "이메일은 필수 입력항목입니다."
			}
		},
		errorPlacement: function(error, element){
			$(element).closest("form")
						.find("small[id='" + element.attr('id') + "']")
						.append(error);	
			/* 
				에러메세지를 보여주는 위치 지정 function
					기존 : Error를 발생시키는 요소(input) 다음
					변경 후 : small 태그 안에 error 내용을 입력 (form 안에 small 태그를 따로 생성해 둠)
					
					closet(요소) : 에러가 발생했을 때 요소 중 가장 가까운 곳 지칭
					find(오류가 발생한 요소의 id를 가져와 비교) : 가져온 id 값과 일치하는 지 여부 확인
					append(error) : error 메세지를 find 한 요소에 입력
			*/
		}
	})
});

	$.validator.addMethod(
		"validId",
		function(data){
			let regId = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,12}$/;
					// /^(?=.*[0-9])(?=.*[a-zA-Z])[A-Za-z0-9]{6, 12}$/;
			return regId.test(data);
		},
		"ID는 영문숫자로 구성된 6 ~ 12 글자를 입력해주세요."
	);
	
	$.validator.addMethod(
		"validPassword",
		function(data){
			let regPwd = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,15}$/;
					// /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8, 15}$/;
			return regPwd.test(data);
		},
		"Password는 영문숫자 및 한 개 이상의 특수문자로 구성된 8 ~ 15 글자를 입력해주세요."
	);
	
	$.validator.addMethod(
		"validEmail",
		function(data){
			let regEmail =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			return regEmail.test(data);
		},
		"이메일 양식이 맞지 않습니다."
	);