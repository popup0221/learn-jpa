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
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author popup
 * JPA에서 상속관게 설정 시 전략을 설정해야함
 * 
 * @Inheritance(strategy = InheritanceType.*)
 * SINGLE_TABLE: 한 테이블에 몰아넣기
 * TABLE_PER_CLASS: 테이블 나누기
 * JOINED: 조인 된 테이블(정교화 된 전략)
 * 
 * @DiscriminatorColumn(name = "dtype") - 부모 클래스에 선언, 하위 클래스를 구분하는 용도의 컬럼
 * @DiscriminatorValue("**") - 하위 클래스에 선언, 엔티티 저장 시 슈퍼타입의 구분 컬럼에 저장할 값을 지정(default 클래스 이름)
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
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
	
	//==비즈니스 로직==//
	// 도메인 지향 개발 및 객체지향적인 코드를 위해 엔티티가 가지고 있는 필드 값에 대한 변경은 엔티티 내에서 만듦으로써 응집도를 향상시킬 수 있다.
	// setter를 사용하여 외부 서비스 내에서 필드 값 변경은 하지 말아야한다 - 세터 생성은 안하는게 좋음
	
	/**
	 * @param quantity 
	 * stock 증가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/**
	 * @param quantity
	 * stock 감소
	 */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
}
