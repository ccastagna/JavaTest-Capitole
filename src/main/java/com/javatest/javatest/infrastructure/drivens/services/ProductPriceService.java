package com.javatest.javatest.infrastructure.drivens.services;

import com.javatest.javatest.application.entities.ProductPrice;
import com.javatest.javatest.application.interfaces.IProductPriceService;
import com.javatest.javatest.infrastructure.drivens.repositories.IProductPriceRepository;
import com.javatest.javatest.infrastructure.drivens.repositories.dtos.ProductPriceRepositoryDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ProductPriceService implements IProductPriceService {

    private IProductPriceRepository productPriceRepository;

    public ProductPriceService(IProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public List<ProductPrice> getProductPriceOrderedByPriority(Integer brandId, Integer productId, LocalDateTime date) {
        return this.productPriceRepository.findByBrandIdAndProductIdAndDate(brandId, productId, date)
                .stream()
                .map(ProductPriceRepositoryDTO::toEntity)
                .toList();
    }
}
