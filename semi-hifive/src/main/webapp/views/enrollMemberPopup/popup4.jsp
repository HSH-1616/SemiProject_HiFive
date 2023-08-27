<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>가이드 텍스트</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/enrollMemberPopup.css" />
</head>
    <script type="text/javascript">
        $(document).ready(function(){
    
            $("#nextBtn").click(function(){    
                if($("#check_1").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
                    return;
                }else if($("#check_2").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다..");
                    return;
                }else if($("#check_3").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다...");
                    return;
                }else{
                    opener.document.getElementById("agree4").checked = true;
                    window.close();
                }
            });    
        });
    </script>
<body>
    
<article id="content" class="cols-d">
    <div>
        <h1>이용약관</h1>
        <form id="terms_form" class="f" action="">
            <h4 class="scheme-g">● 수집 및 이용목적</h4>
            <textarea style="font-size:1em;" readonly>마케팅 정보 활용 동의를 받아 수집한 귀하의 개인정보는 전화, SMS, LMS, MMS, 이메일, 우편을 통하여 뉴스레터, 제품정보, 경연대회, 설문조사, 사이트 기능 관리, 안부 문자, 각종 경품 행사, 이벤트 안내 등 영리목적의 광고성 정보 전달에 활용되거나 영업 및 마케팅 목적으로 활용될 수 있습니다.
                양사는 각각 재화 판매와 서비스 운용에 있어 이용자에게 각종 정보를 서비스 화면, 전화, e-mail, 휴대폰문자메시지(SMS, LMS, MMS 등), 우편물 등의 방법으로 제공할 수 있으며, 서비스별 마일리지 및 통합 포인트 적립 및 소진/소멸 등, 의무적으로 안내되어야 하는 정보성 내용은 수신동의 여부와 무관하게 제공됩니다.
            </textarea>
            <p>
                <input type="checkbox" id="check_1"  name="" /> 위의 약관에 동의 합니다.<br />
            </p>            
            <h4 class="scheme-g">● 마케팅정보 수신동의</h4>
            <textarea style="font-size:1em;" readonly>다만, 판매업체(무던(mudern))와 쇼핑몰 호스팅 및 결제서비스 제공사인 ㈜유디아이디(이하 “양사”)는 구매자(이용자)분들이 추가적인 혜택을 얻으실 수 있는 다양한 마케팅정보를 제공하고 있으며, 이를 통해 귀하가 마케팅정보 수신 시 적립금 등의 부가 혜택을 받으실 수 있사오니, 정보 수신 여부 결정에 참고해 주시기 바랍니다.
                아래 내용은 마케팅정보 수신에 관한 주요 고지내용이며, 전자금융거래 이용자와 더불어, 무통장입금으로 상품을 구매하시는 이용자께도 해당되는 사항입니다.
                ① 마케팅 및 광고에의 활용 신규 서비스(제품) 개발 및 맞춤 서비스 제공, 이벤트 및 광고성 정보/참여기회 제공, 인구통계학적 특성에 따른 서비스 제공 및 광고 게재, 서비스의 유효성 확인, 접속빈도 파악 또는 이용자의 서비스 이용에 대한 통계 등을 목적으로 개인정보를 처리합니다.
            </textarea>
            <p>
                <input type="checkbox" id="check_2"  name="" /> 위의 약관에 동의 합니다.<br />
            </p>
            <h4 class="scheme-g">● 미동의 시 불이익 사항</h4>
            <textarea style="font-size:1em;" readonly>동의 이후 언제든 마케팅 정보 수신을 거부할 수 있으나, 이 경우 이벤트 안내를 받지 못하거나 (주)지란지교시큐리티가 제공하는 유용한 정보를 받지 못할 수 있습니다.
                개인정보보호법 제22조 제5항에 의해 선택정보 사항에 대해서는 동의 거부하시더라도 서비스 이용에 제한되지 않습니다.
            </textarea>
            <p>
                <input type="checkbox" id="check_3"  name="" /> 위의 약관에 동의 합니다.<br />
                <input type="button" id="nextBtn"  class="button_big" style="padding:0 10px 10px 10px;height:24px;" value="동의하기 확인"/>
            </p>
        </form>
    </div>    
</article>

</body>
</html>
