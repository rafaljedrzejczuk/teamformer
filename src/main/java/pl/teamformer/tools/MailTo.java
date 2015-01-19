package pl.teamformer.tools;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTo {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public static void sendMail(final String login, final String email, final String password) {
                final String owner = "teamformerofficial@gmail.com";
                final String passwordOwner = "662133754";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(owner, passwordOwner);
                                }
                        });
                try {

                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(owner));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));
                        message.setSubject("Team Former Official Welcomes,");
                        message.setText("Dear " + login + ","
                                + "\n\nGreate news!"
                                + "\nYou've just created your own TeamFormer.com account!"
                                + "\n\nWe're glad you've joined our community!"
                                + "\n\nYour data:"
                                + "\nLogin: " + login
                                + "\nPassword: " + password
                                + "\n\nBest regards!"
                                + "\nTeam Former Official");

                        Transport.send(message);

                        System.out.println("Done");

                } catch (MessagingException e) {
                        throw new RuntimeException(e);
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
