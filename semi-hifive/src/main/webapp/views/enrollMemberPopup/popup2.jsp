<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <title>가이드 텍스트</title>
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/enrollMemberPopup.css" />
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
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
                    opener.document.getElementById("agree2").checked = true;
                    window.close();
                }
            });    
        });
    </script>

<body>
    

<article id="content" class="cols-d">
    <div>
        <h1>개인정보 수집에 대한 동의</h1>
        <form id="terms_form" class="f" action="">
            <h4 class="scheme-g">● 개인정보의 수집 및 이용목적</h4>
            <textarea style="font-size:1em;" readonly>회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.

                - 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산 콘텐츠 제공 , 구매 및 요금 결제
                - 고객 관리: 고객 서비스 이용에 따른 본인확인 , 개인 식별 , 불량회원의 부정 이용 방지와 비인가 사용 방지 , 가입 의사 확인 , 연령확인 , 불만처리 등 민원처리 , 고지사항 전달
                - 마케팅 및 광고에 활용 : 신규 서비스(제품) 개발 및 특화, 이벤트 등 광고성 정보 전달, 접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계
            </textarea>
            <p>
                <input type="checkbox" id="check_1"  name="" /> 위의 약관에 동의 합니다.<br />
            </p>            
            <h4 class="scheme-g">● 수집하는 개인정보 항목</h4>
            <textarea style="font-size:1em;" readonly>회사는 정보주체가 고객서비스(상담신청, 상담, 서비스 신청 등)를 이용하기 위하여 고객의 개인 정보를 제공할 때의 서비스 제공을 위한 필수 정보와, 보다 향상된 서비스 제공을 위한 선택정보를 온라인상 입력방법을 통하여 수집하고 있습니다. 수집하는 개인정보의 범위는 아래와 같습니다.

                개인정보 수집항목
                - 필수항목: 이름, 생년월일, 전화번호 , 이메일, 주소, 여권사본,
                - 선택항목: 직업, 학력, 이력, 유학동기, 유학계획, 유학목적 등 입학 지원을 하기 위하여 해당 교육기관에서 요청하는 개인 정보.
                - 자동수집 항목 : 접속로그( 접속시간, 접속 아이피,접속 웹브라우져종류,서비스 이용 기록 )
                
                개인정보 수집방법
                정보주체는 웹사이트 또는 서면 절차를 통하여 개인정보처리방침과 이용약관 각각의 내용을 확인하고 ‘동의’ 또는 ‘동의하지 않는다’ 문구를 선택 할 수 있습니다. 정보 주체가 ‘동의’ 문구를 선택한 경우에는 개인정보 수집에 동의한 것으로 봅니다.
            </textarea>
            <p>
                <input type="checkbox" id="check_2"  name="" /> 위의 약관에 동의 합니다.<br />
            </p>
            <h4 class="scheme-g">● 개인정보의 안전성 확보 조치</h4>
            <textarea style="font-size:1em;" readonly>회사는 [개인정보보호법] 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적, 관리적 안전성 확보 조치를 취하고 있습니다.

                관리적 안전성 확보 조치
                - 개인정보의 안전한 처리를 위하여 ‘내부관리계획’을 수립하여 2012년 3월 1일자로 시행하고 있습니다.
                - 회사 내부의 ‘Privacy Policy’를 수립하고 2012년 3월 1일자로 시행하여 개인정보보호를 강화하였습니다.
                - 개인정보 처리 관련 안정성 확보를 위하여 정기적(년 1회)으로 자체적인 감사를 실시하고 있습니다.
                - 개인정보를 처리하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.
                
                기술적 안전성 확보 조치
                - 회사는 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신 및 점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.
                - 개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여,변경,말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템(방화벽)을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.
                - 정보주체의 개인정보는 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화 하거나 파일 잠금 기능을 사용하는 등의 별도 보안기능을 사용하고 있습니다.
                
                물리적 안전성 확보 조치
                - 개인정보를 보관하고 있는 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립, 운영하고 있습니다.
                - 개인정보가 포함된 서류, 보조저장매체 등을 잠금 장치가 있는 안전한 장소에 보관하고 있습니다.
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
