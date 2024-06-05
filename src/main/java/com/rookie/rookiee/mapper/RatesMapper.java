package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.RatesDto;
import com.rookie.rookiee.entity.Rates;

public class RatesMapper {
    public static RatesDto toRateDto(Rates rates) {
        RatesDto ratesDto = new RatesDto();

        if (rates != null) {
            ratesDto.setId(rates.getId());

            ratesDto.setComment(rates.getComment());

            ratesDto.setScores(rates.getScores());

            ratesDto.setName(rates.getEusers().getFirstName() + " " + rates.getEusers().getLastName());

            return ratesDto;
        }
        return null;
    }

    public static Rates ratesDtoToRates(RatesDto ratesDto) {
        Rates rates = new Rates();

        rates.setId(ratesDto.getId());

        rates.setScores(ratesDto.getScores());

        rates.setComment(ratesDto.getComment());

        return rates;
    }
}
