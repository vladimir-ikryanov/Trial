package com.teamdev.trial;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Vladimir Ikryanov
 */
public class EmailService {

    public static void send(String to, final String from, String cc, final String password, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        message.setContent(body, "text/html");
        message.setSubject(subject);

        Transport.send(message);
    }

    public static void main(String[] args) throws MessagingException {
        send("vladimir.ikryanov@teamdev.com", "vladimir.ikryanov@gmail.com", "", "", "Welcome to JxBrowser Evaluation!", "<p>Hi {firstName},</p>" +
                "<p>My name is Vladimir. I'm a developer in JxBrowser Team.</p>" +
                "<p>I'm just checking in to make sure that configuring your project with JxBrowser library went smoothly.</p>" +
                "<p>If you need any help getting started or in case of any questions related to usage of JxBrowser in your application, just let me know. I will be happy to help.</p>" +
                "<p>I hope you will enjoy using JxBrowser!</p><p>All the best,</p><table cellpadding='0' border='0'>" +
                "<tbody><tr><th><a href='https://plus.google.com/u/0/113918825515210809679/posts' style='text-decoration:none;'>" +
                "<img alt='Vladimir Ikryanov' src='http://www.teamdev.com/img/evaluate-email/vladimir-ikryanov.jpg' width='60'/>" +
                "</a></th><td width='10'>&nbsp;</td><td style='line-height:1.4;'>" +
                "<a href='https://plus.google.com/u/0/113918825515210809679/posts' style='text-decoration:none;color:#000;'>" +
                "<strong>Vladimir Ikryanov</strong><br>" +
                "<span style='color:#82898B;'>JxBrowser Team</span><br>" +
                "<span style='color:#82898B;'>TeamDev Ltd.</span>" +
                "</a></td></tr></tbody></table>");
    }
}
