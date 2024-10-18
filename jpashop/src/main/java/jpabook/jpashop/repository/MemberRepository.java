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

	// @PersistenceContext(jpa 표준 어노테이션 )- spring이 entity manager 펙토리를 생성하여 주입해줌
	@PersistenceContext
	private EntityManager em;
	
	// @PersistenceUnit - 직접 entity manager 펙토리를 생성 후 주입
	// private EntityManagerFactory emf;

	public void save(Member member) {
		// persist - 영속성 컨텍스트에 엔티티를 넣음 -> transaction이 commit 되는 시점에 db에 반영
		em.persist(member);
	};

	public Member fineOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		// createQuery(jpql, type) 쿼리생성 메소드
		// getResultList() // 조회 결과를 List로 return 해주는 메소드
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	public List<Member> findByName(String name) {
		 return em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
	}
}
