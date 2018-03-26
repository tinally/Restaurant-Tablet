package edu.toronto.csc207.restaurantsolution.remoting.server;

/**
 * Contains server information so that clients and server built with the same version of the
 * distributed RMI application use the same RMI registry name and TCP port.
 */
public final class ServerInfo {
  public static final String name = "Master";
  public static final int port = 1099;
}
