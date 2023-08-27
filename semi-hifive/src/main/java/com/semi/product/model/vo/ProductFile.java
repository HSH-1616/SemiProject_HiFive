package com.semi.product.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFile {

	private int productId;
	private String imageName;
	private char mainImageYn;
	private int fileNo;

}
