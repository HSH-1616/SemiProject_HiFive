package com.semi.sc.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sc.service.ReportService;


@WebServlet("/service/deleteReport.do")
public class ReportDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReportDeleteServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reportNo=Integer.parseInt(request.getParameter("no"));
		int result=new ReportService().deleteReport(reportNo);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/service/ReportList.do");
		}else {
			request.setAttribute("msg", "삭제 실패했습니다.");
			request.setAttribute("log", "/views/service/boardList.jsp");
			request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
