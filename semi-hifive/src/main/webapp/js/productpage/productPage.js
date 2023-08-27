//댓글창

/*function contentInput(e) {
	const length = $(e.target).val().length;
	if (length > 100) {
		alert("100글자이하로 작성하세요!");
		$(e.target).val($(e.target).val().substring(0, 100));
	}
	$(e.target)
		.next()
		.text(`${$(e.target).val().length}/100`);
	//console.log(length);
	if (length == 0) {
		$(".textContainer span").css("color", "#afafaf");
		$(".cmtBtn").css({ color: "#afafaf", "background-color": "#eeeeee" });
	} else if (length >= 1) {
		$(".textContainer span").css("color", "#20c997");
		$(".cmtBtn").css({ color: "white", "background-color": "#20c997" });
	}
}*/
const userId = sessionStorage.getItem("userId")

$(document).ready(function() {
	$(".textContainer").find("textarea").keyup();
	const price = $("#productPrice")
		.html()
		.toString()
		.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	$("#productPrice").html(price);

	if ($("#productStatus p").text() == "예약중") {
		$("#productStatus").css({ color: "orange" })
	} else if ($("#productStatus p").text() == "판매완료") {
		$("#productStatus").css({ color: "#afafaf" })
	}
	console.log(($("#userManner b").text()).substr(0, 4))
	if (($("#userManner b").text()).substr(0, 4) >= 50) {
		$("#userManner ion-icon").css("color", "orange")
	}
	if (($("#userManner b").text()).substr(0, 4) >= 75) {
		$("#userManner ion-icon").css("color", "red")
	}

	/*if (loginId.length == 0) {
		$("#heartBtn >button").click(function() {
			alert("로그인 하세요")
		})
	} else {
		$("#heartBtn >button").prop("disabled", false)
	}*/

	const hc = $(".heartCheck").val()
	if (hc == "heartOn") {
		$("#heartBtn button").addClass("active")
		/*		$("#heartBtn >button").click(function(){
					deleteAjaxHeart()
				})*/
	}
	
	if(loginId==userId){
		$("#kakaoBtn>button").prop("disabled", true)
	}
	
	const sellStatus=$("input[name=sellStatus]").val()
	console.log(sellStatus)
	if(sellStatus=="판매완료"||loginId==userId||loginId.length==0){
		$("#kakaoBtn>button").css("background-color","#afafaf")
		$("#heartBtn>button").css("background-color","#afafaf")
		$("#kakaoBtn").unbind('click')
		$("#heartBtn button").off()
		$("#heartBtn button").attr("disabled", true)
		$("#kakaoBtn button").attr("disabled", true)
		$("#kakaoBtn b").attr("disabled", true)
	}
	
});




/*답글기능*/
$(document).on("click", ".cancelBtn", e => {
	$(e.target).parents(".cmtForm").remove();
})

const cmtForm = $(".cmtForm").clone();

$(document).on("click", ".writeCmt", e => {
	cmtForm.find(".updateBtn").attr("class", "cmtBtn");
	cmtForm.find(".cmtBtn").html("등록");
	const commentRef = $(e.target).val();
	console.log(commentRef);
	cmtForm.find("#cmtText").val("");
	cmtForm.find("input[name=level]").val("2");
	cmtForm.find("input[name=commentRef]").val(commentRef);

	//$(e.target).parents("div.cmtContainer").css("margin-bottom", "-10px");

	cmtForm.insertAfter($(e.target).parents(".cmtContainer"));
	cmtForm.insertAfter($(e.target).parents(".reComment"));

	$(".cancelBtn").not($(".cmtForm:first .cancelBtn")).css("display", "inline-block");
})

$(document).on("click", ".changeCmt", e => {
	cmtForm.find(".cmtBtn").attr("class", "updateBtn");
	cmtForm.find(".updateBtn").html("수정");

	let text = "";
	if ($(e.target).parent("div").attr("class") == "cmtContainer") {
		text = $(e.target).parent(".cmtContainer").find(".cmt").text()
		cmtForm.find("#cmtText").val(text);
	} else if ($(e.target).parent("div").attr("class") == "reComment") {
		text = $(e.target).parent(".reComment").find(".cmt").text()
		cmtForm.find("#cmtText").val(text);
	}
	console.log(text)

	cmtForm.insertAfter($(e.target).parents(".cmtContainer"));
	cmtForm.insertAfter($(e.target).parents(".reComment"));
	$(".cancelBtn").not($(".cmtForm:first .cancelBtn")).css("display", "inline-block");

})


