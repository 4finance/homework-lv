package io.fourfinanceit.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "customers")
public class Customer extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String surname;

	@OneToMany(mappedBy = "customer")
	private List<Loan> loans;

	@OneToMany(mappedBy = "customer")
	private List<Rejection> risks;
}

