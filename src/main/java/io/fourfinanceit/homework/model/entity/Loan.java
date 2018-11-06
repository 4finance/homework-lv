package io.fourfinanceit.homework.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "loans")
public class Loan extends AuditModel {
	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column
	private Date term;
	@Column
	private BigDecimal amount;

	@OneToMany(mappedBy = "loan")
	private List<LoanExtension> extensions;
}
