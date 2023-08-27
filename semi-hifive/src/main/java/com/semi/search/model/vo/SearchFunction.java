package com.semi.search.model.vo;

import java.util.Date;

import com.semi.product.model.vo.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFunction {
	private Product product;
	private Search search;
}
