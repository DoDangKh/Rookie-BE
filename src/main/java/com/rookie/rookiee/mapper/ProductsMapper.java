package com.rookie.rookiee.mapper;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.rookie.rookiee.dto.AddProductDto;
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

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ProductsDto productsDto = new ProductsDto();

        productsDto.setName(products.getName());
        productsDto.setId(products.getId());
        productsDto.setPrice(products.getPrice());
        productsDto.setDescription(products.getDescription());
        productsDto.setFeature(products.getFeature());
        productsDto.setAmount(products.getAmount());
        productsDto.setIsActive(products.getIsActive());

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

        if (products.getRates() != null)
            for (Rates r : products.getRates()) {
                ratesDto.add(RatesMapper.toRateDto(r));
            }

        productsDto.setRates(ratesDto);

        productsDto.setCreatedAt(dateTimeFormatter.format(products.getDateCreated()));

        productsDto.setUpdatedAt(dateTimeFormatter.format(products.getDateModified()));

        productsDto.setModifiedUser(products.getModifiedUser());

        System.out.println(products.getRates());
        System.out.println(products.getCategories());

        return productsDto;
    }

    public static Products productsDtotoPrducts(ProductsDto productsDto, Products products) {

        products.setId(productsDto.getId());
        products.setName(productsDto.getName());
        products.setPrice(productsDto.getPrice());
        products.setDescription(productsDto.getDescription());
        products.setFeature(products.getFeature());
        products.setAmount(productsDto.getAmount());
        products.setIsActive(productsDto.getIsActive());

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

    public static Products addDtoToProducts(AddProductDto addProductDto, Products products) {

        products.setId(addProductDto.getId());
        products.setName(addProductDto.getName());
        products.setPrice(addProductDto.getPrice());
        products.setDescription(addProductDto.getDescription());
        products.setFeature(products.getFeature());
        products.setAmount(addProductDto.getAmount());
        products.setIsActive(addProductDto.getIsActive());

        Set<Categories> categories;
        categories = new HashSet<>();
        if (products.getCategories() != null) {
            products.getCategories().clear();
            categories = products.getCategories();
        }

        for (CategoriesDto c : addProductDto.getCategories()) {
            categories.add(CategoriesMapper.categoriesDtoToCategories(c));
        }

        products.setCategories(categories);

        products.setFeature(addProductDto.isFeature());
        Set<Images> images = new HashSet<>();

        if (!addProductDto.getImages().isEmpty()) {

            if (products.getImages() != null) {
                products.getImages().clear();
                images = products.getImages();
            }

            for (String i : addProductDto.getImages()) {
                Images temp = new Images();
                temp.setUrl(i);
                temp.setProducts(products);
                images.add(temp);

            }
            if (products.getImages() != null)
                products.getImages().addAll(images);

            else
                products.setImages(images);

        }

        products.setImages(images);
        return products;
    }

}
