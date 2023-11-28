package ai.example.company_tmp.common;

import ai.example.company_tmp.inbound.feature.api.ConfirmInboundApi;
import ai.example.company_tmp.inbound.feature.api.RegisterInboundApi;
import ai.example.company_tmp.inbound.feature.api.RegisterLPNApi;
import ai.example.company_tmp.inbound.feature.api.RejectInboundApi;
import ai.example.company_tmp.product.feature.api.RegisterProductApi;

public class Scenario {

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
}
