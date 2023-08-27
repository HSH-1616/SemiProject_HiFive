package com.semi.product.model.vo;

import java.sql.Date;

import com.semi.productpage.model.vo.ShopPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductComment {
	
	private String userId;
	private int productId;
	private int commentNo;
	private int commentLevel;
	private String content;
	private int commentRef;
	private Date enrollDate;

}

