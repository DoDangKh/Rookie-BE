package com.rookie.rookiee.service.implemantion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.ProductsMapper;
import com.rookie.rookiee.repository.ProductsRepository;
import com.rookie.rookiee.service.ProductsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    @Override
    public ProductsDto findById(Long id) {
        Products products = productsRepository.findById(id).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        return ProductsMapper.maptoProductsDto(products);
    }

    @Override
    public ProductsDto save(ProductsDto productsDto) {

        Products products = ProductsMapper.productsDtotoPrducts(productsDto);

        Products saved = productsRepository.save(products);

        return ProductsMapper.maptoProductsDto(saved);

    }

    @Override
    public void deleteById(Long id) {
        Products products = productsRepository.findById(id).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        productsRepository.delete(products);
    }

    @Override
    public ProductsDto update(ProductsDto productsDto, Long id) {

        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        products = ProductsMapper.productsDtotoPrducts(productsDto);

        return ProductsMapper.maptoProductsDto(productsRepository.save(products));
    }

}
