package com.sanger.springular.utils.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String toEmail, String subject, String message) {

        var mail = new SimpleMailMessage();

        mail.setTo(toEmail);
        mail.setSubject(subject);
        mail.setText(message);

        mail.setFrom("seba_springular@gmail.com");

        javaMailSender.send(mail);

    }

}
