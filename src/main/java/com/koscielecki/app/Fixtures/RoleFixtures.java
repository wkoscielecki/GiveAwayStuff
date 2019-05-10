package com.koscielecki.app.Fixtures;

import com.koscielecki.app.Entity.Role;

import com.koscielecki.app.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleFixtures {

    @Autowired
    RoleRepository roleRepository;

    public void crateAndInsertIntoDb(){
        List<Role> roles=createRole();
    }

    public List<Role> createRole() {
        List<Role> roles=new ArrayList<>();
        Role admin=new Role();
        admin.setRole("ROLE_ADMIN");
        roleRepository.save(admin);

        Role user=new Role();
        user.setRole("ROLE_USER");
        roleRepository.save(user);

        return roles;
    }

}
