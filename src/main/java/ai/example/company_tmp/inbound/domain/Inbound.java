package ai.example.company_tmp.inbound.domain;

import com.google.common.annotations.VisibleForTesting;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Getter
@Table(name = "inbound")
@Comment("입고")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inbound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inbound_no")
    @Comment("입고 번호")
    private Long inboundNo;
    @Column(name = "title", nullable = false)
    @Comment("입고명")
    private String title;
    @Column(name = "description", nullable = false)
    @Comment("입고 설명")
    private String description;
    @Column(name = "order_requested_at", nullable = false)
    @Comment("입고 요청 일시")
    private LocalDateTime orderRequestedAt;
    @Column(name = "estimated_arrival_at", nullable = false)
    @Comment("입고 예정 일시")
    private LocalDateTime estimatedArrivalAt;
    @OneToMany(mappedBy = "inbound", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<InboundItem> inboundItems = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Comment("입고 진행 상태")
    private InboundStatus status = InboundStatus.REQUESTED;
    @Column(name = "rejection_reason")
    @Comment("입고 거부 사유")
    private String rejectionReason;


    public Inbound(final String title, final String description,
        final LocalDateTime orderRequestedAt, final LocalDateTime estimatedArrivalAt,
        final List<InboundItem> inboundItems) {
        validateConstructor(title, description, orderRequestedAt, estimatedArrivalAt, inboundItems);

        this.title = title;
        this.description = description;
        this.orderRequestedAt = orderRequestedAt;
        this.estimatedArrivalAt = estimatedArrivalAt;
        for (final InboundItem inboundItem : inboundItems) {
            this.inboundItems.add(inboundItem);
            inboundItem.assignInbound(this);
        }
    }

    @VisibleForTesting
    Inbound(
        final Long inboundNo,
        final String title,
        final String description,
        final LocalDateTime orderRequestedAt,
        final LocalDateTime estimatedArrivalAt,
        final List<InboundItem> inboundItems, final InboundStatus inboundStatus) {
        this(title, description, orderRequestedAt, estimatedArrivalAt, inboundItems);
        this.inboundNo = inboundNo;
        this.status = inboundStatus;
    }

    private static void validateConstructor(final String title, final String description,
        final LocalDateTime orderRequestedAt, final LocalDateTime estimatedArrivalAt,
        final List<InboundItem> inboundItems) {
        Assert.hasText(title, "title 필수입니다.");
        Assert.hasText(description, "description 필수입니다.");
        Assert.notNull(orderRequestedAt, "orderRequestedAt 필수입니다.");
        Assert.notNull(estimatedArrivalAt, "estimatedArrivalAt 필수입니다.");
        Assert.notEmpty(inboundItems, "inboundItems 필수입니다.");
    }

    public void assignId(final Long inboundNo) {
        this.inboundNo = inboundNo;
    }

    public Long getId() {
        return inboundNo;
    }

    public void confirmed() {
        validateConfirmStatus();
        status = InboundStatus.CONFIRMED;
    }

    private void validateConfirmStatus() {
        validateRejectStatus(rejectionReason);
    }

    public void reject(final String rejectionReason) {
        validateRejectStatus(rejectionReason);
        this.status = InboundStatus.REJECTED;
        this.rejectionReason = rejectionReason;
    }

    private void validateRejectStatus(final String rejectionReason) {
        Assert.hasText(rejectionReason, "반려 사유는 필수입니다.");

        if (status != InboundStatus.REQUESTED) {
            throw new IllegalStateException("입고 요청 상태가 아닙니다.");
        }
    }
}
