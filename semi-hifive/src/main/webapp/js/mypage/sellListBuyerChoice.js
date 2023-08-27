function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function userId(){
	const userId = sessionStorage.getItem("loginId");
	return userId;
};

$(window).on('beforeunload', function() {
	const productId = opener.document.getElementById("sellTarget").value;
	const nowStatus = opener.document.getElementById("nowStatus").value;
	
	alert("거래자를 선택해주세요.");
	
	opener.$(".selectStatus").each((i,e)=>{
		if(e.id==productId) {
			$(e).val(nowStatus);
		}
	})
});

$(".closeBtn").click(e => {
	const productId = opener.document.getElementById("sellTarget").value;
	const nowStatus = opener.document.getElementById("nowStatus").value;
	
	alert("거래자를 선택해주세요.");
	
	opener.$(".selectStatus").each((i,e)=>{
		if(e.id==productId) {
			$(e).val(nowStatus);
		}
	})
	window.close();
});

let buyerId;
$(".commentList").click(e => {
	buyerId = $(e.target)[0].id;
	$(".commentList").css({
		"background-color": "transparent"
	})
	$(e.target).css({
		"background-color": "#eee"
	})
})

$(".okBtn").click(e => {
	let productId = $(e.target)[0].id;
	console.log(productId);
	console.log(buyerId);
	console.log(userId());
	
	/*$("#solBtn").text("판매완료 " + data.countStatusSol);*/
	
	let form = $("<form>").attr("method", "post").attr("action", getContextPath() + "/mypage/sellListBuyerChoiceEnd.do");
	let input = $("<input>").attr("type", "hidden").attr("name", "productId").val(productId);
	let input2 = $("<input>").attr("type", "hidden").attr("name", "userId").val(userId());
	let input3 = $("<input>").attr("type", "hidden").attr("name", "buyerId").val(buyerId);
	form.append(input);
	form.append(input2);
	form.append(input3);
	form.appendTo("body").submit();
	form.submit();
		
})