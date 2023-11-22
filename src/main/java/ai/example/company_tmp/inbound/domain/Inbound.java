package ai.example.company_tmp.inbound.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.util.Assert;

public class Inbound {

    private final String title;
    private final String description;
    private final LocalDateTime orderRequestedAt;
    private final LocalDateTime estimatedArrivalAt;
    private final List<InboundItem> inboundItems;
    private Long id;

    public Inbound(final String title, final String description,
        final LocalDateTime orderRequestedAt, final LocalDateTime estimatedArrivalAt,
        final List<InboundItem> inboundItems) {
        validateConstructor(title, description, orderRequestedAt, estimatedArrivalAt, inboundItems);

        this.title = title;
        this.description = description;
        this.orderRequestedAt = orderRequestedAt;
        this.estimatedArrivalAt = estimatedArrivalAt;
        this.inboundItems = inboundItems;
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

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
