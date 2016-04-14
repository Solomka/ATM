package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import vo.DebitAccount;

public interface DebitAccountDao extends Remote {

	public List<DebitAccount> getAllDebitAccounts() throws RemoteException;

	public DebitAccount getDebitAccount(int accId) throws RemoteException;

	public DebitAccount getDebitAccountCard(int cardNum)
			throws RemoteException;

	public void addDebitAccount(DebitAccount account) throws RemoteException;

	public void updateDebitAccount(DebitAccount account) throws RemoteException;

	public void deleteDebitAccount(int accId) throws RemoteException;

}
