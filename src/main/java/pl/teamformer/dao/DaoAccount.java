package pl.teamformer.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Data;
import pl.teamformer.model.Account;
import pl.teamformer.model.Team;
import static pl.teamformer.tools.GenerateHash.generateHash;
import pl.teamformer.tools.Messages;

@Data
@Stateless
public class DaoAccount {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PersistenceContext
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                getAccounts();
                getTeams();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Account> getAccounts() {
                return (List<Account>) getEntityManager().createNamedQuery("Account.findAll").getResultList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Team> getTeams() {
                return (List<Team>) getEntityManager().createNamedQuery("Team.findAll").getResultList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean registerAccount(Account acc, boolean messaging) {
                try {
                        if (ifLoginOrEmailExists(acc, messaging))
                                return false;

                        acc.setPassword(generateHash(acc.getPassword()));
                        Account a = new Account(acc);
                        entityManager.persist(a);

                        return true;
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                        Logger.getLogger(DaoAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeAccount(Account a) {
                Account toRemove = getEntityManager().merge(a);
                System.out.println("Removing an account..");
                getEntityManager().remove(toRemove);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account getAccountByLogin(String login) {
                for (Account a : getAccounts())
                        if (a.getLogin().equals(login))
                                return a;
                return null;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifLoginOrEmailExists(Account acc, boolean messaging) {
                boolean outcome = false;
                for (Account a : getAccounts()) {
                        if (a.getLogin().equals(acc.getLogin())) {
                                if (messaging)
                                        Messages.showMessageError("Login *" + acc.getLogin() + "* jest już zajęty!");
                                outcome = true;
                        }
                        if (a.getEmail().equals(acc.getEmail())) {
                                if (messaging)
                                        Messages.showMessageError("Na e-mail *" + acc.getEmail() + "* jest już zarejstrowane konto!");
                                outcome = true;
                        }
                }
                return outcome;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifLoginExists(Account acc) {
                for (Account a : getAccounts())
                        if (a.getLogin().equals(acc.getLogin())) {
                                Messages.showMessageError("Login *" + acc.getLogin() + "* jest już zajęty!");
                                return true;
                        }
                return false;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifEmailExists(Account acc) {
                for (Account a : getAccounts())
                        if (a.getEmail().equals(acc.getEmail())) {
                                Messages.showMessageError("Na e-mail *" + acc.getEmail() + "* jest już zarejstrowane konto!");
                                return true;
                        }
                return false;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
