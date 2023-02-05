package com.javatest.javatest.application.interfaces;

import com.javatest.javatest.application.entities.ProductPrice;

import java.time.Instant;
import java.util.List;

public interface IProductPriceService {
    List<ProductPrice> getProductPriceBy(Integer brandId, Integer productId, Instant date);
}
