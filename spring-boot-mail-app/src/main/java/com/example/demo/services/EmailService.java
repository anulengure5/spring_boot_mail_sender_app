package com.example.demo.services;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    void sendEmail(String to, String subject,String message);

    //emails to multiple people
    void sendEmail(String to[],String subject,String message);

    //send email with HTML
    void sendEmailWithHtml(String to ,String subject, String htmlContent) throws MessagingException;

    //send email with file
    void sendEmailWithFile(String to , String subject, String message, File file) throws MessagingException;

    // send email with file input stream as file path
    void sendEmailWithFile(String to, String subject, String message, InputStream is) throws MessagingException;

}
