package com.koscielecki.app.Repository;

import com.koscielecki.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
    @Modifying
    @Query("UPDATE User user SET user.password= :newPassword WHERE user.email= :email")
    void updateUserPassword(@Param("newPassword")String password, @Param("email")String email );
}
