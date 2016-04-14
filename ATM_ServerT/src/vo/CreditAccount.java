package vo;

import java.io.Serializable;
import java.sql.Date;

public class CreditAccount implements Serializable {
	
	private String name;
	private String surname;

	private int accountNumber;
	private int cardNumber;
	private int pin;
	private double maxAvailableSum;
	private double borrowedMoney = 0;
	private double ownMoney = 0;
	private Date openAccount;
	private Date closeAccount;

	public CreditAccount() {

	}

	public CreditAccount(String name, String surname, int accountNumber,
			int cardNumber, int pin, double maxAvailableSum,
			double borrowedMoney, double ownMoney, Date openAccount,
			Date closeAccount) {
		super();
		this.name = name;
		this.surname = surname;
		this.accountNumber = accountNumber;
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.maxAvailableSum = maxAvailableSum;
		this.borrowedMoney = borrowedMoney;
		this.ownMoney = ownMoney;
		this.openAccount = openAccount;
		this.closeAccount = closeAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getMaxAvailableSum() {
		return maxAvailableSum;
	}

	public void setMaxAvailableSum(double maxAvailableSum) {
		this.maxAvailableSum = maxAvailableSum;
	}

	public double getBorrowedMoney() {
		return borrowedMoney;
	}

	public void setBorrowedMoney(double borrowedMoney) {
		this.borrowedMoney = borrowedMoney;
	}

	public double getOwnMoney() {
		return ownMoney;
	}

	public void setOwnMoney(double ownMoney) {
		this.ownMoney = ownMoney;
	}

	public Date getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(Date openAccount) {
		this.openAccount = openAccount;
	}

	public Date getCloseAccount() {
		return closeAccount;
	}

	public void setCloseAccount(Date closeAccount) {
		this.closeAccount = closeAccount;
	}

	@Override
	public String toString() {
		return "CreditAccount [name=" + name + ", surname=" + surname
				+ ", accountNumber=" + accountNumber + ", cardNumber="
				+ cardNumber + ", pin=" + pin + ", maxAvailableSum="
				+ maxAvailableSum + ", borrowedMoney=" + borrowedMoney
				+ ", ownMoney=" + ownMoney + ", openAccount=" + openAccount
				+ ", closeAccount=" + closeAccount + "]";
	}

	

	

}
