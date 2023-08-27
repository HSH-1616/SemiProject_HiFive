package com.semi.mypage.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.MemberShopPage;
import com.semi.productpage.model.vo.ShopPage;


public class MypageMemberDao {
		
		private Properties sql = new Properties();
		
		public MypageMemberDao() {
			String path = MypageMemberDao.class.getResource("/sql/mypage/membersql.properties").getPath();
			try(FileReader fr = new FileReader(path)){
				sql.load(fr);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	
		public List<Member> selectMemberAll(Connection conn){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Member> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql.getProperty("selectMemberAll"));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					list.add(getMember(rs));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			} return list;
		}
		
		public MemberShopPage selectByUserId(Connection conn, String userId) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			MemberShopPage m=null;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("selectByUserId"));
				pstmt.setString(1, userId);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					m=getMemberShopPage(rs);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return m;
		}
		
		
		public int updateProfileImg(Connection conn, Member m) {
			PreparedStatement pstmt=null;
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("updateProfileImg"));
				pstmt.setString(1, m.getProfileImg());
				pstmt.setString(2, m.getUserId());
				pstmt.setString(3, m.getPassword());
				result=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}return result;
		}
		
		public Member selectByNickname(Connection conn, String nickname, String userId) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Member m=null;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("selectByNickname"));
				pstmt.setString(1, nickname);
				pstmt.setString(2, userId);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					m=getMember(rs);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return m;
		}
		
		public Member selectByNicknameAll(Connection conn, String nickname) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Member m=null;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("selectByNicknameAll"));
				pstmt.setString(1, nickname);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					m=getMember(rs);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return m;
		}

		// 현재 내 닉네임 조회
		public Member selectNicknameByUserId(Connection conn, String userId) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Member m=null;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("selectNicknameByUserId"));
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
			}
			return m;
		}
		
		// 유저 정보 수정(닉네임, 소개글)
		public int updateMemberNickname(Connection conn, MemberShopPage m) {
			PreparedStatement pstmt=null;
			String img = null;
			if(m.getMember().getProfileImg()!=null) img = m.getMember().getProfileImg();
			else img = "profileImg_default.png";
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("updateMemberNickname"));
				pstmt.setString(1, m.getMember().getNickName());
				pstmt.setString(2, img);
				pstmt.setString(3, m.getMember().getUserId());
				result=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}return result;
		}
		
		public int updateMemberIntroduce(Connection conn, MemberShopPage m) {
			PreparedStatement pstmt=null;
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("updateMemberIntroduce"));
				pstmt.setString(1, m.getShopPage().getIntroduce());
				pstmt.setString(2, m.getMember().getUserId());
				result=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}return result;
		}
		
//		비밀번호 수정
		public int updatePassword(Connection conn, String userId, String newPwd) {
			PreparedStatement pstmt=null;
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("updatePassword"));
				pstmt.setString(1, newPwd);
				pstmt.setString(2, userId);
				result=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}return result;
		}
		
//		회원탈퇴
		public int deleteUser(Connection conn, String userId) {
			PreparedStatement pstmt=null;
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql.getProperty("deleteUser"));
				pstmt.setString(1, userId);
				result=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}return result;
		}
		
//		Member
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
		}
		
//		Member + ShopPage
		public MemberShopPage getMemberShopPage(ResultSet rs) throws SQLException {
			return MemberShopPage.builder()
						.member(Member.builder()
						.userId(rs.getString("user_Id"))
						.email(rs.getString("email"))
						.nickName(rs.getString("nickname"))
						.password(rs.getString("password"))
						.userName(rs.getString("user_name"))
						.declareCount(rs.getInt("declare_Count"))
						.enrollDate(rs.getDate("enroll_Date"))
						.auth(rs.getString("auth"))
						.temperature(rs.getDouble("temperature"))
						.profileImg(rs.getString("profile_Img")).build())
					.shopPage(ShopPage.builder()
							.userId(rs.getString("user_Id"))
							.introduce(rs.getString("introduce")).build())
					.build();
		}

		
}
