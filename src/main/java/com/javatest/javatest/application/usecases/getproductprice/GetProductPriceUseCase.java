package com.javatest.javatest.application.usecases.getproductprice;

import com.javatest.javatest.application.entities.ProductPrice;
import com.javatest.javatest.application.exceptions.ProductPriceNotFoundException;
import com.javatest.javatest.application.interfaces.IProductPriceService;
import com.javatest.javatest.infrastructure.drivers.intetrfaces.IGetProductPriceUseCase;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GetProductPriceUseCase implements IGetProductPriceUseCase {

    private IProductPriceService productPriceService;

    public GetProductPriceUseCase(IProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @Override
    public GetProductPriceResponse execute(GetProductPriceRequest getProductPriceRequest) throws ProductPriceNotFoundException {

        List<ProductPrice> prioritizedProductPrices = this.productPriceService.getProductPriceOrderedByPriority(
                getProductPriceRequest.getBrandId(),
                getProductPriceRequest.getProductId(),
                getProductPriceRequest.getDate());

        ProductPrice theMostPrioritizedProductPrice = Optional.ofNullable(prioritizedProductPrices)
                .map(Collection::stream)
                .flatMap(Stream::findFirst)
                .orElseThrow(() -> new ProductPriceNotFoundException("Product price not found for " + getProductPriceRequest));

        return GetProductPriceResponse.from(theMostPrioritizedProductPrice);
    }
}
