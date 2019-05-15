package com.koscielecki.app.Service;

import com.koscielecki.app.Entity.User;

public interface UserService {
    User findOneByEmail(String email);

    void saveUser(User user);

     void updateUserPassword(String newPassword, String email);
}
