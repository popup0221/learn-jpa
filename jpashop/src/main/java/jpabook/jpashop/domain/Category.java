package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long Id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	/**
	 * 객체는 컬렉션끼리 다대다가 가능
	 * 관계형 데이터 베이스는 컬렉션 관계를 서로 가질 수 없기 떄문에 일대다, 다대일로 풀어야함
	 * 실무에서는 @ManyToMany 사용 X, 1:N N:1로 풀어내기
	 */
	@ManyToMany
	@JoinTable(name = "category_items",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> items = new ArrayList<>();
	
	/**
	 * 계층구조 표현방법
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();
}
