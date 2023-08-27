
const context = "http://:9090/semi-hifive";


function getContextPath() {
   var hostIndex = location.href.indexOf(location.host) + location.host.length;
   return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};



const checkProductRegist = {  // 상품등록할 때, 각 부분마다 정상적으로 처리됬는지 구분하는 객체 (다 true일경우에만 상품등록됨)
	"productImg": false,  
	"productTitle": false,
	"productPlace": false,
	"productPrice": false,
	"productExplan": false,
};



// 사진 불러오기 작업 

let prouductImgCnt = 0;
const dataTransfer = new DataTransfer();


function getImageFiles(e) {
	
	const uploadFiles = [];

	const files = e.currentTarget.files;
	const imagePreview = document.querySelector('.image-preview');

/*	for(var i=0; i<files.length; i++){  // 한번 이미지 받아올때 dataTransfer에 다 저장함 (그이후 또 받아와도 누적시킴)
		dataTransfer.items.add(files[i]);
	}*/
	
	console.log(dataTransfer);


	// 파일 타입 검사
	[...files].forEach(file => {  // 이미지 파일외 다른 파일 업로드시 (문구 + 이미지 적용 x)
		if (!file.type.match("image/.*")) {
			alert('이미지 파일만 업로드가 가능합니다.');
			return
		}

		// 파일 갯수 검사
		if (dataTransfer.items.length+[...files].length < 11) { // 한번에 10개까지 이미지 등록가능

/*				if (dataTransfer.items.length > 10) {
				alert('이미지는 최대 10개 까지 업로드가 가능합니다.');
				return;
			}*/


			const reader = new FileReader();
			reader.onload = (e) => {
				const preview = createElement(e, file);
				
				imagePreview.appendChild(preview);
				dataTransfer.items.add(file);
			};
			console.log(prouductImgCnt);
			
			uploadFiles.push(file); // 이미지 파일이 7개 미만이면 배열에 파일추가
			prouductImgCnt++;  // 이미지 추가시 개수 증가
			$(".imgCount").text("(" + prouductImgCnt + "/10" + ")");
			reader.readAsDataURL(file);  // 업로드 파일 읽어오기 (이문구 없으면 이미지 추가되지않음)
		}
		else{

		}

	});
	
}


// 태그 만들어주는 함수
function createElement(e, file) {
	const li = document.createElement('li');    // li 태그 만들기
	const img = document.createElement('img');  // img 태그 만들기
	img.setAttribute('src', e.target.result); // 만든 img 태그에 경로 속성 값 넣어줌
	img.setAttribute('data-file', file.name); // 만들 ing 태그에 파일 이름 속성 값 넣어줌
	checkProductRegist.productImg = true; 
	
	
	img.addEventListener("click", e => {  // 해당 이미지 클릭시
		console.log(prouductImgCnt);
		prouductImgCnt--; // 이미지 삭제시 개수 감소
		$(e.target).parent().remove(); // li안의 img까지 삭제
		$(".imgCount").text("(" + prouductImgCnt + "/10" + ")");
		
		 for(var i=0; i<dataTransfer.files.length; i++){
             if(dataTransfer.files[i].name==e.target.dataset.file){
                    dataTransfer.items.remove(i)
                    break;
             }
          }
		
		
		if(dataTransfer.files.length == 0){  
			checkProductRegist.productImg = false; 
		}
		
	});

	li.appendChild(img); // 이미지가 있는 li 태그 완성하여 li 리턴

	return li;
}


window.onload=function(){
	const images=document.querySelectorAll("ul.image-preview>li img");
	prouductImgCnt=images.length;
	document.getElementsByClassName("imgCount")[0].innerText='('+prouductImgCnt+'/10)';
	for(let i=0;i<images.length;i++){
		images[i].addEventListener("click", e => {  // 해당 이미지 클릭시
			prouductImgCnt--; // 이미지 삭제시 개수 감소
			$(e.target).parent().remove(); // li안의 img까지 삭제
			$(".imgCount").text("(" + prouductImgCnt + "/10" + ")");
			
			 for(var i=0; i<dataTransfer.files.length; i++){
	             if(dataTransfer.files[i].name==e.target.dataset.file){
	                    dataTransfer.items.remove(i)
	                    break;
	             }
	          }
			
			if(dataTransfer.files.length == 0){  
				checkProductRegist.productImg = false; 
			}
		});
	}
}


const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.upload');

upload.addEventListener('click', () => realUpload.click()); // 이미지등록 클록시 input file타입 호출
realUpload.addEventListener('change', getImageFiles); // file타입에서 값 변경시키면 getImageFiles() 함수 호출




