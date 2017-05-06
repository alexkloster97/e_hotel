/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.MD5;

import java.security.MessageDigest;

/**
 * This class is used to  organize the security
 * @author Maria
 */
public class HashingMD5 {

    /**
     * Use to secuirity
     * @param password
     * @return hashing password
     * @throws Exception
     */
    public static String hashing(String password) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
}
