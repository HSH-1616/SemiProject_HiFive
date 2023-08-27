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


@WebFilter(urlPatterns={"/enrollMemberEnd.do","/login.do","/mypage/updatePasswordEnd.do",
		"/changePassword.do","/findPwdSendEmail","/mypage/deleteUserCheck.do"})
public class PasswordEncryptoFilter extends HttpFilter implements Filter {
       

    public PasswordEncryptoFilter() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("필터적용된다");
		// 암호화처리하는 로직을 생성...
		// password 호출했을 때 암호화처리
		// getParamter()메소드를 재정의해서 사용
		
		PasswordEncryptoWrapper pwew = new PasswordEncryptoWrapper((HttpServletRequest)request);
		
		chain.doFilter(pwew, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
