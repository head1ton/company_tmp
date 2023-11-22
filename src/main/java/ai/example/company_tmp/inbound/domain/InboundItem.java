package ai.example.company_tmp.inbound.domain;

import ai.example.company_tmp.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Table(name = "inbound_item")
@Comment("입고 상품")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InboundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("입고 상품 번호")
    @Column(name = "inbound_item_no")
    private Long inboundItemNo;
    @Comment("상품")
    @JoinColumn(name = "product_no", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Column(name = "quantity", nullable = false)
    @Comment("수량")
    private Long quantity;
    @Column(name = "unit_price", nullable = false)
    @Comment("단가")
    private Long unitPrice;
    @Column(name = "description", nullable = false)
    @Comment("상품 설명")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_no", nullable = false)
    @Comment("입고 번호")
    private Inbound inbound;

    public InboundItem(final Product product, final Long quantity, final Long unitPrice,
        final String description) {
        validateConstructor(product, quantity, unitPrice, description);

        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;

    }

    private static void validateConstructor(final Product product, final Long quantity,
        final Long unitPrice, final String description) {
        Assert.notNull(product, "product 필수입니다.");
        Assert.notNull(quantity, "quantity 필수입니다.");
        if (0 > quantity) {
            throw new IllegalArgumentException("quantity는 0보다 작을 수 없습니다.");
        }
        Assert.notNull(unitPrice, "unitPrice 필수입니다.");
        if (0 > unitPrice) {
            throw new IllegalArgumentException("unitPrice는 0보다 작을 수 없습니다.");
        }
        Assert.notNull(description, "description 필수입니다.");
    }

    public void assignInbound(final Inbound inbound) {
        Assert.notNull(inbound, "입고는 필수입니다.");
        this.inbound = inbound;
    }
}
