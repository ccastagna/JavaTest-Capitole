package com.javatest.javatest.infrastructure.drivers.controllers;

import com.javatest.javatest.application.exceptions.ProductPriceNotFoundException;
import com.javatest.javatest.application.usecases.getproductprice.GetProductPriceRequest;
import com.javatest.javatest.application.usecases.getproductprice.GetProductPriceResponse;
import com.javatest.javatest.infrastructure.drivers.intetrfaces.IGetProductPriceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeParseException;

@RestController
public class ProductPriceController {

    private final IGetProductPriceUseCase getProductPriceUseCase;

    public ProductPriceController(IGetProductPriceUseCase getProductPriceUseCase) {
        this.getProductPriceUseCase = getProductPriceUseCase;
    }

    @GetMapping("/v1/brands/{brandId}/products/{productId}")
    public ResponseEntity<GetProductPriceResponse> getProductPrice(
            @PathVariable Integer brandId,
            @PathVariable Integer productId,
            @RequestParam(name = "date") String date) throws ProductPriceNotFoundException, DateTimeParseException {

        GetProductPriceRequest getProductPriceRequest = new GetProductPriceRequest(brandId, productId, date);

        return ResponseEntity.ok().body(this.getProductPriceUseCase.execute(getProductPriceRequest));
    }

}
