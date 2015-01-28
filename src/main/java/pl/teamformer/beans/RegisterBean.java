package pl.teamformer.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.Data;
import pl.teamformer.dao.DaoAccount;
import pl.teamformer.tools.Messages;

@Data
@Named("registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private String password;
        private String email;
        private String login;

        @Inject
        private DaoAccount dao;

        @Resource(name = "mail/teamformerofficial@gmail.com")
        private Session mailSession;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                System.out.println("Inits REGISTER BEAN");
                dao.getAccounts();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void registerAcount() throws NoSuchAlgorithmException, UnsupportedEncodingException {
                if (dao.registerAccount(login, password, email, true)) {
                        sendMessage(login, password, email);
                        Messages.redirectWithMessage("admin/home.xhtml", "Konto *" + login + "* zostało pomyślnie zarejstrowane!");
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void sendMessage(String login, String password, String email) {
                Message msg = new MimeMessage(mailSession);
                try {
                        msg.setSubject("TeamFormer Forum");
                        msg.setRecipient(Message.RecipientType.TO,
                                new InternetAddress(email));
                        msg.setText("Dear " + login + ","
                                + "\n\nGreat news!"
                                + "\nYou've just created your own TeamFormer.com account!"
                                + "\n\nWe're glad you've joined our community!"
                                + "\n\nYour data:"
                                + "\nLogin: " + login
                                + "\nPassword: " + password
                                + "\n\nBest regards!"
                                + "\nTeam Former Official");
                        System.out.println("Message created!\nSending..");
                        Transport.send(msg);
                        System.out.println("DONE!");
                } catch (MessagingException me) {
                        throw new RuntimeException(me);
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
