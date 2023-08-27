package com.semi.productlist.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryList {
	private int productId;
	private String userId;
	private String productTitle;
	private String productStatus;
	private String sellStatus;
	private int price;
	private Date registTime;
	private int viewCount;
	private String explanation;
	private String[] keyword;
	private String subcategoryName;
	private String areaName;
	
}
