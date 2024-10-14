package jpabook.jpashop;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Hello {
	
	@Id
	String id;
	
	String password;

	@Builder
	public Hello(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
}
