package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Eusers;
import java.util.Optional;

public interface EusersRepository extends JpaRepository<Eusers, Long> {

    Optional<Eusers> findByEmail(String email);

}
