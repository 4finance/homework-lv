package io.fourfinanceit.homework.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "risks")
public class Risk extends AuditModel {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String ip;

	public Risk(String ip) {
		this.ip = ip;
	}
}

