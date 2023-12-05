package ai.example.company_tmp.outbound.feature.api;

import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.outbound.domain.MaterialType;
import ai.example.company_tmp.outbound.feature.RegisterPackingMaterial;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class RegisterPackingMaterialApi {

    private String name = "name";
    private String code = "code";
    private Long innerWidthInMillimeters = 1000L;
    private Long innerHeightInMillimeters = 1000L;
    private Long innerLengthInMillimeters = 1000L;
    private Long outerWidthInMillimeters = 1000L;
    private Long outerHeightInMillimeters = 1000L;
    private Long outerLengthInMillimeters = 1000L;
    private Long weightInGrams = 100L;
    private Long maxWeightInGrams = 100L;
    private MaterialType materialType = MaterialType.CORRUGATED_BOX;

    public RegisterPackingMaterialApi name(final String name) {
        this.name = name;
        return this;
    }

    public RegisterPackingMaterialApi code(final String code) {
        this.code = code;
        return this;
    }

    public RegisterPackingMaterialApi innerWidthInMillimeters(final Long innerWidthInMillimeters) {
        this.innerWidthInMillimeters = innerWidthInMillimeters;
        return this;
    }

    public RegisterPackingMaterialApi innerHeightInMillimeters(
        final Long innerHeightInMillimeters) {
        this.innerHeightInMillimeters = innerHeightInMillimeters;
        return this;
    }

    public RegisterPackingMaterialApi innerLengthInMillimeters(
        final Long innerLengthInMillimeters) {
        this.innerLengthInMillimeters = innerLengthInMillimeters;
        return this;
    }

    public RegisterPackingMaterialApi outerWidthInMillimeters(final Long outerWidthInMillimeters) {
        this.outerWidthInMillimeters = outerWidthInMillimeters;
        return this;
    }

    public RegisterPackingMaterialApi outerHeightInMillimeters(
        final Long outerHeightInMillimeters) {
        this.outerHeightInMillimeters = outerHeightInMillimeters;
        return this;
    }

    public RegisterPackingMaterialApi outerLengthInMillimeters(
        final Long outerLengthInMillimeters) {
        this.outerLengthInMillimeters = outerLengthInMillimeters;
        return this;
    }

    public RegisterPackingMaterialApi weightInGrams(final Long weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public RegisterPackingMaterialApi maxWeightInGrams(final Long maxWeightInGrams) {
        this.maxWeightInGrams = maxWeightInGrams;
        return this;
    }

    public RegisterPackingMaterialApi materialType(final MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public Scenario request() {

        final RegisterPackingMaterial.Request request = new RegisterPackingMaterial.Request(
            name,
            code,
            innerWidthInMillimeters,
            innerHeightInMillimeters,
            innerLengthInMillimeters,
            outerWidthInMillimeters,
            outerHeightInMillimeters,
            outerLengthInMillimeters,
            weightInGrams,
            maxWeightInGrams,
            materialType
        );

        RestAssured.given().log().all()
                   .contentType(ContentType.JSON)
                   .body(request)
                   .when()
                   .post("/packaging-materials")
                   .then().log().all()
                   .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
