package org.dormhub.www.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "key-store")
public class KeyStoreConfigurationProperties {

    private String path;
    private String password;
    private Map<String, String> entries;

}