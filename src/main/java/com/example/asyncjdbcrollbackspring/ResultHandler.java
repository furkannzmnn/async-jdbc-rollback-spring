package com.example.asyncjdbcrollbackspring;

import java.util.function.Consumer;

public interface ResultHandler<T> extends Consumer<T> {
    void onError(Throwable throwable);
    void onSuccess(T result);

    @Override
    default void accept(T result) {
        onSuccess(result);
    }
}
