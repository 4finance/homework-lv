package io.fourfinanceit.homework.model.dto;

import io.fourfinanceit.homework.config.Constants;
import io.fourfinanceit.homework.validation.dto.InRange;
import io.fourfinanceit.homework.validation.dto.NotInPast;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanDto {

	@NotNull
	@InRange(min = 0, max = Constants.MAX_LOAN_AMOUNT)
	private BigDecimal amount;

	@NotNull
	@NotInPast
	private LocalDateTime term;
}
