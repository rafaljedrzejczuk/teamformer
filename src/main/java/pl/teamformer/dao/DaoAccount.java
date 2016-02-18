package pl.teamformer.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.teamformer.model.Account;
import static pl.teamformer.tools.GenerateHash.generateHash;
import pl.teamformer.tools.Messages;

@Stateless
public class DaoAccount {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PersistenceContext
        private EntityManager em;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Account> getAccounts() {
                return (List<Account>) em.createQuery("SELECT a FROM Account a")
                        .getResultList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean registerAccount(Account acc, boolean messaging) {
                if (ifLoginOrEmailExists(acc, messaging))
                        return false;
                try {
                        acc.setPassword(generateHash(acc.getPassword()));
                        em.persist(acc);
                        return true;
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                        Logger.getLogger(DaoAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeAccount(Account a) {
                System.out.println("Removing an account..");
                em.remove(em.merge(a));
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
                for (Account a : getAccounts()) {
                        if (a.getLogin().equals(acc.getLogin())) {
                                if (messaging)
                                        Messages.showMessageError("Login *" + acc.getLogin() + "* jest już zajęty!");
                                return true;
                        }
                        if (a.getEmail().equals(acc.getEmail())) {
                                if (messaging)
                                        Messages.showMessageError("Na e-mail *" + acc.getEmail() + "* jest już zarejstrowane konto!");
                                return true;
                        }
                }
                return false;
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
