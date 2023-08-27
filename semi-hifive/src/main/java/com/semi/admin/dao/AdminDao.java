package com.semi.admin.dao;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.member.dao.MemberDao.getMember;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.admin.model.vo.ReportProductMember;
import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;
import com.semi.sc.model.dto.Report;

public class AdminDao {
	private Properties sql = new Properties();

	public AdminDao() { // 기본생성자로 만들어도되고, 초기화블록으로 만들어도됨
		String path = AdminDao.class.getResource("/sql/admin/admin_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public List<Member> selectMemberAll(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberAll"));
			pstmt.setInt(1, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(2, cPage * numPerpage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(getMember(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberCount"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	
	public int userRemove(Connection conn, String email) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("userRemove"));
			pstmt.setString(1, email);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int allUserRemove(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("allUserRemove"));
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int updateMember(Connection conn,String userName,String nickName,String declareCount, String enrollDate, String temp, String email) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateMember"));
			pstmt.setString(1, nickName);
			pstmt.setString(2, userName);
			pstmt.setString(3, declareCount);
			pstmt.setString(4, enrollDate);
			pstmt.setString(5, temp);
			pstmt.setString(6, email);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int deleteCheckMember(Connection conn,String dsql) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteCheckMember").replace("#data", dsql));
//			pstmt.setString(1, dsql);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public List<Member> selectMemberByKeyword(Connection conn, String type, String keyword, int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String query=sql.getProperty("selectMemberByKeyword");
		query=query.replace("#COL", type);  // properties파일안의 ?에 문자열을 인식시키기위해서 사용
		List<Member> members = new ArrayList();
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			// type이 성별일때는 % 없이 처리가능하며, 나머지 속성같은경우에는 부분검색이므로 %가 들어감
			pstmt.setInt(2,(cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	
	public int selectMemberByKeywordCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String query=sql.getProperty("selectMemberByKeywordCount").replace("#COL", type);
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);						
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	public int deleteCheckBoard(Connection conn, String bsql) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteCheckBoard").replace("#data", bsql));
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int boardRemove(Connection conn, String boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("boardRemove"));
			pstmt.setString(1, boardNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int allBoardRemove(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("allBoardRemove"));
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int updateBoard(Connection conn,String boardTitle,String boardContent,String boardDate, String boardCategory, String boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateBoard"));
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardDate);
			pstmt.setString(4, boardCategory);
			pstmt.setString(5, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public List<Report> selectReportList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Report> result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectReportList"));
			pstmt.setInt(1, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(2, cPage * numPerpage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(Report.builder()
			            .reportContent(rs.getString("report_content"))
			            .reportNo(rs.getInt("report_no"))
			            .reportWriter(rs.getString("report_writer"))
			            .reportTitle(rs.getString("report_title"))
			            .reportDate(rs.getDate("report_date"))
			            .reportCategory(rs.getString("report_category"))
			            .productId(rs.getInt("product_id"))
			            .tradeId(rs.getInt("trade_id"))
			            .completed(rs.getString("COMPLETED"))
			            .build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public int selectReportCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectReportCount"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int reportRemove(Connection conn, String reportNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("reportRemove"));
			pstmt.setString(1, reportNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int deleteCheckReport(Connection conn, String rsql) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteCheckReport").replace("#data", rsql));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public ReportProductMember reportProductMember(Connection conn, String reportNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportProductMember r = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("reportProductMember"));
			pstmt.setString(1, reportNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 필요한 데이터들만 빌더해서 갖고옴
				r = ReportProductMember.builder()
						.report(Report.builder()
								.reportNo(rs.getInt("REPORT_NO")).build())
						.member(Member.builder()
								.userId(rs.getString("USER_ID"))
								.declareCount(rs.getInt("DECLARE_COUNT"))
								.temperature(rs.getDouble("TEMPERATURE")).build()).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return r;
	}
	
	
	
	public int increaseDeclareCount(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("increaseDeclareCount"));
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int selectDeclareCount(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectDeclareCount"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return cnt;
	}
	
	
	public int decreaseTemp(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result1 = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("decreaseTemp"));
			pstmt.setString(1, userId);
			result1 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result1;
	}
	
	
	public int changeComplete(Connection conn, String reportNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("changeComplete"));
			pstmt.setString(1, reportNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public List<Report> completeSelectReportList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Report> result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("completeSelectReportList"));
			pstmt.setInt(1, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(2, cPage * numPerpage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(Report.builder()
			            .reportContent(rs.getString("report_content"))
			            .reportNo(rs.getInt("report_no"))
			            .reportWriter(rs.getString("report_writer"))
			            .reportTitle(rs.getString("report_title"))
			            .reportDate(rs.getDate("report_date"))
			            .reportCategory(rs.getString("report_category"))
			            .productId(rs.getInt("product_id"))
			            .tradeId(rs.getInt("trade_id"))
			            .completed(rs.getString("COMPLETED"))
			            .build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int completeSelectReportCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("completeSelectReportCount"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public List<Product> selectProdcutList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectProdcutList"));
			pstmt.setInt(1, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(2, cPage * numPerpage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(Product.builder()
			            .productId(rs.getInt("PRODUCT_ID"))
			            .userId(rs.getString("USER_ID"))
			            .title(rs.getString("PRODUCT_TITLE"))
			            .productStatus(rs.getString("PRODUCT_STATUS"))
			            .price(rs.getInt("PRICE"))
			            .registTime(rs.getDate("REGIST_TIME"))
			            .explanation(rs.getString("EXPLANATION"))
			            .keyword(rs.getString("KEYWORD"))
			            .subCategoryName(rs.getString("SUBCATEGORY_NAME"))
			            .areaName(rs.getString("AREA_NAME"))
			            .build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public int selectProductCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectProductCount"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public int productRemove(Connection conn, String no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("productRemove"));
			pstmt.setString(1, no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int deleteCheckProduct(Connection conn, String psql) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteCheckProduct").replace("#data", psql));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
}
