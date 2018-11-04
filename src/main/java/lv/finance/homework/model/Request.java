package lv.finance.homework.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "requests")
public class Request {
	@Id
	private String ip;
	@Column
	private byte attempt;
}
