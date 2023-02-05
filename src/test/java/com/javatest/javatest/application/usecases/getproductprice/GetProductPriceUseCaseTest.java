package com.javatest.javatest.application.usecases.getproductprice;

import com.javatest.javatest.application.entities.ProductPrice;
import com.javatest.javatest.application.exceptions.ProductPriceNotFoundException;
import com.javatest.javatest.application.interfaces.IProductPriceService;
import com.javatest.javatest.infrastructure.drivers.intetrfaces.IGetProductPriceUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetProductPriceUseCaseTest {

    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 35455;
    private static final String REQUEST_DATE = "2020-02-20T02:00:00";
    private static final LocalDateTime START_DATE = LocalDateTime.parse(REQUEST_DATE).minus(1, ChronoUnit.DAYS);
    private static final LocalDateTime END_DATE = LocalDateTime.parse(REQUEST_DATE).plus(1, ChronoUnit.DAYS);
    private static final Float FINAL_PRICE = 35.50f;
    private static final String CURR = "EUR";

    private static final ProductPrice FIRST_PRODUCT_PRICE = new ProductPrice(BRAND_ID, PRODUCT_ID, 1, START_DATE, END_DATE, FINAL_PRICE, 0, CURR);
    private static final ProductPrice SECOND_PRODUCT_PRICE = new ProductPrice(BRAND_ID, PRODUCT_ID, 2, START_DATE, END_DATE, FINAL_PRICE, 1, CURR);
    private static final ProductPrice THIRD_PRODUCT_PRICE = new ProductPrice(BRAND_ID, PRODUCT_ID, 3, START_DATE, END_DATE, FINAL_PRICE, 1, CURR);

    @Mock
    private IProductPriceService productPriceService;

    private IGetProductPriceUseCase getProductPriceUseCase;

    private GetProductPriceRequest getProductPriceRequest = new GetProductPriceRequest(BRAND_ID, PRODUCT_ID, REQUEST_DATE);

    @BeforeEach
    void setUp() {
        this.getProductPriceUseCase = new GetProductPriceUseCase(productPriceService);
    }

    @Test
    void givenANullListOfProductPrices_whenExecute_ThrowProductPriceNotFoundException() {

        when(this.productPriceService.getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any())).thenReturn(null);

        assertThatExceptionOfType(ProductPriceNotFoundException.class)
                .isThrownBy(() -> this.getProductPriceUseCase.execute(getProductPriceRequest));

        verify(this.productPriceService, times(1)).getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any());
    }

    @Test
    void givenAnEmptyListOfProductPrices_whenExecute_ThrowProductPriceNotFoundException() {

        when(this.productPriceService.getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any())).thenReturn(Collections.emptyList());

        assertThatExceptionOfType(ProductPriceNotFoundException.class)
                .isThrownBy(() -> this.getProductPriceUseCase.execute(getProductPriceRequest));

        verify(this.productPriceService, times(1)).getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any());
    }

    @Test
    void givenAListWithOnlyOneProductPrice_whenExecute_ThenReturnTheProductPrice() throws ProductPriceNotFoundException {

        var productPrices = List.of(FIRST_PRODUCT_PRICE);

        when(this.productPriceService.getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any())).thenReturn(productPrices);

        var useCaseResponse = this.getProductPriceUseCase.execute(getProductPriceRequest);

        Assertions.assertThat(useCaseResponse)
                .isNotNull()
                .matches(getProductPriceResponse ->
                        getProductPriceResponse.priceList() == FIRST_PRODUCT_PRICE.priceList());

        verify(this.productPriceService, times(1)).getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any());
    }

    @Test
    void givenAListWithTwoProductPricesWithDifferentPriorities_whenExecute_ThenReturnTheMostPrioritizedProductPrice() throws ProductPriceNotFoundException {

        var productPrices = List.of(SECOND_PRODUCT_PRICE, FIRST_PRODUCT_PRICE);

        when(this.productPriceService.getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any())).thenReturn(productPrices);

        var useCaseResponse = this.getProductPriceUseCase.execute(getProductPriceRequest);

        Assertions.assertThat(useCaseResponse)
                .isNotNull()
                .matches(getProductPriceResponse ->
                        getProductPriceResponse.priceList() == SECOND_PRODUCT_PRICE.priceList());

        verify(this.productPriceService, times(1)).getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any());
    }

    @Test
    void givenAListWithTwoProductPricesWithSamePriorities_whenExecute_ThenReturnTheFirstProductPrice() throws ProductPriceNotFoundException {

        var productPrices = List.of(SECOND_PRODUCT_PRICE, THIRD_PRODUCT_PRICE);

        when(this.productPriceService.getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any())).thenReturn(productPrices);

        var useCaseResponse = this.getProductPriceUseCase.execute(getProductPriceRequest);

        Assertions.assertThat(useCaseResponse)
                .isNotNull()
                .matches(getProductPriceResponse ->
                        getProductPriceResponse.priceList() == SECOND_PRODUCT_PRICE.priceList());

        verify(this.productPriceService, times(1)).getProductPriceOrderedByPriority(eq(BRAND_ID), eq(PRODUCT_ID), any());
    }

}