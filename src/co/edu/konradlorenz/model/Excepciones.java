package co.edu.konradlorenz.model;

public interface Excepciones {

	
	public void depositarDinero(double monto) throws ArithmeticException;
	public void retirarDinero(double monto) throws ArithmeticException;
	public String verificarTarjetas();
	public String verificarInteres();
	
}
