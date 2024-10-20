package jpabook.jpashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpabook.jpashop.domain.Member;

/**
 * @author popup
 * jpa repository 구현방법 2가지
 * 
 * 1. repository interface에서 JpaRepository<Class, pkType> 상속받기
 * - JpaRepository 인터페이스를 상속받으면, 기본적인 CRUD 메서드들이 자동으로 제공
 * - 자동 생성 쿼리 방식은 단순한 조건에 적합하고, 복잡한 쿼리는 @Query 어노테이션을 사용
 * 
 * 2. repository class에서 @PersistenceContext EntityManager 주입 받아 사용학기
 * - 트랜잭션, 성능 최적화 등 세부 제어가 가능
 * - 복잡한 쿼리나 커스텀 로직이 필요한 경우 사용이 유리
 * - 보일러플레이트 코드, 관리해야 할 코드 증가
 */
public interface MemberRepository2 extends JpaRepository<Member, Long>{

	public List<Member> findByName(String name);
	
}
