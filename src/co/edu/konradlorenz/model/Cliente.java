package co.edu.konradlorenz.model;

import java.util.ArrayList;

public class Cliente implements Excepciones{
	private String nombre, direccion;
	private int identificacion;
	private ArrayList<Cuenta> cuentasCliente = new ArrayList<>();
	private ArrayList<Tarjeta> tarjetasCliente = new ArrayList<>();

	public Cliente() {

	}

	public Cliente(String nombre, String direccion, int identificacion, ArrayList<Cuenta> cuentasCliente) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.identificacion = identificacion;
		this.cuentasCliente = cuentasCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public ArrayList<Cuenta> getCuentasCliente() {
		return cuentasCliente;
	}

	public void setCuentasCliente(ArrayList<Cuenta> cuentasCliente) {
		this.cuentasCliente = cuentasCliente;
	}

	public ArrayList<Tarjeta> getTarjetasCliente() {
		return tarjetasCliente;
	}

	public void setTarjetasCliente(ArrayList<Tarjeta> tarjetasCliente) {
		this.tarjetasCliente = tarjetasCliente;
	}

	@Override
	public String toString() {
		return "Clientes [nombre=" + nombre + ", direccion=" + direccion + ", identificacion=" + identificacion
				+ ", cuentasCliente=" + cuentasCliente + "]";
	}

	@Override
	public void depositarDinero(double monto) throws ArithmeticException {
		if(monto <= 0) {
			throw new ArithmeticException("El monto a depositar debe ser mayor que 0");
		}
		
		if (!cuentasCliente.isEmpty()) {
            Cuenta cuenta = cuentasCliente.get(0);
            cuenta.setBalance(cuenta.getBalance() + monto);
        } else {
            throw new ArithmeticException("El cliente no tiene cuentas asociadas para realizar el depósito.");
        }
	}

	@Override
	public void retirarDinero(double monto) throws ArithmeticException {
		if(monto<=0) {
			throw new ArithmeticException("El monto a retirar debe ser mayor que 0");
		}
		
		 if (!cuentasCliente.isEmpty()) {
	            Cuenta cuenta = cuentasCliente.get(0); 
	            if (cuenta.getBalance() < monto) {
	                throw new ArithmeticException("Saldo insuficiente para realizar el retiro.");
	            }
	            cuenta.setBalance(cuenta.getBalance() - monto);
	        } else {
	            throw new ArithmeticException("El cliente no tiene cuentas asociadas para realizar el retiro.");
	        }
	}

	@Override
	public String verificarTarjetas() {
		String mensaje = "";
		if(tarjetasCliente.isEmpty()) {
			mensaje = "No hay tarjetas registradas para este cliente.";
			return mensaje;
		}else {
			for(Tarjeta tarjeta : tarjetasCliente) {
				return tarjeta.toString();
			}
		}
		return "";
	}

	@Override
	public String verificarInteres() {
		String mensaje = "";
		if(cuentasCliente.isEmpty()) {
			mensaje = "No hay cuentas registradas para este cliente";
			return mensaje;
		}else {
			for(Cuenta cuenta: cuentasCliente) {
				if(cuenta instanceof Ahorro) {
					Ahorro cuentaAhorro = (Ahorro) cuenta;
					mensaje = "Interés de la cuenta de ahorro: "+cuentaAhorro.calcularInteres();
				}else if(cuenta instanceof Credito) {
					mensaje = "La cuenta de crédito no cuenta con interés";
				}
			}
			return mensaje;
		}
	}
	
}
