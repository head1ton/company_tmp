package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.location.domain.InventoryRepository;
import ai.example.company_tmp.outbound.domain.Order;
import ai.example.company_tmp.outbound.domain.OrderProduct;
import ai.example.company_tmp.outbound.domain.OrderRepository;
import ai.example.company_tmp.outbound.domain.Outbound;
import ai.example.company_tmp.outbound.domain.OutboundProduct;
import ai.example.company_tmp.outbound.domain.OutboundRepository;
import ai.example.company_tmp.outbound.domain.PackagingMaterial;
import ai.example.company_tmp.outbound.domain.PackagingMaterialRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    private static Outbound createOutbound(
        final Request request,
        final Order order,
        final PackagingMaterial packagingMaterial) {
        return new Outbound(
            order.orderNo,
            order.orderCustomer,
            order.deliveryRequirements,
            mapToOutboundProducts(order.orderProducts),
            request.isPriorityDelivery,
            request.desiredDeliveryAt,
            packagingMaterial
        );
    }

    private static List<OutboundProduct> mapToOutboundProducts(
        final List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                            .map(
                                RegisterOutbound::newOutboundProduct).toList();
    }

    private static OutboundProduct newOutboundProduct(final OrderProduct orderProduct) {
        return new OutboundProduct(
            orderProduct.product,
            orderProduct.orderQuantity,
            orderProduct.unitPrice
        );
    }

    @PostMapping("/outbounds")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void request(@RequestBody @Valid final Request request) {
        // 주문을 먼저 조회
        // 주문 정보를 가져오고
        final Order order = orderRepository.getBy(request.orderNo);

        // 주문 정보에 맞는 상품의 재고가 충분한지 확인하고 충분하지 않으면 예외를 던진다.
        final List<Inventories> inventoriesList = inventoriesList(order.orderProducts());
        // 해당 상품의 재고를 전부 가져온다.
        inventoriesList.forEach(Inventories::validateInventory);

        // 출고에 사용할 포장재를 선택(추천)해준다.
        final Optional<PackagingMaterial> optimalPackagingMaterial = findOptimalPackagingMaterial(
            order, packagingMaterialRepository.findAll());

        // 출고를 생성하고.
        final Outbound outbound = createOutbound(request, order,
            optimalPackagingMaterial.orElse(null));

        // 출고를 등록한다.
        outboundRepository.save(outbound);
    }

    Optional<PackagingMaterial> findOptimalPackagingMaterial(final Order order,
        final List<PackagingMaterial> packagingMaterials) {
        return packagingMaterials.stream()
                                 .filter(
                                     pm -> pm.isAvailable(
                                         order.totalWeight(),
                                         order.totalVolume()))
                                 .min(
                                     Comparator.comparingLong(
                                         PackagingMaterial::outerVolume));
    }

    private List<Inventories> inventoriesList(final List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                            .map(orderProduct -> new Inventories(
                                inventoryRepository.findByProductNo(
                                    orderProduct.getProductNo()),
                                orderProduct.orderQuantity()))
                            .toList();
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
