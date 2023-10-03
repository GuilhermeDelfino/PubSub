package com.guilherme.delfino.pubsub.notification.application.protocol;

import jakarta.mail.MessagingException;

public interface EmailService {
    void send(String from, String to, String subject, String message) throws MessagingException;
}
