package com.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;
import com.semi.sc.model.dto.Report;


@WebServlet("/reportListAdmin.do")
public class ReportListAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReportListAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//페이징 처리하기
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerpage;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerpage = 10;
		}
		
		//1. DB에서 member테이블에 있는 데이터 가져오기
		List<Report> reportList = new AdminService().selectReportList(cPage,numPerpage);
		
		//2. DB에서 가져온 데이터 저장(화면 출력)
		request.setAttribute("reports", reportList);
		
		//3. 페이지바를 구성
		// 1) DB에 저장된 전체 데이터의 수를 가져오기
		int totalData = new AdminService().selectReportCount();
		// 2) 전체페이지수를 계산하기
		int totalPage = (int)Math.ceil((double)totalData / numPerpage);
		int pageBarSize = 5;
		
		// 3) 페이지바 시작번호 계산하기
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		

		// 4) 페이지바를 구성하는 html 저장하기
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()
			+"?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>"; // 현재페이지 숫자 출력(이동안됨)
			}else {
				pageBar+="<a href='"+request.getRequestURI() 
				+"?cPage="+pageNo+"'>"+pageNo+"</a>"; // 현재페이지 제외하고 그 나머지 페이지들 숫자 출력(이동가능)
			}
			pageNo++;
		}
				
		// 다움출력
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+pageNo+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		
		// 3) 출력할 화면을 선택(이동)
		request.getRequestDispatcher("/views/admin/reportListAdmin.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
