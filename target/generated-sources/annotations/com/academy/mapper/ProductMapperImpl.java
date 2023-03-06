package com.academy.mapper;

import com.academy.dto.ProductDto;
import com.academy.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-06T00:36:09+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );

        return productDto;
    }

    @Override
    public Product toModel(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setDescription( productDto.getDescription() );
        product.setPrice( productDto.getPrice() );

        return product;
    }

    @Override
    public List<ProductDto> toDtos(List<Product> productList) {
        if ( productList == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( productList.size() );
        for ( Product product : productList ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public List<Product> toModels(List<ProductDto> productDtoList) {
        if ( productDtoList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDtoList.size() );
        for ( ProductDto productDto : productDtoList ) {
            list.add( toModel( productDto ) );
        }

        return list;
    }
}
