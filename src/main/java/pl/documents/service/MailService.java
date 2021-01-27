package pl.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService implements EmailService
{
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage (String to, String subject, String text)
    {

        SimpleMailMessage message = new SimpleMailMessage();
       //message.setFrom("wasko@wasko.pl");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}