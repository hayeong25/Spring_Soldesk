/**
 * 
 */
$(function(){
	$("#changePwd").validate({
		rules: {
			password: {
				required: true,
				validPassword: true
			},
			new_password: {
				required: true,
				validPassword: true
			},
			confirm_password: {
				equalTo: "#new_password"
			}
		},
		messages: {
			password: {
				required: "비밀번호는 필수 입력항목입니다."
			},
			new_password: {
				required: "비밀번호는 필수 입력항목입니다."
			},
			confirm_password: {
				equalTo: "비밀번호가 일치하지 않습니다."
			}
		},
		errorPlacement: function(error, element){
			$(element).closest("form")
						.find("small[id='" + element.attr('id') + "']")
						.append(error);	
		}
	})
});

	$.validator.addMethod(
		"validPassword",
		function(data){
			let regPwd = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,15}$/;
					// /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8, 15}$/;
			return regPwd.test(data);
		},
		"Password는 영문숫자 및 한 개 이상의 특수문자로 구성된 8 ~ 15 글자를 입력해주세요."
	);