package com.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;


@WebServlet("/updateBoardEnd.do")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdateEndServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardTitle = request.getParameter("BoardTitle");
		String boardContent = request.getParameter("BoardContent");
		String boardDate = request.getParameter("BoardDate");
		String boardCategory = request.getParameter("BoardCategory");
		String boardNo = request.getParameter("BoardNo");
		
		int result = new AdminService().updateBoard(boardTitle,boardContent,boardDate,boardCategory,boardNo);
		
		String msg="";
		String loc="";
		
		if(result==1) {
			msg = "정상적으로 수정되었습니다.";
		}
		else {
			msg = "비밀번호 수정에 실패하였습니다.";
		}
		loc = "/";
		request.setAttribute("script", "opener.location.replace('" + request.getContextPath() + "/boardListAdmin.do'); close();");
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
