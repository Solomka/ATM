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
import vo.ATM;
import vo.CreditAccount;
import vo.DebitAccount;

public class ATMDaoImpl extends UnicastRemoteObject implements ATMDao {

	public ATMDaoImpl() throws RemoteException {

		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ATM> getAllATMs() throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<ATM> atmsList = new ArrayList<ATM>();
		try {
			String query = "SELECT * \n FROM  `atm`";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ATM atm = new ATM();
				atm.setAtmId(rs.getInt("atm_id"));

				DebitAccountDao debitDao = new DebitAccountDaoImpl();
				CreditAccountDao creditDao = new CreditAccountDaoImpl();

				if (creditDao.getCreditAccount(rs.getInt("account_id")) == null && debitDao.getDebitAccount(rs.getInt("account_id")) == null ) {
					//
				}

				else if (creditDao.getCreditAccount(rs.getInt("account_id")) == null) {
					DebitAccount debAcc = debitDao.getDebitAccount(rs
							.getInt("account_id"));
					atm.setDebitAccount(debAcc);

				} else  {
					CreditAccount credAcc = creditDao.getCreditAccount(rs
							.getInt("account_id"));
					atm.setCreditAccount(credAcc);
				} 

				atm.setAddress(rs.getString("address"));

				atmsList.add(atm);

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
		return atmsList;
	}

	@Override
	public ATM getATM(int atmId) throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM atm where atm_id=" + atmId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ATM atm = new ATM();
				atm.setAtmId(rs.getInt("atm_id"));

				DebitAccountDao debitDao = new DebitAccountDaoImpl();
				CreditAccountDao creditDao = new CreditAccountDaoImpl();

				if (creditDao.getCreditAccount(rs.getInt("account_id")) == null && debitDao.getDebitAccount(rs.getInt("account_id")) == null ) {
					//
				}

				else if (creditDao.getCreditAccount(rs.getInt("account_id")) == null) {
					DebitAccount debAcc = debitDao.getDebitAccount(rs
							.getInt("account_id"));
					atm.setDebitAccount(debAcc);

				} else {
					CreditAccount credAcc = creditDao.getCreditAccount(rs
							.getInt("account_id"));
					atm.setCreditAccount(credAcc);
				} 

				atm.setAddress(rs.getString("address"));

				return atm;
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

	public ATM getATM(String address) throws RemoteException {
		DataSource dataSource = new DataSource();
		Connection con = dataSource.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM atm where address LIKE " + "'"+address+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ATM atm = new ATM();
				atm.setAtmId(rs.getInt("atm_id"));

				DebitAccountDao debitDao = new DebitAccountDaoImpl();
				CreditAccountDao creditDao = new CreditAccountDaoImpl();

				if (creditDao.getCreditAccount(rs.getInt("account_id")) == null && debitDao.getDebitAccount(rs.getInt("account_id")) == null) {
					//
				}

				else if (creditDao.getCreditAccount(rs.getInt("account_id")) == null) {
					DebitAccount debAcc = debitDao.getDebitAccount(rs
							.getInt("account_id"));
					atm.setDebitAccount(debAcc);

				} else {
					CreditAccount credAcc = creditDao.getCreditAccount(rs
							.getInt("account_id"));
					atm.setCreditAccount(credAcc);
				} 
				
				atm.setAddress(rs.getString("address"));

				return atm;
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
	public void addATM(ATM atm) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		// java.util.Date current = atm.getCurrentDate();
		// System.out.println("currentDate: " + current);
		// / ?
		// java.sql.Timestamp date = new java.sql.Timestamp(current.getTime());
		// System.out.println("sql.Date: " + date);

		String sql;
		if (atm.getCreditAccount() == null && atm.getDebitAccount() == null) {
			sql = "insert into atm values(" + atm.getAtmId() + "," + "'" + 0
					+ "'" + "," + "'" + atm.getAddress() + "'" + ")";
		} else if (atm.getCreditAccount() == null) {
			sql = "insert into atm values(" + atm.getAtmId() + ","
					+ atm.getDebitAccount().getAccountNumber() + "," + "'"
					+ atm.getAddress() + "'" + ")";
		} else {
			sql = "insert into atm values(" + atm.getAtmId() + ","
					+ atm.getCreditAccount().getAccountNumber() + "," + "'"
					+ atm.getAddress() + "'" + ")";

		}

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is inserted into atm - table for  atm : "
							+ atm.getAtmId() + " " + atm.getAddress());

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
	public void updateATM(ATM atm) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql;
		if ((atm.getCreditAccount() == null) && (atm.getDebitAccount() == null)) {

			sql = "update atm set account_id= " + "'" + 0 + "'"
					+ "where atm_id=" + atm.getAtmId();
		} else if (atm.getCreditAccount() == null) {
			sql = "update atm set account_id= " + "'"
					+ atm.getDebitAccount().getAccountNumber() + "'"
					+ "where atm_id=" + atm.getAtmId();
		} else {
			sql = "update atm set account_id= " + "'"
					+ atm.getCreditAccount().getAccountNumber() + "'"
					+ "where atm_id=" + atm.getAtmId();

		}
		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out.println("Record is updated into atm for Atm id : "
					+ atm.getAtmId());

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
	public void deleteATM(int atmId) throws RemoteException {
		Connection dbConnection = null;
		Statement statement = null;

		String sql = "delete from atm where atm_id=" + atmId;

		try {
			DataSource dataSource = new DataSource();
			dbConnection = dataSource.createConnection();
			statement = dbConnection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out
					.println("Record is deleted from atm - table for atm id : "
							+ atmId);

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
