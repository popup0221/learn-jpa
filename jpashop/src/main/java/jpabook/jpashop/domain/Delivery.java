package jpabook.jpashop.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

	@Id @GeneratedValue
	private Long Id;
	
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;
	
	@Embedded
	Address address;
	
	/**
	 * // ENUM 사용 시 추가 @Enumerated(EnumType.*)
	 * EnumType.ORDINAL 숫자 (default)
	 * EnumType.STRING  문자
	 * 
	 * ORDINAL의 경우 중간에 다른상태 추가 시 숫자가 밀리기 떄문에 사용금지
	 */
	@Enumerated(EnumType.STRING) 
	private DeliveryStatus status; // READY, COMP
}
