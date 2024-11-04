package co.edu.konradlorenz.model;

public class Cuenta {
	private double balance;

	public Cuenta() {

	}

	public Cuenta(double balance) {
		super();
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Cuentas [balance=" + balance + "]";
	}
}