// ==== 가격 입력했을 때, 숫자만입력되고, 3자리수마다 ,로 구분해주는 작업
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
}

function uncomma(str) {
	str = String(str);
	return str.replace(/[^\d]+/g, "");
}

function inputNumberFormat(obj) {
	obj.value = comma(uncomma(obj.value));
}


const priceValue = document.getElementById("priceId")
const spanPrice = $("#spanPrice");
priceValue.addEventListener("keyup", function() {

	if (priceValue.value.length == 0) {
		spanPrice.text("");
		checkProductRegist.productPrice=false;
	}else{
		replacePrice = priceValue.value.replace(",","");
		if(replacePrice <= 0){
			spanPrice.text("0원보다 크게 입력하세요").css("color","red");
			checkProductRegist.productPrice=false;
		}else{
			spanPrice.text("○").css("color","green");
			checkProductRegist.productPrice=true;
		}
	}
});


//=====================================================

const spanTitle = $("#spanTitle");
// ==== 제목 글자수 세주는 작업=====
$(".inputTitle").keyup(e => { // 해당 텍스트부분을 입력할 때
	$(".countTitle").text($(e.target).val().length + "/20");
	const length = $(e.target).val().length;
	
	if (length>20) {
		alert("20글자 이하로 작성하세요");
		$(e.target).val($(e.target).val().substring(0, 20));
		checkProductRegist.productTitle=false;
	}
	
	if(length<=0){
		spanTitle.text("");
		checkProductRegist.productTitle=false;
	}else if(length<10){
		spanTitle.text("최소 10글자 이상 작성하세요").css("color","red");
		checkProductRegist.productTitle=false;
	}else{
		spanTitle.text("");
		checkProductRegist.productTitle=true;
	}
	
	$(".countTitle").text($(e.target).val().length + "/20");
})


// -------------------------------------------------------------------------------------------------------------------
// 카테고리 선택하는 작업

$(() => {
	$(".mainCate").trigger("change", $(".mainCate:selected").val());  // 페이지로딩되었을때, 자동으로 change 함수 실행
	//	대상값은 현재 그 select에 선택된 값
})

function chageSubCate(value) {
	console.log(value);
	$.ajax({
		url: "findSubCate",
		data: { "cateId": value },
		success: function(result) {

			const subCate = result.split(","); // 문자열로 넘어온 값들을 ,를 구분자로 배열을 만듬

			$(".middleCate option").remove();   // 메인카테고리 선택할때마다 옵션들 다 삭제
			for (let i = 0; i < subCate.length; i++) {
				var option = $("<option value=" + subCate[i] + ">" + subCate[i] + "</option>");
				$(".middleCate").append(option);
			}
		},
		error: function() {
			console.log("카테고리 선택 오류발생");
		}
	})
}

/*$(function() {
	var arr = ["서울", "경기도", "인천"];
	for(var i=0; i<arr.length; i++){
	var optionLabel = arr[i];
	var optionValue = 65+i;  
	optionValue = String.fromCharCode(optionValue); // 밸류값을 알파벳으로 바꿈
	
	
	var option = $("<option value=" + optionValue + ">" + optionLabel+"</option>");
	$(".mainCate").append(option);
	}
});*/


// -------------------------------------------------------------------------------------------------------------------

// 거래지역 선택 (api 코드)
function sample6_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("sample6_extraAddress").value = extraAddr;

			} else {
				document.getElementById("sample6_extraAddress").value = '';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample6_postcode').value = data.zonecode;
			document.getElementById("sample6_address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("sample6_address").focus();
		}
	}).open();
}


const placeValue = document.getElementById("sample6_address");
const spanPlace = $("#spanPlace");
/*		spanPlace.text("지역을 반드시 선택해주세요").css("color","red");
		checkProductRegist.productPlace=false;
/*const placeLen = document.getElementById("sample6_address");*/
/*   $(function() {
		if(placeValue.value.length == 0){
			spanPlace.text("지역을 반드시 선택해주세요").css("color","red");
			checkProductRegist.productPlace=false;
		}else{
			spanPlace.text("○").css("color","green");
			checkProductRegist.productPlace=true;
		}
   });*/

placeValue.addEventListener("input", function() {
		spanPlace.text("○").css("color","green");
		checkProductRegist.productPlace=true;
});




// -------------------------------------------------------------------------------------------------------------------



