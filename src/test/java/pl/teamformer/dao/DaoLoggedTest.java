package pl.teamformer.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import pl.teamformer.data.Account;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import pl.teamformer.data.Team;

public class DaoLoggedTest {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private static EntityManager EM;
        private static final String PU = "TestPU";
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private static void myOut(String message) {
                String top = "";
                final int count = 80;
                for (int i = 0; i < count; i++) {
                        top += '#';
                        if (i % 4 == 0)
                                message = " " + message;
                }
                System.out.println("\033[31m" + top + "\n" + message + "  \033[0m");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @BeforeClass
        public static void setUpClass() {
                myOut("DaoLogged Tests");
                EM = Persistence.createEntityManagerFactory(PU).createEntityManager();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @AfterClass
        public static void tearDownClass() {
                EM.close();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Before//each Method
        public void setUp() {
                EM.getTransaction().begin();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @After//each Method
        public void tearDown() {
                EM.flush();
                EM.getTransaction().commit();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testGetAccounts() throws Exception {
                final DaoLogged dao = new DaoLogged();
                assertNull(dao.getAccounts());

                List<Account> accs = new ArrayList();
                dao.setAccounts(accs);

                assertThat(dao.getAccounts(), equalTo(accs));
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testGetTeams() throws Exception {
                final DaoLogged dao = new DaoLogged();
                assertNull(dao.getTeams());

                List<Team> teams = new ArrayList();
                dao.setTeams(teams);

                assertThat(dao.getTeams(), equalTo(teams));
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testGetEntityManager() throws Exception {
                final DaoLogged dao = new DaoLogged();
                assertNull(dao.getEntityManager());

                dao.setEntityManager(EM);

                assertThat(dao.getEntityManager(), equalTo(EM));
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test//(expected = ConstraintViolationException.class)
        public void testRegisterAccount() {
                myOut("registerAccount");

                final DaoLogged dao = new DaoLogged();
                dao.setEntityManager(EM);
                dao.readAccounts();

                dao.regsterAccount(PU, PU, PU);
                dao.regsterAccount(PU + "2", "aaaaaaaa", "aaaa@aa.aa");
                dao.regsterAccount(PU + "3", "bbbbbbbb", "bbbb@bb.bb");
                dao.regsterAccount(PU + "4", "cccccccc", "cccc@cc.cc");

                //Should throw the error
//                dao.regsterAccount(PU, PU, PU);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testReadAccounts() throws Exception {
                myOut("readAccounts");

                final DaoLogged dao = new DaoLogged();
                dao.setEntityManager(EM);
                dao.readAccounts();

                assertEquals(dao.getAccounts().size(), 4);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testGetAccountByLogin() throws Exception {
                myOut("getAccountsByLogin");

                final DaoLogged dao = new DaoLogged();
                dao.setEntityManager(EM);
                dao.readAccounts();

                assertThat(dao.getAccountByLogin(PU).getLogin(), equalTo(PU));
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testRemoveAccount() {
                myOut("removeAccount");

                final DaoLogged dao = new DaoLogged();
                dao.setEntityManager(EM);
                dao.readAccounts();

                dao.removeAccount(dao.getAccountByLogin(PU));
                dao.readAccounts();
                assertEquals(dao.getAccounts().size(), 3);

                EM.flush();
                EM.getTransaction().commit();
                EM.getTransaction().begin();

                dao.regsterAccount(PU, PU, PU);
                dao.readAccounts();
                assertEquals(dao.getAccounts().size(), 4);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Test
        public void testReadTeams() throws Exception {
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
