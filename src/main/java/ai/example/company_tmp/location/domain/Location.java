package ai.example.company_tmp.location.domain;

import ai.example.company_tmp.inbound.domain.LPN;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Table(name = "location")
@Comment("로케이션")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "location_id")
    @Comment("로케이션 번호")
    private Long locationNo;
    @Column(name = "location_barcode", nullable = false)
    @Comment("로케이션 바코드")
    private String locationBarcode;
    @Column(name = "storage_type", nullable = false)
    @Comment("보관 타입")
    @Enumerated(EnumType.STRING)
    private StorageType storageType;
    @Column(name = "usage_purpose", nullable = false)
    @Comment("보관 목적")
    @Enumerated(EnumType.STRING)
    private UsagePurpose usagePurpose;
    private final List<LocationLPN> locationLPNList = new ArrayList<>();

    public Location(
        final String locationBarcode,
        final StorageType storageType,
        final UsagePurpose usagePurpose) {

        validateConstructor(locationBarcode, storageType, usagePurpose);

        this.locationBarcode = locationBarcode;
        this.storageType = storageType;
        this.usagePurpose = usagePurpose;
    }

    private static void validateConstructor(
        final String locationBarcode,
        final StorageType storageType,
        final UsagePurpose usagePurpose) {
        Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다.");
        Assert.notNull(storageType, "보관 타입은 필수입니다.");
        Assert.notNull(usagePurpose, "보관 목적은 필수입니다.");
    }

    public void assignLPN(final LPN lpn) {
        Assert.notNull(lpn, "LPN은 필수입니다.");

        // 1. 로케이션 LPN 목록에 등록하려는 LPN이 없으면 새로 등록. 새로 등록한 LPN은 재고를 1 이다.
        // 2. 로케이션 LPN 목록에 등록하려는 LPN이 존재하면 재고를 1 증가시킨다.
//        locationLPNList
//        lpn.assignLocation(this);
    }
}
