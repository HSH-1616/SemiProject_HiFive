package com.semi.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mypage.model.vo.MemberComment;
import com.semi.mypage.model.vo.ReviewTrade;
import com.semi.mypage.service.MypageProductService;

/**
 * Servlet implementation class SellListBuyerChoiceServlet
 */
@WebServlet("/mypage/buyerIdChoice.do")
public class SellListBuyerChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellListBuyerChoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String userId = request.getParameter("userId");
		
		// 해당 상품 댓글 작성자 아이디 가져오기
		List<MemberComment> mc = new MypageProductService().commentList(productId, userId);
		request.setAttribute("comments", mc);
		request.setAttribute("productId", productId);
		System.out.println(mc);
		System.out.println(productId);
		ReviewTrade rt = new MypageProductService().selectReviewByProductId(productId);
		System.out.println(rt);
		
		if(rt == null) {
			request.getRequestDispatcher("/views/mypage/sellListBuyerChoice.jsp").forward(request, response);
		} else {
			String msg = "이미 등록된 후기가 있습니다.";
			String loc = request.getContextPath() + "/myPage/sellList.do?userId=" + userId;
			String script = "close()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.setAttribute("script", script);
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
