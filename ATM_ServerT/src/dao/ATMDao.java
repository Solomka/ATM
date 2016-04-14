package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import vo.ATM;

public interface ATMDao extends Remote {

	public List<ATM> getAllATMs() throws RemoteException;
	
	public ATM getATM(int atmId) throws RemoteException;
	
	public ATM getATM(String address) throws RemoteException;

	public void addATM(ATM atm) throws RemoteException;

	public void updateATM(ATM atm) throws RemoteException, SQLException;

	public void deleteATM(int atm) throws RemoteException;

}
