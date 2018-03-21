package edu.toronto.csc207.restaurantsolution.remoting.server;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Binds remote objects to an RMI socket.
 * This class should only be instantiated once in any given JVM; otherwise,
 * socket binding conflicts will occur.
 */
public class RemoteObjectBinder {
  /** The RMI registry. */
  private Registry registry;
  private int port;

  /**
   * Constructs a new remote object binder on the local host.
   *
   * @param port the port to which the socket for the remote object will be
   *             bound.
   */
  public RemoteObjectBinder(int port) {
    try {
      this.port = port;
      registry = LocateRegistry.createRegistry(port);
    } catch (RemoteException e) {

    }
  }

  public void bind(String name, Remote object) {
    try {
      Remote stub = UnicastRemoteObject.exportObject(object, port);
      registry.rebind(name, stub);
    } catch (RemoteException e) {

    }
  }

  public boolean unbind(String name) {
    try {
      registry.unbind(name);
      return true;
    } catch (RemoteException | NotBoundException e) {
      return false;
    }
  }
}
