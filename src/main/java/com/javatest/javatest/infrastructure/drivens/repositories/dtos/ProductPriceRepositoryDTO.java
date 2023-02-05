package com.javatest.javatest.infrastructure.drivens.repositories.dtos;

import com.javatest.javatest.application.entities.ProductPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name="prices")
public class ProductPriceRepositoryDTO implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="BRAND_ID")
    private Integer brandId;

    @Column(name="PRODUCT_ID")
    private Integer productId;

    @Column(name="PRICE_LIST")
    private Integer priceList;

    @Column(name="START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Column(name="END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @Column(name="PRICE")
    private Float finalPrice;

    private Integer priority;

    private String curr;

    protected ProductPriceRepositoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getCurr() {
        return curr;
    }

    public ProductPrice toEntity() {
        return new ProductPrice(
                this.brandId,
                this.productId,
                this.priceList,
                this.startDate,
                this.endDate,
                this.finalPrice,
                this.priority,
                this.curr
        );
    }
}
