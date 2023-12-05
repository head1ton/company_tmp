package ai.example.company_tmp.outbound.domain;

public enum MaterialType {
    CORRUGATED_BOX("완충재가 있는 골판지 상자"),
    ;

    private final String description;

    MaterialType(final String description) {
        this.description = description;
    }
}
