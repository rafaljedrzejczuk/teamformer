package pl.teamformer.beans;

import javax.inject.Named;
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
import pl.teamformer.model.Account;
import pl.teamformer.tools.Messages;

@Data
@Named("registerBean")
@RequestScoped
public class RegisterBean {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private Account account = new Account();

        @Inject
        private DaoAccount daoAccount;

//        @Resource(name = "mail/teamformerofficial@gmail.com")
//        private Session mailSession;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public String registerAcount() {
                if (daoAccount.registerAccount(account)) {
                        //sendMessage(login, password, email);
                        Messages.redirectWithMessage("admin/home.xhtml", "Konto *" + account.getLogin() + "* zostało pomyślnie zarejstrowane!");
                        return "home";
                }
                return "";
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        private void sendMessage(Account acc) {
//                Message msg = new MimeMessage(mailSession);
//                try {
//                        msg.setSubject("TeamFormer Forum");
//                        msg.setRecipient(Message.RecipientType.TO,
//                                new InternetAddress(acc.getEmail()));
//                        msg.setText("Dear " + acc.getLogin() + ","
//                                + "\n\nGreat news!"
//                                + "\nYou've just created your own TeamFormer.com account!"
//                                + "\n\nWe're glad you've joined our community!"
//                                + "\n\nYour data:"
//                                + "\nLogin: " + acc.getLogin()
//                                + "\nPassword: " +  acc.getPassword()
//                                + "\n\nBest regards!"
//                                + "\nTeam Former Official");
//                        System.out.println("Message created!\nSending..");
//                        Transport.send(msg);
//                        System.out.println("DONE!");
//                } catch (MessagingException me) {
//                        throw new RuntimeException(me);
//                }
//        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifLoginExists() {
                return daoAccount.ifLoginExists(account);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifEmailExists() {
                return daoAccount.ifEmailExists(account);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
