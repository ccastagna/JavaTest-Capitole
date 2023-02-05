package com.javatest.javatest.integration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class GetProductPriceIntegrationTest {

    private static final String GET_PRODUCT_PRICE_ENDPOINT_TEMPLATE = "/v1/brands/{brandId}/products/{productId}?date={date}";

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class GivenADataSetWithBrandIdProductIdDateRangeAndPriority {

        /**
         * DATA SET
         * (ID,  BRAND_ID,   START_DATE,             END_DATE,               PRICE_LIST,     PRODUCT_ID,     PRIORITY,   PRICE, CURR)
         * (1,   1,          '2020-06-14 00:00:00',  '2020-12-31 23:59:59',  1,              35455,          0,          35.50, 'EUR'),
         * (2,   1,          '2020-06-14 15:00:00',  '2020-06-14 18:30:00',  2,              35455,          1,          25.45, 'EUR'),
         * (3,   1,          '2020-06-15 00:00:00',  '2020-06-15 11:00:00',  3,              35455,          1,          30.50, 'EUR'),
         * (4,   1,          '2020-06-15 16:00:00',  '2020-12-31 23:59:59',  4,              35455,          1,          38.95, 'EUR')
         */

        @Test
        void whenGetProductPriceForAProductIdWhichIsNotPresent_thenReturn404() throws Exception {
            var brandId = 1;
            var productId = 11111;
            var date = "2020-06-14T00:00:00";

            ResultActions result = performApiCall(brandId, productId, date);

            result.andExpect(status().isNotFound());
        }

        @Test
        void whenGetProductPriceForABrandIdWhichIsNotPresent_thenReturn404() throws Exception {
            var brandId = 2;
            var productId = 35455;
            var date = "2020-06-14T00:00:00";

            ResultActions result = performApiCall(brandId, productId, date);

            result.andExpect(status().isNotFound());
        }

        @Test
        void whenGetProductPriceForAnInvalidDateFormat_thenReturn400() throws Exception {
            var brandId = 1;
            var productId = 35455;
            var date = "2020-06-14 00:00:00";

            ResultActions result = performApiCall(brandId, productId, date);

            result.andExpect(status().isBadRequest());
        }

        @Test
        void whenGetProductPriceForADateMatchingOnlyProductPrice1_thenReturn200ProductPrice1() throws Exception {
            var brandId = 1;
            var productId = 35455;

            //Provided test number 1
            var date1 = "2020-06-14T10:00:00";

            performApiCall(brandId, productId, date1)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(1));

            //Provided test number 3
            var date3 = "2020-06-14T21:00:00";

            performApiCall(brandId, productId, date3)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(1));
        }

        @Test
        void whenGetProductPriceForADateMatchingProductPrice1And2_thenReturn200ProductPrice2DueToItsPriority() throws Exception {
            var brandId = 1;
            var productId = 35455;
            var date = "2020-06-14T16:00:00";

            ResultActions result = performApiCall(brandId, productId, date);

            result.andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(2));
        }

        @Test
        void whenGetProductPriceForADateMatchingProductPrice1And4_thenReturn200ProductPrice4DueToItsPriority() throws Exception {
            var brandId = 1;
            var productId = 35455;
            var date = "2020-06-15T10:00:00";

            ResultActions result = performApiCall(brandId, productId, date);

            result.andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(3));
        }

        @Test
        void whenGetProductPriceForADateMatchingProductPrice1And5_thenReturn200ProductPrice5DueToItsPriority() throws Exception {
            var brandId = 1;
            var productId = 35455;
            var date = "2020-06-16T21:00:00";

            ResultActions result = performApiCall(brandId, productId, date);

            result.andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(4));
        }

        private ResultActions performApiCall(int brandId, int productId, String date1) throws Exception {
            RequestBuilder endpoint1 = get(GET_PRODUCT_PRICE_ENDPOINT_TEMPLATE, brandId, productId, date1);

            ResultActions result1 = mockMvc.perform(endpoint1);
            return result1;
        }

    }

}
