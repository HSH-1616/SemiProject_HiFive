package com.semi.mypage.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mypage.model.vo.ReviewTrade;
import com.semi.mypage.service.MypageMemberService;
import com.semi.mypage.service.MypageProductService;

/**
 * Servlet implementation class BuyListReviewServlet
 */
@WebServlet("/mypage/reviewWrite.do")
public class BuyListReviewAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyListReviewAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String BuyerId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		String reviewMsg = request.getParameter("reviewMsg");
		String radioValue = request.getParameter("radioValue");
		// radioValue별 점수
		double reviewScore = 0;
		if (radioValue.equals("super-happy")) {
			reviewScore = 0.2;
		} else if (radioValue.equals("neutra1")) {
			reviewScore = 0;
		} else if (radioValue.equals("super-sad")) {
			reviewScore = -0.1;
		}
		
		// 판매자 현재 온도
		double nowTem = new MypageProductService().nowTemperature(productId);

		// 온도 변경(0~100)
		double changeTem = 0;
		if((nowTem + reviewScore) > 100) {
			// 현재 온도 + 리뷰 온도가 100보다 높으면 100으로 저장
			changeTem = 100.0;
		} else if((nowTem + reviewScore) < 0) {
			// 현재 온도 + 리뷰 온도가 0보다 낮으면 0으로 저장
			changeTem = 0.0;
		} else {
			changeTem = nowTem + reviewScore;
		}
		changeTem = Math.round(changeTem * 1000.0) / 1000.0;

		// 판매자 온도 수정
		int scoreResult = new MypageProductService().sellerScore(productId, changeTem);
		
		// 거래후기 등록
		int reviewResult = new MypageProductService().insertReview(productId, reviewScore, reviewMsg);
		
		String result = "";
		if(scoreResult>0 && reviewResult>0) {
			result = "거래후기가 등록되었습니다.";
		} else {
			result = "거래후기 등록에 실패하였습니다.";
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
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
