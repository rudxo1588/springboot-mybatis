package com.mybatis.demo.biz.domain;

import java.util.List;

import lombok.Data;


/**
 * lombok 사용시 @Data 릉 사용하면 
 * @Getter, @Setter @ToString, @EqualsAndHashCod, @RequiredArgsConstructor
 * 를 선언하지 않아도 모두 포함하게 된다.
 */
@Data
public class FaqVo {
	
	private int faqSeq;
	
	private String faqTitle;
	
	private String faqContent;
	
	private List<FaqImgVo> faqImgList;
}
