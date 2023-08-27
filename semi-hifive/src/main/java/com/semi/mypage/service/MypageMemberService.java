package com.semi.mypage.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.member.model.vo.Member;
import com.semi.mypage.dao.MypageMemberDao;
import com.semi.mypage.model.vo.MemberShopPage;

public class MypageMemberService {
	private MypageMemberDao dao = new MypageMemberDao();
	
//	전체 유저 조회 메소드
	public List<Member> selectMemberAll(){
		Connection conn = getConnection();
		List<Member> list = dao.selectMemberAll(conn);
		close(conn);
		return list;
	}
	
// 아이디로 유저조회
	public MemberShopPage selectByUserId(String userId) {
		Connection conn = getConnection();
		MemberShopPage m = dao.selectByUserId(conn, userId);
		close(conn);
		return m;
	}
	
//	유저 프로필 이미지 수정
	public int updateProfileImg(Member m) {
		Connection conn=getConnection();
		int result=dao.updateProfileImg(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
//	닉네임으로 유저조회
	public Member selectByNickname(String nickname, String userId) {
		Connection conn = getConnection();
		Member m = dao.selectByNickname(conn, nickname, userId);
		close(conn);
		return m;
	}
	
//	닉네임으로 유저조회
	public Member selectByNicknameAll(String nickname) {
		Connection conn = getConnection();
		Member m = dao.selectByNicknameAll(conn, nickname);
		close(conn);
		return m;
	}
	
//	현재 내 닉네임 조회
	public Member selectNicknameByUserId(String userId) {
		Connection conn = getConnection();
		Member m = dao.selectNicknameByUserId(conn, userId);
		close(conn);
		return m;
	}
	
//	유저 정보 수정(닉네임)
	public int updateMemberNickname(MemberShopPage m) {
		Connection conn = getConnection();
		int result = dao.updateMemberNickname(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
//	유저 정보 수정(상점소개)
	public int updateMemberIntroduce(MemberShopPage m) {
		Connection conn = getConnection();
		int result = dao.updateMemberIntroduce(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
//	비밀번호 수정
	public int updatePassword(String userId, String newPwd) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, userId, newPwd);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
//	회원탈퇴
	public int deleteUser(String userId) {
		Connection conn = getConnection();
		int result = dao.deleteUser(conn, userId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}
