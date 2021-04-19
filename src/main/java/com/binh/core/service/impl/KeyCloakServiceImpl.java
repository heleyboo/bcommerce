package com.binh.core.service.impl;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;

import com.binh.core.service.KeyCloakService;

@Service
public class KeyCloakServiceImpl implements KeyCloakService {
	public String getAuthUsername(KeycloakAuthenticationToken authentication) {
		return authentication.getPrincipal().toString();
	}

	public AccessToken getAuthAccessToken(KeycloakAuthenticationToken authentication) {
		SimpleKeycloakAccount account = (SimpleKeycloakAccount) authentication.getDetails();
		return account.getKeycloakSecurityContext().getToken();
	}
}
