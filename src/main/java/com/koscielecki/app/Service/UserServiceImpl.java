//package com.koscielecki.app.Service;
//
//import com.koscielecki.app.Entity.Role;
//import com.koscielecki.app.Entity.User;
//import com.koscielecki.app.Repository.RoleRepository;
//import com.koscielecki.app.Repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
//@Service
//@Transactional
//public class UserServiceImpl {
//
//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//    public UserServiceImpl(UserService userService, UserRepository userRepository,
//                           RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }
//
//
//    public User findOneByEmail(String email) {
//        return userRepository.findOneByEmail(email);
//    }
//
//    public void saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEnabled(1);
//        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        userRepository.save(user);
//    }
//
//    @Override
//    public void updateUserPassword(String newPassword, String email) {
//        userRepository.updateUserPassword(passwordEncoder.encode(newPassword),email);
//    }
//
//
//}
