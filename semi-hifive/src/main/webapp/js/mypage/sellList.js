function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function userId() {
	const userId = sessionStorage.getItem("loginId");
	return userId;
}

const open = () => {
	document.querySelector(".modal").classList.remove("hidden");
}

const close = () => {
	document.querySelector(".modal").classList.add("hidden");
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

// 상품 삭제하기
$(".openBtn").click(e => {
	let productId = $(e.target)[0].id;
	$(".modalDelBtn").click(e => {
		let form = $("<form>").attr("method", "post").attr("action", getContextPath() + "/mypage/deleteProduct.do");
		let input = $("<input>").attr("type", "hidden").attr("name", "productId").val(productId);
		let input2 = $("<input>").attr("type", "hidden").attr("name", "userId").val(userId());
		form.append(input);
		form.append(input2);
		form.appendTo("body").submit();
		form.submit();
	});
})


// 변경 전 value
let nowStatus;
$(".selectStatus").focus(e=>{
	nowStatus = $(e.target).val();
});


// 판매중, 예약중, 판매완료 데이터 변경 ajax
$(".selectStatus").change(e => {

	const selectValue = $(e.target).val();
	/* i = 인덱스, v = $(e.target).children() 배열 */
	/* option 3개 순회 */
	/* 클라이언트가 선택한 value가 option value와 같다면 */
	/* 옵션 id값 가져옴 */
	/*$(e.target).children().each((i,v)=>{
		if(selectValue==v.value){
			productId=v.id
		}
	});*/

	let productId = $(e.target).children()[0].id;
	let color = $(e.target).find('option:selected').data('color');

	// 판매완료 선택 시
	if (selectValue === 'soldOut') {
		// 새 창 열기
		var width = '450';
		var height = '370';
		// 팝업창 가운데
		let left = Math.ceil((window.screen.width - width) / 2);
		let top = Math.ceil((window.screen.height - height) / 2);

		window.open(getContextPath() + "/mypage/buyerIdChoice.do?userId=" + userId() + "&productId=" + productId, "_blank", 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
		$("#sellTarget").val(productId);
		$("#nowStatus").val(nowStatus);
		//openWin.document.getElementById("target").value = productId;

		// 부모창 데이터 자식창으로 보내기
		/*var data = {
			productId: productId
		};

		var childWindow = window.open(getContextPath() + "/mypage/buyerIdChoice.do", "_blank", 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);

		childWindow.postMessage(data, "*");*/

	} else {
		// 판매중, 예약중 선택 시(바로 변경)
		$.ajax({
			url: getContextPath() + "/mypage/ajaxSelect.do",
			data: {
				"selectValue": selectValue,
				"productId": productId,
				"userId": userId()
			},
			success: (data) => {
				$(e.target).css({
					border: "2px solid " + color,
					color: color
				});

				$("#allBtn").text("전체 " + data.total);
				$("#sellBtn").text("판매중 " + data.countStatusSell);
				$("#resBtn").text("예약중 " + data.countStatusRes);
				$("#solBtn").text("판매완료 " + data.countStatusSol);

				console.log("판매상태 변경 성공");
			},
			error: function() {
				alert("판매상태 변경 실패");
			}
		});
	}

})

// 판매상태 별 이동
$("#allBtn").click(e => {
	location.assign(getContextPath() + "/myPage/sellList.do?userId=" + userId());
});

$("#sellBtn").click(e => {
	location.assign(getContextPath() + "/mypage/sellBtn.do?userId=" + userId());
});

$("#resBtn").click(e => {
	location.assign(getContextPath() + "/mypage/resBtn.do?userId=" + userId());
});

$("#solBtn").click(e => {
	location.assign(getContextPath() + "/mypage/solBtn.do?userId=" + userId());
});

// 상품 수정 링크로 이동
$(".updateBtn").click(e => {
	const productId = $(e.target).next().attr('id');

	let form = $("<form>").attr("method", "post").attr("action", getContextPath() + "/productUpdate.do");
	let input = $("<input>").attr("type", "hidden").attr("name", "productId").val(productId);
	form.append(input);
	form.appendTo("body").submit();
	form.submit();
});