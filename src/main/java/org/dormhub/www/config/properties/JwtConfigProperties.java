package org.dormhub.www.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperties {
    private Long expirationTime = 24 * 60 * 60 * 1000L;
}