$(document).on("click", ".deleteCmt", e => {
	const commentNo = $(e.target).parents("div").children("input[name=commentNo]").val();
	const productId = $("input[name=productId]").val();
	if (!confirm('댓글을 삭제 하시겠습니까?')) {

	} else {
		location.href = getContextPath() + "/deleteComment?commentNo=" + commentNo + "&&productId=" + productId;
	}
});




function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};


//AJAX JSON
//const loginId = sessionStorage.getItem("loginId");
;
var cc = Number(sessionStorage.getItem("commentCount"));
//댓글 작성

/*$(function(){
	cmtBtnOff();
	if($("#cmtText").val().length()){
		cmtBtnOn()		
	}else{
		cmtBtnOff();
	}
})


function cmtBtnOff(){
	$(".cmtBtn").attr("disabled", true);
}
function cmtBtnOn(){
	$(".cmtBtn").attr("disabled", false);
}
*/
$(document).on("click", ".cmtBtn", e => {
	//console.log("e",e.target);
	$.ajax({
		type: "POST",
		url: getContextPath() + "/insertAjaxComment",
		dataType: "json",
		data: {
			"productId": $("input[name=productId]").val(),
			"userId": $("input[name=userId]").val(),
			"level": $(e.target).parents(".textContainer").find("input[name=level]").val(),
			"nickName": $("input[name=nickName").val(),
			"commentRef": $(e.target).parents(".textContainer").find("input[name=commentRef]").val(),
			"content": $(e.target).parents(".textContainer").find("#cmtText").val(),
		},
		success: function(result) {
			if (result > 0) {
				console.log($(e.target).parents(".cmtForm").find("input[name=level]").val());
				const cn = $(e.target).parents(".cmtForm").prev("div").find("input[name=commentNo]").val()
				if ($(e.target).parents(".textContainer").find("input[name=level]").val() == 1) {
					selectAjaxProductComment();
				}
				else {
					selectReAjaxProductComment(cn);
				}
				console.log(cn)

				$(".commentCount").html(cc = cc + 1)
			}
		},
		error: function() {
			alert("댓글 등록 실패")
		}, complete: function() {
			$("#cmtText").val("");
			$(".cmtForm").not($(".cmtForm:first")).remove();
		}
	})
})

//새로 등록된 댓글 출력
/*$(function() {
	selectCmt();
	setInterval(selectCmt, 1000)//1초마다 갱신

})*/
function selectAjaxProductComment() {
	$.ajax({
		url: getContextPath() + "/selectAjaxComment",
		type: "POST",
		dataType: "json",
		data: { "productId": $("input[name=productId]").val() },
		success: function(ajaxComment) {

			var html = "";
			console.log($("input[name=productId]").val())
			console.log(ajaxComment.content)
			html +=
				"<div class='cmtContainer'> " +
				"<div class='cmtProfile'>" +
				"<a href='"+getContextPath()+"/shop?id="+ajaxComment.userId+"'>" +
				"<img name='userProfile' src='" + getContextPath() + "/upload/profileImg/" + ajaxComment.profileImg + "' alt='' />" +
				"</a>" +
				"<a href='"+getContextPath()+"/shop?id="+ajaxComment.userId+"'class='cmtUser' name='userId' id='tagName'>" + ajaxComment.nickName
			if (userId == ajaxComment.userId) {
				html +=
					"<span id='rcmtWriter'>" + " 작성자" + "</span>"
			}

			html +=
				"</a>" +
				"</div>" +
				"<p class='cmt' name='content''>" + ajaxComment.content + "</p>" +
				"<span class='time' name='enrollDate''>" + ajaxComment.enrollDate + "</span>" +
				"<button class='writeCmt' value='" + ajaxComment.commentNo + "'>" + "답글쓰기" + "</button>" +
				"<button class='changeCmt'>" + "수정하기" + "</button>" +
				"<button class='deleteCmt'>" + "삭제하기" + "</button>" +
				"<input type='hidden' name='commentNo' value='" + ajaxComment.commentNo + "'>" +
				"<input type='hidden' name='commentRef2' value='" + ajaxComment.commentRef + "'>" +
				"<hr color='#eeeeee' noshade />" +
				"</div>"

			if ($(".cmtContainer").length) {
				$("#comment").append(html);
			} else {
				$("#comment >hr:first").after(html);
			}
		}, complete: function() {

		}
	})
}

