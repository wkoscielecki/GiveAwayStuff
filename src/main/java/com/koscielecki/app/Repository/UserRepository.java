package com.koscielecki.app.Repository;

import com.koscielecki.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
}
