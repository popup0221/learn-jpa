package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 도메인 모델 패턴
 * - Entity가 비즈니스 로직을 가지고 객체지향의 특성을 적극적으로 활용
 * - 서비스 계층은 단순히 엔티티에 필요한 요청을 위임하는 역할
 *
 * 트랜잭션 스크립트 패턴
 * - 도메인 모델 패턴에 반대되는 패턴
 * - Entity에는 비즈니스 로직이 없고, 서비스 계층에서 대부분의 비즈니스 로직을 처리
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.fineOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        // Order만 persist 하는 이유 - order   entity에서 다른 필드(컬렉션에 정의한 cascade 때문에 따로 하지않아도 강제로 persist 된다
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     * transactional script없이 jpa에서는 entity 객체의 데이터만 바꾸면 변경내역 감지(더티체킹)을 통하여 변경 내역에 대한 업데이트 쿼리를 자동으로 날린다
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    /**
     * 검색
     */
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }

}
