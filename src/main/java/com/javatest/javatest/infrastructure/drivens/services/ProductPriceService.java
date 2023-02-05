package com.javatest.javatest.infrastructure.drivens.services;

import com.javatest.javatest.application.entities.ProductPrice;
import com.javatest.javatest.application.interfaces.IProductPriceService;
import com.javatest.javatest.infrastructure.drivens.repositories.IProductPriceRepository;
import com.javatest.javatest.infrastructure.drivens.repositories.dtos.ProductPriceRepositoryDTO;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPriceService implements IProductPriceService {

    private IProductPriceRepository productPriceRepository;

    public ProductPriceService(IProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public List<ProductPrice> getProductPriceBy(Integer brandId, Integer productId, Instant date) {
        return this.productPriceRepository.findByBrandIdAndProductIdAndDate(brandId, productId, date)
                .stream()
                .map(ProductPriceRepositoryDTO::toEntity)
                .collect(Collectors.toList());
    }
}
