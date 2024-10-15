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
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long Id;
	
	private String name;
	
	@Embedded // JPA 내장타입 매핑 시 embedded 어노테이션 명시
	private Address address;
	
	@OneToMany(mappedBy = "member") // 읽기전용, 연관관게의 주인 설정
	private List<Order> orders = new ArrayList<>();

}
