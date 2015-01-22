package pl.teamformer.dao;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Data;
import pl.teamformer.data.Account;
import pl.teamformer.tools.MailTo;
import pl.teamformer.tools.Messages;

@Data
@Stateless
public class DaoRegister {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private List<Account> accounts;

        @PersistenceContext
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                System.out.println("Inits DAO");
                readAccounts();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PreDestroy
        public void destroy() {
                System.out.println("Destroys DAO");
                System.out.println();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void readAccounts() {
                setAccounts((List<Account>) getEntityManager().createNamedQuery("Account.findAll").getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addAccount(String login, String password, String email) {
                boolean error = false;
                for (Account a : accounts) {
                        if (a.getLogin().equals(login)) {
                                Messages.showMessageError("Login *" + login + "* jest już zajęty!");
                                error = true;
                        }
                        if (a.getEmail().equals(email)) {
                                Messages.showMessageError("Na e-mail *" + email + "* jest już zarejstrowane konto!");
                                error = true;
                        }
                }
                if (error)
                        return;
                Account a = new Account(login, password, email);
                accounts.add(a);
                entityManager.persist(a);

                System.out.println("Wysylanie emaila jest nieaktywne!!! -> DaoRegister.addAccount()");
                //MailTo.sendMail(login, email, password);
                Messages.redirectWithMessage("admin/home.xhtml", "Konto *" + login + "* zostało pomyślnie zarejstrowane!");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
