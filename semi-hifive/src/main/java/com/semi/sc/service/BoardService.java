package com.semi.sc.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.sc.dao.BoardDao;
import com.semi.sc.model.dto.Board;
import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.model.dto.BoardFile;
public class BoardService {
	private BoardDao dao=new BoardDao();

	public int selectBoardCount(String noticeYN) {
		Connection conn=getConnection();
		int count=dao.selectBoardCount(conn, noticeYN);
		close(conn);
		return count;
	}

	public List<Board> selectBoardList(int cPage, int numPerpage, String noticeYN) {
		Connection conn=getConnection();
		List<Board> boardList=dao.selectBoardList(conn, cPage, numPerpage, noticeYN);
		close(conn);
		return boardList;
	}

	public int insertBoard(Board b, List<String> filesNames) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn, b);
		int fileresult=0;
		for(String file:filesNames) {
			fileresult+=dao.insertBoardFile(conn, file);
		}
		if(result>0&&fileresult==filesNames.size()) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Board selectBoardContent(int boardNo) {
		Connection conn=getConnection();
		Board b=dao.selectBoardContent(conn, boardNo);
		close(conn);
		return b;
	}

	public List<BoardFile> selectBoardFile(int boardNo) {
		Connection conn=getConnection();
		List<BoardFile> files=dao.selectBoardFile(conn, boardNo);
		close(conn);
		return files;
	}

	public List<BoardComment> selectBoardComment(int boardNo) {
		Connection conn=getConnection();
		List<BoardComment> comments=dao.selectBoardComment(conn, boardNo);
		close(conn);
		return comments;
	}

	public List<Board> selectBoardByCategory(int cPage, int numPerpage, String category) {
		Connection conn=getConnection();
		List<Board> boards=dao.selectBoardByCategory(conn, cPage, numPerpage, category);
		close(conn);
		return boards;
	}

	public int selectBoardCountByCategory(String category) {
		Connection conn=getConnection();
		int count=dao.selectBoardCountByCategory(conn,category);
		close(conn);
		return count;
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn, bc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateComment(int commentNo, String data) {
		Connection conn=getConnection();
		int result=dao.updateComment(conn, commentNo, data);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteComment(int commentNo) {
		Connection conn=getConnection();
		int result=dao.deleteComment(conn, commentNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteBoard(int boardNo) {
		Connection conn=getConnection();
		int result=dao.deleteBoard(conn, boardNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
}
