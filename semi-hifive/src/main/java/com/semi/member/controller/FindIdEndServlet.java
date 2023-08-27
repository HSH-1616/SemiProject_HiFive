package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.member.service.MemberService;


@WebServlet("/findId.do")
public class FindIdEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public FindIdEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Member findIdMember = new MemberService()
				.selectByUserNameAndEmail(name, email);
		
		
		if(findIdMember!=null) {
			request.setAttribute("userId",findIdMember.getUserId());
			request.getRequestDispatcher("/views/login/findidresult.jsp")
			.forward(request, response);
			
			
		}else {
			request.setAttribute("msg", "입력하신 회원은 존재하지 않는 회원입니다");
			request.setAttribute("loc", "/findIdView.do");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
