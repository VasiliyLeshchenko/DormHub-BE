package org.dormhub.www.service;

import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.config.properties.KeyStoreConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;

@Slf4j
@Service
public class KeyStoreService {
    private KeyStore keyStore;
    private KeyStoreConfigurationProperties keyStoreConfigurationProperties;

    public KeyStoreService(KeyStoreConfigurationProperties keyStoreConfigurationProperties) throws Exception {
        this.keyStoreConfigurationProperties = keyStoreConfigurationProperties;

        keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream keyStoreData = new FileInputStream(this.keyStoreConfigurationProperties.getPath())) {
            keyStore.load(keyStoreData, this.keyStoreConfigurationProperties.getPassword().toCharArray());
        }
    }

    public PrivateKey getPrivateKey(String alias) {
        try {
            return (PrivateKey) keyStore.getKey(alias, keyStoreConfigurationProperties.getEntries().get(alias).toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getPublicKey(String alias) {
        try {
            return keyStore.getCertificate(alias).getPublicKey();
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

}