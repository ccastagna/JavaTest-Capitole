package com.javatest.javatest.application.interfaces;

import com.javatest.javatest.application.entities.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductPriceService {
    List<ProductPrice> getProductPriceOrderedByPriority(Integer brandId, Integer productId, LocalDateTime date);
}
