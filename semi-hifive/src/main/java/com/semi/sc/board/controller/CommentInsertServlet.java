package com.semi.sc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.service.BoardService;


@WebServlet("/service/commentInsert.do")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//댓글 추가하는 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//저장한 댓글 정보 가져와서 객체에 저장
		int fk=0;
		if(request.getParameter("commentFK")!=null) {
			fk=Integer.parseInt(request.getParameter("commentFK"));
		}
		BoardComment bc=BoardComment.builder()
				.commentWriter(request.getParameter("writer"))
				.boardNo(Integer.parseInt(request.getParameter("boardNo")))
				.commentContent(request.getParameter("commentContent"))
				.commentNoFK(fk)
				.build();
		
		int result=new BoardService().insertBoardComment(bc);
		
		//response.sendRedirect(request.getContextPath()+"/service/boardContent.do?boardNo="+bc.getBoardNo());
		response.setContentType("application/json;charset=utf-8");
		new Gson().toJson(result>0?true:false,response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
