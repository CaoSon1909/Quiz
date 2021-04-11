/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.utils;

import com.google.common.hash.Hashing;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ACER
 */
public class HashHelpers implements Serializable {

    public static String hashingPassword(String password) {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8)
                .toString();
        return hashedPassword;
    }

}
