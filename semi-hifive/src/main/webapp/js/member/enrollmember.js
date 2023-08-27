// input
/* 약관 동의 모두선택 기능 */
function checkSelectAll() {
	// 전체 체크박스
	const checkboxes // 전체 체크박스의 개수를 세기위해 만듬
		= document.querySelectorAll('input[name="agree"]');

	// 선택된 체크박스
	const checked
		= document.querySelectorAll('input[name="agree"]:checked');
	// select all 체크박스
	const selectAll
		= document.querySelector('input[name="selectall"]');

	if (checkboxes.length === checked.length) {  //  전체 체크개수와 현재 선택된 체크개수가 같으면 ->전체체크 상자에 체크해주고
		selectAll.checked = true;                   // 하나라도 박스에 체크가 빠지면, 전체체크 상자에 체크 표시 x
	} else {
		selectAll.checked = false;
	}
}


/* 팝업창 열게하는 기능 */
function selectAll(selectAll) {  // 이함수 호출시 1,2,3,4 체크부분들 다 체크표시로 설정
	const checkboxes
		= document.getElementsByName('agree');

	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked
	})
}

const fn_viewDetail1 = () => {
	window.open("/semi-hifive/views/enrollMemberPopup/popup1.jsp", "팝업창1", 'width=600, height=600 left=650 top=250'); // left, top -> 원하는 위치에 팝업창이 뜨도록 설정
}

const fn_viewDetail2 = () => {
	window.open("/semi-hifive/views/enrollMemberPopup/popup2.jsp", "팝업창2", 'width=600, height=600 left=650 top=250'); // left, top -> 원하는 위치에 팝업창이 뜨도록 설정
}

const fn_viewDetail3 = () => {
	window.open("/semi-hifive/views/enrollMemberPopup/popup3.jsp", "팝업창3", 'width=600, height=600 left=650 top=250'); // left, top -> 원하는 위치에 팝업창이 뜨도록 설정
}

const fn_viewDetail4 = () => {
	window.open("/semi-hifive/views/enrollMemberPopup/popup4.jsp", "팝업창4", 'width=600, height=600 left=650 top=250'); // left, top -> 원하는 위치에 팝업창이 뜨도록 설정
}




/**/

const checkObj = {  // 해당 회원가입 정보입력할 때, 정상적으로 처리됬는지 구분하는 객체
	"memberEmail": false,  
	"memberPw": false,
	"memberPwConfirm": false,
	"memberId": false,
	"memberName": false,
	"memberNickName": false,
	"sendEmail": false, // 인증번호 성공
	"successNumber": false
};


const memberEmail = document.getElementById("emailId");
/*const emailMessage = document.getElementById("emailMessageId");*/
const emailMessage = $("#emailMessageId");

memberEmail.addEventListener("keyup", function() {
	//입력이 되지 않은경우

	if (memberEmail.value.length == 0) {
		emailMessage.text("");
		checkObj.memberEmail = false;             // 기록할객체에 유효X 기록X  
		return;
	}

	//입력이 된 경우 (이메일 정규표현식)
	const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;


	if (regExp.test(memberEmail.value)) {   // 정규표현식 유효한경유
		// 이메일 중복검사 (ajax) 진행****************

		$.ajax({
			url: "emailDupCheck",  // 필수속성 url , 
			// 현재주소 : /community/member/signUp
			// 상대경로 : /community/member/emailDupCheck
			data: { "memberEmail": memberEmail.value },
			// data속성 : 비동기통신시 서버로 전달할값을 작성(JS객체 형식)
			//  --> 비동기통신시 input에 입력된 값을
			// "memberEmail" 이라는 key값(파라미터) 으로 전달
			success: function(result) {
				// 비동기통신(.ajax)가 오류없이 요청/응답 성공한경우
				// 매개변수 result : Servlet에서 출력된 result값이 담겨있음
				console.log(result);
				if (result == 1) {    // 중복 O
					emailMessage.text("이미 사용중인 이메일입니다.").css("color", "red");

					checkObj.memberEmail = false;
				} else {     // 중복 X
					emailMessage.text("사용 가능한 이메일입니다.").css("color", "green");
					checkObj.memberEmail = true;          // 사용가능함. true 세팅
				}
			},
			error: function() {
				// 비동기통신(.ajax)중 오류가 발생한 경우
				console.log("에러발생");
			}

		});
	} else {            // 정규표현식 유효하지 않은경우
		emailMessage.text("이메일 형식이 유효하지 않습니다!!").css("color", "red");
		checkObj.memberEmail = false;           // 기록할객체에 유효X 기록X  
	}
});



