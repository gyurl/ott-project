package com.ott.project.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class EncryptionConfig {
    
    @Value("${encryption.key}")
    private String encryptionKey;
    
    @Value("${encryption.salt}")
    private String salt;

    @PostConstruct
    public void init() {
        System.out.println("Encryption Key: " + encryptionKey);
        System.out.println("Salt: " + salt);
    }

    @Bean
    public StringEncryptor stringEncryptor() {
        if (encryptionKey == null || salt == null) {
            throw new IllegalStateException("Encryption key and salt must not be null");
        }
        return new StringEncryptor(encryptionKey, salt);
    }
}