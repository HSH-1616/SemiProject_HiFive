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
                    opener.document.getElementById("agree3").checked = true;
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
            <h4 class="scheme-g">● 광고성 정보의 이용목적
            </h4>
            <textarea style="font-size:1em;" readonly>HIFIVE가 제공하는 이용자 맞춤형 서비스, 뉴스레터 발송, 새로운 기능의 안내, 각종 경품 행사, 이벤트 등의 광고성 정보를 전자우편이나 서신우편, 문자(SMS), 푸시 등을 통해 이용자에게 제공합니다. 이메일 및 SMS 수신거부와 관계없이 광고나 영리성 목적 외의 약관안내 및 서비스내용, 회사의 주요 정책 관련 변경에 따른 안내 등 의무적으로 안내되어야 하는 메일은 정상적으로 발송됩니다.

                마케팅 수신 동의는 거부하실 수 있으며 동의 이후에라도 고객의 의사에 따라 동의를 철회할 수 있습니다. 동의를 거부하시더라도 HIFIVE가 제공하는 서비스의 이용에 제한이 되지 않습니다. 단, 이벤트 및 이용자 맞춤형 상품 추천 등의 마케팅 정보 안내 서비스가 제한됩니다.
                
                이메일 수신동의를 하셨음에도 이메일을 받지 못하고 계신다면, 아래 내용을 확인해 주세요.
                
                - 이메일 주소가 잘못 등록되어 있을 경우
                - 해당 사이트 이메일이 스팸메일로 설정되어 있을 경우
                - 위 사항을 점검했음에도 문제 해결이 안 되신 경우, 고객센터로 연락 주세요.
                
                ‘수신거부'로 변경하여도 수정 전에 예약발송 메일 또는 SMS가 설정되어 있어 약 일주일 동안은 메일 또는 SMS가 발송될 수 있습니다. 이 점 양해 부탁드립니다.
            </textarea>
            <p>
                <input type="checkbox" id="check_1"  name="" /> 위의 약관에 동의 합니다.<br />
            </p>            
            <h4 class="scheme-g">● 미동의 시 불이익 사항</h4>
            <textarea style="font-size:1em;" readonly>개인정보보호법 제22조 제5항에 의해 선택정보 사항에 대해서는 동의 거부하시더라도 서비스 이용에 제한되지 않습니다. 단, 이벤트 및 이용자 맞춤형 상품 추천 등의 마케팅 정보 안내 서비스가 제한됩니다.
                응답하라 마케팅은 광고성 정보 역시 유익한 콘텐츠가 될 수 있다고 생각합니다. 해당 기업, 브랜드로부터 광고 제안을 받았을 때 정말 해당 광고성 정보가 응답이에게 유익함을 줄 수 있는지 면밀히 검토하겠습니다. 또한 광고라도 응답하라 마케팅의 톤앤매너에 맞게 작성하여 응답이가 뉴스레터를 읽을 때 방해를 받지 않도록 고심하며 콘텐츠를 제작하겠습니다. 
            </textarea>
            <p>
                <input type="checkbox" id="check_2"  name="" /> 위의 약관에 동의 합니다.<br />
            </p>
            <h4 class="scheme-g">● 서비스 정보 수신 동의 철회</h4>
            <textarea style="font-size:1em;" readonly>HIFIVE에서 제공하는 마케팅 정보를 원하지 않을 경우 hivife@naver.com로 철회를 요청할 수 있습니다.
            구독자 응답이님에게 공지 드릴 내용은 광고성 메일 수신 동의에 대한 것입니다. 응답하라 마케팅은 지금껏 도서 이벤트, 팝업 스토어 소개, 주목할 브랜드와 같이 구독자에게 도움이 될 수 있는 정보를 전달해 드렸는데요.

이런 내용을 포함한 뉴스레터를 보내기 위해서는 구독자로부터 광고성 메일 수신 동의를 받아야 한다는 것을 인지하게 되어 앞으로 광고성 메일을 발송하기 전 수신 동의 안내를 드립니다. 
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