// 인증번호 보내기
const sendBtn = document.getElementById("authEmail");
const timerMessage = document.getElementById("timerMemssageId");
const timerMessageClass = $(".timerMessageClass");

sendBtn.addEventListener("click", function() {

	if (checkObj.memberEmail) {  // 유효한 이메일이 작성되어 있을경우에만 메일보내기
		$.ajax({
			url: "sendEmail",
			data: { "inputEmail": memberEmail.value },
			success: function(result) {
				console.log("이메일 발송 성공");
				console.log(result);

				// 인증버튼이 클릭되어 정상적으로 메일이 보내졌음을
				checkObj.sendEmail = true;
			},
			error: function() {
				console.log("이메일 발송 실패");
				checkObj.sendEmail = false;
			}
		})

		timerMessageClass.text("5:00");// 초기값 5분

		min = 4;
		sec = 59;

		checkInterval = setInterval(function() {
			if (sec < 10) sec = "0" + sec;
			timerMessageClass.text(min + ":" + sec);

			if (Number(sec) === 0) {
				min--;
				sec = 59;
			} else {
				sec--;
			}
			if (min === -1) {
				timerMessageClass.text("인증번호가 만료되었습니다.");
				clearInterval(checkInterval);  // setInterval 함수 반복을 지움.
			}
		}, 1000);   // 1초 지연 후 수행
		alert("인증번호가 발송되었습니다. 이메일을 확인해주세요,,");
	}
});


const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");

cBtn.addEventListener("click", function() {
	// 1. 인증번호 받기 버튼이 클릭되어 이메일 발송되었는지 확인하기.
	if (checkObj.sendEmail) {

		// 2. 입력된 인증번호가 6자리 맞는지 확인
		if (cNumber.value.length == 6) {
			// ajax 송신.
			$.ajax({
				url: "checkNumber",
				data: {
					"cNumber": cNumber.value,
					"inputEmail": memberEmail.value
				},
				success: function(result) {
					console.log(result);

					//    1  : 인증번호 O , 시간도 O
					//    2  : 인증번호 O , 시간이 X 
					//    3  : 인증번호 X
					if (result == 1) {
						clearInterval(checkInterval);  // 제일먼저, 타이머 멈춤
						timerMessageClass.text("인증되었습니다.").css("color","green");
						checkObj.successNumber = true;
						cNumber.readOnly = true; // 인증완료되면 더이상입력못하도록 막음
						memberEmail.readOnly = true;  // 이메일정보 더이상 입력못함
						sendBtn.disabled = true; // 이메일인증 버튼 비활성화
						cBtn.disabled = true; // 인증하기 버튼 비활성화
					} else if (result == 2) {
						checkObj.successNumber = false;
						alert("만료된 인증번호 입니다");
					} else {
						checkObj.successNumber = false;
						alert("인증번호가 일치하지 않습니다.");
					}
				},
				error: function() {
					console.log("이메일 인증 실패");
				}
			})
		} else {
			checkObj.successNumber = false;
			alert("인증번호를 정확하게 입력해주세요.");
			cNumber.focus();
		}
	} else {
		checkObj.successNumber = false;
		alert("인증번호 받기 버튼을 먼저 클릭해주세요.");
	}
});



// 아이디 중복검사 
const regId = /^[a-z0-9]{6,12}$/
const userId_ = document.getElementById("userId_");


userId_.addEventListener("keyup", function() {

	if (regId.test(userId_.value)) {

		$.ajax({
			url: "duplicateId.do",
			data: { "userId": userId_.value },
			success: function(data) {
				if (data === 'true') {
					$("#idDupMessageId").text("사용 가능한 아이디입니다.").css("color", "green");
					checkObj.memberId = true;
				} else {
					$("#idDupMessageId").text("이미 사용중인 아이디입니다.").css("color", "red");
					checkObj.memberId = false;
				}
			},

			error: function(data) {
				console.log(r);
				console.log(m);
			}
		});
	} else {
		$("#idDupMessageId").text("영소문자,숫자로만 6~12글자의 아이디를 입력하세요").css("color", "red");
		checkObj.memberId = false;
	}
})



