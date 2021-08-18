package com.olegilminsky.service;

import com.olegilminsky.controller.ProductListParams;
import com.olegilminsky.persist.Product;
import com.olegilminsky.persist.ProductRepository;
import com.olegilminsky.persist.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec = Specification.where(null);

        if (productListParams.getProductTitleFilter() != null && !productListParams.getProductTitleFilter().isBlank()) {
            spec = spec.and(ProductSpecifications.titlePrefix(productListParams.getProductTitleFilter()));
        }
        if (productListParams.getMinPrice() != null) {
            spec = spec.and(ProductSpecifications.minPrice(productListParams.getMinPrice()));
        }
        if (productListParams.getMaxPrice() != null) {
            spec = spec.and(ProductSpecifications.maxPrice(productListParams.getMaxPrice()));
        }

        return productRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(productListParams.getSize()).orElse(3),
                        Sort.by(Sort.Direction.fromString(Optional.ofNullable(productListParams.getSortDirection())
                                        .filter(c -> !c.isBlank())
                                        .orElse("asc")),
                                Optional.ofNullable(productListParams.getSortField())
                                        .filter(c -> !c.isBlank())
                                        .orElse("id"))));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
