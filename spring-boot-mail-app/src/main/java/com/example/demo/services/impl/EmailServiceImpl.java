package com.example.demo.services.impl;

import com.example.demo.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;

@Service
public class EmailServiceImpl implements EmailService {

     private JavaMailSender mailSender;


     private Logger logger= LoggerFactory.getLogger(EmailServiceImpl.class);

     public EmailServiceImpl(JavaMailSender mailSender){
         this.mailSender=mailSender;
     }

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom("anulengure5@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent 1...");

    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {

         SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

         simpleMailMessage.setTo(to);
         simpleMailMessage.setSubject(subject);
         simpleMailMessage.setText(message);
         simpleMailMessage.setFrom("anulengure5@gmail.com");

         mailSender.send(simpleMailMessage);
         logger.info("Email has been sent to "+ to.length+"...");

    }


    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) throws MessagingException {

        MimeMessage simpleMailMessage=mailSender.createMimeMessage();
      try {
          MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
          helper.setTo(to);
          helper.setFrom("anulengure5@gmail.com");
          helper.setSubject(subject);
          helper.setText(htmlContent,true);
          mailSender.send(simpleMailMessage);
          logger.info("Email has been sent...");

      }catch(MessagingException e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) throws MessagingException {

           MimeMessage simpleMailMessage=mailSender.createMimeMessage();
           try{
               MimeMessageHelper helper=new MimeMessageHelper(simpleMailMessage,true);
               helper.setTo(to);
               helper.setFrom("anulengure5@gmail.com");
               helper.setSubject(subject);
               helper.setText(message);

               FileSystemResource fileSystemResource=new FileSystemResource(file);
               helper.addAttachment(fileSystemResource.getFilename(),file);

               mailSender.send(simpleMailMessage);
               logger.info("Email with File is sent...");

           }catch(MessagingException e){
               throw new RuntimeException(e);
        }


    }


    @Override
    public void sendEmailWithFile(String to , String subject, String message, InputStream is) throws MessagingException {
        MimeMessage simpleMailMessage=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(simpleMailMessage,true);
            helper.setTo(to);
            helper.setFrom("anulengure5@gmail.com");
            helper.setSubject(subject);
            helper.setText(message,true);
            File file=new File("src/main/resources/static/test.png");
            Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);

            mailSender.send(simpleMailMessage);
            logger.info("Email with File is sent...");

        }catch(MessagingException | IOException e){
            throw new RuntimeException(e);
        }


    }

}
