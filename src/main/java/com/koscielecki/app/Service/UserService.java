package com.koscielecki.app.Service;

import com.koscielecki.app.Entity.User;

public interface UserService {
    User findByEmail(String email);
    void saveUser(User user);
}
