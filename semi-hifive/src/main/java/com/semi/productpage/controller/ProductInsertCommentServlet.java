package com.semi.productpage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductComment;
import com.semi.productpage.model.vo.ProductCommentUser;
import com.semi.productpage.service.ProductPageService;

/**
 * Servlet implementation class ProductPageComment
 */
@WebServlet("/insertAjaxComment")
public class ProductInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductCommentUser pc=ProductCommentUser.builder()
				.product(Product.builder()
				.productId(Integer.parseInt(request.getParameter("productId")))
				.build())
				.productComment(ProductComment.builder()
				.commentLevel(Integer.parseInt(request.getParameter("level")))
				.userId(request.getParameter("userId"))
				.content(request.getParameter("content"))
				.commentRef(Integer.parseInt(request.getParameter("commentRef")))
				.build())
				.member(Member.builder()
				.nickName(request.getParameter("nickName"))
				.build())
				.build();
		int result=new ProductPageService().insertAjaxProductComment(pc);
	
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
