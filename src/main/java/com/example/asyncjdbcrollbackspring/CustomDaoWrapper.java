package com.example.asyncjdbcrollbackspring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomDaoWrapper {
    private final UserRepository userRepository;

    public CustomDaoWrapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public User save(User user) {
        User save = userRepository.save(user);
        try {
            Thread.sleep(10000);
            throw new RuntimeException("Error");
        } catch (Exception e) {
            throw new RuntimeException(save.getEmail(), e.getCause());
        }
    }
}
