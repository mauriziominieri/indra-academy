package com.academy.service;

import com.academy.dto.ProductDto;
import com.academy.exception.ProductException;
import com.academy.mapper.ProductMapper;
import com.academy.model.Product;
import com.academy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDto> getAll() {
        return productMapper.toDtos(productRepository.findAll());
    }

    public ProductDto getById(Long id) throws ProductException {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(() -> new ProductException("Prodotto con id " + id + " non trovato")));
    }

    public ProductDto add(ProductDto productDto) {
        return productMapper.toDto(productRepository.save(productMapper.toModel(productDto)));
    }

    public ProductDto update(Long id, ProductDto productDto) throws ProductException {
        productRepository.findById(id).orElseThrow(() -> new ProductException("Prodotto con id " + id + " non trovato"));
        productDto.setId(id);
        return productMapper.toDto(productRepository.save(productMapper.toModel(productDto)));
    }

    public ProductDto delete(Long id) throws ProductException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("Prodotto con id " + id + " non trovato"));
        productRepository.deleteById(id);
        return productMapper.toDto(product);
    }
}