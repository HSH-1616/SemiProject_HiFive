package com.semi.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mypage.dao.MypageMemberDao;
import com.semi.mypage.model.vo.MemberShopPage;
import com.semi.mypage.service.MypageMemberService;
import com.semi.mypage.service.MypageProductService;

/**
 * Servlet implementation class MyPageMain
 */
@WebServlet("/myPage/myPageMain.do")
public class MyPageMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPageMainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원정보
		String userId = request.getParameter("userId");
		MemberShopPage m = new MypageMemberService().selectByUserId(userId);
		
		// 성사된 거래수
		int trade = new MypageProductService().countTrade(userId);
		
		// 암호화

		request.setAttribute("mypageMember", m);
		request.setAttribute("countTrade", trade);

		request.getRequestDispatcher("/views/mypage/myPageMain.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
