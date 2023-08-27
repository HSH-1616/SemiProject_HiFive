package com.semi.sc.inquiry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.model.dto.Inquiry;
import com.semi.sc.model.dto.ServiceFile;
import com.semi.sc.service.InquiryService;


@WebServlet("/service/inquiryContent.do")
public class InquiryContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InquiryContentServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		char secret=request.getParameter("s").charAt(0);
		HttpSession session = ((HttpServletRequest)request).getSession();
		String loginId=((String)((Member)session.getAttribute("loginMember")).getNickName());
		int inquiryNo=Integer.parseInt(request.getParameter("no"));
		Inquiry q=new InquiryService().selectInquiryContent(inquiryNo);
		if (secret == 'Y') {// 비밀글
			if (!(q.getInquiryWriter().equals(loginId))
					&&((Member)session.getAttribute("loginMember")).getAuth().equals("U")) { 
				//작성자랑 일치하거나 관리자 권한이 아닐때
				request.setAttribute("msg", "조회 권한이 없습니다.");
				request.setAttribute("loc", "/service/inquiryList.do");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				return;
			}
		}
		
		//첨부파일 불러오는 메소드
		List<ServiceFile> files=new InquiryService().selectInquiryFile(inquiryNo);
				
		//댓글 데이터 가져오는 메소드
		List<BoardComment> comments=new InquiryService().selectInquiryComment(inquiryNo);
		
		request.setAttribute("inquiry", q);
		request.setAttribute("files", files);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/views/service/inquiryContent.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
