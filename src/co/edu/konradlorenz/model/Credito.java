package co.edu.konradlorenz.model;

import java.util.ArrayList;

public class Credito extends Cuenta{
	
	private ArrayList<Tarjeta> listTarjetas= new ArrayList<>();

	public Credito() {
		super();
	}

	public Credito(double balance) {
		super(balance);
	}

	public Credito(ArrayList<Tarjeta> listTarjetas) {
		super();
		this.listTarjetas = listTarjetas;
	}
	
	public Credito(ArrayList<Tarjeta> listTarjetas, double balance) {
		super(balance);
		this.listTarjetas = listTarjetas;
	}

	public ArrayList<Tarjeta> getListTarjetas() {
		return listTarjetas;
	}

	public void setListTarjetas(ArrayList<Tarjeta> listTarjetas) {
		this.listTarjetas = listTarjetas;
	}

	@Override
	public String toString() {
		return "Credito [listTarjetas=" + listTarjetas + "]";
	}
}
