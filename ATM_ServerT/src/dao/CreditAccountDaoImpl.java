package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import service.DataSource;
import vo.CreditAccount;


public class CreditAccountDaoImpl  extends UnicastRemoteObject implements
CreditAccountDao{

	public CreditAccountDaoImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CreditAccount> getAllCreditAccounts() throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<CreditAccount> accList = new ArrayList<CreditAccount>();
		try {
			String query = "SELECT * FROM creditaccount";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CreditAccount acc = new CreditAccount();
				acc.setAccountNumber(rs.getInt("account_num"));
				acc.setCardNumber(rs.getInt("card_number"));
				acc.setPin(rs.getInt("pin"));

				acc.setOpenAccount(rs.getDate("open_date"));
				acc.setCloseAccount(rs.getDate("close_date"));

				acc.setMaxAvailableSum(rs.getDouble("max_available_sum"));
				acc.setOwnMoney(rs.getDouble("own_money"));
				acc.setBorrowedMoney(rs.getDouble("borrowed_money"));
				acc.setName(rs.getString("name"));
				acc.setSurname(rs.getString("surname"));
				accList.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exe) {
				exe.printStackTrace();
			}

		}
		return accList;
	}

	@Override
	public CreditAccount getCreditAccount(int accId) throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM creditaccount where account_num="
					+ accId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CreditAccount acc = new CreditAccount();
				acc.setAccountNumber(rs.getInt("account_num"));
				acc.setCardNumber(rs.getInt("card_number"));
				acc.setPin(rs.getInt("pin"));

				acc.setOpenAccount(rs.getDate("open_date"));
				acc.setCloseAccount(rs.getDate("close_date"));

				acc.setMaxAvailableSum(rs.getDouble("max_available_sum"));
				acc.setOwnMoney(rs.getDouble("own_money"));
				acc.setBorrowedMoney(rs.getDouble("borrowed_money"));
				acc.setName(rs.getString("name"));
				acc.setSurname(rs.getString("surname"));
				return acc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exe) {
				exe.printStackTrace();
			}

		}
		return null;
	}
	@Override
	public CreditAccount getCreditAccountCard(int cardNum) throws RemoteException{
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM creditaccount where card_number="
					+ cardNum;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CreditAccount acc = new CreditAccount();
				acc.setAccountNumber(rs.getInt("account_num"));
				acc.setCardNumber(rs.getInt("card_number"));
				acc.setPin(rs.getInt("pin"));

				acc.setOpenAccount(rs.getDate("open_date"));
				acc.setCloseAccount(rs.getDate("close_date"));

				acc.setMaxAvailableSum(rs.getDouble("max_available_sum"));
				acc.setOwnMoney(rs.getDouble("own_money"));
				acc.setBorrowedMoney(rs.getDouble("borrowed_money"));
				acc.setName(rs.getString("name"));
				acc.setSurname(rs.getString("surname"));
				return acc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exe) {
				exe.printStackTrace();
			}

		}
		return null;
		
	}
	@Override
	public void addCreditAccount(CreditAccount account) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "insert into creditaccount values("
				+ account.getAccountNumber() + "," + account.getCardNumber() + "," + account.getPin() + ","
				+ "'" + ((java.sql.Date) account.getOpenAccount()) + "'" + ","
				+ "'" + ((java.sql.Date) account.getCloseAccount()) + "'" + ","
				+ account.getMaxAvailableSum() + "," + account.getBorrowedMoney() + "," + account.getOwnMoney() + "," +  "'" + account.getName()
				+ "'" + "," + "'" + account.getSurname() + "'" + ")";

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is inserted into creditaccount-table for  creditaccount : "
							+ account.getAccountNumber()
							+ " "
							+ account.getSurname()
							+ " "
							+ account.getCloseAccount().toString());

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		
	}

	@Override
	public void updateCreditAccount(CreditAccount account)
			throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "update creditaccount set pin=" + "'" + account.getPin()
				+ "'" + ",own_money= " + "'" + account.getOwnMoney()
				+ "'" + ",borrowed_money= " + "'" + account.getBorrowedMoney()
				+ "'" + " where account_num=" + account.getAccountNumber();

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is updated into creditaccount-table for creditaccount num : "
							+ account.getAccountNumber());

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		
	}

	@Override
	public void deleteCreditAccount(int accId) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "delete from creditaccount where account_num=" + accId;

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is deleted from creditaccount - table for creditaccount num : "
							+ accId);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
		
	}
	


