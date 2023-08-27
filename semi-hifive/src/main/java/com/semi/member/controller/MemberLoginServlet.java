package com.semi.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.member.service.MemberService;
import com.semi.mypage.model.vo.MemberWishList;
import com.semi.mypage.service.MypageProductService;
import com.semi.search.model.vo.Search;
import com.semi.search.service.SearchService;


@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		
		// checkbox에 check가 되면 on
		// checkbox에 check가 안되면 null
		if(saveId!=null) {  // 저장된값이 널이 아니면
			Cookie c = new Cookie("saveId",userId); // 쿠키생성
			c.setMaxAge(60*60*24*7); 
			response.addCookie(c);  // 쿠키만들어줌
		}else {
			Cookie c = new Cookie("saveId","");
			c.setMaxAge(0); // 쿠키 삭제
			response.addCookie(c);
		}
		
		
		//2. DB에 접속해서 id와 password가 일치하는 회원이 있는지 확인
		Member loginMember = new MemberService().selectByUserIdAndPw(userId, password);
		
		// loginMember null을 기준으로 로그인처리 여부를 결정할 수 있음
		if(loginMember!=null) {
				
				HttpSession session=request.getSession();
				session.setAttribute("loginMember", loginMember);

				// 세션에 저장하면 sendRedirect해도 데이터가 날라가지 않음
				response.sendRedirect(request.getContextPath());




		}else {
			// 로그인실패 -> 인증못받음
			// 실패 메세지 출력
			request.setAttribute("msg", "아이디,패스워드가 일치하지 않습니다");
			request.setAttribute("loc", "/views/login/loginview.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
