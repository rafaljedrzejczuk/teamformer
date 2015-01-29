package pl.teamformer.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Data;
import pl.teamformer.data.Account;
import pl.teamformer.data.Team;
import static pl.teamformer.tools.GenerateHash.generateHash;
import pl.teamformer.tools.Messages;

@Data
@Stateless
public class DaoAccount {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private List<Account> accounts;
        private List<Team> teams;

        @PersistenceContext
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                readAccounts();
                readTeams();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void readAccounts() {
                setAccounts((List<Account>) getEntityManager().createNamedQuery("Account.findAll").getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void readTeams() {
                setTeams((List<Team>) getEntityManager().createNamedQuery("Team.findAll").getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean registerAccount(String login, String password, String email, boolean messaging) {
                try {
                        if (ifLoginOrEmailExists(login, email, messaging))
                                return false;

                        Account a = new Account(login, generateHash(password), email);
                        accounts.add(a);
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
                accounts.remove(a);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account getAccountByLogin(String login) {
                for (Account a : accounts)
                        if (a.getLogin().equals(login))
                                return a;
                return null;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifLoginOrEmailExists(String login, String email, boolean messaging) {
                boolean outcome = false;
                for (Account a : accounts) {
                        if (a.getLogin().equals(login)) {
                                if (messaging)
                                        Messages.showMessageError("Login *" + login + "* jest już zajęty!");
                                outcome = true;
                        }
                        if (a.getEmail().equals(email)) {
                                if (messaging)
                                        Messages.showMessageError("Na e-mail *" + email + "* jest już zarejstrowane konto!");
                                outcome = true;
                        }
                }
                return outcome;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
