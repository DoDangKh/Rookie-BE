package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;

public interface EusersService {

    EusersDto findbyEmail(String email);

    EusersDto login(CredentialsDto credentialsDto);

    EusersDto register(SignUpDto signUpDto);

}
