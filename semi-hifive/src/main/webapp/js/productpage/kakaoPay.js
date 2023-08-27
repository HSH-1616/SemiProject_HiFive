$("#kakaoBtn").click(function () {
		console.log(loginId)
		if(loginId.length==0){
			alert("로그인하세요")
			return false;
		}else{
		var IMP = window.IMP; // 생략가능
		IMP.init('imp01205556'); 
		
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		// ''안에 띄어쓰기 없이 가맹점 식별코드를 붙여넣어주세요. 안그러면 결제창이 안뜹니다.
		IMP.request_pay({
			pg: 'kakaopay.TC0ONETIME',
			pay_method: 'card',
			merchant_uid: 'merchant_' + new Date().getTime(),
			/* 
			 *  merchant_uid에 경우 
			 *  https://docs.iamport.kr/implementation/payment
			 *  위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
			 */
			name: $("input[name=kakaoTitle]").val(),
			// 결제창에서 보여질 이름
			// name: '주문명 : ${auction.a_title}',
			// 위와같이 model에 담은 정보를 넣어 쓸수도 있습니다.
			amount: $("#productPrice").text(),
			// amount: ${bid.b_bid},
			// 가격 
			buyer_name: loginId,
			// 구매자 이름, 구매자 정보도 model값으로 바꿀 수 있습니다.
			// 구매자 정보에 여러가지도 있으므로, 자세한 내용은 맨 위 링크를 참고해주세요.
			buyer_postcode: '123-456',
			}, function (rsp) {
				console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '결제 금액 : ' + rsp.paid_amount;
				const product=$("input[name=ajaxProductId]").val()
				location.href=getContextPath()+"/payment?id="+product+"&&user="+loginId;
			} else {
				var msg = '결제에 실패하였습니다.';ß
				msg += '에러내용 : ' + rsp.error_msg;
			}
			alert(msg);
		});
		}
	});