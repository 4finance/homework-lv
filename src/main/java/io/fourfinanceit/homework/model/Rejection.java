package io.fourfinanceit.homework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "rejections")
public class Rejection extends AuditModel {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column
	private Date term;
	@Column
	private BigDecimal amount;
	@Column
	private String ip;
	@Column
	private RejectionReason rejectionReason;
}

