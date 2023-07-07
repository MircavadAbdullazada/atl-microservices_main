package az.atl.product.service.impl;

import az.atl.product.dao.entity.ProductEntity;
import az.atl.product.dao.repository.ProductRepository;
import az.atl.product.model.dto.ProductDto;
import az.atl.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static az.atl.product.model.mapper.ProductMapper.buildDtoList;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService, CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        var productList = productRepository.findAll();
        return buildDtoList(productList);

    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 5; i++) {
            var product = ProductEntity.builder()
                    .name(i + ": Product")
                    .price(new BigDecimal(10).add(new BigDecimal(i)))
                    .count(1)
                    .build();
            productRepository.save(product);
        }


    }
}
