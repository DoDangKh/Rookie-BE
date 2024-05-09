package com.rookie.rookiee.service.implemantion;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.EusersMapper;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.service.EusersService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EusersServiceImpl implements EusersService {

    private final EusersRepository eusersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EusersDto findbyEmail(String email) {
        Eusers eusers = eusersRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknow User", HttpStatus.NOT_FOUND));
        return EusersMapper.maptoEusersDto(eusers);
    }

    @Override
    public EusersDto login(CredentialsDto credentialsDto) {
        Eusers eusers = eusersRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknow User", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), eusers.getPassword())) {
            return EusersMapper.maptoEusersDto(eusers);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public EusersDto register(SignUpDto signUpDto) {
        Optional<Eusers> optionalEuser = eusersRepository.findByEmail(signUpDto.getEmail());
        if (optionalEuser.isPresent()) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Eusers eusers = EusersMapper.signUptoEusers(signUpDto);

        eusers.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        Eusers saved = eusersRepository.save(eusers);

        return EusersMapper.maptoEusersDto(saved);
    }

}
