package com.guilherme.delfino.pubsub.notification.infra.useCase;

import com.guilherme.delfino.pubsub.notification.domain.entity.Event;
import com.guilherme.delfino.pubsub.notification.application.useCase.SendEmailNewEventCreated;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;
import com.guilherme.delfino.pubsub.notification.application.protocol.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

@Service
public class SendEmailNewEventCreateBySimpleMailSpring implements SendEmailNewEventCreated {

    private final EmailService emailService;
    @Value("${project.email}")
    private String USERNAME_EMAIL;

    public SendEmailNewEventCreateBySimpleMailSpring(EmailService emailService) {
        this.emailService = emailService;
    }

    public void execute(Event event, Subscriber subscriber){
            String fileContent = getHtmlEventTemplate();
            String html = setDataIntoHtmlTemplate(fileContent, event, subscriber);
        try {
            String SUBJECT = "HEEY, A NEW EVENT HAS BEEN CREATED! CHECK IT NOW";
            emailService.send(USERNAME_EMAIL, subscriber.getEmail(), SUBJECT, html);
        } catch (MessagingException e) {
            throw new RuntimeException("Not be able to send email, message = "+ e.getMessage());
        }
    }

    public String getHtmlEventTemplate() {
        try {
            File template = new File("HtmlEventTemplate.html");
            BufferedReader reader = new BufferedReader(new FileReader(template));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha);
            }

            return conteudo.toString();
        } catch (IOException e) {
            throw new RuntimeException("Template has not found, message = "+ e.getMessage());
        }
    }

    public String setDataIntoHtmlTemplate(String fileContent, Event event, Subscriber subscriber){
        String htmlEvent = setEventDataIntoHtmlTemplate(fileContent, event);
        return setSubscriberDataintoHtmlTemplate(htmlEvent, subscriber);
    }

    public String setEventDataIntoHtmlTemplate(String fileContent, Event event){
        fileContent = fileContent.replace("${eventDate}", event.getDate().toString());
        fileContent = fileContent.replace("${eventLocation}", event.getLocation());
        fileContent = fileContent.replace("${eventAuthor}", event.getAuthorName());
        fileContent = fileContent.replace("${eventTitle}", event.getTitle());
        return fileContent.replace("${eventDescription}", event.getDescription());
    }
    public String setSubscriberDataintoHtmlTemplate(String fileContent, Subscriber subscriber){
        return fileContent.replace("${subscriberName}", subscriber.getName());
    }
}
