package com.rookie.rookiee.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AddProductDto {

    private Long id;
    private String name;
    private Double price;

    @JsonProperty("amount")
    private Long amount;

    private String description;

    @JsonProperty("feature")
    private boolean feature;

    private Set<CategoriesDto> categories;
    private Set<String> images;
    private Boolean isActive;

}
