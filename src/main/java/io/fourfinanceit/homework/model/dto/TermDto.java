package io.fourfinanceit.homework.model.dto;

import io.fourfinanceit.homework.validation.dto.NotInPast;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TermDto {

	@NotNull
	@NotInPast
	private LocalDateTime term;
}
