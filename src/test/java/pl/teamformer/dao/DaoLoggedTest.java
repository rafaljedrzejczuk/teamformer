///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pl.teamformer.dao;
//
//import java.util.List;
//import javax.ejb.embeddable.EJBContainer;
//import javax.persistence.EntityManager;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import pl.teamformer.data.Account;
//import pl.teamformer.data.Team;
//
///**
// *
// * @author praktyka.it
// */
//public class DaoLoggedTest {
//        
//        public DaoLoggedTest() {
//        }
//
//        @BeforeClass
//        public static void setUpClass() {
//        }
//
//        @AfterClass
//        public static void tearDownClass() {
//        }
//
//        @Before
//        public void setUp() {
//        }
//
//        @After
//        public void tearDown() {
//        }
//        /**
//         * Test of init method, of class DaoLogged.
//         */
//        @Test
//        public void testInit() throws Exception {
//                System.out.println("init");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.init();
//                container.close();
//                assertNotNull(instance);
//        }
//        /**
//         * Test of destroy method, of class DaoLogged.
//         */
//        @Test
//        public void testDestroy() throws Exception {
//                System.out.println("destroy");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                instance.destroy();
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /**
//         * Test of getAccountByLogin method, of class DaoLogged.
//         */
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
//                fail("The test case is a prototype.");
//        }
//        /**
//         * Test of readAccounts method, of class DaoLogged.
//         */
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
//        /**
//         * Test of readTeams method, of class DaoLogged.
//         */
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
//        /**
//         * Test of getAccounts method, of class DaoLogged.
//         */
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
//        /**
//         * Test of getTeams method, of class DaoLogged.
//         */
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
//        /**
//         * Test of getEntityManager method, of class DaoLogged.
//         */
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
//        /**
//         * Test of setAccounts method, of class DaoLogged.
//         */
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
//        /**
//         * Test of setTeams method, of class DaoLogged.
//         */
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
//        /**
//         * Test of setEntityManager method, of class DaoLogged.
//         */
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
//        /**
//         * Test of equals method, of class DaoLogged.
//         */
//        @Test
//        public void testEquals() throws Exception {
//                System.out.println("equals");
//                Object o = null;
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                boolean expResult = false;
//                boolean result = instance.equals(o);
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /**
//         * Test of hashCode method, of class DaoLogged.
//         */
//        @Test
//        public void testHashCode() throws Exception {
//                System.out.println("hashCode");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                int expResult = 0;
//                int result = instance.hashCode();
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//        /**
//         * Test of toString method, of class DaoLogged.
//         */
//        @Test
//        public void testToString() throws Exception {
//                System.out.println("toString");
//                EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//                DaoLogged instance = (DaoLogged) container.getContext().lookup("java:global/classes/DaoLogged");
//                String expResult = "";
//                String result = instance.toString();
//                assertEquals(expResult, result);
//                container.close();
//                // TODO review the generated test code and remove the default call to fail.
//                fail("The test case is a prototype.");
//        }
//}
