package service;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vo.ATM;
import vo.CreditAccount;
import vo.DebitAccount;
import dao.ATMDaoImpl;
import dao.CreditAccountDaoImpl;
import dao.DebitAccountDaoImpl;

public class ATMServiceImpl implements ATMService {
	public DebitAccountDaoImpl debDao;
	public CreditAccountDaoImpl credDao;
	public ATMDaoImpl atmDao;

	@Override
	public boolean withdrawMoney(int accountNum, double sum)
			throws RemoteException {
		if (isDebit(accountNum)) {

			return withdrawMoneyDebit(accountNum, sum);
		} else if (isCredit(accountNum)) {

			return withdrawMoneyCredit(accountNum, sum);
		} else {
			System.out.println("Wrong CARD NUMBER!");
			return false;
		}
	}

	@Override
	public double checkAccount(int accountNum) throws RemoteException {
		if (isDebit(accountNum)) {

			return checkDebitAccount(accountNum);
		} else if (isCredit(accountNum)) {

			return checkCreditAccount(accountNum);
		} else {
			System.out.println("Wrong CARD NUMBER!");
			return -1;
		}
	}

	@Override
	public boolean transferMoney(int accountFrom, int cardNumber, double sum)
			throws RemoteException {
		// cardNum->accountNum
		int accountTo = 0;
		debDao = new DebitAccountDaoImpl();
		credDao = new CreditAccountDaoImpl();
		if (debDao.getDebitAccountCard(cardNumber) != null) {
			accountTo = debDao.getDebitAccountCard(cardNumber)
					.getAccountNumber();
		} else if (credDao.getCreditAccountCard(cardNumber) != null) {
			accountTo = credDao.getCreditAccountCard(cardNumber)
					.getAccountNumber();
		} else {
			return false;
		}
		if (isDebit(accountTo)) {

			if (withdrawMoney(accountFrom, sum)) {
				putMoneyDebit(accountTo, sum);
				System.out
						.println("OPeration transferMoney toDebit was successfully executed!");
				return true;
			}
			System.out
					.println("OPeration transferMoney toDebit was UNsuccessfully executed!");
			return false;
		} else if (isCredit(accountTo)) {
			if (withdrawMoney(accountFrom, sum)) {
				putMoneyCredit(accountTo, sum);
				System.out
						.println("OPeration transferMoney toCredit was successfully executed!");
				return true;
			}
			System.out
					.println("OPeration transferMoney toCredit was UNsuccessfully executed!");
			return false;

		} else {
			System.out.println("Wrong CARD NUMBER!");
			return false;
		}

	}

	@Override
	public String printCheck(int atmId, int accountNum) throws RemoteException {

		atmDao = new ATMDaoImpl();
		ATM atm = atmDao.getATM(atmId);

		java.util.Date currentDate = new Date();
		java.sql.Timestamp date = new java.sql.Timestamp(currentDate.getTime());

		String currDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(date);
		// System.out.println("Timestamp date: "+date.toString());
		// System.out.println("check is successfully printed!");

		double accountBalance = checkAccount(accountNum);
		return "Date: " + currDate /* date.toString() */+ '\n'
				+ "ATM Address: " + atm.getAddress() + '\n'
				+ "Account balance: " + accountBalance;
	}

	private boolean withdrawMoneyDebit(int debitNum, double sum)
			throws RemoteException {
		debDao = new DebitAccountDaoImpl();
		DebitAccount debit = debDao.getDebitAccount(debitNum);
		double sumAvailable = debit.getSumAvailable();
		if (sumAvailable >= sum) {
			debit.setSumAvailable(sumAvailable -= sum);
			debDao.updateDebitAccount(debit);
			System.out
					.println("OPeration withdrawMoneyDebit was successfully executed!");
			return true;
		} else {
			System.out
					.println("OPeration withdrawMoneyDebit was UNsuccessfully executed!");
			return false;

		}

	}

	private boolean withdrawMoneyCredit(int creditNum, double sum)
			throws RemoteException {
		credDao = new CreditAccountDaoImpl();
		CreditAccount credit = credDao.getCreditAccount(creditNum);
		double maxAvailable = credit.getMaxAvailableSum();
		double borrowedMoney = credit.getBorrowedMoney();
		double ownMoney = credit.getOwnMoney();

		if (ownMoney != 0 && ownMoney >= sum) {
			credit.setOwnMoney(ownMoney - sum);
			credDao.updateCreditAccount(credit);
			System.out
					.println("OPeration withdrawMoneyCredit was successfully executed!-(ownMoney!=0 && ownMoney >= sum)");
			return true;
		} else if (ownMoney != 0 && ownMoney < sum
				&& (maxAvailable - (sum - ownMoney)) >= 0) {
			credit.setOwnMoney(0);
			credit.setBorrowedMoney(sum - ownMoney);
			credDao.updateCreditAccount(credit);
			System.out
					.println("OPeration withdrawMoneyCredit was successfully executed!-(ownMoney!=0 && ownMoney < sum && )");
			return true;
		}

		else if (ownMoney == 0 && (maxAvailable - borrowedMoney) > 0
				&& (maxAvailable - (borrowedMoney + sum)) >= 0) {
			credit.setBorrowedMoney(borrowedMoney += sum);
			credDao.updateCreditAccount(credit);
			System.out
					.println("OPeration withdrawMoneyCredit was successfully executed!");
			return true;

		} else {
			System.out
					.println("OPeration withdrawMoneyCredit was UNsuccessfully executed!");
			return false;
		}

	}

