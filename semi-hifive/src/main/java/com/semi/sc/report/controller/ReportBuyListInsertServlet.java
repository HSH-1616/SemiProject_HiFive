package com.semi.sc.report.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.sc.model.dto.ReportData;
import com.semi.sc.service.ReportService;

/**
 * Servlet implementation class ReportInsertServlet
 */
@WebServlet("/service/reportBuylistInsert.do")
public class ReportBuyListInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReportBuyListInsertServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String loginId=((String)((Member)session.getAttribute("loginMember")).getUserId());
		
		List<ReportData> dataList=new ReportService().selectBuyList(loginId);
		request.setAttribute("dataList", dataList);
		request.getRequestDispatcher("/views/service/reportInsertByBuylist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
