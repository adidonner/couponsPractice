package app.core.entities;

import javax.persistence.Entity;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int sub;
	private int id;
	private String email;
	private String password;
	private String name;
	private String firstName;
	private String lastName;
	private String userName;
	private String logoImage;
	@Enumerated(EnumType.STRING)
	private ClientType clientType;

	public enum ClientType {
		ADMINISTRATION, COMPANY, CUSTOMER;
	}

}