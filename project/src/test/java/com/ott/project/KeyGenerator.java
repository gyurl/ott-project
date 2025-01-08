package com.ott.project;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        // 32바이트(256비트) 키 생성
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32];
        byte[] salt = new byte[16];
        
        secureRandom.nextBytes(key);
        secureRandom.nextBytes(salt);
        
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        
        System.out.println("Generated Encryption Key: " + encodedKey);
        System.out.println("Generated Salt: " + encodedSalt);
    }
}