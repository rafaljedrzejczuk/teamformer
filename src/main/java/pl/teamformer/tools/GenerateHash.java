package pl.teamformer.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class GenerateHash {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private GenerateHash() {
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public static String generateHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
                byte[] digest = md.digest();
                BigInteger bigInt = new BigInteger(1, digest);
                String output = bigInt.toString(16);

                return output;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
