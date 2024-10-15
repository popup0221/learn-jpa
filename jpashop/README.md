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