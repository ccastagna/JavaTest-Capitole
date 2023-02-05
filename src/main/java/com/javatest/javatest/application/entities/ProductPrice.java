package com.javatest.javatest.application.entities;

import java.time.Instant;

public record ProductPrice(Integer brandId, Integer productId, Integer priceList, Instant startDate,
                           Instant endDate, Float finalPrice, Integer priority, String curr) {
}
