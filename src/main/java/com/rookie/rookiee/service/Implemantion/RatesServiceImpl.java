package com.rookie.rookiee.service.implemantion;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.RatesDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.entity.Rates;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.RatesMapper;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.repository.ProductsRepository;
import com.rookie.rookiee.repository.RateRepository;
import com.rookie.rookiee.service.RatesService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatesServiceImpl implements RatesService {

    private final ProductsRepository productsRepository;

    private final RateRepository rateRepository;

    private final EusersRepository eusersRepository;

    @Override
    @Transactional
    public RatesDto save(RatesDto ratesDto, Long idProduct, Long idUser) {

        Products products = productsRepository.findById(idProduct).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        Eusers eusers = eusersRepository.findById(idUser).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        Rates rates = RatesMapper.ratesDtoToRates(ratesDto);

        // rates.setProducts(products);

        // rates.setEusers(eusers);

        rates.setEusers(eusers);
        rates.setProducts(products);

        products.getRates().clear();

        Set<Rates> productRates = products.getRates();
        productRates.add(rates);
        products.setRates(productRates);

        productsRepository.save(products);

        Set<Rates> euserRates = eusers.getRates();
        eusers.setRates(euserRates);
        eusers.setRates(euserRates);

        eusersRepository.save(eusers);

        return RatesMapper.toRateDto(rateRepository.save(rates));

    }

    @Override
    public RatesDto findbyProductandUser(Long idProduct, Long idUser) {

        Products products = productsRepository.findById(idProduct).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        Eusers eusers = eusersRepository.findById(idUser).orElseThrow(
                () -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        return RatesMapper.toRateDto(rateRepository.findOneByEusersAndProducts(eusers, products));

    }

}
