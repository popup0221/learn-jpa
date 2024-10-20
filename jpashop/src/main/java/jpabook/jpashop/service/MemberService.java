package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

/**
 * @author popup
 * 
 * @Transactional
 * 
 * 1. @jakarta.transaction.Transactional
 * javax(jakarta, Java SE 17부터 네임스페이스 명칭 변경)에서 제공하는 Transactional 어노테이션 보다 
 * springframework에서 제공하는 어노테이션이 부가기능이 더 많음
 * 
 * 2. 조회 메소드에 @Transactional(readOnly = true) 옵션 사용 시 조회에 있어 JPA가 성능 최적화를 함
 * 영속성 컨택스트를 flush하지 않고, 더티체킹을 안하며 데이터베이스에 따라 읽기전용 트랜잭션임을 명시 함으로써 드라이버의 자원을 아낄 수 있다
 * 주의) 읽기가 아닌 곳에 사용 시, 데이터 변경이 안됌
 */

@Service
@Transactional(readOnly = true) // jpa 데이터변경 또는 로직은 트랜잭션 안에서 처리되야 함
// @RequiredArgsConstructor -> final으로 선언된 필드만 가지고 생성자를 만들어줌
public class MemberService {

	/*
		@Autowired // 필드 주입 - 테스트나 다른 작업 시 필드 값 변경이 필요 할 떄 엑세스를 못함
		private MemberRepository memberRepository;
		
		@Autowired // 세터 주입 - 테스트나 다른 작업 시 목 객체를 직접 주입 할 수 있음
		public void setMemberRepository(MemberRepository memberRepository) {
			this.memberRepository = memberRepository;
		}
	*/
	
	private final MemberRepository memberRepository;
	
	@Autowired // 생성자 주입 - @Autowired를 명시 안해도 자동으로 주입을 해줌 
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원가입
	@Transactional // 개별 설정 시 class에 걸은 @Transactional보다 우선권을 가지며, readOnly 옵션의 default는 false
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	// 계정존재 검증 메소드 - 멀티스레드 또는 다중 was를 고려하여 데이터 베이스에 검증할 키 컬럼을 unique로 설정
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체조회
	public List<Member> finMembers() {
		return memberRepository.findAll();
	}
}
