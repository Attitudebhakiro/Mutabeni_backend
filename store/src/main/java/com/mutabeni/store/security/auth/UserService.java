package com.mutabeni.store.security.auth;


import com.mutabeni.store.security.user.User;
import com.mutabeni.store.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public Optional<User> userProfile(String email){
        return repository.findByEmail(email);
    }
}
