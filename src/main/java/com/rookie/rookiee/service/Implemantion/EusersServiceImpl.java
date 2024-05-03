package com.rookie.rookiee.service.Implemantion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.mapper.EusersMapper;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.service.EusersService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EusersServiceImpl implements EusersService {

    @Autowired
    private EusersRepository eusersRepository;

    @Override
    public EusersDto createEusers(EusersDto eusersDto) {
        // TODO Auto-generated method stub
        System.out.println("Dto:" + eusersDto.getC_ID() + eusersDto.getDate_of_birth());
        Eusers eusers = EusersMapper.maptoEusers(eusersDto);
        System.out.println("Value:" + eusers.getC_ID() + eusers.getDate_of_birth());
        Eusers savedEusers = eusersRepository.save(eusers);
        return EusersMapper.maptoEusersDto(savedEusers);

    }

}
