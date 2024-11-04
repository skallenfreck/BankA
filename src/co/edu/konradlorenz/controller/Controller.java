package co.edu.konradlorenz.controller;

import java.util.ArrayList;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.Vista;

public class Controller {
	private Cliente clienteGlobal = new Cliente();
	private Cuenta cuentaGlobal = new Cuenta();
	private Tarjeta tarjetaGloTarjeta = new Tarjeta();
	private ArrayList<Cliente> listaClientes = new ArrayList<>();
	private int opcion = 0;

	public void run() {

	}

	public void switchPrincipal() {
		opcion = Vista.mostrarMenu();
		while (opcion != 2) {
			switch (opcion) {
			case 1:

				break;
			case 2:
				opcion = 2;
				break;
			default:
				break;
			}
		}
	}
	
	public void switchCliente(){
		opcion= Vista.menuCliente();
		while (opcion!=3) {
			switch (opcion) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;

			default:
				break;
			}
		}
	}

	public void crearCliente() {
		Cliente nuevoCliente = new Cliente();
		Vista.mostrarMensajer("Bienvenido, vamos a registrar un nuevo cliente a banKA");
		nuevoCliente.setNombre(Vista.pedirString("Por favor ingrese el nombre del nuevo cliente"));
		nuevoCliente.setDireccion(Vista.pedirString("Por favor ingrese la direccion del nuevo cliente"));
		nuevoCliente.setIdentificacion(
				Integer.parseInt(Vista.pedirString("Por favor ingrese la identificacion del nuevo cliente")));

		nuevoCliente.setCuentasCliente(new ArrayList<>());
		opcion = Integer.parseInt(Vista.pedirString(
				"¿Cual tipo de cuenta desea asignar para el nuevo cliente?\n" + "[1] Ahorros\n" + "[2] Creditos"));
		try {
			if (opcion <= 2 && opcion >= 1) {
				if (opcion == 1) {
					Ahorro cuentaAhorro = new Ahorro();
					cuentaAhorro.setBalance(0);
					nuevoCliente.getCuentasCliente().add(cuentaAhorro);
				} else {
					Credito cuentaCredito = new Credito();
					cuentaCredito.setBalance(0);
					nuevoCliente.getCuentasCliente().add(cuentaCredito);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		listaClientes.add(nuevoCliente);
	}
}
