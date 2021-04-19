package com.binh.core.service;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

public interface KeyCloakService {
	public String getAuthUsername(KeycloakAuthenticationToken authentication);
	public AccessToken getAuthAccessToken(KeycloakAuthenticationToken authentication);

}