function selectReAjaxProductComment(cn) {
	$.ajax({
		url: getContextPath() + "/selectReAjaxComment",
		type: "POST",
		dataType: "json",
		data: { "productId": $("input[name=productId]").val() },
		success: function(ajaxReComment) {
			var html = "";
			html +=
				"<div id='arrow'></div>" +
				"<div class='reComment'> " +
				"<div class='cmtProfile'>" +
				"<a href='"+getContextPath()+"/shop?id="+ajaxReComment.userId+"'>" +
				"<img name='userProfile' src='" + getContextPath() + "/upload/profileImg/" + ajaxReComment.profileImg + "' alt='' />" +
				"</a>" +
				"<a href='"+getContextPath()+"/shop?id="+ajaxReComment.userId+"' class='cmtUser' name='userId' id='tagName'>" + ajaxReComment.nickName
			if (userId == ajaxReComment.userId) {
				html +=
					"<span id='rcmtWriter'>" + " 작성자" + "</span>"
			}
			html +=
				"</a>" +
				"</div>" +
				"<p class='cmt' name='content''>" + ajaxReComment.content + "</p>" +
				"<span class='time' name='enrollDate''>" + ajaxReComment.enrollDate + "</span>" +
				"<button class='writeCmt' value='" + ajaxReComment.commentNo + "'>" + "답글쓰기" + "</button>" +
				"<button class='changeCmt'>" + "수정하기" + "</button>" +
				"<button class='deleteCmt'>" + "삭제하기" + "</button>" +
				"<input type='hidden' name='commentNo' value='" + ajaxReComment.commentNo + "'>" +
				"<input type='hidden' name='commentRef2' value='" + ajaxReComment.commentRef + "'>" +
				"<hr color='#eeeeee' noshade />" +
				"</div>"


			console.log(cn);
			const replycoment = ($($('input[value=' + cn + '][name=commentNo]')).parent("div").nextUntil("div.cmtContainer"));
			if (replycoment.length > 0)
				$($('input[value=' + cn + '][name=commentNo]')).parent("div").nextUntil("div.cmtContainer").last().after(html);
			else
				$($('input[value=' + cn + '][name=commentNo]')).parent("div").after(html);
		}
	})
}

$(document).on("click", ".updateBtn", e => {
	$.ajax({
		type: "POST",
		url: getContextPath() + "/updateAjaxComment",
		dataType: "json",
		data: {
			"commentNo": $(e.target).parents(".cmtForm").prev("div").find("input[name=commentNo]").val(),
			"productId": $("input[name=productId]").val(),
			"content": $(e.target).parents(".textContainer").find("#cmtText").val()
		},
		success: function(result) {
			const cn = $(e.target).parents(".cmtForm").prev("div").find("input[name=commentNo]").val()
			if (result > 0) {
				updatetAjaxProductComment(cn);
				console.log(cn)
			}
		},
		error: function() {
			alert("댓글 수정 실패")
		}, complete: function() {

			console.log($(e.target).parents(".cmtForm").prev("div").find("input[name=commentNo]").val())
			$(".cmtForm").not($(".cmtForm:first")).remove();
		}
	})
})

function updatetAjaxProductComment(cn) {
	$.ajax({
		url: getContextPath() + "/updateSelectAjaxComment",
		type: "POST",
		dataType: "json",
		data: { "productComment": cn },
		success: function(updateAjaxComment) {
			if (!confirm('댓글을 수정 하시겠습니까?')) {

			} else {
				if ($("input[name=commentNo][value=" + cn + "]").parent("div").attr("class") == "cmtContainer") {
					$("input[name=commentNo][value=" + cn + "]").parent(".cmtContainer").find(".cmt").html(updateAjaxComment.content);
					//$("input[name=commentNo][value=" + cn + "]").parent(".cmtContainer").find(".cmt").after("<span id='cmtOn'>")
					//$("input[name=commentNo][value=" + cn + "]").parent(".cmtContainer").find("#cmtOn").text("(수정됨)")
				} else {
					$("input[name=commentNo][value=" + cn + "]").parent(".reComment").find(".cmt").html(updateAjaxComment.content);
				}
			}
		}
	})
}

