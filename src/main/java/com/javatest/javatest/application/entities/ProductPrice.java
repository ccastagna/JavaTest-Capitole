package com.javatest.javatest.application.entities;

import java.time.LocalDateTime;

public record ProductPrice(Integer brandId, Integer productId, Integer priceList, LocalDateTime startDate,
                           LocalDateTime endDate, Float finalPrice, Integer priority, String curr) {
}
