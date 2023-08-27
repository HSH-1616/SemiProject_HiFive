package com.semi.admin.model.vo;

import com.semi.category.model.vo.Category;
import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;
import com.semi.sc.model.dto.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportProductMember {
	private Report report; // 하나의 클래스안의 멤버변수에 3개의 객체클래스 넣음
	private Product product;
	private Member member;
}
