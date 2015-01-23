//package pl.teamformer.dao;
//
//import java.util.List;
//import javax.ejb.embeddable.EJBContainer;
//import javax.persistence.EntityManager;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import pl.teamformer.data.Account;
//import pl.teamformer.data.Team;
//
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//
///**
// *
// * @author praktyka.it
// */
//public class DaoLoggedTest {
//
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        public DaoLoggedTest() {
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @BeforeClass
//        public static void setUpClass() {
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @AfterClass
//        public static void tearDownClass() {
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Before
//        public void setUp() {
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @After
//        public void tearDown() {
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetAccountByLogin() throws Exception {
//                System.out.println("getAccountByLogin");
//                String login = "";
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                Account expResult = null;
//                Account result = instance.getAccountByLogin(login);
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                anything(login);
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testReadAccounts() throws Exception {
//                System.out.println("readAccounts");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.readAccounts();
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testReadTeams() throws Exception {
//                System.out.println("readTeams");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.readTeams();
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetAccounts() throws Exception {
//                System.out.println("getAccounts");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                List<Account> expResult = null;
//                List<Account> result = instance.getAccounts();
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetTeams() throws Exception {
//                System.out.println("getTeams");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                List<Team> expResult = null;
//                List<Team> result = instance.getTeams();
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testGetEntityManager() throws Exception {
//                System.out.println("getEntityManager");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                EntityManager expResult = null;
//                EntityManager result = instance.getEntityManager();
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testSetAccounts() throws Exception {
//                System.out.println("setAccounts");
//                List<Account> accounts = null;
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.setAccounts(accounts);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testSetTeams() throws Exception {
//                System.out.println("setTeams");
//                List<Team> teams = null;
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.setTeams(teams);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        @Test
//        public void testSetEntityManager() throws Exception {
//                System.out.println("setEntityManager");
//                EntityManager entityManager = null;
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.setEntityManager(entityManager);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//}
