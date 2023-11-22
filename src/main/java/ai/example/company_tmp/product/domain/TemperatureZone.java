package ai.example.company_tmp.product.domain;

public enum TemperatureZone {
    ROOM_TEMPERATURE("상온");

    private final String description;

    TemperatureZone(final String description) {
        this.description = description;
    }
}
