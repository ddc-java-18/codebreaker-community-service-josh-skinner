package edu.cnm.deepdive.codebreaker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

@Configuration
@EnableWebSecurity
@Profile("service")
public class SecurityConfiguration {

  private final Converter<Jwt, ? extends AbstractAuthenticationToken> converter;
  private final String issuerUri;
  private final String clientId;

  @Autowired
  public SecurityConfiguration(Converter<Jwt, ? extends AbstractAuthenticationToken> converter,
     @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri,
      @Value("${spring.security.oauth2.resourceserver.jwt.client-id}") String clientId) {
    this.converter = converter;
    this.issuerUri = issuerUri;
    this.clientId = clientId;
  }
}
