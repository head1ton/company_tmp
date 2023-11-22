package ai.example.company_tmp.product.domain;

import org.springframework.util.Assert;

public class ProductSize {

    private final Long widthInMillimeters;
    private final Long heightInMillimeters;
    private final Long lengthInMillimeters;

    public ProductSize(final Long widthInMillimeters, final Long heightInMillimeters,
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
        this.widthInMillimeters = widthInMillimeters;
        this.heightInMillimeters = heightInMillimeters;
        this.lengthInMillimeters = lengthInMillimeters;
    }
}
