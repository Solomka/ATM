package test;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import vo.ATM;
import vo.CreditAccount;
import vo.DebitAccount;
import dao.ATMDao;
import dao.ATMDaoImpl;
import dao.CreditAccountDao;
import dao.CreditAccountDaoImpl;
import dao.DebitAccountDao;
import dao.DebitAccountDaoImpl;

public class Tester {

	public static void main(String[] args) throws RemoteException, SQLException {

		// DebitAccount testing-----
		DebitAccountDao debDao = new DebitAccountDaoImpl();
		DebitAccount acc1 = new DebitAccount();
		acc1.setAccountNumber(1);
		acc1.setPin(123);

		Date open1 = new Date(112, 7, 24);
		Date open2 = new Date(115, 7, 24);
		acc1.setOpenAccount(open1);
		acc1.setCloseAccount(open2);
		// add
		acc1.setName("Solomka");
		acc1.setSurname("Yaremko");
		// debDao.addDebitAccount(acc1);
		// get
		DebitAccount getAcc1 = debDao.getDebitAccount(1);
		// System.out.println("Get debitAccount: "+ getAcc1);
		
		DebitAccount debCard = debDao.getDebitAccountCard(177);
		System.out.println("Get debitAccountCard: "+ debCard);
		
		// update
		getAcc1.setPin(1234);
		getAcc1.setSumAvailable(getAcc1.getSumAvailable() + 200);
		// debDao.updateDebitAccount(getAcc1);

		// System.out.println("Get debitAccount: "+ getAcc1);
		// delete
		// debDao.deleteDebitAccount(1);

		// print--------------------------------------

		System.out.println();
		System.out
				.println("-------------------------Print all the DebitAccount--------------------------------Start ");

		// print all Books
		for (DebitAccount acc : debDao.getAllDebitAccounts()) {
			System.out.println(acc.toString());
		}

		System.out
				.println("-------------------------Print all the DebitAccount-------------------------------- End");
		System.out.println();

		// --------------------------------------------------------------

		// CreditAccount testing-----
		
		CreditAccountDao credDao = new CreditAccountDaoImpl();
		// add
		CreditAccount cAcc = new CreditAccount();
		cAcc.setAccountNumber(1);
		cAcc.setPin(123);

		Date open3 = new Date(112, 4, 12);
		Date open4 = new Date(115, 4, 15);
		cAcc.setOpenAccount(open3);
		cAcc.setCloseAccount(open4);
		cAcc.setMaxAvailableSum(500);
		cAcc.setName("Solomka");
		cAcc.setSurname("Yaremko");
		//credDao.addCreditAccount(cAcc);
		
//get
		
		CreditAccount getAcc2 = credDao.getCreditAccount(12);
		//System.out.println("Get creditAccount: "+ getAcc2);	
		
		CreditAccount creditNumber= credDao.getCreditAccountCard(575);
		System.out.println("Get creditAccountCard: "+ creditNumber);	
//update
		getAcc2.setPin(1234);
		getAcc2.setBorrowedMoney(70);
		//credDao.updateCreditAccount(getAcc2);
		
// delete
	//credDao.deleteCreditAccount(1);
	
		
// print--------------------------------------

		System.out.println();
		System.out
				.println("-------------------------Print all the CreditAccount--------------------------------Start ");

		// print all Books
		for (CreditAccount acc : credDao.getAllCreditAccounts()) {
			System.out.println(acc.toString());
		}

		System.out
				.println("-------------------------Print all the CreditAccount-------------------------------- End");
		System.out.println();

		// --------------------------------------------------------------

		// ATM testing-----
		
		ATMDao atmDao = new ATMDaoImpl();
		//add
		ATM unique = new ATM();
		unique.setAtmId(555);
		
		DebitAccount deb1 = debDao.getDebitAccount(2);
		System.out.println("deb1: "+ deb1);
		unique.setDebitAccount(deb1);
		unique.setAddress("11 Skovorody str.");
		//atmDao.addATM(unique);
		
		//delete
		//atmDao.deleteATM(555);
		
		//get
		//ATM atm1 =atmDao.getATM(777);
	    //System.out.println("Get atm1 = "+ atm1);
		
		//ATM atm1 =atmDao.getATM(999);
		//System.out.println("Get atm1 = "+ atm1);
		
		//ATM atm2 =atmDao.getATM("11 Skovorody str.");
		ATM atm2 =atmDao.getATM("12 Kotsubynskogo str.,Kyiv");
	 	System.out.println("Get atm2 = "+ atm2);
		
		
		
		//update
		ATM a=atmDao.getATM(777);
		
		//DebitAccount deb = debDao.getDebitAccount(1);
		//a.setDebitAccount(deb);
		
		a.setDebitAccount(null);
		//atmDao.updateATM(a);
		//System.out.println("A updated: "+ a);
		
		
		
		// print--------------------------------------

				System.out.println();
				System.out
						.println("-------------------------Print all the ATMs--------------------------------Start ");

				// print all Books
				for (ATM acc : atmDao.getAllATMs()) {
					System.out.println(acc.toString());
				}

				System.out
						.println("-------------------------Print all the ATMs-------------------------------- End");
				System.out.println();

				// --------------------------------------------------------------
		

	}
	

	
}
