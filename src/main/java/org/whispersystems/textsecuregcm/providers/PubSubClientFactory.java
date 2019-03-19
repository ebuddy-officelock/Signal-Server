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
import org.whispersystems.dispatch.io.RedisPubSubConnectionFactory;
import org.whispersystems.dispatch.redis.PubSubConnection;
import org.whispersystems.textsecuregcm.configuration.PubSubConfiguration;
import org.whispersystems.textsecuregcm.util.Util;

import java.io.IOException;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class PubSubClientFactory implements RedisPubSubConnectionFactory {

  private final Logger logger = LoggerFactory.getLogger(PubSubClientFactory.class);

  private final PubSubConfiguration configuration;

  public PubSubClientFactory(PubSubConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public PubSubConnection connect() {
    while (true) {
      try {
        if (configuration.getSsl()) {
          SocketFactory sslFactory = SSLSocketFactory.getDefault();
          Socket socket = sslFactory.createSocket(configuration.getHostname(), configuration.getPort());
          return new PubSubConnection(socket, configuration.getPassword());
        } else {
          Socket socket = new Socket(configuration.getHostname(), configuration.getPort());
          return new PubSubConnection(socket, configuration.getPassword());
        }

      } catch (IOException e) {
        logger.warn("Error connecting", e);
        Util.sleep(200);
      }
    }
  }
}
