package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(User user);

    User addUser(String username, String password, String role) throws Exception;
}
