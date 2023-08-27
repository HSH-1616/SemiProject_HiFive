// 검색기능 (ex구글)
function searchData() {
  let keyword = document.getElementsByClassName("search")[0].value;
  location.href = "https://www.google.co.kr/search?q=" + keyword;
  console.log(keyword);
}
// /검색기능/


// 카테고리 사이드바

$(".pdcCategory> .collapse").on("show.bs.collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-plus-square");
  target.addClass("fa-minus-square");
});
$(".pdcCategory >.collapse").on("shown.bs.collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-plus-square");
  target.addClass("fa-minus-square");
});
$(".pdcCategory >.collapse").on("hide.bs.collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-minus-square");
  target.addClass("fa-plus-square");
});
$(".pdcCategory> .collapse").on("hidden.bs.collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-minus-square");
  target.addClass("fa-plus-square");
});

/*$(document).on("show.bs.collapse", ".pdcCategory> .collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-plus-square");
  target.addClass("fa-minus-square");
});
$(document).on("show.bs.collapse", ".pdcCategory > .collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-plus-square");
  target.addClass("fa-minus-square");
});
$(document).on("show.bs.collapse", ".pdcCategory > .collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-plus-square");
  target.addClass("fa-minus-square");
});
$(document).on("show.bs.collapse", ".pdcCategory> .collapse", function () {
  var target = $("[href='#" + $(this).prop("id") + "']");
  target.removeClass("fa-plus-square");
  target.addClass("fa-minus-square");
});*/
// /카테고리 사이드바/

//가격검색 콤마//
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

function inputOnlyNumberFormat(obj) {
  obj.value = onlynumber(uncomma(obj.value));
}

function onlynumber(str) {
  str = String(str);
  return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1");
}

//가격검색 콤마//
$("document").ready(function () {
  var area0 = [
    "시/도 선택",
    "서울특별시",
    "인천광역시",
    "대전광역시",
    "광주광역시",
    "대구광역시",
    "울산광역시",
    "부산광역시",
    "경기도",
    "강원도",
    "충청북도",
    "충청남도",
    "전라북도",
    "전라남도",
    "경상북도",
    "경상남도",
    "제주도",
  ];
  var area1 = [
    "강남구",
    "강동구",
    "강북구",
    "강서구",
    "관악구",
    "광진구",
    "구로구",
    "금천구",
    "노원구",
    "도봉구",
    "동대문구",
    "동작구",
    "마포구",
    "서대문구",
    "서초구",
    "성동구",
    "성북구",
    "송파구",
    "양천구",
    "영등포구",
    "용산구",
    "은평구",
    "종로구",
    "중구",
    "중랑구",
  ];
  var area2 = [
    "계양구",
    "남구",
    "남동구",
    "동구",
    "부평구",
    "서구",
    "연수구",
    "중구",
    "강화군",
    "옹진군",
  ];
  var area3 = ["대덕구", "동구", "서구", "유성구", "중구"];
  var area4 = ["광산구", "남구", "동구", "북구", "서구"];
  var area5 = [
    "남구",
    "달서구",
    "동구",
    "북구",
    "서구",
    "수성구",
    "중구",
    "달성군",
  ];
  var area6 = ["남구", "동구", "북구", "중구", "울주군"];
  var area7 = [
    "강서구",
    "금정구",
    "남구",
    "동구",
    "동래구",
    "부산진구",
    "북구",
    "사상구",
    "사하구",
    "서구",
    "수영구",
    "연제구",
    "영도구",
    "중구",
    "해운대구",
    "기장군",
  ];
  var area8 = [
    "고양시",
    "과천시",
    "광명시",
    "광주시",
    "구리시",
    "군포시",
    "김포시",
    "남양주시",
    "동두천시",
    "부천시",
    "성남시",
    "수원시",
    "시흥시",
    "안산시",
    "안성시",
    "안양시",
    "양주시",
    "오산시",
    "용인시",
    "의왕시",
    "의정부시",
    "이천시",
    "파주시",
    "평택시",
    "포천시",
    "하남시",
    "화성시",
    "가평군",
    "양평군",
    "여주군",
    "연천군",
  ];
  var area9 = [
    "강릉시",
    "동해시",
    "삼척시",
    "속초시",
    "원주시",
    "춘천시",
    "태백시",
    "고성군",
    "양구군",
    "양양군",
    "영월군",
    "인제군",
    "정선군",
    "철원군",
    "평창군",
    "홍천군",
    "화천군",
    "횡성군",
  ];
  var area10 = [
    "제천시",
    "청주시",
    "충주시",
    "괴산군",
    "단양군",
    "보은군",
    "영동군",
    "옥천군",
    "음성군",
    "증평군",
    "진천군",
    "청원군",
  ];
  var area11 = [
    "계룡시",
    "공주시",
    "논산시",
    "보령시",
    "서산시",
    "아산시",
    "천안시",
    "금산군",
    "당진군",
    "부여군",
    "서천군",
    "연기군",
    "예산군",
    "청양군",
    "태안군",
    "홍성군",
  ];
  var area12 = [
    "군산시",
    "김제시",
    "남원시",
    "익산시",
    "전주시",
    "정읍시",
    "고창군",
    "무주군",
    "부안군",
    "순창군",
    "완주군",
    "임실군",
    "장수군",
    "진안군",
  ];
  var area13 = [
    "광양시",
    "나주시",
    "목포시",
    "순천시",
    "여수시",
    "강진군",
    "고흥군",
    "곡성군",
    "구례군",
    "담양군",
    "무안군",
    "보성군",
    "신안군",
    "영광군",
    "영암군",
    "완도군",
    "장성군",
    "장흥군",
    "진도군",
    "함평군",
    "해남군",
    "화순군",
  ];
  var area14 = [
    "경산시",
    "경주시",
    "구미시",
    "김천시",
    "문경시",
    "상주시",
    "안동시",
    "영주시",
    "영천시",
    "포항시",
    "고령군",
    "군위군",
    "봉화군",
    "성주군",
    "영덕군",
    "영양군",
    "예천군",
    "울릉군",
    "울진군",
    "의성군",
    "청도군",
    "청송군",
    "칠곡군",
  ];
  var area15 = [
    "거제시",
    "김해시",
    "마산시",
    "밀양시",
    "사천시",
    "양산시",
    "진주시",
    "진해시",
    "창원시",
    "통영시",
    "거창군",
    "고성군",
    "남해군",
    "산청군",
    "의령군",
    "창녕군",
    "하동군",
    "함안군",
    "함양군",
    "합천군",
  ];
  var area16 = ["서귀포시", "제주시", "남제주군", "북제주군"];

  // 시/도 선택 박스 초기화

  $("select[name^=sido]").each(function () {
    $selsido = $(this);
    $.each(eval(area0), function () {
      $selsido.append("<option value='" + this + "'>" + this + "</option>");
    });
    $selsido.next().append("<option value=''>구/군 선택</option>");
  });

  // 시/도 선택시 구/군 설정

  $("select[name^=sido]").off("change").on("change",function () {
    var area =
      "area" + $("option", $(this)).index($("option:selected", $(this))); // 선택지역의 구군 Array
    var $gugun = $(this).next(); // 선택영역 군구 객체
    $("option", $gugun).remove(); // 구군 초기화

    if (area == "area0") $gugun.append("<option value=''>구/군 선택</option>");
    else {
      $.each(eval(area), function () {
        $gugun.append("<option value='" + this + "'>" + this + "</option>");
      });
    }
  });
});

