package com.rookie.rookiee.dto;

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
@EqualsAndHashCode
public class EusersDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String modifiedUser;

    @EqualsAndHashCode.Exclude
    private String token;

}
