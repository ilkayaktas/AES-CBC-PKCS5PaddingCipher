import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by ilkayaktas on 18.09.2020 at 09:36.
 */

public class App {
    public static void main(String[] args) {
        String textToEncrypt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String secretKey = "?349ns9sva2#ls!a";
        String initializationVector = "5alnkca8sdf.^asd";


        String decryptedText = encryptAESCBCPKCS5Padding(textToEncrypt, secretKey, initializationVector);

        System.out.println(decryptedText);


        String encryptedText = decryptAESCBCPKCS5Padding(decryptedText, secretKey, initializationVector);

        System.out.println(encryptedText);

        System.out.println("is equal = "+textToEncrypt.equals(encryptedText));

    }

    public static String encryptAESCBCPKCS5Padding(String textToEncrypt, String secretKey, String initializationVector){
        try {
            if (secretKey == null) {
                return null;
            }

            // initialization vector
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector.getBytes());

            // secret key
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

            // Cipher type initialization
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

            // Decrypt
            byte[] ret = cipher.doFinal(textToEncrypt.getBytes());

            // Encode to Base64
            ret = Base64.getEncoder().encode(ret);

            return new String(ret, "utf-8");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }


    public static String decryptAESCBCPKCS5Padding(String textToDecrypt, String secretKey, String initializationVector)  {
        try {
            if (secretKey == null) {
                return null;
            }

            // Decode Base64
            byte[] decode = Base64.getDecoder().decode(textToDecrypt);

            // initialization vector
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector.getBytes());

            // secret key
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

            // Cipher type initialization
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);

            // Decrypt
            byte[] ret = cipher.doFinal(decode);


            return new String(ret, "utf-8");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
