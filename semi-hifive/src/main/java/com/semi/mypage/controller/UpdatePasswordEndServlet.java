package com.semi.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.member.service.MemberService;
import com.semi.mypage.service.MypageMemberService;

/**
 * Servlet implementation class UpdatePasswordEndServlet
 */
@WebServlet("/mypage/updatePasswordEnd.do")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String oriPwd = request.getParameter("oriPwd");
		String newPwd = request.getParameter("newPwd");
		String newPwdCheck = request.getParameter("newPwdCheck");
		Member m = new MemberService().selectByUserIdAndPw(userId, oriPwd);
		String msg;
		String loc = "/mypage/updatePassword.do?userId=" + userId;
		if(m==null) {
			// 일치하는 회원 x
			msg = "현재 비밀번호가 일치하지 않습니다.";
		} else {
			// 일치하는 회원 o
			int result = new MypageMemberService().updatePassword(userId, newPwd);
			if(result > 0) {
			msg = "비밀번호가 수정되었습니다.";
			loc = "/";
			request.setAttribute("script", "opener.location.replace('" + request.getContextPath() + "/logout.do'); close();");
			} else {
				msg = "비밀번호 수정에 실패하였습니다.";
			}
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
