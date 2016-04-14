package test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JLabel;
import javax.swing.JTable;

import service.ATMService;
import vo.ATM;


public class Tester {

	public static void main(String[] args) throws NamingException, RemoteException, NotBoundException, SQLException {
		Registry registry = LocateRegistry.getRegistry("localhost", 2099);
		ATMService service = (ATMService) registry.lookup("atm");
		
		//service.withdrawMoney(10, 400);
		//service.withdrawMoney(1, 100);
		//service.withdrawMoney(12, 100);
		//service.withdrawMoney(1,200);
		//service.withdrawMoney(12, 100);
		//service.withdrawMoney(12, 330);
		
		//System.out.println("checkAccount: "+ service.checkAccount(14));
		//System.out.println("checkAccount: "+ service.checkAccount(12));
		
		//System.out.println(service.printCheck(777,12));
		//System.out.println(service.printCheck(777,1));
		
		//service.transferMoney(1, 12, 100);
		//service.transferMoney(2, 1, 300);
		//service.transferMoney(12, 1, 20);
		//service.transferMoney(12, 14, 50);
		
		ATM atm1= service.getATM("12 Kotsubynskogo str.,Kyiv");
		System.out.println("ATM!: "+ atm1);
		//ATM atm=service.startWork(service.getATM("12 Kotsubynskogo str.").getAtmId(),144, 1234);
		//service.endWork(777);
		/*
		System.out.println("All ATMs: ");
		List <ATM> atmList = service.getAllATMs();
		for (ATM atm : atmList) {
			System.out.println(atm);*/
		}
		////
		/*
		private void init() throws RemoteException, NotBoundException {
			service = new ServiceLibrary();
			List<Book> bookList = service.getService().getOrderedBooks();
			if (bookList.isEmpty()==true) {
				lable = new JLabel("У бібліотеці немає зараз доступних книжок!");
				lable.setHorizontalAlignment(JLabel.CENTER);
				return;
			}
			String[] columnNames = { "Автор", "Назва"};
			String[][] rowData = new String[bookList.size()][2];
		
			for (int i = 0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				rowData[i][0] = book.getAuther();
				rowData[i][1] = book.getTitle();
				
			}
			table = new JTable(rowData, columnNames);
			
		}
		*/

	}


