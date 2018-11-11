package io.fourfinanceit.controller.dto;

import java.io.Serializable;

public class LoanResponseDTO implements Serializable {

    private final String message;

    public LoanResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
