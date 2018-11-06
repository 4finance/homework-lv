package io.fourfinanceit.homework.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "customers")
public class Customer extends AuditModel {
	@Id
	private Long id;
	@Column
	private String name;
	@Column
	private String surname;

	@OneToMany(mappedBy = "customer")
	private List<Loan> loans;

	@OneToMany(mappedBy = "customer")
	private List<Risk> risks;
}

