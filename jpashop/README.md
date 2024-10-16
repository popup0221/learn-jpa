# 내용 요약

## 섹션 3 - 도메인 분석 설계

### 엔티티 클래스 개발 1

* 테이블 간 연관관계 표현

```
	1. 각 테이블의 엔티티에 연관관계에 맞는 매핑 어노테이션 정의  
	2. `@JoinColumn`을 사용해 연관관계의 주인을 정의 (연관관계의 주인 테이블에 외래 키 컬럼 생성)  
	3. 상대 테이블에서 `mappedBy` 속성을 통해 양방향 연관관계 설정
```
  
* 임베디드 사용 - 객체 지향적인 설계  

```
	1. `@Embeddable`, `@Embedded` 어노테이션을 사용해 임베디드 타입임을 명시
```
  
* 열거형(enum) 타입 정의  

```
	1. 열거형 타입 정의 시 `@Enumerated` 어노테이션 사용  
	2. EnumType 설정 시 `EnumType.ORDINAL` 대신 `EnumType.STRING` 사용 권장
```

### 엔티티 클래스 개발 2

* 한 엔티티 내 부모자식 관계 표현
* 다대다 관계 에시
* 값 타입 사용 시 주의할 것 들

### 엔티티 설계 시 주의점

* setter를 사용하지 말기

```
	1. 변경 포인트가 너무 많아 유지보수가 어렵다
```

* 모든 연관관계는 지연로딩(LazyLoading)으로 설정

```
	1. 즉시로딩(Eager)은 예측이 어렵고 어떤 sql이 실행될지 추적이 어렵다, JPQL 사용 시 N+1 문제가 자주 발생
	2. 연관된 Entity를 조회 해야하면 Fetch join 또는 EntityGraph를 사용
	3. *ToOne(OneToOne, ManyToOne)은 default가 즉시로딩임으로 직접 지연로딩으로 설정해야함
	
	* LazyInitializationException 발생 시 즉시로딩 말고 다른방법으로 해결

```

* 컬렉션은 필드에서 초기화하기

```
	1. 컬렉션은 필드에서 바로 초기화 하는 것이 안전
	2. null 문제에서 안전
	3. hibernate는 entity를 영속화(persist)시 컬렉션을 감싸서 hibernate가 제공하는 컬렉션으로 변환한다, 이에 따라 컬렉션은 필드에서 생성하고 컬렉션에 대한 변경을 하지않는다.
```

```java
	*3번 예시
	Member member = new Member();
	System.out.println(member.getOrders().getClass());
	en.persist(team);
	System.out.println(member.getOrders().getClass());
	
	// 출력 결과
	Class java.util.ArrayList
	Class org.hibernate.collection.internal.PersistentBag
```

* 테이블, 컬럼명 생성전략

```
	1. 스프링 부트에서는 hibernate가 기본 매핑 전략을 변경해서 실제 테이블 필드명은 다름
	2. SpringPhysicalNamingStrategy(물리명) 사용
		- 카멜 케이스 -> 언더스코어 ex) memberPoint -> member_point
		- .(점) -> 언더스코어
		- 대문자 -> 소문자
```

* cascade

```
	1. cascade를 사용 안할 시 엔티티당 무조건 각각 persist를 호출해야 됌
	2. cascade를 사용 시 persist를 전파함
	3. 연관관계 설정 시 한 쪽만 cascade 걸면 됌
```

* 연관관계 편의 메소드

```
	1. 양방향 연관관계를 위해 연관관계 편의 메소드를 만든다.
	2. 연관관계 편의 메소드의 위치는 핵심적으로 컨트롤 하는 쪽이 가지고 있는다.
```





