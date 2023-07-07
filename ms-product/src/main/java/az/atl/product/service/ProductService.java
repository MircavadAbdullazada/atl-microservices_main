package az.atl.product.service;

import az.atl.product.model.dto.ProductDto;

import java.util.List;


public interface ProductService {
    List<ProductDto> getAllProducts();
}
