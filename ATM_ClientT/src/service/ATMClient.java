package service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ATMClient {

	private Registry registry;
	private ATMService service;

	public ATMClient() throws RemoteException, NotBoundException {
		this.registry = LocateRegistry.getRegistry("localhost", 2099);
		this.service = (ATMService) registry.lookup("atm");
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public ATMService getService() {
		return service;
	}

	public void setService(ATMService service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Service [registry=" + registry + ", service=" + service + "]";
	}

}
