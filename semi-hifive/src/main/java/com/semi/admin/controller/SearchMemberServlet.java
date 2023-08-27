package com.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;
import com.semi.member.model.vo.Member;

@WebServlet("/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("searchKeyword");
		String type=request.getParameter("option");
		
		int cPage, numPerPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage=5;
		}
		
		List<Member> members = new AdminService().selectMemberByKeyword(type,keyword,cPage,numPerPage);
		
		request.setAttribute("members", members);
		
		String pageBar="";
		int totalData=new AdminService().selectMemberByKeywordCount(type, keyword);
		
		int totalPage=(int)Math.ceil((double)totalData/numPerPage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1) {  // -> 1~5페이지까지 해당됨
			pageBar+="<span>[이전]</span>";
		}else {  // -> 그 이후부터는 이전페이지를 클릭 가능한 태그를 만들어서 이동할때의 페이지에 값을 넘겨줌
			pageBar+="<a href='"+request.getRequestURI()+"?option="+type // 현재페이지에서 이동
					+"&searchKeyword="+keyword //
					+"&cPage="+(pageNo-1)
					+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) { 
				pageBar+="<span>"+pageNo+"</span>";
			}else {
						pageBar+="<a href='"+request.getRequestURI()+"?option="+type
						+"&searchKeyword="+keyword
						+"&cPage="+pageNo
						+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";  // 다음이 클릭될수없다
		}else {
					pageBar+="<a href='"+request.getRequestURI()+"?option="+type
					+"&searchKeyword="+keyword
					+"&cPage="+pageNo
					+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/views/admin/manageMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
