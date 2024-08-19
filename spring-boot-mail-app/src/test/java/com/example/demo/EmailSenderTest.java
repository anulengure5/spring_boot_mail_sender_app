package com.example.demo;

import com.example.demo.services.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        System.out.println("sending email");
        emailService.sendEmail("anulengure5@gmail.com", "Email for test", "the mail is test");
    }

  @Test
    void sendHtmlInEmail() throws MessagingException {
        String html=""+
                "<h1 style='color:red;border:1px solid black;'>Hi ! I am Anurag</h1>"+"";

        emailService.sendEmailWithHtml("rajesh.anurag22@pccoepune.org","Test Email",html);
    }

    @Test
    void sendEmailWithFile() throws MessagingException {
        emailService.sendEmailWithFile("anulengure5@gmail.com","Test Email","This is a test mail",new File("D:/Projects/Rich-Text Email Sender (SPRINGBOOT)/spring-boot-mail-app/src/main/resources/static//Anurag-image.jpg"));
    }

    @Test
    void sendEmailWithFile() throws MessagingException, FileNotFoundException, IOException {

        File file=new File("D:/Projects/Rich-Text Email Sender (SPRINGBOOT)/spring-boot-mail-app/src/main/resources/static//Anurag-image.jpg");
     try {
         InputStream is = new FileInputStream(file);
         emailService.sendEmailWithFile("anulengure5@gmail.com","Test Email","This is a test mail",is);

     }catch(FileNotFoundException e)
     {
         throw new RuntimeException(e);
     }

      }


}
