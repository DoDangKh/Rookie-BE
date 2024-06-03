package com.rookie.rookiee.service.implemantion;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Role;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.EusersMapper;
import com.rookie.rookiee.mapper.RoleMapper;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.service.EusersService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EusersServiceImpl implements EusersService, UserDetailsService {

    private final EusersRepository eusersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EusersDto findbyEmail(String email) {
        Eusers eusers = eusersRepository.findOneByEmail(email)
                .orElseThrow(() -> new AppException("Unknow User", HttpStatus.NOT_FOUND));
        return EusersMapper.maptoEusersDto(eusers);
    }

    @Override
    public EusersDto login(CredentialsDto credentialsDto) {
        Eusers eusers = eusersRepository.findOneByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknow User", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), eusers.getPassword())) {
            return EusersMapper.maptoEusersDto(eusers);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional
    public EusersDto register(SignUpDto signUpDto) {
        Optional<Eusers> optionalEuser = eusersRepository.findOneByEmail(signUpDto.getEmail());
        if (optionalEuser.isPresent()) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Eusers eusers = EusersMapper.signUptoEusers(signUpDto);
        Set<Role> roles = RoleMapper.roleDtoToRole(signUpDto.getRoles());
        eusers.setRoles(roles);

        eusers.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        Eusers saved = eusersRepository.save(eusers);

        return EusersMapper.maptoEusersDto(saved);
    }

    @Override
    public Eusers loadUserByUsername(String email) throws UsernameNotFoundException {
        return eusersRepository.findOneByEmail(email)
                .orElseThrow(() -> new AppException("Users Not Found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Eusers loadEusersByEmail(String email) {

        return eusersRepository.findOneByEmail(email)
                .orElseThrow(() -> new AppException("Users Not Found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<EusersDto> findAll() {
        List<Eusers> eusers = eusersRepository.findAll();
        List<EusersDto> eusersDtos = new ArrayList();

        for (Eusers e : eusers) {
            eusersDtos.add(EusersMapper.maptoEusersDto(e));
        }
        return eusersDtos;
    }

    @Override
    public EusersDto findById(Long id) {
        // TODO Auto-generated method stub

        Eusers eusers = eusersRepository.findById(id)
                .orElseThrow(() -> new AppException("Users Not Found", HttpStatus.NOT_FOUND));
        return EusersMapper.maptoEusersDto(eusers);

    }

}
