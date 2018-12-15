package gun2.dev.glovesquest.utils;

import android.util.Base64;

import java.io.Reader;
import java.io.StringReader;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecretFile {
    public SecretFile(){

    }

    public static Key getAESKey() throws Exception {
        String iv;
        Key keySpec;

        String key = "1234567890123456";
        iv = key.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(b, 0, keyBytes, 0, len);
        keySpec = new SecretKeySpec(keyBytes, "AES");

        return keySpec;
    }

    public static Reader encodeFile(String key, byte[] fileData) throws Exception {


        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] data = cipher.doFinal(fileData);
        Reader encryptReader = new StringReader(new String(data));
        return encryptReader;
    }

    public static Reader decodeFile( byte[] fileData) throws Exception {

        Key keySpec = getAESKey();
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] byteStr = Base64.decode(fileData, 0);
        byte[] data = c.doFinal(byteStr);

        Reader decryptReader = new StringReader(new String(data));
        return decryptReader;
    }
}
