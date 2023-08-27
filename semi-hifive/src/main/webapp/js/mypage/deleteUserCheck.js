// 비밀번호 정규표현식
const pwd2 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*~()_+])[a-zA-Z\d!@#$%^&*~()_+]{8,16}$/

// 취소버튼 클릭 시 인풋창 초기화
const clearInput = () => {
	$("#password").val("").focus();
}

// 확인버튼 onsubmit
const checkPwd=()=>{
	if(!pwd2.test($("#password").val())){
		alert("비밀번호는 영대소문자, 숫자, 특수기호 포함 8~16글자로 입력해주세요.");
		$("#password").focus();
		return false;
	} else {
		return true;
	}
}