const spanExplan = $("#spanExplan");
// ==== 설명 글자수 세주는 작업=====
$(".explan").keyup(e => { // 해당 텍스트부분을 입력할 때
	$(".countExpaln").text($(e.target).val().length + "/40");
	const length = $(e.target).val().length;

	if (length>2000) {
		alert("2000글자 이하로 작성하세요");
		$(e.target).val($(e.target).val().substring(0, 2000));
		checkProductRegist.productExplan=false;
	}else if(length>10){
		spanExplan.text("○").css("color","green");
		checkProductRegist.productExplan=true;
	}else if(length>0){
		spanExplan.text("최소 10글자 이상 작성하세요").css("color","red");
		checkProductRegist.productExplan=false;
	}else{
		spanExplan.text("").css("color","red");
		checkProductRegist.productExplan=false;
	}
	
	$(".countExpaln").text($(e.target).val().length + "/2000");
})


// -------------------------------------------------------------------------------------------------------------------


// 상품태그 검색 관련 js
const dataList = ["#패션", "#패션의류","#패션잡화","#자켓","#아우터","#자켓/아우터","#상의","#하의","#셔츠","#블라우스","#언더웨어","#홈웨어",
"#시계", "#신발", "#모자", "#가방", "#기타잡화", "#가전제품", "#주방가전", "#TV", "#세탁기","#건조대","#냉장고","#기타 가전제품","#소프트웨어",
 "#컴퓨터", "#노트북", "#스마트폰","#기타 주변기기","#PC/모바일","#PC","#모바일","#가구","#인테리어","#가구/인테리어","#거실가구","#침구","#주방가구",
 "#인테리어소품","#기타가구","#리빙/생활","#리빙","#생활","#욕실욕품","#주방용품","#식품","#청소","#청소/세탁용품","#세탁용품","#기타 생활용품",
 "#스포츠","#레저","#스포츠/레저","#자전거","#골프","#등산용품","#캠핑용품","#기타 레저스포츠","#도서/음반/도구","#도서","#음반","#도구",
 "#CD/DVD/LP","#CD","#DVD","#LP","#소설/만화책","#소설","#만화책","#문구","#사무용품","#문구/사무용품","#기타 도서/문구","#문구",
 "#차량","#오토바이","#차량/오토바이","#중고차","#오토바이/스쿠터","#스쿠터","#차량용품","#공구산업용품","#기타차량장비","#기타","#선풍기",
 ,"#식품","#그릇","#가방","#에어팟","#버즈","#에어컨","#모니터","#본체","#슬리퍼","#물통","#책","#교육","#키보드","#마우스","#헤드셋"
 ,"#후드티","#안경","#바지","#반바지","#반팔","#캡모자","#모자","#칠판","#스크린","#빔프로젝트","#전구","#형광등","#무선충전기","#유선충전기",
 "#사물함","#유병승","#귀걸이","#피어싱","#목걸이","#펜던트","#팔찌","#발찌","#반지","#선글라스","#목도리","#장갑","#스카프","#넥타이",
 "#벨트","#양말","#스타킹","#우산","#양산","#키링","#키케이스","#태블릿","#카메라","#오디오","#생활가전","#주방가전","#복사기","#팩스"
 ,"#골프","#캠핑","#낚시","#축구","#야구","#농구","#자전거","#등산","#클라이밍","#헬스","#필라테스","#스케이트보드","#전동킥보드","#테니스"
 ,"#배드민턴","#볼링","#탁구","#당구","#겨울 스포츠","#여름 스포츠","#수상 스포츠","#격투","#무술","#기타 스포츠","#국산차","#수입차"
 ,"#피규어","#인형","#레고"];
let registTagList = [];

const $searchTag = document.querySelector("#searchTag");
const $autoComplete = document.querySelector(".autocomplete");

