package ai.example.company_tmp.product.common;

import ai.example.company_tmp.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }
}
