package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.location.domain.InventoryRepository;
import ai.example.company_tmp.outbound.domain.Inventories;
import ai.example.company_tmp.outbound.domain.Order;
import ai.example.company_tmp.outbound.domain.OrderProduct;
import ai.example.company_tmp.outbound.domain.OrderRepository;
import ai.example.company_tmp.outbound.domain.Outbound;
import ai.example.company_tmp.outbound.domain.OutboundRepository;
import ai.example.company_tmp.outbound.domain.PackagingMaterialRepository;
import ai.example.company_tmp.outbound.domain.PackagingMaterials;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterOutbound {

    private final OrderRepository orderRepository;
    private final OutboundRepository outboundRepository;
    private final InventoryRepository inventoryRepository;
    private final PackagingMaterialRepository packagingMaterialRepository;
    private final ConstructOutbound constructOutbound = new ConstructOutbound();

    @PostMapping("/outbounds")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void request(@RequestBody @Valid final Request request) {
        // 주문을 먼저 조회
        // 주문 정보를 가져오고
        final Order order = orderRepository.getBy(request.orderNo);

        // 주문 정보에 맞는 상품의 재고가 충분한지 확인하고 충분하지 않으면 예외를 던진다.
        final List<Inventories> inventoriesList = inventoriesList(order.orderProducts());
        final PackagingMaterials packagingMaterials = new PackagingMaterials(
            packagingMaterialRepository.findAll());

        final Outbound outbound = constructOutbound.create(
            inventoriesList,
            packagingMaterials,
            order,
            request.isPriorityDelivery,
            request.desiredDeliveryAt);

        // 출고를 등록한다.
        outboundRepository.save(outbound);
    }

    private List<Inventories> inventoriesList(final List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                            .map(orderProduct -> inventoryRepository.inventoriesBy(
                                orderProduct.getProductNo())).toList();
    }

    public record Request(
        @NotNull(message = "주문번호는 필수입니다.")
        Long orderNo,
        @NotNull(message = "우선출고여부는 필수입니다.")
        Boolean isPriorityDelivery,
        @NotNull(message = "희망출고일은 필수입니다.")
        LocalDate desiredDeliveryAt) {
    }
}
