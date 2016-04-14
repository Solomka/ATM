package service;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ATMServer {
	public static void main(String[] args) throws Exception {
		System.out.print("Starting registry...");
		final Registry registry = LocateRegistry.createRegistry(2099);
		System.out.println(" OK");

		final ATMServiceImpl service = new ATMServiceImpl();
		Remote stub = UnicastRemoteObject.exportObject(service, 0);

		System.out.print("Binding service...");
		registry.bind("atm", stub);
		System.out.println(" OK");

		while (true) {
			Thread.sleep(Integer.MAX_VALUE);
		}
	}

}
