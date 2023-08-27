package com.semi.sc.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.sc.dao.InquiryDao;
import com.semi.sc.model.dto.BoardComment;
import com.semi.sc.model.dto.Inquiry;
import com.semi.sc.model.dto.ServiceFile;

public class InquiryService {
	private InquiryDao dao=new InquiryDao();

	public int selectInquiryCount() {
		Connection conn=getConnection();
		int count=dao.selectInquiryCount(conn);
		close(conn);
		return count;
	}

	public List<Inquiry> selectInquiryList(int cPage, int numPerpage) {
		Connection conn=getConnection();
		List<Inquiry> inquiryList=dao.selectInquiryList(conn,cPage,numPerpage);
		close(conn);
		return inquiryList;
	}

	public Inquiry selectInquiryContent(int inquiryNo) {
		Connection conn=getConnection();
		Inquiry q=dao.selectInquiryContent(conn, inquiryNo);
		close(conn);
		return q;
	}

	public int insertInquiry(Inquiry q, List<String> filesNames) {
		Connection conn=getConnection();
		int result=dao.insertInquiry(conn, q);
		int fileresult=0;
		for(String file:filesNames) {
			fileresult+=dao.insertInquiryFile(conn, file);
		}
		if(result>0&&fileresult==filesNames.size()) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<ServiceFile> selectInquiryFile(int inquiryNo) {
		Connection conn=getConnection();
		List<ServiceFile> files=dao.selectInquiryFile(conn, inquiryNo);
		close(conn);
		return files;
	}

	public List<BoardComment> selectInquiryComment(int inquiryNo) {
		Connection conn=getConnection();
		List<BoardComment> comments=dao.selectInquiryComment(conn, inquiryNo);
		close(conn);
		return comments;
	}

	public int insertComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertComment(conn, bc);
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

	public int updateComment(int commentNo, String data) {
		Connection conn=getConnection();
		int result=dao.updateComment(conn, commentNo, data);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteInquiry(int inquiryNo) {
		Connection conn=getConnection();
		int result=dao.deleteInquiry(conn, inquiryNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}
