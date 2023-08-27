package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.model.vo.ReportProductMember;
import com.semi.admin.service.AdminService;


@WebServlet("/reportProcess.do")
public class ReportProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReportProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reportNo = request.getParameter("reportNo");
		
		ReportProductMember rpm = new AdminService().reportProductMember(reportNo); // 반환형은 3개의 객체클래스를 담은 클래스이다
		
		int reportNo1 = rpm.getReport().getReportNo(); 
		String userId = rpm.getMember().getUserId();
		int declareCount = rpm.getMember().getDeclareCount();
		double temp = rpm.getMember().getTemperature();
		System.out.println(declareCount);
		
		int result0 = 0;
		int result = new AdminService().increaseDeclareCount(userId);
		if(result>=1) {
			result0 = new AdminService().changeComplete(reportNo);
		}else{
			return;
		}
		
		int cnt = new AdminService().selectDeclareCount(userId);
		
		if(cnt%3==0) { // 신고누적수가 3이되면은 온도 1감소
			int result1 = new AdminService().decreaseTemp(userId);
			if(result1>=1) {
				System.out.println("온도 감소 성공!");
			}else {
				System.out.println("온도 감소 실패...");
			}
		}
		
		response.sendRedirect("/semi-hifive/reportListAdmin.do");
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
