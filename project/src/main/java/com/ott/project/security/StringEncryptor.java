package com.ott.project.security;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Converter
@Component
public class StringEncryptor implements AttributeConverter<String, String> {
    
    private final String encryptionKey;
    private final String salt;
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_LENGTH = 16; // AES block size
    
    // 기본 생성자 추가
    public StringEncryptor() {
        this.encryptionKey = ""; // 임시값
        this.salt = "";         // 임시값
    }
    
    // 기존 생성자는 유지
    public StringEncryptor(String encryptionKey, String salt) {
        this.encryptionKey = encryptionKey;
        this.salt = salt;
    }
    
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            
            // Generate random IV
            byte[] iv = new byte[IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encryptedBytes = cipher.doFinal(attribute.getBytes(StandardCharsets.UTF_8));
            
            // Combine IV and encrypted data
            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);
            
            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting string", e);
        }
    }
    
    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            
            // Decode combined IV and encrypted data
            byte[] combined = Base64.getDecoder().decode(dbData);
            
            // Extract IV
            byte[] iv = Arrays.copyOfRange(combined, 0, IV_LENGTH);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            // Extract encrypted data
            byte[] encryptedData = Arrays.copyOfRange(combined, IV_LENGTH, combined.length);
            
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedData);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting string", e);
        }
    }
    
    private Key generateKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(
            encryptionKey.toCharArray(),
            Base64.getDecoder().decode(salt), // salt를 Base64 디코딩
            65536,  // 반복 횟수
            256    // 키 길이
        );
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
}