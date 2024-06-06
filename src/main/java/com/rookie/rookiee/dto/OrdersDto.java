package com.rookie.rookiee.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrdersDto {

    private Long id;

    private Long idUser;

    private Boolean status;

    private String date;

    private Set<Orders_ProductsDto> orders_ProductsDtos;

}
