function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function userId() {
	const userId = sessionStorage.getItem("loginId");
	return userId;
};

// 이밴트 베너 부분
const nextBtn = document.querySelector('.nextBtn');
const slide = document.querySelector('.slide');
const slideLength = document.querySelectorAll('.slide li').length;
let currentSlide = 1;
const IMAGE_WIDTH = 1280;

// 다음 버튼 클릭
nextBtn.addEventListener('click', next);
function next() {
	console.log(currentSlide);

	if (currentSlide >= slideLength) {
		currentSlide = 0;
	}
	slide.style.transform = `translateX(-${IMAGE_WIDTH * currentSlide}px)`;
	currentSlide++;
}

const prevBtn = document.querySelector(".prevBtn");
// 이전 버튼 클릭
prevBtn.addEventListener('click', prev);
function prev() {
	if (currentSlide === 1) {
		currentSlide = slideLength;
	} else {
		currentSlide--;
	}
	slide.style.transform = `translateX(-${IMAGE_WIDTH * (currentSlide - 1)}px)`;
}

$(".wishCheck").click(e => {
	let productId = $(e.target).attr("id");
	console.log(productId);
	let isChecked = $(e.target).prop('checked');
	console.log(isChecked);

	if (!isChecked) {
		// 하트가 꺼질 경우(=찜 취소) -> 새로고침 시 상품 사라짐
		$.ajax({
			url: getContextPath() + "/myPage/wishListDel.do",
			data: {
				"userId": userId(),
				"productId": productId
			},
			success: (data) => {
				console.log("삭제 완료");
			},
			error: (data) => {
				alert("삭제 실패");
			}
		});
	} else {
		// 취소한거 다시 찜하기
		$.ajax({
			url: getContextPath() + "/mypage/wishListInsert.do",
			data: {
				"userId": userId(),
				"productId": productId
			},
			success: (data) => {
				console.log("추가 완료");
			},
			error: (data) => {
				alert("삭제 실패");
			}
		});
	}
});

// 로그인 안한 회원 찜기능 막기
$(".notLogin").click(e => {
	alert("찜 기능은 로그인 후 이용가능합니다.");
	$(".notLogin").prop("checked", false);
});

/*$(".poMoreBtn").click(e => {
	let conditions = "1+1";
	//location.assign(getContextPath() + "");
	$.ajax({
		url: getContextPath() + "/viewcount",
		dataType: 'html',
		data: conditions,
		success: function(data) {
			$("#contentdata").html(data);
		}
	});
})*/

$(".poMoreBtn").click(e => {
	location.assign(getContextPath() + "/getproduct.do?choice=popular");
});

$(".newMoreBtn").click(e => {
	location.assign(getContextPath() + "/getproduct.do");
});