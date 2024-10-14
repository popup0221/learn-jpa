package jpabook.jpashop.domain;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

@Entity
@Getter
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToAny
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice; // 주문가격
	
	private int count; // 주문 수량

	public void setItem(Item item) {
		this.item = item;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
