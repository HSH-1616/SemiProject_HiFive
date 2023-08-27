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
public class Board {
	private int boardNo;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String boardCategory;
	private char noticeYn;
	private String boardOriginalFileName;
	private String boardRenamedFileName;
}