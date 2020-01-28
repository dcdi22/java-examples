package com.company.shippingservice.controller;

public class EntityNotFoundException extends Throwable {
    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with id " + id + " not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
