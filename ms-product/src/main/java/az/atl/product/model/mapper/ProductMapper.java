package az.atl.product.model.mapper;

import az.atl.product.dao.entity.ProductEntity;
import az.atl.product.model.dto.ProductDto;

import java.util.List;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public static List<ProductDto> buildDtoList(List<ProductEntity> entityList) {
        return entityList.stream().map(ProductMapper::buildDto).toList();
    }

    public static ProductDto buildDto(ProductEntity entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .count(entity.getCount()).build();
    }
}


