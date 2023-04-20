package com.example.asyncjdbcrollbackspring;

import org.springframework.stereotype.Component;

@Component
public class AsyncUserErrorHandler implements ResultHandler<User> {
    private final CustomDaoWrapper userRepository;

    public AsyncUserErrorHandler(CustomDaoWrapper userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onError(Throwable throwable) {
        String email  = throwable.getCause().getMessage();
        userRepository.deleteByEmail(email);
        System.out.println("Custom Error Handler: " + throwable.getCause());
    }

    @Override
    public void onSuccess(User result) {
        System.out.println("Success: " + result);
    }
}
