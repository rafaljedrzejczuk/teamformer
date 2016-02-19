package pl.teamformer.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import pl.teamformer.model.Account;
import pl.teamformer.producers.DataRepository;
import pl.teamformer.tools.Messages;

@Stateless
public class DaoAccount {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Inject
        @DataRepository
        private EntityManager em;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Account> getAccounts() {
                return (List<Account>) em.createQuery("SELECT a FROM Account a")
                        .getResultList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account update(Account a) {
                return em.merge(a);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean registerAccount(Account acc) {
                if (ifLoginExists(acc) || ifEmailExists(acc))
                        return false;
                em.persist(acc);
                return true;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeAccount(Account a) {
                em.remove(em.merge(a));
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account getAccountByLogin(String login) {
                try {
                        return (Account) em.createQuery("SELECT a FROM Account a WHERE a.login = :login")
                                .setParameter("login", login)
                                .getSingleResult();
                } catch (NoResultException e) {
                        return null;
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifLoginExists(Account acc) {
                if (getAccountByLogin(acc.getLogin()) != null) {
                        Messages.showMessageError("Login *" + acc.getLogin() + "* jest już zajęty!");
                        return true;
                }
                return false;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public boolean ifEmailExists(Account acc) {
                try {
                        em.createQuery("SELECT a FROM Account a WHERE a.email = :email")
                                .setParameter("email", acc.getEmail())
                                .getSingleResult();
                        return true;
                } catch (NoResultException e) {
                        return false;
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
