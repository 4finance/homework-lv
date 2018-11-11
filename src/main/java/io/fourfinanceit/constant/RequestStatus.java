package io.fourfinanceit.constant;

public enum RequestStatus {

    APPROVED("APPROVED"),
    ON_HOLD("ON_HOLD"),
    REJECTED("REJECTED");

    private final String status;

    RequestStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
