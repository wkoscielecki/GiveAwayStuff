package com.koscielecki.app.Service;

import com.koscielecki.app.Entity.Role;
import com.koscielecki.app.Entity.User;
import com.koscielecki.app.Repository.RoleRepository;
import com.koscielecki.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public User get(Long id){
        return userRepository.findById(id).get();
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public User findByEmail(String email){
        return userRepository.findOneByEmail(email);
    }
    public void updatePassword(String password, String email){
        userRepository.updateUserPassword(password,email);
    }


}
