package com.rookie.rookiee.service;

import java.util.Optional;

import com.rookie.rookiee.dto.RatesDto;

public interface RatesService {

    RatesDto save(RatesDto ratesDto, Long idProduct, Long idUser);

    RatesDto findbyProductandUser(Long idProduct, Long idUser);
}
