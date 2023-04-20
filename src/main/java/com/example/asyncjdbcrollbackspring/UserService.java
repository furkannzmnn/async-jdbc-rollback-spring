package com.example.asyncjdbcrollbackspring;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private final CustomDaoWrapper userRepository;

    public UserService(CustomDaoWrapper userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<User> save(User user, ResultHandler<User> resultHandler) {

        return CompletableFuture.supplyAsync(() -> userRepository.save(user))
                .thenApply(result -> {
                    resultHandler.onSuccess(result);
                    return result;
                })
                .exceptionally(throwable -> {
                    resultHandler.onError(throwable);
                    return null;
                });
    }
}
