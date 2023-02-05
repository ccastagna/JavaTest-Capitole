package com.javatest.javatest.application.usecases.getproductprice;

import java.time.LocalDateTime;

public class GetProductPriceRequest {

    private Integer brandId;
    private Integer productId;
    private LocalDateTime date;

    public GetProductPriceRequest(Integer brandId, Integer productId, String date) {
        this.brandId = brandId;
        this.productId = productId;
        this.date = LocalDateTime.parse(date.trim());
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public LocalDateTime getDate() {
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
