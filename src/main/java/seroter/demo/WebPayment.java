package seroter.demo;

public class WebPayment {
	
	private String fromAccount;
	private String toAccount;
	private long transferAmount;
	
	public String getFromAccount() {
		return fromAccount;
	}
	
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public String getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	
	public long getTransferAmount() {
		return transferAmount;
	}
	
	public void setTransferAmount(long transferAmount) {
		this.transferAmount = transferAmount;
	}

}
