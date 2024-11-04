package co.edu.konradlorenz.model;

import java.util.ArrayList;

public class Cliente {
	private String nombre, direccion;
	private int identificacion;
	private ArrayList<Cuenta> cuentasCliente = new ArrayList<>();

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

	@Override
	public String toString() {
		return "Clientes [nombre=" + nombre + ", direccion=" + direccion + ", identificacion=" + identificacion
				+ ", cuentasCliente=" + cuentasCliente + "]";
	}

}
