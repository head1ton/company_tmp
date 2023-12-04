package ai.example.company_tmp.location.domain;

public enum StorageType {
    TOTE("토트바구니"),
    ;

    private final String description;

    StorageType(final String description) {
        this.description = description;
    }
}
