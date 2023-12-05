package ai.example.company_tmp.outbound.domain;

import org.springframework.util.Assert;

public class PackagingMaterialDimension {

    private final long innerWidthInMillimeters;
    private final long innerHeightInMillimeters;
    private final long innerLengthInMillimeters;
    private final long outerWidthInMillimeters;
    private final long outerHeightInMillimeters;
    private final long outerLengthInMillimeters;

    public PackagingMaterialDimension(final long innerWidthInMillimeters,
        final long innerHeightInMillimeters, final long innerLengthInMillimeters,
        final long outerWidthInMillimeters, final long outerHeightInMillimeters,
        final long outerLengthInMillimeters) {
        validateConstructor(innerWidthInMillimeters, innerHeightInMillimeters,
            innerLengthInMillimeters,
            outerWidthInMillimeters, outerHeightInMillimeters,
            outerLengthInMillimeters);
        this.innerWidthInMillimeters = innerWidthInMillimeters;
        this.innerHeightInMillimeters = innerHeightInMillimeters;
        this.innerLengthInMillimeters = innerLengthInMillimeters;
        this.outerWidthInMillimeters = outerWidthInMillimeters;
        this.outerHeightInMillimeters = outerHeightInMillimeters;
        this.outerLengthInMillimeters = outerLengthInMillimeters;
    }

    private void validateConstructor(final long innerWidthInMillimeters,
        final long innerHeightInMillimeters, final long innerLengthInMillimeters,
        final long outerWidthInMillimeters, final long outerHeightInMillimeters,
        final long outerLengthInMillimeters) {
        Assert.notNull(innerWidthInMillimeters, "내부 폭은 필수입니다.");
        if (innerWidthInMillimeters < 1) {
            throw new IllegalArgumentException("내부 폭은 1mm 이상이어야 합니다.");
        }
        Assert.notNull(innerHeightInMillimeters, "내부 높이는 필수입니다.");
        if (innerHeightInMillimeters < 1) {
            throw new IllegalArgumentException("내부 높이는 1mm 이상이어야 합니다.");
        }
        Assert.notNull(innerLengthInMillimeters, "내부 길이는 필수입니다.");
        if (innerLengthInMillimeters < 1) {
            throw new IllegalArgumentException("내부 길이는 1mm 이상이어야 합니다.");
        }
        Assert.notNull(outerWidthInMillimeters, "외부 폭은 필수입니다.");
        if (outerWidthInMillimeters < 1) {
            throw new IllegalArgumentException("외부 폭은 1mm 이상이어야 합니다.");
        }
        Assert.notNull(outerHeightInMillimeters, "외부 높이는 필수입니다.");
        if (outerHeightInMillimeters < 1) {
            throw new IllegalArgumentException("외부 높이는 1mm 이상이어야 합니다.");
        }
        Assert.notNull(outerLengthInMillimeters, "외부 길이는 필수입니다.");
        if (outerLengthInMillimeters < 1) {
            throw new IllegalArgumentException("외부 길이는 1mm 이상이어야 합니다.");
        }
    }
}