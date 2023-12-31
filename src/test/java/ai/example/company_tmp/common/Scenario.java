package ai.example.company_tmp.common;

import ai.example.company_tmp.inbound.feature.api.ConfirmInboundApi;
import ai.example.company_tmp.inbound.feature.api.RegisterInboundApi;
import ai.example.company_tmp.inbound.feature.api.RegisterLPNApi;
import ai.example.company_tmp.inbound.feature.api.RejectInboundApi;
import ai.example.company_tmp.location.feature.api.AssignInventoryApi;
import ai.example.company_tmp.location.feature.api.RegisterLocationApi;
import ai.example.company_tmp.outbound.feature.api.RegisterOutboundApi;
import ai.example.company_tmp.outbound.feature.api.RegisterPackingMaterialApi;
import ai.example.company_tmp.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterLocationApi registerLocation() {
        return new RegisterLocationApi();
    }

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }

    public static RegisterInboundApi registerInbound() {
        return new RegisterInboundApi();
    }

    public static ConfirmInboundApi confirmInbound() {
        return new ConfirmInboundApi();
    }

    public static RejectInboundApi rejectInbound() {
        return new RejectInboundApi();
    }

    public static RegisterLPNApi registerLPN() {
        return new RegisterLPNApi();
    }

    public static AssignInventoryApi assignInventory() {
        return new AssignInventoryApi();
    }

    public static RegisterPackingMaterialApi registerPackingMaterial() {
        return new RegisterPackingMaterialApi();
    }

    public static RegisterOutboundApi registerOutbound() {
        return new RegisterOutboundApi();
    }
}
