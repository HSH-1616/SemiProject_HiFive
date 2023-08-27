 package com.semi.member.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.member.model.vo.Member;


public class MemberDao {

	private Properties sql=new Properties(); // Properties 파일객체 만듬
	{
		String path=MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
		try {
			sql.load(new FileReader(path)); // 해당경로에 객체 파일 연결
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 아이디는 기본키이기때문에 하나만나옴
	public Member selectByUserIdAndPw(Connection conn,
			String userId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(
					sql.getProperty("selectByUserIdAndPw"));
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	
	public Member selectByUser(Connection conn,String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(
					sql.getProperty("selectByUser"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	
	// 회원정보를 추가하는 dao
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertMember"));
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getNickName());
			pstmt.setString(4, m.getPassword());
			pstmt.setString(5, m.getUserName());
//			pstmt.setString(9, String.join(",",m.getHobby()));  // 배열을 ,구분해서 문자열로만듬
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	// 쇼핑페이지를 추가하는 dao
	public int insertShopPage(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertShopPage"));
			pstmt.setString(1, userId);

			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	
	// 이메일 중복검사 DAO
	public int emailDupCheck(Connection conn, String memberEmail) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs =null;
		try {
			//sql 얻어오기
			pstmt=conn.prepareStatement(sql.getProperty("emailDupCheck"));
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();       // sql 실행후 결과받기
			
			if (rs.next()) {
				result = rs.getInt(1);  // count(*) 값.
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	// 이메일 인증번호 발급일 수정 DAO
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateCertification"));
			pstmt.setString(1, cNumber);
			pstmt.setString(2, inputEmail);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	// 이메일 인증번호 생성 dao
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertCertification"));

			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			
			result = pstmt.executeUpdate();
					
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	// 인증번호 확인하는 dao
	public int checkNumber(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("checkNumber"));
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			pstmt.setString(3, inputEmail);			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				result = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	// 아이디 중복확인해주는 dao
	public Member selectByUserId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByUserId"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	
	public int checkNickName(Connection conn, String userNickName) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result = 0;
		try {

			pstmt=conn.prepareStatement(sql.getProperty("selectByNickName"));

			pstmt.setString(1, userNickName);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	
	// 이름과 이메일로 아이디를 찾아주는 dao
	public Member selectByUserNameAndEmail(Connection conn, String name, String email) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByUserNameAndEmail"));
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	
	// // 해당 입력받은 아이디, 이름, 이메일로 회원을 찾는 Dao
	public Member selectByIdAndNameAndEmail(Connection conn, String id, String name, String email) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByIdAndNameAndEmail"));
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}

		
	// 받은 인증번호로 패스워드가 변경되는 dao
	public int updatePassword(Connection conn, String inputEmail, String cNumber) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updatePassword"));
			
			pstmt.setString(1, cNumber);
			pstmt.setString(2, inputEmail);	

			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	// 이메일로 비밀번호 변경하는 dao
	public int changePasswordByEmail(Connection conn, String newPass, String email) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("changePasswordByEmail"));
			
			pstmt.setString(1, newPass);
			pstmt.setString(2, email);	

			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	
	//public static 하는 이유 : 다른 패키지에서도 이 메소드 사용하기 위해서
	public static Member getMember(ResultSet rs) throws SQLException{ 
		return Member.builder()
	            .userId(rs.getString("user_Id"))
	            .email(rs.getString("email"))
	            .nickName(rs.getString("nickname"))
	            .password(rs.getString("password"))
	            .userName(rs.getString("user_name"))
	            .declareCount(rs.getInt("declare_Count"))
	            .enrollDate(rs.getDate("enroll_Date"))
	            .auth(rs.getString("auth"))
	            .temperature(rs.getDouble("temperature"))
	            .profileImg(rs.getString("profile_Img"))
	            .build();
		//	.gender(rs.getString("gender").charAt(0))
		//	.hobby(rs.getString("hobby")!=null?rs.getString("hobby").split(","):null)
	}
}
