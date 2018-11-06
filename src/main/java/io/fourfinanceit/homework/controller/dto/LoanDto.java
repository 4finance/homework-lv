package io.fourfinanceit.homework.controller.dto;

import io.fourfinanceit.homework.config.Constants;
import io.fourfinanceit.homework.validation.InRange;
import io.fourfinanceit.homework.validation.NotInPast;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanDto {
    @NotNull
    @InRange(min = 0, max = Constants.MAX_LOAN_AMOUNT)
    private BigDecimal amount;
    @NotNull
    @NotInPast
    private Date term;
}