$(document).ready(function(){
	if($("#searchCount").text()==0){
		$("#pimgWraperpricechart").css("display","none");
	}	
})

$(document).ready(function(){
	if($("#productImgContainer").length==0){
		$(".pageContainer").css("display","none");	
		}
})	
$(document).ready(function() {
	$("#categoryFunction span").click(function() {
	// 클릭한 span 태그에 active 클래스 추가
	  $(this).addClass("active");
	// 다른 span 태그에서 active 클래스 제거
	$("#categoryFunction span").not(this).removeClass("active");
	});
});

// 시세 차트 설정
// 먼저 범례부분에 담을 div를 가져옴
/* window.onload = function () {
  let legendDiv = document.getElementById('legend-div');
  legendDiv.innerHTML = myChart.generateLegend();
}
// 차트를 그럴 영역을 dom요소로 가져온다.
var chartArea = document.getElementById('marketpricechart').getContext('2d');
// 차트를 생성한다. 
var myChart = new Chart(chartArea, {
    // ①차트의 종류(String)
    type: 'line',
    // ②차트의 데이터(Object)
    data: {
        // ③x축에 들어갈 이름들(Array)
        labels: ['1개월전', '3개월전', '6개월전', '1년', '전체'],
        // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
        datasets: [{
            // ⑤dataset의 이름(String)
            label: '가격',
            fill: false,
            // ⑥dataset값(Array)
            data: [870000, 890000, 900000, 950000, 970000, 1000000],
            pointRadius: [3, 3, 3, 3, 3, 3, 3],
            // ⑦dataset의 배경색(rgba값을 String으로 표현)
            backgroundColor: '#20c997',
            // ⑧dataset의 선 색(rgba값을 String으로 표현)
            borderColor: '#20c997',
            // ⑨dataset의 선 두께(Number)
            borderWidth: 2
        }]
    },
    // ⑩차트의 설정(Object)
    options: {
      responsive: false,
        // ⑪축에 관한 설정(Object)
        scales: { // ⑫y축에 대한 설정(Object)
          
            y: {// ⑬시작을 0부터 하게끔 설정(최소값이 0보다 크더라도)(boolean)
              position: 'right',  
              beginAtZero: true
            }
        }
    }
});*/

/*//ajax로 페이징 처리한 페이지 바 선택시 출력해주는 함수
    function changePage(pageNo) {
        $.ajax({
            url: "<%=request.getContextPath()%>/categoryproductlist.do",
            type: "GET",
            data: {
                'cPage': pageNo,
                'numPerpage': 32
            },
            dataType: "html",
            success: function(data) {
                $("section").html(data);
            }
        });
    }
    //대표카테고리 클릭시 출력 ajax
            function searchProduct(Cid){
           		$.ajax({
           			url: "<%=request.getContextPath()%>/serachcategory.do",
           			dateType: 'html',
           			data:{'Cid':Cid},
           			success: function(data){
           				$("#productContainer").html(data); 
           			}
           		});
           	};
			function subsearchProduct(sub){
				$.ajax({
          			url: "<%=request.getContextPath()%>/subserachcategory.do",
          			dateType: 'html',
          			data:{'sub':sub},
          			success: function(data){
          				$("#productContainer").html(data);
          			}
          		});
          	}*/
