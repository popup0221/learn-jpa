package jpabook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author popup
 * JPA에서 상속관게 설정 시 전략을 설정해야함
 * @Inheritance(strategy = InheritanceType.*)
 * SINGLE_TABLE: 한 테이블에 몰아넣기
 * TABLE_PER_CLASS: 테이블 나누기
 * JOINED:정교화 된 전략
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int StockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
	
}
