package com.semi.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.MemberShopPage;
import com.semi.mypage.service.MypageMemberService;
import com.semi.productpage.model.vo.ShopPage;

/**
 * Servlet implementation class UpdateEndMember
 */
@WebServlet("/member/updateEndMember.do")
public class UpdateEndMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateEndMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일업로드 설정
		String path = getServletContext().getRealPath("/upload/profileImg");
		int maxSize = 1024 * 1024 * 50;
		String encode = "UTF-8";
		DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encode, dfr);

		// post형식 닉네임, 유저
		String inputNick = mr.getParameter("myPageNickname");
		String userId = mr.getParameter("myPageUserId");

		// 파일제외 업데이트
		MemberShopPage m = MemberShopPage.builder().member(Member.builder().userId(mr.getParameter("myPageUserId"))
				.nickName(mr.getParameter("myPageNickname")).profileImg(mr.getFilesystemName("uploadProfile")).build())
				.shopPage(ShopPage.builder().introduce(mr.getParameter("myPageIntroduce")).build()).build();
		//System.out.println("입력받은 닉네임" + inputNick);
		
		// 내 닉네임
		Member m1 = new MypageMemberService().selectNicknameByUserId(userId);
		//System.out.println("m1" + m1.getNickName());

		// 다른 유저가 사용중인 닉네임
		Member m2 = new MypageMemberService().selectByNicknameAll(inputNick);
		//System.out.println("m2" + m2);	
		
		String msg="",loc="";
		
		if(m2 == null || m1.getNickName().equals(m2.getNickName())) {
			System.out.println("사용가능");
			int result = new MypageMemberService().updateMemberNickname(m);
			int result2 = new MypageMemberService().updateMemberIntroduce(m);
			if(result>0 && result2>0) {
				// 성공
				msg="회원정보가 수정되었습니다.";
				loc="/mypage/mypageUpdate.do?userId="+m.getMember().getUserId();
			} else {
				// 실패
				msg="회원정보 수정에 실패했습니다.";
				loc="/mypage/mypageUpdate.do?userId="+m.getMember().getUserId();
			}
		} else {
			System.out.println("중복");
			msg="이미 존재하는 닉네임입니다.";
			loc="/mypage/mypageUpdate.do?userId="+m.getMember().getUserId();
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
