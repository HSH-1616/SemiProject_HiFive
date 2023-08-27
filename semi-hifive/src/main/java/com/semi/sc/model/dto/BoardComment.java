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
public class BoardComment {
	private int commentNo;
	private String commentWriter;
	private int boardNo;
	private int commentNoFK;
	private Date commentDate;
	private String commentContent;
}