$(document).on("click", "#heartBtn button", function(e){
	console.log($(this).attr('class'));
	if($(this).attr('class')=='active'){
		deleteAjaxHeart();
	}else{
		updateAjaxHeart();
	}
	$(this).toggleClass('active');
	
//	$.ajax({
//		type: "POST",
//		url: getContextPath() + "/ajaxHeart",
//		dataType: "json",
//		data: {
//			"loginId": loginId,
//			"productId": $("input[name=productId]").val(),
//		},
//		success: function(result) {
//			console.log(loginId)
//			console.log($("input[name=productId]").val())
//			console.log(result)
//			if (result.length == 0) {
//				updateAjaxHeart();
//
//			} else {
//				deleteAjaxHeart();
//			}
//		},
//		error: function() {
//
//		}, complete: function() {
//
//		}
//	})
})

function updateAjaxHeart() {
	$.ajax({
		type: "POST",
		url: getContextPath() + "/updateAjaxHeart",
		dataType: "json",
		data: {
			"loginId": loginId,
			"productId": $("input[name=productId]").val(),
		},
		success: function(result) {
			console.log($("#heartCount").html())
			if (result > 0) {
				$("#heartBtn button").css("background-color", "rgba(255,0,0,0.2)")
				$("#heartCount").html(result);
				$('#heartBtn button').mouseover(function() {
					$(this).css("background-color", "#afafaf");
				})
				$('#heartBtn button').mouseout(function() {
					$(this).css("background-color", "rgba(255,0,0,0.2)");
				})
			}

		}
	})
}

function deleteAjaxHeart() {
	$.ajax({
		type: "POST",
		url: getContextPath() + "/deleteAjaxHeart",
		dataType: "json",
		data: {
			"loginId": loginId,
			"productId": $("input[name=productId]").val(),
		},
		success: function(result) {
			$("#heartBtn button").css("background-color", "#afafaf")
			$("#heartCount").html(result)
			$('#heartBtn button').mouseover(function() {
				$(this).css("background-color", "rgba(255,0,0,0.2)");
			})
			$('#heartBtn button').mouseout(function() {
				$(this).css("background-color", "#afafaf");
			})

		}
	})
}

$("#pDelete").click(e => {
	const id = $("input[name=ajaxProductId]").val();
	console.log(id)
	if (confirm('상품을 삭제 하시겠습니까?')) {
		$.ajax({
			type: "POST",
			url: getContextPath() + "/deleteproduct",
			dataType: "json",
			data: {
				"productId": id,
			},
			success: function(result) {
				if (result > 0) {
					$("section").empty();
					const html ="<div id='notProduct'>" 
						+"<h2><span>존재하지 않는</span> 상품입니다....</h2>"
						+ "<img src='images/common/hifiveCharacter.png'>"
						+ "</div>"
					$("section").append(html)
				}
			}
		})
	}

})


/*$(document).on("click", ".deleteCmt", e => {
	let deletecommentNo = "";
	if ($(e.target).parent("div").attr("class") == "reComment") {
		deletecommentNo = $(e.target).parents(".reComment").find("input[name=commentNo]").val()
	} else {
		deletecommentNo = $(e.target).parents(".cmtContainer").find("input[name=commentNo]").val()
	}
	console.log(deletecommentNo)
	$.ajax({
		type: "POST",
		url: getContextPath() + "/deleteAjaxComment",
		dataType: "json",
		data: {
			"commentNo": deletecommentNo
		},
		success: function(result) {
			if (!confirm('댓글을 삭제 하시겠습니까?')) {

			} else {
				if (result > 0) {
					$(".cmtContainer,.reComment,#arrow").remove(length)
					selectAllAjaxProductComment()

					
										if ($(e.target).parent("div").attr("class") == "reComment") {
											$(e.target).parents(".reComment").remove();
										} else {
											$(e.target).parents(".cmtContainer").prev("#arrow").remove();
											$(e.target).parents(".cmtContainer").remove();
					
											const ref = $(e.target).parents(".cmtContainer").find("input[name=commentNo]").val()
											const ref2 = $("input[name=commentRef2][value=" + ref + "]").parents(".reComment").find("input[name=commentNo]").val()
											console.log(ref)
											console.log(ref2)
											$("input[name=commentRef2][value=" + ref + "]").parents(".reComment").prev("#arrow").remove()
											$("input[name=commentRef2][value=" + ref + "]").parents(".reComment").remove()
											$("input[name=commentRef2][value=" + ref2 + "]").parents(".reComment").prev("#arrow").remove()
											$("input[name=commentRef2][value=" + ref2 + "]").parents(".reComment").remove()
										}
				}
			}
		},
		error: function() {
			alert("댓글 삭제 실패")
		}, complete: function() {
		}
	})
})*/



