package org.dormhub.www.service;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyProvider {

    PrivateKey getPrivateKey();

    PublicKey getPublicKey();

}