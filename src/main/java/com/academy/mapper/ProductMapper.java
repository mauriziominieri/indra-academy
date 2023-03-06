package com.academy.mapper;

import com.academy.dto.ProductDto;
import com.academy.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toModel(ProductDto productDto);
    List<ProductDto> toDtos(List<Product> productList);
    List<Product> toModels(List<ProductDto> productDtoList);
}
