package co.edu.konradlorenz.model;

public class Ahorro extends Cuenta implements Interes{
	
	public double calcularInteres() {
		double balanceCuenta= getBalance();
		return INTERES*balanceCuenta;
	}
}
