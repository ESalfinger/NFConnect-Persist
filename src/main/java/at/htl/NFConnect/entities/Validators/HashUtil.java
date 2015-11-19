package at.htl.nfconnect.entities.Validators;

/**
 * Created by Elias Salfinger on 19/11/15.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtil {

    private static MessageDigest digest;

    /**
     * Bequemlichkeitsmethode zum Hashen eines Passworts.
     *
     * @param klar Klartext
     * @return Hash des Klartext
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String klar) {
        String hash = "";
        try {
            if (digest == null) {
                digest = MessageDigest.getInstance("MD5");
            }
            digest.update(klar.getBytes());
            byte[] hashBytes = digest.digest();

            hash = new String(hashBytes);
        }
        catch (NoSuchAlgorithmException ex) {
            hash = "???";
        }
        finally {
            return hash;
        }
    }
}
