function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function userId(){
	const userId = sessionStorage.getItem("loginId");
	return userId;
}

// 모달창 열기, 닫기
const open = () => {
	document.querySelector(".modal").classList.remove("hidden");
}

const close = () => {
	document.querySelector(".modal").classList.add("hidden");
	$(".btnSpanDiv input[type=radio]").prop("checked", false);
	$(".reviewMsg").val("");
}


const openBtn = document.querySelectorAll(".openBtn");
openBtn.forEach(e => {
	e.addEventListener("click", open);
})

const closeBtn = document.querySelectorAll(".closeBtn");
closeBtn.forEach(e => {
	e.addEventListener("click", close);
})

const bg = document.querySelectorAll(".bg");
bg.forEach(e => {
	e.addEventListener("click", close);
})


// 상품id 값 설정
let productId = 0;

// 거래후기 유무 확인
$(".reviewBtn").click(e => {
	productId = $(e.target)[0].id;
});

$(".okBtn").click(e => {
	// 좋아요, 보통, 싫어요 선택(super-happy, neutra1, super-sad)
	const rating = document.getElementsByName('rating');  // 이름값(js)
	let radioValue;
	for (var i = 0; i < rating.length; i++) {
		if (rating[i].checked) {
			radioValue = rating[i].value;
			break;
		}
	}

	// 후기 메세지
	let reviewMsg = $(".reviewMsg").val();

	$.ajax({
		url: getContextPath() + "/mypage/reviewWrite.do",
		type: "POST",
		data: {
			"userId": userId(),
			"productId": productId,
			"radioValue": radioValue,
			"reviewMsg": reviewMsg
		},
		success: (data) => {
			$(".btnSpanDiv input[type=radio]").prop("checked", false);
			$(".reviewMsg").val("");
			if (data == "거래후기가 등록되었습니다.") {
				$(".reviewBtn[id='" + productId + "']").prop({
					"disabled": true
				}).css({
					"color": "#ccc",
					"border": "2px solid #ccc",
					"background-color": "white"
				});
			}
			alert(data);
			$(".modal").addClass("hidden");
		}
	});
});


// 후기메세지 최대 글자수 설정
$(".reviewMsg").keyup(e => {
	let reviewMsg = $(".reviewMsg").val();
	if (reviewMsg.length > 30) {
		alert("후기메세지는 최대 30자로 입력해주세요.");
		$(e.target).val(reviewMsg.substring(0, 30));
	}
});