package org.whispersystems.textsecuregcm.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MessageCacheConfiguration {

  @JsonProperty
  @NotNull
  @Valid
  private RedisConfiguration redis;

  @JsonProperty
  @NotNull
  @Valid
  private PubSubConfiguration pubSub;

  @JsonProperty
  private int persistDelayMinutes = 10;

  public RedisConfiguration getRedisConfiguration() {
    return redis;
  }

  public PubSubConfiguration getPubSubConfiguration() {
    return pubSub;
  }

  public int getPersistDelayMinutes() {
    return persistDelayMinutes;
  }

}
