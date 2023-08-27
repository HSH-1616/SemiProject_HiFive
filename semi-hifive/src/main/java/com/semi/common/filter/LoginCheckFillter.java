package com.semi.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;


/**
 * Servlet Filter implementation class CheckAthunticate
 */
@WebFilter(urlPatterns = {
		"/service/inquiryInsert.do","/service/commentInsert.do","/service/boardInsert.do"
		,"/service/reportList.do","/service/inquiryContent.do","/service/commentDelete.do"
		,"/service/commentUpdate.do","/productRegist.do","/service/reportContent.do"


})
public class LoginCheckFillter extends HttpFilter implements Filter {

    public LoginCheckFillter() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session = ((HttpServletRequest)request).getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			request.setAttribute("msg", "로그인 후 사용하세요.");
			request.setAttribute("loc", "/loginView.do");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		} else {
			
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
