/**
 * Copyright (C) 2013 Open WhisperSystems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.whispersystems.textsecuregcm.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whispersystems.textsecuregcm.redis.ReplicatedJedisPool;
import org.whispersystems.textsecuregcm.util.Util;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisClientFactory {

  private final ReplicatedJedisPool jedisPool;

  public RedisClientFactory(String url, List<String> replicaUrls) throws URISyntaxException {
    // JedisPoolConfig poolConfig = new JedisPoolConfig();
    // poolConfig.setTestOnBorrow(true);

    JedisPool       masterPool   = new JedisPool(url); // FIXME: poolConfig not used
    List<JedisPool> replicaPools = new LinkedList<>();

    for (String replicaUrl : replicaUrls) {
      replicaPools.add(new JedisPool(replicaUrl));
    }

    this.jedisPool = new ReplicatedJedisPool(masterPool, replicaPools);
  }

  public ReplicatedJedisPool getRedisClientPool() {
    return jedisPool;
  }
}
