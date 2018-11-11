package io.fourfinanceit.management;

public enum RiskRatingResult {

    OK("OK"),
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
