package co.edu.konradlorenz.model;

public class Tarjeta {
	
	private int num;
	private String nomPropietario;
	
	public Tarjeta() {
		super();
	}
	
	public Tarjeta(int num, String nomPropietario) {
		super();
		this.num = num;
		this.nomPropietario = nomPropietario;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getNomPropietario() {
		return nomPropietario;
	}
	
	public void setNomPropietario(String nomPropietario) {
		this.nomPropietario = nomPropietario;
	}
}
