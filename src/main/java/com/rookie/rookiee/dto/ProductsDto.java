package com.rookie.rookiee.dto;

import java.util.Set;
import java.util.List;

// import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
// @EqualsAndHashCode
public class ProductsDto {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Set<CategoriesDto> categories;
    private Set<ImagesDto> images;
    private Set<RatesDto> rates;

}
