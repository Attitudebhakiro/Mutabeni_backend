package com.mutabeni.store.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("email")
  private String email;

  public AuthenticationResponse(String accessToken, String email) {
    this.accessToken = accessToken;
    this.email = email;
  }
}
