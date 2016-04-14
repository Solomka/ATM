package vo;

import java.io.Serializable;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ATM implements Serializable {
	private int atmId;
	private DebitAccount debitAccount;
	private CreditAccount creditAccount;
	private String address;
	
	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "current_date")
	java.util.Date currentDate = new Date();*/

	public ATM() {

	}
	public ATM(int atmId, String address) {
		super();
		this.atmId = atmId;
		this.address = address;
	}
	public ATM(int atmId, DebitAccount debitAccount, String address) {
		super();
		this.atmId = atmId;
		this.debitAccount = debitAccount;
		this.address = address;
	}
	
	public ATM(int atmId, CreditAccount creditAccount, String address) {
		super();
		this.atmId = atmId;
		this.creditAccount = creditAccount;
		this.address = address;
	}
	public int getAtmId() {
		return atmId;
	}
	public void setAtmId(int atmId) {
		this.atmId = atmId;
	}
	public DebitAccount getDebitAccount() {
		return debitAccount;
	}
	public void setDebitAccount(DebitAccount debitAccount) {
		this.debitAccount = debitAccount;
	}
	public CreditAccount getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(CreditAccount creditAccount) {
		this.creditAccount = creditAccount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ATM [atmId=" + atmId + ", debitAccount=" + debitAccount
				+ ", creditAccount=" + creditAccount + ", address=" + address
				+ "]";
	}

	

}
