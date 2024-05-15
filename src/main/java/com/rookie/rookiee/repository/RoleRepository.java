package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
