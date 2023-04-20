package com.example.asyncjdbcrollbackspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class AsyncJdbcRollbackSpringApplication implements CommandLineRunner {

    private final UserService userService;
    private final CustomDaoWrapper userRepository;

    public AsyncJdbcRollbackSpringApplication(UserService userService, CustomDaoWrapper userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncJdbcRollbackSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("skdksadaskd");
        user.setPhone("123456789");
        user.setStatus("active");
       user.setPassword("active");

        AsyncUserErrorHandler errorHandler = new AsyncUserErrorHandler(userRepository);
        CompletableFuture<User> save = userService.save(user, errorHandler);
        System.out.println("User saved ?: "+ save.isDone());
    }
}
