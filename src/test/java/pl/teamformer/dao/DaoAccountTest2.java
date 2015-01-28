/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.teamformer.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rafal.Jedrzejczuk1
 */
public class DaoAccountTest2 {

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
                myOut("DaoAccount Tests #2");
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
        @Test//(expected = ConstraintViolationException.class)
        public void testRegisterAccount() {
                myOut("registerAccount");

                final DaoAccount dao = new DaoAccount();
                dao.setEntityManager(EM);
                dao.init();

                dao.registerAccount(PU, PU, PU, false);
                dao.registerAccount(PU + "2", "aaaaaaaa", "aaaa@aa.aa", false);
                dao.registerAccount(PU + "3", "bbbbbbbb", "bbbb@bb.bb", false);
                dao.registerAccount(PU + "4", "cccccccc", "cccc@cc.cc", false);

                dao.init();
                assertEquals(4, dao.getAccounts().size());

//                EM.flush();
//                EM.getTransaction().commit();
//                EM.getTransaction().begin();
//                //Should throw the error
//                dao.regsterAccount(PU, PU, PU);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
