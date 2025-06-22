package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

@Component
@RequiredArgsConstructor
public class PasswordKeyProvider implements KeyProvider {

    private static final String ALIAS = "password";
    private final KeyStoreService keyStoreService;

    @Override
    public PrivateKey getPrivateKey() {
        return keyStoreService.getPrivateKey(ALIAS);
    }

    @Override
    public PublicKey getPublicKey() {
        return keyStoreService.getPublicKey(ALIAS);
    }

}
