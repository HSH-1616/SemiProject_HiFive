package com.semi.sc.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportData {
	private String userId;
	private int productId;
	private int tradeId;
	private String productTitle;
	private int price;
	private Date registTime;
	private Date sellDate;
	private String buyerId;
}
