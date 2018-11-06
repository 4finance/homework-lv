package io.fourfinanceit.homework.controller.dto;

import io.fourfinanceit.homework.validation.NotInPast;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TermDto {
    @NotNull
    @NotInPast
    private Date term;
}
