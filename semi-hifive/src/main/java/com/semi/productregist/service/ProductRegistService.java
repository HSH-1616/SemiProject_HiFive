package com.semi.productregist.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.semi.category.model.vo.Category;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductFile;
import com.semi.productregist.dao.ProductRegistDao;

public class ProductRegistService {
	private ProductRegistDao dao = new ProductRegistDao();

	
	// 대표카테고리 찾는 작업
	public List<Category> selectAll() {
		Connection conn = getConnection(); // jdbc 연결객체
		List<Category> categorys = dao.selectAll(conn);
		close(conn);
		return categorys;
	}

	// 서브카테고리 찾는 작업
	public List<String> selectSubCate(String cateId){
		Connection conn = getConnection(); // jdbc 연결객체
		List<String> subCategorys = dao.selectSubCate(conn,cateId);
		close(conn);
		return subCategorys;
	}
	
	
	public int insertProduct(Product p, String userId) {
		Connection conn = getConnection(); // jdbc 연결객체
		int result = dao.insertProduct(conn,p,userId);  // Product 테이블안에 하나의 튜플(로우)를 만드는 작업

		List<ProductFile> files=p.getFiles(); // files에 해당 상품이미지첨부파일 객체 값을 넣음
		
		int count = 0;
		if(p.getFiles().size()>0) {
			for(ProductFile f: files) { // 저장해놓은 이미지파일객체들을 모두 순회하면서 각 f객체에 하나씩 값을 넣으면서 하나의 로우를 만들어줌
				f.setImageName(f.getImageName());
				f.setMainImageYn('N'); // 첫번째이미지외 나머지들은 N으로해줌
				if(count == p.getFiles().size()-1) { // 첫번째 이미지는 대표이미지 표시인 Y로 해줌
					f.setMainImageYn('Y');
				}
				result=dao.insertProductFile(conn,f);
				count++;
				if(result>0)commit(conn);
				else rollback(conn);
			}
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public Product selectProduct(String productId) {
		Connection conn = getConnection(); // jdbc 연결객체
		Product p = dao.selectProduct(conn,productId);
		close(conn);
		return p;
	}
	
	
	public List<ProductFile> selectProductFile(String productId){
		Connection conn = getConnection(); // jdbc 연결객체
		List<ProductFile> pf = dao.selectProductFile(conn,productId);
		close(conn);
		return pf;
	}
	
	
	public int updateProduct(Product p, String userId) {
		Connection conn = getConnection(); // jdbc 연결객체
		int result = dao.updateProduct(conn,p,userId);  // Product 테이블안에 하나의 튜플(로우)를 만드는 작업
		p.getProductId();
		int result2 = dao.deleteProductFile(conn,p);
		
		List<ProductFile> files=p.getFiles(); // files에 해당 상품이미지첨부파일 객체 값을 넣음
		
		
		int count = 0;
		if(p.getFiles().size()>0) {
			for(ProductFile f: files) { // 저장해놓은 이미지파일객체들을 모두 순회하면서 각 f객체에 하나씩 값을 넣으면서 하나의 로우를 만들어줌
				f.setProductId(p.getProductId());
//				f.setImageName(f.getImageName());
				f.setMainImageYn('N'); // 첫번째이미지외 나머지들은 N으로해줌
				System.out.println(f.getProductId());
				if(count == p.getFiles().size()-1) { // 첫번째 이미지는 대표이미지 표시인 Y로 해줌
					f.setMainImageYn('Y');
				}
				result=dao.updateInsertProductFile(conn,f);
				count++;
				if(result>0)commit(conn);
				else rollback(conn);
			}
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	
}
