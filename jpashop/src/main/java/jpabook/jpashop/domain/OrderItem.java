package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @NoArgsConstructor(access = AccessLevel.PROTECTED) - jpa는 spec상 protected까지 생성자로 만들 수 있다
 * 이를 통해 외부에서 생성 메소드 이외에 new로 생성자 생성을 막음
 * protected OrderItem () {} 와 동일
 */

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice; // 주문가격
	
	private int count; // 주문 수량

	//==생성 메소드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count);
		return orderItem;
	}

	//==비즈니스 메소드==//
    public void cancel() {
		getItem().addStock(count);
    }

	//==조회 메소드==//

	/**
	 * 주문상품 전체 가격 조회
	 */
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

}
