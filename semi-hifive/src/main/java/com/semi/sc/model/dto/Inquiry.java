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
public class Inquiry {
	private int inquiryNo;
	private String inquiryWriter;
	private String inquiryTitle;
	private String inquiryContent;
	private Date inquiryDate;
	private char inquirySecret;
}
