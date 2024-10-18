package jpabook.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;

/**
 * @author popup
 * sql - 테이블 대상 쿼리
 * jpql - Entity 객체를 대상으로 하는 쿼리
 */

@Repository
public class MemberRepository {

	// jpa 표준 어노테이션 - spring이 entity manager를 생성하여 주입해줌
	@PersistenceContext
	private EntityManager em;

	public void save(Member member) {
		em.persist(member);
	};

	public Member fineOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		// createQuery(jpql, Entity.class) 쿼리생성 메소드
		// getResultList() // 조회 결과를 List로 return 해주는 메소드
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

}
