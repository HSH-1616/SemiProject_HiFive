package com.semi.sc.dao;

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

import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.model.dto.Inquiry;
import com.semi.sc.model.dto.ServiceFile;

public class InquiryDao {
	private final Properties sql=new Properties();
	{
		String path = BoardDao.class.getResource("/sql/service/inquiry_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Inquiry 객체 반환 메소드
	public Inquiry getInquiry(ResultSet rs) throws SQLException {
		return Inquiry.builder()
				.inquiryContent(rs.getString("inquiry_content"))
				.inquiryNo(rs.getInt("inquiry_no"))
				.inquiryWriter(rs.getString("nickname"))
				.inquiryTitle(rs.getString("inquiry_title"))
				.inquiryDate(rs.getDate("inquiry_date"))
				.inquirySecret(rs.getString("inquiry_secret").charAt(0))
				.build();
	}
	
	//file 반환 메소드
	public static ServiceFile getFile(ResultSet rs) throws SQLException {
		return ServiceFile.builder()
				.contentNo(rs.getInt("inquiry_no"))
				.filename(rs.getString("inquiry_renamed_filename"))
				.fileNo(rs.getInt("file_no")).build();
	}
	
	//comment 반환 메소드
	public static BoardComment getComment(ResultSet rs) throws SQLException {
		return BoardComment.builder()
				.commentNo(rs.getInt("inquiry_comment_no"))
				.commentWriter(rs.getString("nickname"))
				.boardNo(rs.getInt("inquiry_no"))
				.commentNoFK(rs.getInt("inquiry_comment_fk"))
				.commentDate(rs.getDate("inquiry_comment_date"))
				.commentContent(rs.getString("inquiry_comment_content"))
				.build();
	}
	
	//문의글 전체 수
	public int selectInquiryCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	//문의글 리스트 페이징처리 해서 가져오기
	public List<Inquiry> selectInquiryList(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Inquiry> inquiryList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectInquiryList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				inquiryList.add(getInquiry(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return inquiryList;
	}

	//문의글 내용
	public Inquiry selectInquiryContent(Connection conn, int inquiryNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Inquiry q=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectInquiryContent"));
			pstmt.setInt(1, inquiryNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				q=getInquiry(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return q;
	}

	//파일 저장
	public int insertInquiryFile(Connection conn, String file) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertFile"));
			pstmt.setString(1, file);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//문의글 저장
	public int insertInquiry(Connection conn, Inquiry q) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertInquiry"));
			pstmt.setString(1, q.getInquiryWriter());
			pstmt.setString(2, q.getInquiryTitle());
			pstmt.setString(3, q.getInquiryContent());
			pstmt.setString(4, String.valueOf(q.getInquirySecret()));
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//file 조회
	public List<ServiceFile> selectInquiryFile(Connection conn, int inquiryNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ServiceFile> files=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFile"));
			pstmt.setInt(1, inquiryNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				files.add(getFile(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return files;
	}

	//댓글 조회
	public List<BoardComment> selectInquiryComment(Connection conn, int inquiryNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> comments=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectComment"));
			pstmt.setInt(1, inquiryNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				comments.add(getComment(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return comments;
	}

	//댓글 추가
	public int insertComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=sql.getProperty("insertComment");
		try {
			query=query.replaceAll("#FK", bc.getCommentNoFK()==0?"null":String.valueOf(bc.getCommentNoFK()));
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bc.getCommentWriter());
			pstmt.setInt(2, bc.getBoardNo());
			pstmt.setString(3, bc.getCommentContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//댓글 삭제
	public int deleteComment(Connection conn, int commentNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteComment"));
			pstmt.setInt(1, commentNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//댓글 수정
	public int updateComment(Connection conn, int commentNo, String data) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateComment"));
			pstmt.setString(1, data);
			pstmt.setInt(2, commentNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteInquiry(Connection conn, int inquiryNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteInquiry"));
			pstmt.setInt(1, inquiryNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
}
