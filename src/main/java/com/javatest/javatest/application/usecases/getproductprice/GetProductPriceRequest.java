package com.javatest.javatest.application.usecases.getproductprice;

import java.time.Instant;

public class GetProductPriceRequest {

    private Integer brandId;
    private Integer productId;
    private Instant date;

    public GetProductPriceRequest(Integer brandId, Integer productId, String date) {
        this.brandId = brandId;
        this.productId = productId;
        this.date = Instant.parse(date);
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Instant getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "GetProductPriceRequest{" +
                "brandId=" + brandId +
                ", productId=" + productId +
                ", date=" + date +
                '}';
    }
}
