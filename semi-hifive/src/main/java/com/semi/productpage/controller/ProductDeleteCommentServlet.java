package com.semi.productpage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.productpage.model.vo.ProductCommentUser;
import com.semi.productpage.service.ProductPageService;

/**
 * Servlet implementation class ProductDeleteCommentServlet
 */
@WebServlet("/deleteComment")
public class ProductDeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("productId"));
		int cn=Integer.parseInt(request.getParameter("commentNo"));

		int result=new ProductPageService().deleteProductComment(cn);
		String view;
		if(result>0) {
			request.setAttribute("msg", "댓글을 삭제하였습니다.");
			request.setAttribute("loc", "/productpage?no="+no);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "댓글삭제를 실패하였습니다.");
			request.setAttribute("loc", "/productpage?no="+no);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
