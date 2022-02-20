package com.mybatis.demo.faq.domain;

import java.util.List;

import lombok.Data;


/**
 * lombok 사용시 @Data 릉 사용하면 
 * @Getter, @Setter, @ToString, @EqualsAndHashCod, @RequiredArgsConstructor
 * 를 선언하지 않아도 모두 포함하게 된다.
 * 하지만 @Getter, @Setter 를 사용시 setter 메소드가 필요없는 필드에 대해서도
 * setter메소드가 강제 생성되므로 필드 값 변경의 위험이 있어서 잘 보고 사용해야한다.
 * 이런 부분들은 리펙토링 대상이기 때문에 롬복을 사용할 경우 리팩토링이 힘들어지는 단점이 있다.
 */
@Data
public class FaqVo {
	
	private int faqSeq;
	
	private String faqTitle;
	
	private String faqContent;
	
	private String faqCreatedt;
	
	private String faqDelYn;
	
	private List<FaqImgVo> faqImgList;
	 
}
