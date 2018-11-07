package io.fourfinanceit.homework.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "risks")
public class Risk extends AuditModel {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private LocalDateTime term;
	@Column
	private BigDecimal amount;
	@Column
	private String ip;
	@Column
	@Enumerated(EnumType.STRING)
	private RejectionReason rejectionReason;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "customer_id")
	private Customer customer;
}

