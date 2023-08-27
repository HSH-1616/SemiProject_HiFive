package com.semi.product.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private int productId;
	private String userId;
	private String title;
	private String productStatus;
	private String sellStatus;
	private int price;
	private Date registTime;
	private int viewCount;
	private String explanation;
	private String keyword;
	private String areaName;
	private String subCategoryName;
	private List<ProductFile> files=new ArrayList();
	
}