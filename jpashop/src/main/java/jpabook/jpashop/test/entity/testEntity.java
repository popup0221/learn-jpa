package jpabook.jpashop.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class testEntity {
	
	@Id
	String id;
	
	String password;

	@Builder
	public testEntity(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
}
