package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.member.service.MemberService;


@WebServlet("/enrollMemberEnd.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnrollMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 보내는 값들은 parameter로 받으면됨
		
		
		String userId = request.getParameter("userId"); 
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
	
		
		Member m = Member.builder()
				.userId(userId)
				.email(email)
				.nickName(nickName)
				.password(password)
				.userName(userName)
				.build();
		
		int result = new MemberService().insertMember(m); // 회원정보 데이터 추가하는 작업
		int result2 = new MemberService().insertShopPage(userId); // 회원쇼핑페이지 추가하는 작업
		
		String msg="";
		String loc="";
		if(result>0 && result2 >0) { // db는 결과값이 정수로 나옴
			// 입력성공
				
			msg="회원가입을 축하드립니다!";
			loc="/";
			
		}else {
			// 입력실패
			msg="회원가입에 실패하였습니다. :( \n다시 시도하세요.";
			loc="/enrollMember.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		
		
		request.getRequestDispatcher("/views/common/msg.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}