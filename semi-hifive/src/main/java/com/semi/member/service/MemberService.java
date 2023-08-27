package com.semi.member.service;
import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.member.dao.MemberDao;
import com.semi.member.model.vo.Member;



public class MemberService {
   private MemberDao dao = new MemberDao();
   
   public Member selectByUserIdAndPw(String userId, String password) {
      Connection conn=getConnection(); // jdbc 연결객체
      Member m=dao.selectByUserIdAndPw(conn,userId,password);
      close(conn);
      return m;
   }
   
   
   public Member selectByUser(String userId) {
	      Connection conn=getConnection(); // jdbc 연결객체
	      Member m=dao.selectByUser(conn,userId);
	      close(conn);
	      return m;
	   }
   
   
   // 회원가입 진행시 회원정보 추가하는 서비스
   public int insertMember(Member m) {
      Connection conn = getConnection();
      int result = dao.insertMember(conn,m);
      if(result>0)commit(conn);
      else rollback(conn);
      close(conn);
      return result;
   }
   
   
   // 회원가입 진행시 쇼핑페이지를(소개글) 추가하는 서비스
   public int insertShopPage(String userId) {
      Connection conn = getConnection();
      int result = dao.insertShopPage(conn,userId);
      if(result>0)commit(conn);
      else rollback(conn);
      close(conn);
      return result;
   }
   
   
   // 이메일 중복체크 서비스
	public int emailDupCheck(String memberEmail) throws Exception {
		
		Connection conn = getConnection();
		int result= dao.emailDupCheck(conn,memberEmail);
		close(conn);
		return result;
	}
    
	
	// 이메일 인증번호 추가 서비스
	public int insertCertification(String inputEmail, String cNumber) throws Exception {
		
		Connection conn = getConnection();
		
		//1 ) 입력한 이메일과 일치하는 값이 있으면 수정(UPDATE)
		int result = dao.updateCertification(conn,inputEmail,cNumber);
		//2 ) 일치하는 이메일이 없는경우--> 처음인증번호발급-> 삽입(INSERT)
		if ( result ==0 ) {
		    result = dao.insertCertification(conn,inputEmail,cNumber);
		}
		// 트랜잭션 제어 !! (DML구문)
		if(result > 0 ) commit(conn);
		else            rollback(conn);
		
		close(conn);
		
		return result;
	}
   
	
	// 인증번호 맞는지 확인 서비스
	public int checkNumber(String inputEmail, String cNumber) throws Exception {
		
		Connection conn = getConnection();
		int result = dao.checkNumber(conn,inputEmail,cNumber);
		close(conn);
		return result;
	}
	
	
	// 아이디 중복확인해주는 서비스
	public Member selectByUserId(String userId) {
		Connection conn=getConnection();
		Member m = dao.selectByUserId(conn,userId);
		close(conn);
		return m;
	}
	
	// 중복된 닉네임이 있는지 확인해주는 서비스

	public int checkNickName(String userNickName) throws Exception{
		Connection conn = getConnection();
		int result = dao.checkNickName(conn,userNickName);
		close(conn);
		return result;
	}
	
	
	// 이름과 이메일로 아이디를 찾아주는 서비스
	public Member selectByUserNameAndEmail(String name, String email) {
		Connection conn=getConnection();
		Member m = dao.selectByUserNameAndEmail(conn,name,email);
		close(conn);
		return m;
	}
	
	
	// 해당 입력받은 아이디, 이름, 이메일로 회원을 찾는 서비스 
	public Member selectByIdAndNameAndEmail(String id, String name, String email) {
		Connection conn=getConnection();
		Member m = dao.selectByIdAndNameAndEmail(conn,id,name,email);
		close(conn);
		return m;
	}
	
	
	// 받은 인증번호로 패스워드가 변경되는 서비스
	public int updatePassword(String email, String cNumber) {
		Connection conn=getConnection();
		int result = dao.updatePassword(conn,email,cNumber);
		close(conn);
		return result;
	}
	
	
	// 이메일로 비밀번호를 변경하는 서비스
	public int changePasswordByEmail(String newPass, String email) {
		Connection conn=getConnection();
		int result = dao.changePasswordByEmail(conn,newPass,email);
		close(conn);
		return result;
	}
	
	
	
	
}