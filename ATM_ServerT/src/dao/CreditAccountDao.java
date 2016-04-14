package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import vo.CreditAccount;


public interface CreditAccountDao extends Remote {

	public List<CreditAccount> getAllCreditAccounts() throws RemoteException;

	public CreditAccount getCreditAccount(int accId) throws RemoteException;

	public CreditAccount getCreditAccountCard(int cardNum)
			throws RemoteException;

	public void addCreditAccount(CreditAccount account) throws RemoteException;

	public void updateCreditAccount(CreditAccount account) throws RemoteException;

	public void deleteCreditAccount(int accId) throws RemoteException;
}
