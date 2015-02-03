//package pl.teamformer.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.AfterClass;
//import org.junit.Test;
//
//import pl.teamformer.model.Account;
//import pl.teamformer.model.Team;
//import static pl.teamformer.tools.MyOut.myOut;
//
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//
//public class DaoAccountTest {
//
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        private static EntityManager EM;
//        private static final String PU = "TestPU";
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @BeforeClass
//        public static void setUpClass() {
//                myOut("DaoAccount Tests #1");
//                EM = Persistence.createEntityManagerFactory(PU).createEntityManager();
//
//                System.out.println("INSERTING 4 ACCOUNT ROWS!!!");
//                EM.persist(new Account(PU, PU, PU));
//                EM.persist(new Account(PU + "2", "aaaaaaaa", "aaaa@aa.aa"));
//                EM.persist(new Account(PU + "3", "bbbbbbbb", "bbbb@bb.bb"));
//                EM.persist(new Account(PU + "4", "cccccccc", "cccc@cc.cc"));
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @AfterClass
//        public static void tearDownClass() {
//                EM.close();
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Before//each Method
//        public void setUp() {
//                EM.getTransaction().begin();
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @After//each Method
//        public void tearDown() {
//                EM.flush();
//                EM.getTransaction().commit();
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetAccounts() throws Exception {
//                myOut("getAccounts");
//                
//                final DaoAccount dao = new DaoAccount();
//                assertNull(dao.getAccounts());
//
//                List<Account> accs = new ArrayList();
//                dao.setAccounts(accs);
//
//                assertThat(dao.getAccounts(), equalTo(accs));
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetTeams() throws Exception {
//                myOut("getTeams");
//                
//                final DaoAccount dao = new DaoAccount();
//                assertNull(dao.getTeams());
//
//                List<Team> teams = new ArrayList();
//                dao.setTeams(teams);
//
//                assertThat(dao.getTeams(), equalTo(teams));
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetEntityManager() throws Exception {
//                myOut("getEntityManager");
//                
//                final DaoAccount dao = new DaoAccount();
//                assertNull(dao.getEntityManager());
//
//                dao.setEntityManager(EM);
//
//                assertThat(dao.getEntityManager(), equalTo(EM));
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testInit() throws Exception {
//                myOut("init");
//
//                final DaoAccount dao = new DaoAccount();
//                dao.setEntityManager(EM);
//                dao.init();
//
//                assertEquals(4, dao.getAccounts().size());
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetAccountByLogin() throws Exception {
//                myOut("getAccountsByLogin");
//
//                final DaoAccount dao = new DaoAccount();
//                dao.setEntityManager(EM);
//                dao.init();
//
//                assertThat(dao.getAccountByLogin(PU).getLogin(), equalTo(PU));
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testRemoveAccount() {
//                myOut("removeAccount");
//
//                final DaoAccount dao = new DaoAccount();
//                dao.setEntityManager(EM);
//                dao.init();
//
//                dao.removeAccount(dao.getAccountByLogin(PU));
//                dao.init();
//                assertEquals(3, dao.getAccounts().size());
//
//                EM.flush();
//                EM.getTransaction().commit();
//                EM.getTransaction().begin();
//
//                dao.registerAccount(PU, PU, PU, false);
//                dao.init();
//                assertEquals(4, dao.getAccounts().size());
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//}
