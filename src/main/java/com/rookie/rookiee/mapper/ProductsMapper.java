package com.rookie.rookiee.mapper;

import java.util.HashSet;
import java.util.Set;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.dto.ImagesDto;
import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.dto.RatesDto;
import com.rookie.rookiee.entity.Categories;
import com.rookie.rookiee.entity.Images;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.entity.Rates;

public class ProductsMapper {

    public static ProductsDto maptoProductsDto(Products products) {

        ProductsDto productsDto = new ProductsDto();

        productsDto.setName(products.getName());
        productsDto.setId(products.getId());
        productsDto.setPrice(products.getPrice());
        productsDto.setDescription(products.getDescription());
        productsDto.setFeature(products.getFeature());

        Set<ImagesDto> ImagesDto = new HashSet();
        for (Images i : products.getImages()) {
            ImagesDto.add(ImagesMapper.toImagesDto(i));
        }
        productsDto.setImages(ImagesDto);

        Set<CategoriesDto> categoriesDto = new HashSet();

        for (Categories c : products.getCategories()) {
            categoriesDto.add(CategoriesMapper.toCategoriesDto(c));
        }

        productsDto.setCategories(categoriesDto);

        Set<RatesDto> ratesDto = new HashSet();

        for (Rates r : products.getRates()) {
            ratesDto.add(RatesMapper.toRateDto(r));
        }

        productsDto.setRates(ratesDto);

        return productsDto;
    }

    public static Products productsDtotoPrducts(ProductsDto productsDto) {
        Products products = new Products();

        products.setId(productsDto.getId());
        products.setName(productsDto.getName());
        products.setPrice(productsDto.getPrice());
        products.setDescription(productsDto.getDescription());
        products.setFeature(products.getFeature());

        Set<Categories> categories = new HashSet();

        for (CategoriesDto c : productsDto.getCategories()) {
            categories.add(CategoriesMapper.categoriesDtoToCategories(c));
        }

        products.setCategories(categories);

        Set<Images> images = new HashSet();

        for (ImagesDto i : productsDto.getImages()) {
            images.add(ImagesMapper.imagesDtoToImages(i));
        }

        products.setImages(images);

        Set<Rates> rates = new HashSet();

        for (RatesDto r : productsDto.getRates()) {
            rates.add(RatesMapper.ratesDtoToRates(r));
        }

        products.setRates(rates);

        return products;
    }

}
