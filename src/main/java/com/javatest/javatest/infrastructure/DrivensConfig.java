package com.javatest.javatest.infrastructure;

import com.javatest.javatest.application.interfaces.IProductPriceService;
import com.javatest.javatest.infrastructure.drivens.repositories.IProductPriceRepository;
import com.javatest.javatest.infrastructure.drivens.services.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class DrivensConfig {

    @Bean
    IProductPriceService getProductPriceService(@Autowired IProductPriceRepository productPriceRepository) {
        return new ProductPriceService(productPriceRepository);
    }
}
