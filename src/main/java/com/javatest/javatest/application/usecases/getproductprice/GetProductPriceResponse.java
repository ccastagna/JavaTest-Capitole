package com.javatest.javatest.application.usecases.getproductprice;

import com.javatest.javatest.application.entities.ProductPrice;

import java.time.Instant;

public record GetProductPriceResponse(Integer brandId, Integer productId, Integer priceList, Instant startDate,
                                      Instant endDate, Float finalPrice) {
    public static GetProductPriceResponse from(ProductPrice productPrice) {
        return new GetProductPriceResponse(
                productPrice.brandId(),
                productPrice.productId(),
                productPrice.priceList(),
                productPrice.startDate(),
                productPrice.endDate(),
                productPrice.finalPrice()
        );
    }
}