// 비밀번호 중복검사 및 일치 규칙
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = $("#pwMessage");
/*const regExp2 = /^[\w!@#_-]{6,30}$/;*/
// 영대소문자, 숫자, 특수기호 최소 하나씩 8글자 이상
const regExp2 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*~()_+])[a-zA-Z\d!@#$%^&*~()_+]{8,16}$/

memberPwConfirm.addEventListener("keyup", function() {
	console.log("dd");
	if (memberPw.value.length == 0 || memberPwConfirm.value.length == 0) {
		pwMessage.text("");
	}

	if (regExp2.test(memberPw.value) || regExp2.test(memberPwConfirm.value)) {

		if (memberPw.value == memberPwConfirm.value) {
			pwMessage.text("비밀번호가 일치합니다").css("color", "green");

			checkObj.memberPw = true;
			checkObj.memberPwConfirm = true;

		} else {
			pwMessage.text("비밀번호가 일치하지않습니다").css("color", "red");
			checkObj.memberPw = false;
			checkObj.memberPwConfirm = false;
		}

	}
	else {
		pwMessage.text("영대소문자, 숫자, 특수기호 포함 8글자 이상으로 작성").css("color", "red");
		checkObj.memberPw = false;
		checkObj.memberPwConfirm = false;
	}
});


memberPw.addEventListener("keyup", function() {
	console.log("dd");
	if (memberPw.value.length == 0 || memberPwConfirm.value.length == 0) {
		pwMessage.text("");
	}

	if (regExp2.test(memberPw.value) || regExp2.test(memberPwConfirm.value)) {

		if (memberPw.value == memberPwConfirm.value) {
			pwMessage.text("비밀번호가 일치합니다").css("color", "green");

			checkObj.memberPw = true;
			checkObj.memberPwConfirm = true;

		} else {
			pwMessage.text("영대소문자, 숫자, 특수기호 포함 8글자 이상으로 작성").css("color", "red");
			checkObj.memberPw = false;
			checkObj.memberPwConfirm = false;
		}

	}
	else {
		pwMessage.text("영대소문자, 숫자, 특수기호 포함 8글자 이상으로 작성").css("color", "red");
		checkObj.memberPw = false;
		checkObj.memberPwConfirm = false;
	}
});



// 이름 정규표현식
const regName = /^[가-힣]{2,4}$/
const userNameId = document.getElementById("userNameId");
const userNameMessage = $("#userNameMessage");

userNameId.addEventListener("keyup", function() {
	console.log("dd");
	if (userNameId.value.length == 0) {
		userNameMessage.text("");
	}
	
	if(regName.test(userNameId.value)){
		userNameMessage.text("이름을 정확하게 입력하셨습니다.").css("color","green");
		checkObj.memberName = true;   
	}else{
		userNameMessage.text("이름을 정확하게 입력하세요.").css("color","red");
		checkObj.memberName = false;   
	}
})




// 닉네임 정규표현식
const regNickName = /^[가-힣a-zA-Z0-9]{2,8}$/
const nickNameId = document.getElementById("nickNameId");
const userNickNameMessage = $("#userNickNameMessage");

nickNameId.addEventListener("keyup", function() {
	
	if (regNickName.test(nickNameId.value)) {

		$.ajax({

			url: "duplicateNickName.do",
			data: { "userNickName": nickNameId.value },

			success: function(data) {
				if (data == 1) {
					userNickNameMessage.text("이미 사용중인 닉네임입니다").css("color", "red");
					checkObj.memberNickName = false;
				} else {
					userNickNameMessage.text("사용 가능한 닉네임입니다").css("color", "green");
					checkObj.memberNickName = true;
				}
			},
			error: function(data) {
				console.log(r);
				console.log(m);
			}
		});
	} else {
		userNickNameMessage.text("특수문자 제외하고 2~8자로 입력해주세요").css("color", "red");
		checkObj.memberNickName = false;
	}
})




function fn_registEnrollMember(){ 
	if(checkObj.memberId && checkObj.memberEmail && checkObj.memberName && checkObj.successNumber &&
		checkObj.memberNickName && checkObj.memberPw&& checkObj.sendEmail && checkObj.memberPwConfirm){
			return true;	
	}
	console.log("다 입력해라")
	return false;
}

