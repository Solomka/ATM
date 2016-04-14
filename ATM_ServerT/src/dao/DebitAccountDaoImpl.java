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
import vo.DebitAccount;

public class DebitAccountDaoImpl extends UnicastRemoteObject implements
		DebitAccountDao {

	public DebitAccountDaoImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DebitAccount> getAllDebitAccounts() throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<DebitAccount> accList = new ArrayList<DebitAccount>();
		try {
			String query = "SELECT * FROM debitaccount";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				DebitAccount acc = new DebitAccount();
				acc.setAccountNumber(rs.getInt("account_num"));
				acc.setCardNumber(rs.getInt("card_number"));
				acc.setPin(rs.getInt("pin"));

				acc.setOpenAccount(rs.getDate("open_date"));
				acc.setCloseAccount(rs.getDate("close_date"));

				acc.setSumAvailable(rs.getDouble("sum_available"));
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
	public DebitAccount getDebitAccount(int accId) throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM debitaccount where account_num="
					+ accId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				DebitAccount acc = new DebitAccount();
				acc.setAccountNumber(rs.getInt("account_num"));
				acc.setCardNumber(rs.getInt("card_number"));
				acc.setPin(rs.getInt("pin"));

				acc.setOpenAccount(rs.getDate("open_date"));
				acc.setCloseAccount(rs.getDate("close_date"));

				acc.setSumAvailable(rs.getDouble("sum_available"));
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
	public DebitAccount getDebitAccountCard(int cardNum)
			throws RemoteException{
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM debitaccount where card_number="
					+ cardNum;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				DebitAccount acc = new DebitAccount();
				acc.setAccountNumber(rs.getInt("account_num"));
				acc.setCardNumber(rs.getInt("card_number"));
				acc.setPin(rs.getInt("pin"));

				acc.setOpenAccount(rs.getDate("open_date"));
				acc.setCloseAccount(rs.getDate("close_date"));

				acc.setSumAvailable(rs.getDouble("sum_available"));
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
	public void addDebitAccount(DebitAccount account) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "insert into debitaccount values("
				+ account.getAccountNumber() + ","+ account.getCardNumber() + "," + account.getPin() + ","
				+ "'" + ((java.sql.Date) account.getOpenAccount()) + "'" + ","
				+ "'" + ((java.sql.Date) account.getCloseAccount()) + "'" + ","
				+ account.getSumAvailable() + "," + "'" + account.getName()
				+ "'" + "," + "'" + account.getSurname() + "'" + ")";

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is inserted into debitaccount-table for  debitaccount : "
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
	public void updateDebitAccount(DebitAccount account) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "update debitaccount set pin=" + "'" + account.getPin()
				+ "'" + ",sum_available= " + "'" + account.getSumAvailable()
				+ "'" + " where account_num=" + account.getAccountNumber();

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is updated into debitaccount-table for debitaccount num : "
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
	public void deleteDebitAccount(int accId) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "delete from debitaccount where account_num=" + accId;

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is deleted from debitaccount - table for debitaccount num : "
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
