package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // JPA 내장 타입일시 embeddable 명시
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
}
