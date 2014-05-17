
package py.com.academia.face;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;

@Named
@ApplicationScoped
public class Codifica 
{
    
    

    public static String md5(String valor) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    
}
