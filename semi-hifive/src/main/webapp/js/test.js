// 검색기능 (ex구글)
function searchData() {
  let keyword = document.getElementsByClassName("search")[0].value;
  location.href = "https://www.google.co.kr/search?q=" + keyword;
  console.log(keyword);
}
// /검색기능/

// 상단메뉴바
$("#category1").mouseenter(function () {
  $("#sideMenu1").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu1").mouseleave(function () {
  $("#sideMenu1").removeAttr("style");
});

$("#category2").mouseenter(function () {
  $("#sideMenu2").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu2").mouseleave(function () {
  $("#sideMenu2").removeAttr("style");
});

$("#category3").mouseenter(function () {
  $("#sideMenu3").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu3").mouseleave(function () {
  $("#sideMenu3").removeAttr("style");
});

$("#category4").mouseenter(function () {
  $("#sideMenu4").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu4").mouseleave(function () {
  $("#sideMenu4").removeAttr("style");
});

$("#category5").mouseenter(function () {
  $("#sideMenu5").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu5").mouseleave(function () {
  $("#sideMenu5").removeAttr("style");
});

$("#category6").mouseenter(function () {
  $("#sideMenu6").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu6").mouseleave(function () {
  $("#sideMenu6").removeAttr("style");
});

$("#category7").mouseenter(function () {
  $("#sideMenu7").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu7").mouseleave(function () {
  $("#sideMenu7").removeAttr("style");
});

$("#category8").mouseenter(function () {
  $("#sideMenu8").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu8").mouseleave(function () {
  $("#sideMenu8").removeAttr("style");
});

$("#category9").mouseenter(function () {
  $("#sideMenu9").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu10").removeAttr("style");
});

$("#sideMenu9").mouseleave(function () {
  $("#sideMenu9").removeAttr("style");
});

$("#category10").mouseenter(function () {
  $("#sideMenu10").css({
    position: "absolute",
    width: "150px",
    height: "605px",
    top: "50px",
    left: "150px",
    "box-sizing": "border-box",
    "box-shadow": "0px 0px 3px 0px #eeeeee",
    "padding-top": "20px",
    "padding-bottom": "20px",
    fontSize: "15px",
  });
  $("#sideMenu1").removeAttr("style");
  $("#sideMenu2").removeAttr("style");
  $("#sideMenu3").removeAttr("style");
  $("#sideMenu4").removeAttr("style");
  $("#sideMenu5").removeAttr("style");
  $("#sideMenu6").removeAttr("style");
  $("#sideMenu7").removeAttr("style");
  $("#sideMenu8").removeAttr("style");
  $("#sideMenu9").removeAttr("style");
});

$("#sideMenu10").mouseleave(function () {
  $("#sideMenu10").removeAttr("style");
});

$(document).change(function () {
  if ($("#menuIcon").is(":checked") == false) {
    $("#sideMenu1").removeAttr("style");
    $("#sideMenu2").removeAttr("style");
    $("#sideMenu3").removeAttr("style");
    $("#sideMenu4").removeAttr("style");
    $("#sideMenu5").removeAttr("style");
    $("#sideMenu6").removeAttr("style");
    $("#sideMenu7").removeAttr("style");
    $("#sideMenu8").removeAttr("style");
    $("#sideMenu9").removeAttr("style");
    $("#sideMenu10").removeAttr("style");
  }
});
// /상단메뉴바/
