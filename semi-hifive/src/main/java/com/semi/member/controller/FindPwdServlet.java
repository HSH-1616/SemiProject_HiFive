package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.member.service.MemberService;


@WebServlet("/findPwd.do")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		Member findPwdMember = new MemberService()
				.selectByIdAndNameAndEmail(id,name,email);
		
		
		if(findPwdMember!=null) {
			request.setAttribute("userEmail",findPwdMember.getEmail());

			request.getRequestDispatcher("/views/login/findPwdResult.jsp")
			.forward(request, response);
			
			
		}else {
			request.setAttribute("msg", "입력하신 회원은 존재하지 않는 회원입니다");
			request.setAttribute("loc", "/findPwdView.do");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
