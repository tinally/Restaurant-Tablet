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
public final class RemoteObjectBinder {
  /** The RMI registry. */
  private Registry registry;
  private int port;

  /**
   * Constructs a new remote object binder on the local host.
   *
   * @param port the port to which the socket for the remote object will be
   *             bound.
   */
  public RemoteObjectBinder(int port) throws RemoteException {
    this.port = port;
    registry = LocateRegistry.createRegistry(port);
  }

  /**
   * Binds a remote object with a readable name on the local host.
   *
   * @param name the name for RMI lookup of the bound object.
   * @param object the remote object to be bound.
   */
  public void bind(String name, Remote object) throws RemoteException {
    Remote stub = UnicastRemoteObject.exportObject(object, port);
    registry.rebind(name, stub);
  }

  /**
   * Unbinds an object by name from the local host.
   *
   * @param name the name of the object to be released.
   */
  public void unbind(String name) throws RemoteException, NotBoundException {
      registry.unbind(name);
  }
}
