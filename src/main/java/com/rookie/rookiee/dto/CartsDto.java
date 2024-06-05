package com.rookie.rookiee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartsDto {

    private Long id;

    private Long idUser;

    private ProductsDto productsDto;

    private int amount;

}
