package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "orders") // 지정 안할 시 클래스명으로 자동생성 - order는 예약어
public class Order {

	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id") // OneToOne 매핑 시 Foreignkey의 위치는 자주 사용하는 곳에 위치
	private Delivery delivery;
	
	//private Date date; Date 타입 사용 시 날짜 관련 어노테이션 mapping이 필요
	// java 8 이상에서 LocalDateTime 사용 시 Hibernate가 자동으로 타입을 매핑
	private LocalDateTime orderDate; // 주문시간
	
	private OrderStatus status; // 주문상태 (ORDER/CANCEL)

	//==연관관계 메소드==//
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
}
