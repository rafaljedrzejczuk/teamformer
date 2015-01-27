package pl.teamformer.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Data;
import pl.teamformer.data.Account;
import pl.teamformer.data.Team;

@Data
@Stateless
public class DaoLogged {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private List<Account> accounts;
        private List<Team> teams;

        @PersistenceContext
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public DaoLogged() {
                readAccounts();
                readTeams();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account regsterAccount(String login, String password, String email) {
                Account a = new Account(login, password, email);
                getAccounts().add(a);
                System.out.println("Account registering..");

                getEntityManager().persist(a);
                return getEntityManager().merge(a);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public final void readAccounts() {
                System.out.println("readAccounts()");
                setAccounts((List<Account>) getEntityManager().createNamedQuery("Account.findAll").getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeAccount(Account a) {
                Account toRemove = getEntityManager().merge(a);
                System.out.println("Removing an account..");
                getEntityManager().remove(toRemove);
                accounts.remove(a);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public final void readTeams() {
                setTeams((List<Team>) getEntityManager().createNamedQuery("Team.findAll").getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account getAccountByLogin(String login) {
                return (Account) getEntityManager().createNamedQuery("Account.findByLogin").setParameter("login", login).getSingleResult();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
