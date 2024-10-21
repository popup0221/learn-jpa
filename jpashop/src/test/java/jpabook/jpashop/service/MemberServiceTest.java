package jpabook.jpashop.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringRunner.class) // JUnit사용 시 Spring의 테스트 컨텍스트 프레임워크와 통합(Integration)
@SpringBootTest // Spring Boot 애플리케이션의 모든 설정을 로드하고, 실제 애플리케이션처럼 동작하는 테스트 환경을 제공(스프링 부트 컨테이너 안에서 테스트를 실행)
@Transactional // 테스트에서 반복가능한 테스트를 위해 @Transactional 사용 (rollback)
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	// @Autowired EntityManager em; // insert 쿼리 실행을 보고 싶다면 사용
	
	@Test
	// 테스트 케이스에 @Transactional가 있다면 기본적으로 롤백을 실행(영속성 컨테스트가 flush를 안함)
	// @Rollback(false) - 쿼리 실행 결과를 확인하고 싶다면 @Rollback(false) 
	public void 회원가입() throws Exception {

		// given - 상황이 주어졌을 떄
		Member member = new Member();
		member.setName("Yoon");
		
		// when - 어떻게 하면
		Long saveId = memberService.join(member); // 영속성 컨텍스트에 멤버 저장
		
		// then - 이렇게 된다
		// em.flush(); // flush: 영속성 컨텍스트 내 변경 또는 등록된 내용을 데이터베이스에 반영
		// @Transactional을 통해서 하나의 트랙잭션 안에 같은 영속성 컨텍스트를 사용하기 떄문에 PK 값 비교가 가능
		assertEquals(member, memberRepository.fineOne(saveId));
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		
		// when
		memberService.join(member1);
		memberService.join(member2); // 예외발생 예측

		// then
		fail("예외가 발생해야 한다");
	}
	
}
