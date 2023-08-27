// 비밀번호 정규표현식
const pwd = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*~()_+])[a-zA-Z\d!@#$%^&*~()_+]{8,16}$/

// 눈 아이콘 클릭 시 비밀번호 보이게
$(document).ready(function() {
	$('.eyeIcon').on('click', function() {
		$('.newPwdCheck').toggleClass('active');
		if ($('.newPwdCheck').hasClass('active')) {
			$(this).attr('name', "eye")
				.parents().find('.newPwdCheck').attr('type', "text");
		} else {
			$(this).attr('name', "eye-off")
				.parents().find('.newPwdCheck').attr('type', 'password');
		}
	});
});

// 비밀번호 확인
const fn_pwdCheck = () => {
	if ($(".oriPwd").val() == $(".newPwd").val()) {
		alert("현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
		$(".newPwd").focus();
		return false;
	} else if (!pwd.test($(".newPwd").val())) {
		alert("비밀번호는 영대소문자, 숫자, 특수기호 포함 8~16글자입니다.");
		$(".newPwd").focus();
		return false;
	} else if ($("#newPwd").val() == $("#newPwdCheck").val()) {
		return true;
	} else {
		alert("새 비밀번호가 일치하지 않습니다.");
		$("#newPwd").focus();
		return false;
	}
}

// 비밀번호 변경창 닫기
$(".resetBtn").click(e=>{
	window.close();
});