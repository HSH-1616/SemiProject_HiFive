package com.semi.productlist.model.vo;

import com.semi.category.model.vo.Category;
import com.semi.category.model.vo.SubCategory;
import com.semi.product.model.vo.ProductFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryTimeList {
	private ProductCategoryList productCategoryList;
//	private String productAndSubCategoryname;
//	private String categorySubCategoryname;
	private long elapsedTime;
	private Category category;
	private SubCategory subCategory;
	private ProductFile productfile;

}
