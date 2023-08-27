//대댓글 토글
$(document).on("click",".recommentBtn",function(e){
	$(".recomment").find(".commentWrite").remove();
	const write=$(".commentWrite").first().clone();
	const div=$(e.target).parent().nextAll(".recomment").first();
	div.prepend(write.attr("placeholder","댓글을 작성하세요."));
	div.find("input[type=hidden]").val($(e.target).next(".commentPK").val()); //대댓글 번호값 부여
});

//댓글 수정 버튼 생성
$(document).on("click",".commentUpdateBtn",e=>{
	const $content=$(e.target).parent().prev(); //div commentContent
	$content.find(".commentWrite").remove();
	const data=$content.children("p").text().trim(); //공백 들어가는 거 지움
	
	if($content.find("p").css("display")=="none"&&
		($content.find("button").css("display")=="none"||$content.find("button").css("display")!="block")){
		$content.find("p").css("display","block"); //댓글 출력
		$content.find("button").css("display","block");
		return;
	}else{
		$content.find("p").css("display","none"); //댓글 출력 안보이게함
		$content.find("button").css("display","none");
	}
	const write=$(".commentWrite").clone().first();
	write.find("textarea").val(data); //작성됐던 내용 넣어줌
	$content.append(write);
	$content.find(".commentBtn").attr("class","updateCommentData");
	$(".updateCommentData").text("수정");
});

