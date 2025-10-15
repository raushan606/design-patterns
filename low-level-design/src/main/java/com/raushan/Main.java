package com.raushan;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Main {
   public void sendEmail() {
        // Set your email server properties (e.g., SMTP host, port, credentials)
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587"); // Use the appropriate port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.office365.com");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("support.bot@c2b2.co.uk", "Fuh91325");
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("support.bot@c2b2.co.uk"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("raushankumar606@gmail.com"));
            message.setSubject("Subject of your email");

            // Create a MimeMultipart object to hold the HTML content
            MimeMultipart multipart = new MimeMultipart();

            // Create the HTML part
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<!-- Your HTML template here -->";
            htmlPart.setContent(htmlContent, "text/html");
            multipart.addBodyPart(htmlPart);

            // Set the content of the message to the HTML content
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main sender = new Main();
        sender.sendEmail();
    }
}