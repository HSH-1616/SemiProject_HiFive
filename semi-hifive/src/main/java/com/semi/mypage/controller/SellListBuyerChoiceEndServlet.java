package com.semi.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mypage.service.MypageMemberService;
import com.semi.mypage.service.MypageProductService;

/**
 * Servlet implementation class SellListBuyerChoiceEndServlet
 */
@WebServlet("/mypage/sellListBuyerChoiceEnd.do")
public class SellListBuyerChoiceEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellListBuyerChoiceEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String userId = request.getParameter("userId");
		String buyerId = request.getParameter("buyerId");
		
		int result1 = new MypageProductService().changeSelectValue("판매완료", productId);
		int result = new MypageProductService().insertTrade(productId, buyerId);
		
		String msg;
		String loc = "/myPage/sellList.do?userId=" + userId;
		if(result>0) {
			msg = "거래가 완료되었습니다!";
			request.setAttribute("script", "opener.location.replace('" + request.getContextPath() + "/myPage/sellList.do?userId=" + userId + "'); close();");
		} else {
			msg = "거래 실패했습니다.";
			request.setAttribute("script", "opener.location.replace('" + request.getContextPath() + "/myPage/sellList.do?userId=" + userId + "'); close();");
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