let nowIndex = 0;
let matchDataList;
$searchTag.onkeyup = (event) => {
	// 검색어
	const value = $searchTag.value.trim();

	// 자동완성 필터링
	matchDataList = value
		? dataList.filter((label) => label.includes(value))
		: [];


	switch (event.keyCode) {
		// UP KEY
		case 38:
			nowIndex = Math.max(nowIndex - 1, 0);
			break;

		// DOWN KEY
		case 40:
			nowIndex = Math.min(nowIndex + 1, matchDataList.length - 1);
			// document.querySelector("#searchTag").value = matchDataList[nowIndex];
			break;

		// ENTER KEY
		case 13:
			document.querySelector("#searchTag").value = matchDataList[nowIndex] || "";

			const key = document.getElementById("searchTag").value;

			const aa = document.getElementById("registTag>li label");


			if ((key != "") && (!registTagList.includes(key))) {

				if (registTagList.length >= 5) {
					alert("태그는 최대 5개까지만 추가 가능합니다.");
					document.querySelector("#searchTag").value = "";  // 연관검색창 닫기
					break;
				}

				registTagList.push(key);
				const $li = document.createElement("li");

				document.getElementById("relativeTagDiv").appendChild($li);
				const $button1 = document.createElement("label");
				const $button2 = document.createElement("button");

				const $img = document.createElement("img");
				$img.height = "15";
				$img.width = "15";
				$img.src = context + "/images/productregist/xbtn.png";

				$img.addEventListener("click", e => {  // 해당 이미지 클릭시

					for (let i = 0; i < registTagList.length; i++) { // 저장해놓은 키워드배열에서 값 삭제하고 개수 줄임
						if (registTagList[i] == e.target.parentElement.previousElementSibling.innerHTML) {
							registTagList.splice(i, 1);
							break;
						}
					}
					$(e.target).parent().parent().remove(); // li밑 label+button 밑 img까지 삭제
				});

				$button2.appendChild($img);
				$button1.innerHTML = key;

				$li.appendChild($button1);
				$li.appendChild($button2);

				var input1 = document.createElement('input');
				input1.setAttribute("type", "hidden");
				input1.setAttribute("name", "data1");
				input1.setAttribute("value", key);

				$li.appendChild(input1);
			}

			// 초기화
			nowIndex = 0;
			matchDataList.length = 0;
			document.querySelector("#searchTag").value = ""; // 연관검색창 닫기
			break;

		case 27: // esc 눌렀을때 입력창 초기화 및 관련검색어 창 닫기
			document.querySelector("#searchTag").value = "";
			matchDataList.length = 0;

		// 그외 다시 초기화
		default:
			nowIndex = 0;
			break;

	}

	// 리스트 보여주기
	showList(matchDataList, value, nowIndex);
};

const showList = (data, value, nowIndex) => {
	// 정규식으로 변환
	const regex = new RegExp(`(${value})`, "g");

	$autoComplete.innerHTML = data
		.map(
			(label, index) => `
      <div class='${nowIndex === index ? "active" : ""}'>
        ${label.replace(regex, "<label>$1</label>")}
      </div>
    `
		)
		.join("");
};


$autoComplete.addEventListener("click", e => {  // 관련검색어 클릭했을경우

	let clickAnswer = e.target.innerHTML.trim();

	for (let i = 0; i <= 10; i++) {
		clickAnswer = clickAnswer.replace("<label>", "");
		clickAnswer = clickAnswer.replace("</label>", "");
	}

	document.querySelector("#searchTag").value = clickAnswer;

	const key = document.getElementById("searchTag").value;

	const aa = document.getElementById("registTag>li label");

	if (registTagList.length >= 5) {
		alert("태그는 최대 5개까지만 추가 가능합니다.");
		document.querySelector("#searchTag").value = "";  // 연관검색창 닫기
		return;
	}

	if ((key != "") && (!registTagList.includes(key))) {
		
		registTagList.push(key);
		const $li = document.createElement("li");

		document.getElementById("relativeTagDiv").appendChild($li);
		const $button1 = document.createElement("label");
		const $button2 = document.createElement("button");
		/*const $button2 = $("<button>").css({"border":"none", "background-color":"transparent"});
		*/
		const $img = document.createElement("img");
		$img.height = "15";
		$img.width = "15";
		$img.src = context + "/images/productregist/xbtn.png";

		$button2.appendChild($img);

		$button1.innerHTML = key;

		$li.appendChild($button1);
		$li.appendChild($button2);


		var input1 = document.createElement('input');
		input1.setAttribute("type", "hidden");
		input1.setAttribute("name", "data1");
		input1.setAttribute("value", key);

		$li.appendChild(input1);


		$img.addEventListener("click", e => {  // 해당 이미지 클릭시
			$(e.target).parent().parent().remove(); // li밑 label+button 밑 img까지 삭제

			for (let i = 0; i < registTagList.length; i++) { // 저장해놓은 키워드배열에서 값 삭제하고 개수 줄임
				if (registTagList[i] == e.target.parentElement.previousElementSibling.innerHTML) {
					registTagList.splice(i, 1);
					break;
				}
			}
		});

		document.querySelector("#searchTag").dispatchEvent(new KeyboardEvent("keyup", { keyCode: 13 })); // 엔터 한번 발생
		$("#searchTag").val("");   // document.querySelector("#searchTag").value =""; 
		$("#searchTag").focus();   // document.getElementById("searchTag").focus(); 
	}
})


