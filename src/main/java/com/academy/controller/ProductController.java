package com.academy.controller;

import com.academy.dto.ProductDto;
import com.academy.exception.ProductException;
import com.academy.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Api("REST Controller per le operazioni sui Prodotti")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("Restituisce tutti i prodotti")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @ApiOperation("Restituisce il prodotto tramite id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) throws ProductException {
        return ResponseEntity.ok(productService.getById(id));
    }

    @ApiOperation("Aggiunge un nuovo prodotto")
    @PostMapping
    public ResponseEntity<ProductDto> add(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.add(productDto));
    }

    @ApiOperation("Aggiorna il prodotto tramite id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto) throws ProductException {
        return ResponseEntity.ok(productService.update(id, productDto));
    }

    @ApiOperation("Cancella il prodotto tramite id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDto> delete(@PathVariable Long id) throws ProductException {
        return ResponseEntity.ok(productService.delete(id));
    }
}