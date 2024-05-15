package com.rookie.rookiee.mapper;

import java.util.HashSet;
import java.util.Set;

import com.rookie.rookiee.dto.RoleDto;
import com.rookie.rookiee.entity.Role;

public class RoleMapper {
    public static Set<Role> roleDtoToRole(Set<RoleDto> roleDto) {
        Set<Role> roles = new HashSet();
        for (RoleDto r : roleDto) {
            Role temp = new Role();
            temp.setRolename(r.getRolename());
            roles.add(temp);
        }
        return roles;
    }
}
