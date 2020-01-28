package com.trilogyed.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends Throwable {
    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with id " + id + " not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}

