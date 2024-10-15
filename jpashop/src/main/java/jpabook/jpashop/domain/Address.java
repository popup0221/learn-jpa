package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * 
 * @author popup
 * 값 타입은 불변하게(immutable) 설게
 * 생성시에만 값이 셋팅되야함
 * getter만 제공하고 setter는 제공 X
 */

@Embeddable // JPA 내장 타입일시 embeddable 명시
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	// jpa 기본 스펙에서 proxy, reflection 등을 사용하기 위해 기본생성자가 필요
	// immutable한 설게를 위해 public으로 사용하지 않고, jpa 스펙에서 허용하느 protected를 사용
	protected Address() {}
	
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
}
