package com.semi.sc.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.service.ReportService;


@WebServlet("/service/reportCommentInsert.do")
public class ReportCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReportCommentInsertServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fk=0;
		if(request.getParameter("commentFK")!=null) {
			fk=Integer.parseInt(request.getParameter("commentFK"));
		}
		BoardComment bc=BoardComment.builder()
				.commentWriter(request.getParameter("writer"))
				.boardNo(Integer.parseInt(request.getParameter("reportNo")))
				.commentContent(request.getParameter("commentContent"))
				.commentNoFK(fk)
				.build();
		int result=new ReportService().insertComment(bc);
		
		response.setContentType("application/json;charset=utf-8");
		new Gson().toJson(result>0?true:false,response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
