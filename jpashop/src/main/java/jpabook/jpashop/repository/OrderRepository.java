package jpabook.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }


    public List<Order> findAll(OrderSearch orderSearch) {
    	return em.createQuery("select o from o join o.member m" + 
    			" where o.status = :status " +
			 	" and m.name like :name", Order.class)
			 	.setParameter("status", orderSearch.getOrderStatus())
			 	.setParameter("name", orderSearch.getMemberName())
//    			.setFirstResult(100) // 페이징 / param(startPosition) -> 100 부터 시작이란 의미
			 	.setMaxResults(1000) // 최대 1000건 조회
			 	.getResultList();
    }

}
