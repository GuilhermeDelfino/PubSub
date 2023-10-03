package com.guilherme.delfino.pubsub.notification.infra.protocol.mail;

import com.guilherme.delfino.pubsub.notification.application.protocol.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceJavaMailSender implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void send(String from, String to, String subject, String content){
        MimeMessage message = mailSender.createMimeMessage();
        setMessageData(message, from, to, subject, content);
        mailSender.send(message);
    }

    public void setMessageData(MimeMessage message, String from, String to, String subject, String content){
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject(subject);
            String contentType = MediaType.TEXT_HTML_VALUE + "; charset=utf-8";
            message.setContent(content, contentType);
        } catch (MessagingException e) {
            throw new RuntimeException("Not be able to send email, message = "+ e.getMessage());
        }
    }
}
