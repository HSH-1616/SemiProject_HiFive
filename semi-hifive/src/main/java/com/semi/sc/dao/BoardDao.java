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

import com.semi.sc.model.dto.Board;
import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.model.dto.BoardFile;
public class BoardDao {
	private final Properties sql=new Properties();
	{
		String path = BoardDao.class.getResource("/sql/service/board_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Board 객체 반환 메소드
	public static Board getBoard(ResultSet rs) throws SQLException{
		return Board.builder()
				.boardContent(rs.getString("board_content"))
				.boardNo(rs.getInt("board_no"))
				.boardWriter(rs.getString("nickname"))
				.boardTitle(rs.getString("board_title"))
				.boardDate(rs.getDate("board_date"))
				.boardCategory(rs.getString("board_category"))
				.noticeYn(rs.getString("notice_yn").charAt(0))
				.build();
	}
	
	//board file 반환 메소드
	public static BoardFile getBoardFile(ResultSet rs, int boardNo) throws SQLException{
		return BoardFile.builder().boardNo(rs.getInt("board_no"))
				.boardFileName(rs.getString("board_renamed_filename"))
				.fileNo(rs.getInt("file_no")).build();
	}
	
	//board comment 반환 메소드
	public static BoardComment getComment(ResultSet rs) throws SQLException{
		return BoardComment.builder()
				.commentNo(rs.getInt("board_comment_no"))
				.commentWriter(rs.getString("nickname"))
				.boardNo(rs.getInt("board_no"))
				.commentNoFK(rs.getInt("board_comment_fk"))
				.commentDate(rs.getDate("board_comment_date"))
				.commentContent(rs.getString("board_comment_content"))
				.build();
	}
	
	//구분에 따라 공지사항, 자주하는 질문 테이블 갯수 불러오는 메소드
	public int selectBoardCount(Connection conn, String noticeYN) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCount"));
			pstmt.setString(1, noticeYN);
			rs=pstmt.executeQuery();	
			if(rs.next()) {
				count=rs.getInt("boardCount");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public List<Board> selectBoardList(Connection conn, int cPage, int numPerpage, String noticeYN) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> boardList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardList"));
			pstmt.setString(1, noticeYN);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				boardList.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBoard"));
			pstmt.setString(1, b.getBoardWriter());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardCategory());
			pstmt.setString(5, String.valueOf(b.getNoticeYn()));
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//파일 저장
	public int insertBoardFile(Connection conn, String file) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertFile"));
			pstmt.setString(1, file);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//boardNo에 해당하는 컬럼 불러옴
	public Board selectBoardContent(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardContent"));
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=getBoard(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	public List<BoardFile> selectBoardFile(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardFile> files=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardFile"));
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				files.add(getBoardFile(rs, boardNo));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return files;
	}
	public List<BoardComment> selectBoardComment(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> comments=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selecComment"));
			pstmt.setInt(1, boardNo);
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
	
	//카테고리별 게시판 갯수
	public int selectBoardCountByCategory(Connection conn, String category) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectCategoryCount"));
			pstmt.setString(1, category);
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
	
	//카테고리 별로 자주하는 질문 리스트 조회
	public List<Board> selectBoardByCategory(Connection conn, int cPage, int numPerpage, String category) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> boards=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCategory"));
			pstmt.setString(1, category);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				boards.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boards;
	}
	
	//댓글 추가 메소드
	public int insertBoardComment(Connection conn, BoardComment bc) {
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

	public int deleteBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteBoard"));
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	

}
