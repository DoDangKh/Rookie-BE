package com.rookie.rookiee.service.implemantion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.AddProductDto;
import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.entity.Images;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.ProductsMapper;
import com.rookie.rookiee.repository.IamgesRepository;
import com.rookie.rookiee.repository.ProductsRepository;
import com.rookie.rookiee.service.ProductsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProductsRepository productsRepository;

    private final IamgesRepository iamgesRepository;

    @Override
    public ProductsDto findById(Long id) {
        Products products = productsRepository.findById(id).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        return ProductsMapper.maptoProductsDto(products);
    }

    @Override
    public ProductsDto save(AddProductDto productsDto) {

        Products products = new Products();

        products = ProductsMapper.addDtoToProducts(productsDto, products);

        Set<Images> idImages = new HashSet();

        // for (Images i : products.getImages()) {
        // iamgesRepository.save(i);

        // }

        // System.out.println(idImages);
        // products.setImages(idImages);

        // System.out.println(products);

        Products saved = productsRepository.save(products);

        return ProductsMapper.maptoProductsDto(saved);

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Products products = productsRepository.findById(id).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        Set<Images> img = products.getImages();

        for (Images i : img) {
            iamgesRepository.deleteById(i.getId());
        }

        productsRepository.deleteById(id);

        // productsRepository.delete(products);
    }

    @Override
    public ProductsDto update(AddProductDto productsDto, Long id) {

        Products products = entityManager.find(Products.class, id);

        products = ProductsMapper.addDtoToProducts(productsDto, products);


        return ProductsMapper.maptoProductsDto(productsRepository.save(products));

    }

    @Override
    public List<ProductsDto> findAll() {

        List<Products> products = productsRepository.findAll();

        List<ProductsDto> productsDtos = new ArrayList<>();

        for (Products p : products) {
            productsDtos.add(ProductsMapper.maptoProductsDto(p));
        }

        return productsDtos;
    }

    @Override
    @Transactional
    public void deleteManyById(List<Long> idList) {

        List<Products> listProducts = productsRepository.findAllById(idList);
        for (Products p : listProducts) {
            p.setIsActive(false);
            productsRepository.save(p);
        }
    }

}
