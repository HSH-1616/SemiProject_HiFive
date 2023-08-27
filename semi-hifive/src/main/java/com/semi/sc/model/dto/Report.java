package com.semi.sc.model.dto;

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
public class Report {
	private int reportNo;
	private String reportWriter;
	private String reportTitle;
	private String reportContent;
	private Date reportDate;
	private String reportCategory;
	private int productId;
	private int tradeId;
	private String completed;
}
