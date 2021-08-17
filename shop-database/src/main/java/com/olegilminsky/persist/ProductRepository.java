package com.olegilminsky.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByTitleStartsWith(String prefix);

    List<Product> findProductByTitleLike(String title);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    @Query("select p " +
            "from Product p " +
            "where (p.title like CONCAT(:prefix, '%') or :prefix is null ) and " +
            "(p.price >= :minPrice or :minPrice is null ) and " +
            "(p.price <= :maxPrice or :maxPrice is null )")
    List<Product> filterProducts(@Param("prefix") String prefix,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice);
}
