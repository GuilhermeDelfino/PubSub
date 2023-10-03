package com.guilherme.delfino.pubsub.notification.infra.protocol.mail;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class EmailServiceJavaMailSenderTest {

    @InjectMocks
    private EmailServiceJavaMailSender mailSender;

    @Mock
    private JavaMailSender javaMailSender;
    @Test
    @DisplayName("Should be send message correctly")
    void send() {

        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(Mockito.mock(MimeMessage.class));

        String emailFrom = "test@test.com";
        String emailTo = "toTest@toTest.com";
        String subject = "Example Subject";
        String contentHTML = """
                    <h1>Teste</h1>
                """;
        mailSender.send(emailFrom, emailTo, subject, contentHTML);
    }
}