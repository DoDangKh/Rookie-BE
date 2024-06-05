package com.rookie.rookiee.service.implemantion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.CartsDto;
import com.rookie.rookiee.entity.Carts;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.CartsMapper;
import com.rookie.rookiee.repository.CartsRepository;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.repository.ProductsRepository;
import com.rookie.rookiee.service.CartsService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartsServiceImpl implements CartsService {

    private final CartsRepository cartsRepository;

    private final EusersRepository eusersRepository;

    private final ProductsRepository productsRepository;

    @Override
    @Transactional
    public List<CartsDto> findByUserId(Long userId) {

        Eusers eusers = eusersRepository.findById(userId)
                .orElseThrow(() -> new AppException("Users Not Found", HttpStatus.NOT_FOUND));

        List<Carts> carts = cartsRepository.findByEusers(eusers);

        List<CartsDto> cartsDtos = new ArrayList();

        for (Carts c : carts) {
            cartsDtos.add(CartsMapper.mapToCartsDto(c));
        }

        return cartsDtos;

    }

    @Override
    @Transactional
    public CartsDto addToCarts(CartsDto cartsDto) {

        Products products = productsRepository.findById(cartsDto.getProductsDto().getId())
                .orElseThrow(() -> new AppException("Products Not Found", HttpStatus.NOT_FOUND));

        Eusers eusers = eusersRepository.findById(cartsDto.getIdUser())
                .orElseThrow(() -> new AppException("Users Not Found", HttpStatus.NOT_FOUND));

        Carts carts = cartsRepository.findByProductsAndEusers(products, eusers);

        if (carts == null) {

            carts = new Carts();

            carts = CartsMapper.mapToCarts(cartsDto, carts);

            carts.setEusers(eusers);
            carts.setProducts(products);

            Set<Carts> setCarts = products.getCarts();

            setCarts.add(carts);

            products.setCarts(setCarts);

            productsRepository.save(products);

            setCarts = eusers.getCarts();

            setCarts.add(carts);

            eusersRepository.save(eusers);

            return CartsMapper.mapToCartsDto(cartsRepository.save(carts));
        } else {
            carts.setAmount(carts.getAmount() + 1);
            return CartsMapper.mapToCartsDto(cartsRepository.save(carts));
        }

    }

    @Override
    @Transactional
    public CartsDto updateCarts(CartsDto cartsDto, Long id) {
        Carts carts = cartsRepository.findById(id)
                .orElseThrow(() -> new AppException("Carts Not Found", HttpStatus.NOT_FOUND));

        carts = CartsMapper.mapToCarts(cartsDto, carts);

        return CartsMapper.mapToCartsDto(cartsRepository.save(carts));

    }

    @Override
    public void DeleteCarts(Long id) {
        // try {
        cartsRepository.deleteById(id);
        // } catch (Exception e) {
        // throw (new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        // }
    }

}
