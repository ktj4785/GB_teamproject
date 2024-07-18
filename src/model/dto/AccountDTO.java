package model.dto;

public class AccountDTO {
	private int accountId;
	private String bank;
	private int balance;
	private String userId;
	
	public AccountDTO() {}
	//데이베이스에서 불러올때 생성자 여기다 담아야함
	public AccountDTO(int accountId, String bank, int balance, String userId) {
		this.accountId = accountId;
		this.bank = bank;
		this.balance = balance;
		this.userId = userId;
	}
	//데이터베이스에서 입력할때(회원가입시 , 생성시엔 밸런스가 0이어야함)
	public AccountDTO(int accountId, String bank, String userId) {
		this.accountId = accountId;
		this.bank = bank;
		this.userId = userId;
	}
	
	
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
