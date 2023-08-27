<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminHome.css" />
    
    <title>Document</title>
</head>
<body>  
    <aside class="sidebar">
      <div class="front">
        <h3 class="text-lime text-center mt-4 mb-4"><a href="<%=request.getContextPath()%>/adminMode.do">Home</a></h3>
        <h3 class="text-white text-center mt-4 mb-4"><a href="<%=request.getContextPath()%>/index.jsp">사용자페이지</a></h3>
      </div>
      <div class="menubal ">
        <div class="accordion" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                  회원관리
                </button>
              </h2>
              <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingOne">
                <div class="accordion-body">
                  <a href="<%=request.getContextPath()%>/memberList.do">회원조회</a>
                </div>
<!--                 <div class="accordion-body">
                    탈퇴한 회원조회
                  </div> -->
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                  고객센터 관리
                </button>
              </h2>
              <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
                <div class="accordion-body">
                  <a href="<%=request.getContextPath()%>/boardListAdmin.do">공지사항 관리</a>
                </div>
                <div class="accordion-body">
                  <a href="<%=request.getContextPath()%>/reportListAdmin.do">처리되지 않은 신고글</a>
                </div>
                <div class="accordion-body">
                  <a href="<%=request.getContextPath()%>/completeReportList.do">처리된 신고글</a>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                  상품 관리
                </button>
              </h2>
              <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
                <div class="accordion-body">
                    <a href="<%=request.getContextPath()%>/productAdmin.do">상품 게시물 관리</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </nav>
    </aside>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>

</body>
</html>