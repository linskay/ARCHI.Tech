package com.coworking.exception;

import java.util.UUID;

public class GuestAccessNotFoundException extends RuntimeException {
    public GuestAccessNotFoundException(UUID guestAccessId) {
        super("Guest access not found with ID:" + guestAccessId);
    }
}
