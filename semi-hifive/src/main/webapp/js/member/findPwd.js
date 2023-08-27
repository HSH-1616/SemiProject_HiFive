const url = "http://localhost:9090/semi-hifive/";



const hiddenEmail = document.getElementById("hiddenEmail");
$("#newPassword").css("display", "none");
$("#newPasswordCheck").css("display", "none");
$("#newPasswordButton").css("display", "none");

$(function() {
	$.ajax({
		url: "findPwdSendEmail",
		data: { "inputEmail": hiddenEmail.value },
		success: function(result) {
			console.log("이메일 발송 성공");
			console.log(result);
			// 인증버튼이 클릭되어 정상적으로 메일이 보내졌음을
			/*			checkObj.sendEmail = true;*/
		},
		error: function() {
			console.log("이메일 발송 실패");
		}
	})
});



const findPwdAuth = document.getElementById("findPwdAuth");
const findPwdAuthButton = document.getElementById("findPwdAuthButton");
const findPwdAuthId = $("#findPwdAuthId");

findPwdAuthButton.addEventListener("click", function() {
	// 1. 인증번호 받기 버튼이 클릭되어 이메일 발송되었는지 확인하기.

	// 2. 입력된 인증번호가 6자리 맞는지 확인
	if (findPwdAuth.value.length == 8) {
		// ajax 송신.
		$.ajax({
			url: "checkNumber",
			data: {
				"cNumber": findPwdAuth.value,
				"inputEmail": hiddenEmail.value
			},
			success: function(result) {
				console.log(result);

				//    1  : 인증번호 O , 시간도 O
				//    2  : 인증번호 O , 시간이 X 
				//    3  : 인증번호 X
				if (result == 1) {
					findPwdAuthId.text("인증되었습니다.").css("color", "green");
					$("#newPassword").css("display", "block");
					$("#newPasswordCheck").css("display", "block");
					$("#newPasswordButton").css("display", "block");

					/*					cNumber.readOnly = true; // 인증완료되면 더이상입력못하도록 막음
										memberEmail.readOnly = true;  // 이메일정보 더이상 입력못함
										sendBtn.disabled = true; // 이메일인증 버튼 비활성화
										cBtn.disabled = true; // 인증하기 버튼 비활성화*/
				} else if (result == 2) {
					alert("만료된 인증번호 입니다");
				} else {
					alert("인증번호가 일치하지 않습니다.");
				}
			},
			error: function() {
				console.log("이메일 인증 실패");
			}
		})
	} else {
		alert("인증번호를 정확하게 입력해주세요.");
		findPwdAuth.focus();
	}
});

// 영대소문자, 숫자, 특수기호 최소 하나씩 8글자 이상
const newPassword = document.getElementById("newPassword");


const newPasswordButton = document.getElementById("newPasswordButton");

newPasswordButton.addEventListener("click", function() {
	/*	if($("#newPassword").val() == $("#newPasswordCheck").val()){
			alert("변경되었습니다.");
		}else if(regExp2.test($("#newPassword").val())){
			alert("영대소문자,숫자,특수기호 포함 8글자이상 입력하세요");
			$("#newPassword").focus();
		}else{
			alert("두개의 비밀번호가 일치하지 않습니다");
			$("#newPassword").focus();
		}*/
	const regExp3 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*~()_+])[a-zA-Z\d!@#$%^&*~()_+]{8,16}$/;
	//console.log(newPassword);	
	if (findPwdAuth.value == $("#newPassword").val() && $("#newPassword").val() == $("#newPasswordCheck").val()) {
		alert("현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.")
		$("#newPassword").focus();
	} else if (!regExp3.test($("#newPassword").val())) {
		alert("영대소문자,숫자,특수기호 포함 8글자이상 입력하세요.");
		$("#newPassword").focus();
	} else if ($("#newPassword").val() == $("#newPasswordCheck").val()) {
		/*location.href = url+"changePassword.do?newPass=" + $("#newPassword").val() + "&email=" + hiddenEmail.value;*/

		$.ajax({
			url: "changePassword.do",
			data: { "password": $("#newPassword").val(),
					"email": hiddenEmail.value},
			success: function(result) {
				console.log("서블릿으로이동성공");
				console.log(result);
				
				if(result!=null){
					alert("비밀번호가 성공적으로 변경되었습니다.");
					location.href = url;
				}
			},
			error: function() {
				console.log("서블릿으로이동실패");
			}
		})


	} else {
		alert("두개의 비밀번호가 일치하지 않습니다.");
		$("#newPassword").focus();
	}
})