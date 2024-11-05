package co.edu.konradlorenz.controller;

import java.util.ArrayList;
import java.util.Iterator;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.Vista;

public class Controller {
	private Cliente clienteGlobal = new Cliente();
	private Cuenta cuentaGlobal = new Cuenta();
	private Tarjeta tarjetaGloTarjeta = new Tarjeta();
	private ArrayList<Cliente> listaClientes = new ArrayList<>();
	private int opcion = 0;
	private Cliente clienteSeleccionado;

	public void run() {
		switchPrincipal();
	}

	public void switchPrincipal() {
		opcion = Vista.mostrarMenu();
		while (opcion != 2) {
			switch (opcion) {
			case 1:
				switchCliente();
				break;
			case 2:
				Vista.mostrarMensaje("Finalizando servicio...");
				opcion = 2;
				break;
			default:
				Vista.mostrarMensaje("Opción inválida.");
				break;
			}
		}
	}

	public void switchCliente() {
		opcion = Vista.menuCliente();
		while (opcion != 3) {
			switch (opcion) {
			case 1:
				crearCliente();
				break;
			case 2:
				mostrarClientes();
				elegirCliente();
				break;
			case 3:
				Vista.mostrarMensaje("Cerrando menú cliente...");
				opcion = 2;
				break;
			default:
				Vista.mostrarMensaje("Opción inválida.");
				break;
			}
		}
	}

	public void crearCliente() {
		Cliente nuevoCliente = new Cliente();
		Vista.mostrarMensaje("Bienvenido, vamos a registrar un nuevo cliente a banKA");
		nuevoCliente.setNombre(Vista.pedirString("Por favor ingrese el nombre del nuevo cliente"));
		nuevoCliente.setDireccion(Vista.pedirString("Por favor ingrese la direccion del nuevo cliente"));
		nuevoCliente.setIdentificacion(Integer.parseInt(Vista.pedirString("Por favor ingrese la identificacion del nuevo cliente")));
		nuevoCliente.setCuentasCliente(new ArrayList<>());
		
		while (true) {
			opcion = Integer.parseInt(Vista.pedirString("¿Cual tipo de cuenta desea asignar para el nuevo cliente?\n" + "[1] Ahorros\n" + "[2] Creditos"));
			if (opcion <= 2 && opcion >= 1) {
				if (opcion == 1) {
					Ahorro cuentaAhorro = new Ahorro();
					cuentaAhorro.setBalance(0);
					nuevoCliente.getCuentasCliente().add(cuentaAhorro);
					break;
				} else {
					Credito cuentaCredito = new Credito();
					cuentaCredito.setBalance(0);
					nuevoCliente.getCuentasCliente().add(cuentaCredito);
					break;
				}
			} else {
				Vista.mostrarMensaje("Esa opcion no esta disponible, intenta de nuevo");
			}
		}
		listaClientes.add(nuevoCliente);
		Vista.mostrarMensaje("Cliente registrado exitosamente.");
	}

	public void mostrarClientes() {
		if (listaClientes.isEmpty()) {
			Vista.mostrarMensaje("No hay ningun cliente registrado en banKA");
		} else {
			Vista.mostrarMensaje("La lista de clientes es:");
			for (Cliente cliente : listaClientes) {
				cliente.toString();
				Vista.mostrarMensaje("\n");
			}

		}

	}

	public void elegirCliente() {
		String nombreBusqueda = Vista.pedirString("¿Que cliente quieres elegir?");
		for(Cliente cliente : listaClientes) {
			if(cliente.getNombre().equalsIgnoreCase(nombreBusqueda)) {
				clienteSeleccionado = cliente;
				Vista.mostrarMensaje("Cliente seleccionado: "+clienteSeleccionado.getNombre());
				switchCajero();
				return;
			}
		}
		Vista.mostrarMensaje("Cliente no encontrado.");
	}
	
	public void switchCajero() {
		opcion = Vista.menuCajero();
		while (opcion != 5) {
			switch (opcion) {
			case 1:
				realizarDeposito();
				break;
			case 2:
				realizarRetiro();
				break;
			case 3:
				Vista.mostrarMensaje(clienteSeleccionado.verificarTarjetas());
				break;
			case 4:
				verificarInteres();
				break;
			case 5:
				Vista.mostrarMensaje("Saliendo del menú de cajero...");
				break;
			default:
				Vista.mostrarMensaje("Opción inválida.");
				break;
			}
		}
	}

	public void realizarDeposito() {
		try {
			double monto = Double.parseDouble(Vista.pedirString("Ingrese el monto a depositar: "));
			clienteSeleccionado.depositarDinero(monto);
			Vista.mostrarMensaje("Depósito realizado exitosamente.");
		}catch(ArithmeticException e) {
			Vista.mostrarMensaje("Eror en el depósito: "+e.getMessage());
		}catch(NumberFormatException e) {
			Vista.mostrarMensaje("Monto inválido, intente de nuevo.");
		}finally {
			Vista.mostrarMensaje("Depósito finalizado.");
		}
	}
	
	public void realizarRetiro() {
		try {
			double monto = Double.parseDouble(Vista.pedirString("Ingrese el monto a retirar: "));
			clienteSeleccionado.retirarDinero(monto);
			Vista.mostrarMensaje("Retiro realizado ecitosamente.");
		}catch(ArithmeticException e) {
			Vista.mostrarMensaje("Error en el retiro: "+e.getMessage());
		}catch(NumberFormatException e) {
			Vista.mostrarMensaje("Monto inválido, intente de nuevo.");
		}finally {
			Vista.mostrarMensaje("Depósito finalizado.");
		}
	}
	
	private void verificarInteres() {
		
		
	}

	
}
