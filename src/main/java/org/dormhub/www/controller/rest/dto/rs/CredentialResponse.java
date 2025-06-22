package org.dormhub.www.controller.rest.dto.rs;

import java.util.UUID;

public record CredentialResponse(
       UUID id,
       String login,
       String password
) { }
