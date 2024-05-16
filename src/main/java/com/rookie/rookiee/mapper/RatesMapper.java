package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.RatesDto;
import com.rookie.rookiee.entity.Rates;

public class RatesMapper {
    public static RatesDto toRateDto(Rates rates) {
        return new RatesDto(rates.getId(), rates.getScores());
    }

    public static Rates ratesDtoToRates(RatesDto ratesDto) {
        Rates rates = new Rates();

        rates.setId(ratesDto.getId());

        rates.setScores(ratesDto.getScores());

        return rates;
    }
}