/*============>  #키 입력 못하도록 설정*/
$(document).ready(function() {
	$("#searchTag").keypress(function(e) {
		if (event.key == '#') {
			e.preventDefault();
			e.returnValue = false;
		}
	});
});
/*=============================*/




function productRegist() {  // 상품등록 버튼 클릭됬을 때,
	

	if(checkProductRegist.productTitle && checkProductRegist.productPrice && checkProductRegist.productExplan
		&& checkProductRegist.productImg){
	}else{
			console.log("다 입력해라")
			return;
	}


	
	const form = new FormData();  // form 객체에 입력한 값들을 먼저 다 추가함
	form.append("title", $(".inputTitle").val());
	form.append("subCate", $(".middleCate").val());
	form.append("place", $("#sample6_address").val());
	form.append("state", $("input[name=state]:checked").val());
	form.append("price", $("#priceId").val())
	form.append("explan", $("#explanId").val())
	let tag="";
	$("input[name=data1]").each((i,element)=>{ // jquery로 해당 선택자로 값을 가져옴 .each(i,elemnet) -> 해당 데이터들의 인덱스번호와, 해당 값을 가져옴  
		if(i!=0) tag+=",";
		tag+=element.value;	
	})
	form.append("tag",tag); 
	
	const files= dataTransfer.files;
	/*const files=$("input[type=file]")[0].files;*/ // 

	$.each(files,(index,file)=>{
		form.append("upfile"+index,file);
		console.log(files[index]);
	});


	$.ajax({
		url: "productRegistEnd.do", // 해당 서블릿으로 ajax로 요청
		data: form,   // 저정한 form 객체를 데이터로 보냄
		processData:false, // 멀티파트폼으로 보내기위해서 설정
		contentType:false, // 멀티파트폼으로 보내기위해서 설정
		type:"post",
		success: function(result) {
			if(result==1) { // db는 결과값이 정수로 나옴 // 입력성공
					alert("등록 성공");
					location.replace("http://localhost:9090/semi-hifive/");
			}else{ 
					alert("등록 실패");
					location.replace("http://localhost:9090/semi-hifive/"+"productRegist.do");
			}
		},
		error: function() {
			alert("오류발생");
			location.replace("http://localhost:9090/semi-hifive/"+"productRegist.do");
		}
	})
}

/*================================================================================================*/

function productUpdate() {  // 상품수정 버튼 클릭됬을 때,
	
	if(checkProductRegist.productTitle && checkProductRegist.productPrice && checkProductRegist.productExplan
		&& checkProductRegist.productImg){
	}else{
			console.log("다 입력해라")
			return;
	}

	const form = new FormData();  // form 객체에 입력한 값들을 먼저 다 추가함
	form.append("productId",$(".inputProductId").val());
	form.append("title", $(".inputTitle").val());
	form.append("subCate", $(".middleCate").val());
	form.append("place", $("#sample6_address").val());
	form.append("state", $("input[name=state]:checked").val());
	form.append("price", $("#priceId").val())
	form.append("explan", $("#explanId").val())
	let tag="";
	$("input[name=data1]").each((i,element)=>{ // jquery로 해당 선택자로 값을 가져옴 .each(i,elemnet) -> 해당 데이터들의 인덱스번호와, 해당 값을 가져옴  
		if(i!=0) tag+=",";
		tag+=element.value;	
	})
	form.append("tag",tag); 
	
	const files= dataTransfer.files;
	/*const files=$("input[type=file]")[0].files;*/ // 

	$.each(files,(index,file)=>{
		form.append("upfile"+index,file);
		console.log(files[index]);
	});


	$.ajax({
		url: "productUpdateEnd.do", // 해당 서블릿으로 ajax로 요청
		data: form,   // 저정한 form 객체를 데이터로 보냄
		processData:false, // 멀티파트폼으로 보내기위해서 설정
		contentType:false, // 멀티파트폼으로 보내기위해서 설정
		type:"post",
		success: function(result) {
			if(result>=1) { // db는 결과값이 정수로 나옴 // 입력성공
					alert("수정 성공");
					location.replace(getContextPath());
			}else{ 
					alert("수정 실패");
					location.replace(getContextPath()+"productUpdate.do");
			}
		},
		error: function() {
			alert("오류발생");
			location.replace("http://localhost:9090/semi-hifive/"+"productRegist.do");
		}
	})
}




