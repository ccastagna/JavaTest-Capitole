package com.javatest.javatest.infrastructure.drivers.intetrfaces;

import com.javatest.javatest.application.usecases.getproductprice.GetProductPriceRequest;
import com.javatest.javatest.application.usecases.getproductprice.GetProductPriceResponse;

public interface IGetProductPriceUseCase {
    GetProductPriceResponse execute(GetProductPriceRequest getProductPriceRequest);
}
