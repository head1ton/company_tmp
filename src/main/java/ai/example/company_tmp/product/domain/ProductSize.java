package ai.example.company_tmp.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSize {

    @Column(name = "width_in_millimeters", nullable = false)
    @Comment("상품 너비(mm)")
    private Long widthInMillimeters;
    @Column(name = "height_in_millimeters", nullable = false)
    @Comment("상품 높이(mm)")
    private Long heightInMillimeters;
    @Column(name = "length_in_millimeters", nullable = false)
    @Comment("상품 길이(mm)")
    private Long lengthInMillimeters;

    public ProductSize(final Long widthInMillimeters, final Long heightInMillimeters,
        final Long lengthInMillimeters) {
        validateProductSize(widthInMillimeters, heightInMillimeters, lengthInMillimeters);
        this.widthInMillimeters = widthInMillimeters;
        this.heightInMillimeters = heightInMillimeters;
        this.lengthInMillimeters = lengthInMillimeters;
    }

    private static void validateProductSize(final Long widthInMillimeters,
        final Long heightInMillimeters,
        final Long lengthInMillimeters) {
        Assert.notNull(widthInMillimeters, "가로 길이는 필수입니다.");
        if (0 > widthInMillimeters) {
            throw new IllegalArgumentException("가로 길이는 0보다 작을 수 없습니다.");
        }
        Assert.notNull(heightInMillimeters, "세로 길이는 필수입니다.");
        if (0 > heightInMillimeters) {
            throw new IllegalArgumentException("세로 길이는 0보다 작을 수 없습니다.");
        }
        Assert.notNull(lengthInMillimeters, "길이는 필수입니다.");
        if (0 > lengthInMillimeters) {
            throw new IllegalArgumentException("길이는 0보다 작을 수 없습니다.");
        }
    }

    public Long getVolume() {
        return widthInMillimeters * heightInMillimeters * lengthInMillimeters;
    }
}
