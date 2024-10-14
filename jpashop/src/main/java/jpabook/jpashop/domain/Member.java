package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long Id;
	
	private String name;
	
	@Embedded // JPA 내장타입 매핑 시 embedded 어노테이션 명시
	private Address address;
	
	@OneToMany
	private List<Order> orders = new ArrayList<>();

	
	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
