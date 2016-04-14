package vo;

import java.io.Serializable;
import java.sql.Date;

public class DebitAccount implements Serializable {
	private String name;
	private String surname;

	private int accountNumber;
	private int cardNumber;
	private int pin;
	private double sumAvailable;

	private Date openAccount;
	private Date closeAccount;

	public DebitAccount() {

	}

	public DebitAccount(String name, String surname, int accountNumber,
			int cardNumber, int pin, double sumAvailable, Date openAccount,
			Date closeAccount) {
		super();
		this.name = name;
		this.surname = surname;
		this.accountNumber = accountNumber;
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.sumAvailable = sumAvailable;
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

	public double getSumAvailable() {
		return sumAvailable;
	}

	public void setSumAvailable(double sumAvailable) {
		this.sumAvailable = sumAvailable;
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
		return "DebitAccount [name=" + name + ", surname=" + surname
				+ ", accountNumber=" + accountNumber + ", cardNumber="
				+ cardNumber + ", pin=" + pin + ", sumAvailable="
				+ sumAvailable + ", openAccount=" + openAccount
				+ ", closeAccount=" + closeAccount + "]";
	}

	

}
