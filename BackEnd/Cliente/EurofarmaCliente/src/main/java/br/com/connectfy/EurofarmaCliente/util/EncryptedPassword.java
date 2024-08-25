package br.com.connectfy.EurofarmaCliente.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncryptedPassword {


    @Value("${secretOfEncoder}")
    private static String secret = "eurofarma";

    private static final Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(secret, 16, 310000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
    private static final DelegatingPasswordEncoder passwordEncoder;

    static  {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
    }

    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static Boolean matchesPassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}