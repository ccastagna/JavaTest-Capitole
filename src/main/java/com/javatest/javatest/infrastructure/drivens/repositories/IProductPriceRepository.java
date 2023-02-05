package com.javatest.javatest.infrastructure.drivens.repositories;

import com.javatest.javatest.infrastructure.drivens.repositories.dtos.ProductPriceRepositoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface IProductPriceRepository extends JpaRepository<ProductPriceRepositoryDTO, Long> {

    @Query("SELECT pp FROM prices pp WHERE pp.brandId = :brandId AND pp.productId = :productId AND pp.startDate <= :date AND pp.endDate >= :date ORDER BY pp.priority DESC")
    List<ProductPriceRepositoryDTO> findByBrandIdAndProductIdAndDate(@Param("brandId") Integer brandId, @Param("productId") Integer productId, @Param("date") Instant date);
}
