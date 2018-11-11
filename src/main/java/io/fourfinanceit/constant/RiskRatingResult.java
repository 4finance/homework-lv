package io.fourfinanceit.constant;

public enum RiskRatingResult {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String rating;

    RiskRatingResult(final String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }
}
