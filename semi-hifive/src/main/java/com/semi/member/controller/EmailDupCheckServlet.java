package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.service.MemberService;


@WebServlet("/emailDupCheck")
public class EmailDupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmailDupCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 비동기 통신으로 전달된 파라미터 ( data 속성의 key값 ) 얻어오기
		String memberEmail = request.getParameter("memberEmail");
		
		try {
			// 이메일 중복 검사 서비스 호출 후 결과받기
			MemberService service = new MemberService();
			
			int result = service.emailDupCheck(memberEmail);   // 받아온 값 0 / 1 
			
			
			response.getWriter().print(result);
			//응답용 스트림을 이용해 result 출력
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
