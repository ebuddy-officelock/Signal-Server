package org.whispersystems.textsecuregcm.configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PubSubConfiguration {

  @JsonProperty
  @NotEmpty
  private String hostname;

  @JsonProperty
  @NotNull
  private int port;

  @JsonProperty
  private boolean ssl = false;

  @JsonProperty
  private String password;


  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

  public boolean getSsl() {
    return ssl;
  }

  public String getPassword() {
    return password;
  }

}