	private void putMoneyDebit(int debitNum, double sum) throws RemoteException {

		debDao = new DebitAccountDaoImpl();
		DebitAccount debit = debDao.getDebitAccount(debitNum);
		double sumAvailable = debit.getSumAvailable();
		debit.setSumAvailable(sumAvailable += sum);
		debDao.updateDebitAccount(debit);

	}

	private void putMoneyCredit(int creditNum, double sum)
			throws RemoteException {

		credDao = new CreditAccountDaoImpl();
		CreditAccount credit = credDao.getCreditAccount(creditNum);

		double borrowedMoney = credit.getBorrowedMoney();
		double ownMoney = credit.getOwnMoney();

		if (borrowedMoney >= sum) {
			credit.setBorrowedMoney(borrowedMoney -= sum);
			credDao.updateCreditAccount(credit);
			System.out.println("CreditAccount is successfully refilled ");

		} else {
			credit.setBorrowedMoney(0);
			credit.setOwnMoney(ownMoney += (sum - borrowedMoney));
			credDao.updateCreditAccount(credit);
			System.out
					.println("CreditAccount is successfully refilled + own money ");

		}

	}

	private double checkDebitAccount(int accountNum) throws RemoteException {
		debDao = new DebitAccountDaoImpl();
		DebitAccount debit = debDao.getDebitAccount(accountNum);
		return debit.getSumAvailable();

	}

	private double checkCreditAccount(int accountNum) throws RemoteException {
		credDao = new CreditAccountDaoImpl();
		CreditAccount credit = credDao.getCreditAccount(accountNum);
		System.out.println("Own money: " + credit.getOwnMoney());
		double ownMoney = credit.getOwnMoney();
		if (ownMoney != 0) {
			return (credit.getMaxAvailableSum() + ownMoney);

		} else {
			System.out.println("Own money: " + credit.getOwnMoney());
			return (credit.getMaxAvailableSum() - credit.getBorrowedMoney());
		}

	}

	public ATM startWork(int atmId, int cardNum, int pin)
			throws RemoteException, SQLException {
		atmDao = new ATMDaoImpl();
		debDao = new DebitAccountDaoImpl();
		credDao = new CreditAccountDaoImpl();
		ATM currentATM = atmDao.getATM(atmId);

		if (isRightDebitAccount(cardNum, pin)) {
			DebitAccount debAcc = debDao.getDebitAccountCard(cardNum);
			currentATM.setDebitAccount(debAcc);
			atmDao.updateATM(currentATM);
			return currentATM;
		} else if (isRightCreditAccount(cardNum, pin)) {
			CreditAccount credAcc = credDao.getCreditAccountCard(cardNum);
			currentATM.setCreditAccount(credAcc);
			atmDao.updateATM(currentATM);
			return currentATM;
		} else {
			System.out.println("Wrong cardNumber and pin");
			return null;
		}
	}

	public void endWork(int atmId) throws RemoteException, SQLException {
		atmDao = new ATMDaoImpl();

		ATM currentATM = atmDao.getATM(atmId);
		currentATM.setCreditAccount(null);
		currentATM.setDebitAccount(null);
		atmDao.updateATM(currentATM);
		System.out.println("ATM finished work.");

	}

	private boolean isRightDebitAccount(int cardNum, int pin)
			throws RemoteException {
		debDao = new DebitAccountDaoImpl();
		List<DebitAccount> accList = debDao.getAllDebitAccounts();
		for (int i = 0; i < accList.size(); i++) {
			if ((accList.get(i).getCardNumber() == cardNum)
					&& (accList.get(i).getPin() == pin)) {
				return true;
			}
		}
		return false;

	}

	private boolean isRightCreditAccount(int cardNum, int pin)
			throws RemoteException {
		credDao = new CreditAccountDaoImpl();
		List<CreditAccount> accList = credDao.getAllCreditAccounts();
		for (int i = 0; i < accList.size(); i++) {
			if ((accList.get(i).getCardNumber() == cardNum)
					&& (accList.get(i).getPin() == pin)) {
				return true;
			}
		}
		return false;
	}

	public boolean exist(int cardNum) throws RemoteException {
		debDao = new DebitAccountDaoImpl();
		credDao = new CreditAccountDaoImpl();
		if ((debDao.getDebitAccountCard(cardNum) != null)
				|| (credDao.getCreditAccountCard(cardNum) != null)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isDebit(int accountNum) throws RemoteException {
		debDao = new DebitAccountDaoImpl();
		if (debDao.getDebitAccount(accountNum) != null) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isCredit(int accountNum) throws RemoteException {
		credDao = new CreditAccountDaoImpl();
		if (credDao.getCreditAccount(accountNum) != null) {
			return true;
		} else {
			return false;
		}
	}

	// ATM
	public List<ATM> getAllATMs() throws RemoteException {
		atmDao = new ATMDaoImpl();
		return atmDao.getAllATMs();
	}

	public ATM getATM(String address) throws RemoteException {
		atmDao = new ATMDaoImpl();
		return atmDao.getATM(address);
	}
}
