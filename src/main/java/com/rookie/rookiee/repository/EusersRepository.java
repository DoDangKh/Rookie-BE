package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.rookie.rookiee.entity.Eusers;
import java.util.Optional;

public interface EusersRepository extends JpaRepository<Eusers, Long> {

    Optional<Eusers> findOneByEmail(String email);

    Optional<UserDetails> findByEmail(String email);

}
