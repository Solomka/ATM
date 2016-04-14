package view;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartATM {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		new ChooseATMFrame();
	}
}