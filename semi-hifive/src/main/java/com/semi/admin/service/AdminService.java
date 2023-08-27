package com.semi.admin.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.semi.admin.dao.AdminDao;
import com.semi.admin.model.vo.ReportProductMember;
import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;
import com.semi.sc.model.dto.Report;

public class AdminService {

	private AdminDao dao = new AdminDao();

	public List<Member> selectMemberAll(int cPage, int numPerpage) {
		Connection conn = getConnection();
		List<Member> m = dao.selectMemberAll(conn, cPage, numPerpage);
		close(conn);
		return m;
	}

	public int selectMemberCount() {
		Connection conn = getConnection();
		int result = dao.selectMemberCount(conn);
		close(conn);
		return result;
	}

	public int userRemove(String email) {
		Connection conn = getConnection();
		int result = dao.userRemove(conn, email);
		close(conn);
		return result;
	}

	public int allUserRemove() {
		Connection conn = getConnection();
		int result = dao.allUserRemove(conn);
		close(conn);
		return result;
	}
	// userName,nickName,declareCount,enrollDate,temp,email

	public int updateMember(String userName, String nickName, String declareCount, String enrollDate, String temp,
			String email) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, userName, nickName, declareCount, enrollDate, temp, email);
		close(conn);
		return result;
	}

	// 체크된 멤버들 삭제
	public int deleteCheckMember(String sql) {
		Connection conn = getConnection();
		int result = dao.deleteCheckMember(conn, sql);
		close(conn);
		return result;
	}

	public List<Member> selectMemberByKeyword(String type, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> members = dao.selectMemberByKeyword(conn, type, keyword, cPage, numPerPage);
		close(conn);
		return members;
	}

	public int selectMemberByKeywordCount(String type, String keyword) {
		Connection conn = getConnection();
		int count = dao.selectMemberByKeywordCount(conn, type, keyword);
		close(conn);
		return count;
	}
	
	public int allBoardRemove() {
		Connection conn = getConnection();
		int result = dao.allBoardRemove(conn);
		close(conn);
		return result;
	}
	
	
	//체크된 공지사항들 삭제
	public int deleteCheckBoard(String sql) {
		Connection conn = getConnection();
		int result = dao.deleteCheckBoard(conn, sql);
		close(conn);
		return result;
	}
	
	
	
	//삭제버튼 눌러서 공지사항 삭제
	public int boardRemove(String boardNo) {
		Connection conn = getConnection();
		int result = dao.boardRemove(conn, boardNo);
		close(conn);
		return result;
	}
	
	//게시글 수정
	public int updateBoard(String boardTitle, String boardContent, String boardDate, String boardCategory, String boardNo) {
		Connection conn = getConnection();
		int result = dao.updateBoard(conn, boardTitle, boardContent, boardDate, boardCategory, boardNo);
		close(conn);
		return result;
	}
	
	// 모든 신고글 조회
	public List<Report> selectReportList(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Report> result = dao.selectReportList(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	
	// 모든 신고글 개수
	public int selectReportCount() {
		Connection conn = getConnection();
		int result = dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	// 삭제버튼 눌러서 신고글 삭제
	public int reportRemove(String reportNo) {
		Connection conn = getConnection();
		int result = dao.reportRemove(conn, reportNo);
		close(conn);
		return result;
	}
	
	//체크된 신고글들 삭제
	public int deleteCheckReport(String sql) {
		Connection conn = getConnection();
		int result = dao.deleteCheckReport(conn, sql);

		close(conn);
		return result;
	}
	
	// 신고번호로 해당 상품정보와, 유저정보까지 같이 조인
	public ReportProductMember reportProductMember(String reportNo) {
		Connection conn = getConnection();
		ReportProductMember rpm = dao.reportProductMember(conn, reportNo);
		close(conn);
		return rpm;
	}
	
	// 해당 아이디 신고 누적수 1 증가
	public int increaseDeclareCount(String userId) {
		Connection conn = getConnection();
		int result = dao.increaseDeclareCount(conn, userId);
		close(conn);
		return result;
	}
	
	// 해당 아이디의 누적 신고수 개수 확인
	public int selectDeclareCount(String userId) {
		Connection conn = getConnection();
		int cnt = dao.selectDeclareCount(conn, userId);
		close(conn);
		return cnt;
	}
	
	
	// 해당아이디의 온도 1감소
	public int decreaseTemp(String userId) {
		Connection conn = getConnection();
		int result1 = dao.decreaseTemp(conn, userId);
		close(conn);
		return result1;
	}
	
	// 신고처리된 신고글 처리여부로 바꿔줌
	public int changeComplete(String reportNo) {
		Connection conn = getConnection();
		int result0 = dao.changeComplete(conn,reportNo);
		close(conn);
		return result0;
	}
	
	// 신고처리된 신고글 불러오기
	public List<Report> completeSelectReportList(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Report> result = dao.completeSelectReportList(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	
	// 신고처리된 신고글 개수
	public int completeSelectReportCount() {
		Connection conn = getConnection();
		int result = dao.completeSelectReportCount(conn);
		close(conn);
		return result;
	}
	
	
	// 모든 상품게시글 조회
	public List<Product> selectProdcutList(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Product> result = dao.selectProdcutList(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	
	
	// 모든 상품게시글 개수
	public int selectProductCount(){
		Connection conn = getConnection();
		int result = dao.selectProductCount(conn);
		close(conn);
		return result;
	}
	
	// 해당 아이디로 상품게시글 삭제
	public int productRemove(String no) {
		Connection conn = getConnection();
		int result = dao.productRemove(conn, no);
		close(conn);
		return result;
	}
	
	// 체크한 상품들 다 삭제
	public int deleteCheckProduct(String sql) {
		Connection conn = getConnection();
		int result = dao.deleteCheckProduct(conn, sql);
		close(conn);
		return result;
	}
	
}



