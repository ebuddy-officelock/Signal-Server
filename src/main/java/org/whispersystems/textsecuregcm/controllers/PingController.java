package org.whispersystems.textsecuregcm.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class PingController {

  public PingController()
  {
  }

  @GET
  @Path("/ping")
  @Produces(MediaType.TEXT_PLAIN)
  public String getPing() {
    return "pong";
  }

}
