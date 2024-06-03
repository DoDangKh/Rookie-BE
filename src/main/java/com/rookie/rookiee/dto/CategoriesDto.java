package com.rookie.rookiee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
// @EqualsAndHashCode
public class CategoriesDto {

    private Long id;
    private String name;
    private String description;
    private boolean status;

}
