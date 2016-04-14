package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import vo.ATM;

public interface ATMService extends Remote {

	public boolean withdrawMoney(int accountNum, double sum)
			throws RemoteException;

	public double checkAccount(int accountNum) throws RemoteException;

	public boolean transferMoney(int accountFrom, int accountTo, double sum)
			throws RemoteException;

	public String printCheck(int atmId, int accountNum) throws RemoteException;

	public ATM startWork(int atmId, int cardNum, int pin)
			throws RemoteException, SQLException;

	public void endWork(int atmId) throws RemoteException, SQLException;

	public boolean exist(int cardNum) throws RemoteException;

	// ATM
	public List<ATM> getAllATMs() throws RemoteException;

	public ATM getATM(String address) throws RemoteException;

}
