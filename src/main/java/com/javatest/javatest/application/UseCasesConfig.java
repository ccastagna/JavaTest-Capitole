package com.javatest.javatest.application;

import com.javatest.javatest.application.interfaces.IProductPriceService;
import com.javatest.javatest.application.usecases.getproductprice.GetProductPriceUseCase;
import com.javatest.javatest.infrastructure.drivers.intetrfaces.IGetProductPriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(3)
public class UseCasesConfig {

    @Bean
    IGetProductPriceUseCase getGetProductPriceUseCase(@Autowired IProductPriceService productPriceService) {
        return new GetProductPriceUseCase(productPriceService);
    }
}
