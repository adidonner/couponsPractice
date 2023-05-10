package app.core.entities;

import java.time.LocalDate;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = { "company", "customers"} )
@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	@Enumerated(EnumType.STRING)
	private Category category;
	@Column(unique = true, nullable = false)
	private String title;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(

			name = "customer_coupon",

			joinColumns = @JoinColumn(name = "coupon_id"),

			inverseJoinColumns = @JoinColumn(name = "customer_id")

	)
	private List<Customer> customers;
	
	public enum Category {
		SPORTS, CLOTHINGS, ELECTRICITY, CAMPING, FOOD, RESTAURANTS, VACATIONS 
	}

}