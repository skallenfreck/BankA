package co.edu.konradlorenz.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.Vista;

public class Controller {
	private Cliente clienteGlobal = new Cliente();
	private Cuenta cuentaGlobal = new Cuenta();
	private Tarjeta tarjetaGloTarjeta = new Tarjeta();
	private ArrayList<Cliente> listaClientes = new ArrayList<>();
	private int opcion = 0;

	public void run() {
		switchPrincipal();
	}

	public void switchPrincipal() {
		opcion = Vista.mostrarMenu();
		do {
			switch (opcion) {
			case 1:
				switchCliente();
				break;
			case 2:
				Vista.mostrarMensaje("Finalizando servicio...");
				break;
			default:
				Vista.mostrarMensaje("Opción inválida.");
				break;
			}
		} while (opcion != 2);
	}

	public void switchCliente() {
		while(true) {
			try {
				opcion = Vista.menuCliente();
				switch (opcion) {
					case 1:
						crearCliente();
						break;
					case 2:
						mostrarClientes();
						break;
					case 3:
						Vista.mostrarMensaje("Cerrando menú cliente...");
						switchPrincipal();
						break;
					default:
						Vista.mostrarMensaje("Opción inválida.");
						break;
				}
			}catch(InputMismatchException e) {
				Vista.mostrarMensaje("Digite un valor numerico por favor");
				Vista.limpiarScanner();
			}finally {
				Vista.mostrarMensaje("Proceso finalizado.");
			}
		}
	}

	public void crearCliente() {
		Cliente nuevoCliente = new Cliente();
		Vista.mostrarMensaje("Bienvenido, vamos a registrar un nuevo cliente a banKA");
		nuevoCliente.setNombre(Vista.pedirString("Por favor ingrese el nombre del nuevo cliente"));
		nuevoCliente.setDireccion(Vista.pedirString("Por favor ingrese la direccion del nuevo cliente"));

		try {
			nuevoCliente.setIdentificacion(
					Integer.parseInt(Vista.pedirString("Por favor ingrese la identificacion del nuevo cliente")));
		} catch (NumberFormatException e) {
			Vista.mostrarMensaje("La identificación ingresada no es un número válido. Intente de nuevo.");
			return;
		}finally {
			Vista.mostrarMensaje("Acción finalizada.");
		}

		nuevoCliente.setCuentasCliente(new ArrayList<>());

		while (true) {
			try {
				opcion = Integer.parseInt(Vista.pedirString("¿Cual tipo de cuenta desea asignar para el nuevo cliente?\n" + "[1] Ahorros\n" + "[2] Creditos"));
				if (opcion <= 2 && opcion >= 1) {
					if (opcion == 1) {
						Ahorro cuentaAhorro = new Ahorro();
						cuentaAhorro.setBalance(0);
						nuevoCliente.getCuentasCliente().add(cuentaAhorro);
						break;
					} else {
						Random random = new Random();
						int numeroAleatorio = 100 + random.nextInt(900);
						Credito cuentaCredito = new Credito();
						cuentaCredito.setBalance(0);
						Tarjeta tarjeta= new Tarjeta(numeroAleatorio, nuevoCliente.getNombre());
						
						nuevoCliente.getCuentasCliente().add(cuentaCredito);
						break;
					}
				} else {
					Vista.mostrarMensaje("Esa opción no esta disponible, intenta de nuevo");
				}
			} catch (NumberFormatException e) {
				Vista.mostrarMensaje("La cuenta elegida no es valida. Intente de nuevo.");
			} finally {
				Vista.mostrarMensaje("Acción finalizada.");
			}
		}
		listaClientes.add(nuevoCliente);
		Vista.mostrarMensaje("Cliente registrado exitosamente.");
	}

	public void mostrarClientes() {
		if (listaClientes.isEmpty()) {
			Vista.mostrarMensaje("No hay ningun cliente registrado en banKA");
			switchCliente();
		} else {
			Vista.mostrarMensaje("La lista de clientes es:");
			for (Cliente cliente : listaClientes) {
				Vista.mostrarMensaje(cliente.toString());
			}

			String nombreBusqueda = Vista.pedirString("¿Que cliente quieres elegir?");

			for (Cliente cliente : listaClientes) {
				if (cliente.getNombre().equalsIgnoreCase(nombreBusqueda)) {
					clienteGlobal = cliente;
					Vista.mostrarMensaje("Cliente seleccionado: " + clienteGlobal.getNombre());
					switchCajero();
					return;
				}
			}
			Vista.mostrarMensaje("Cliente no encontrado.");
		}

	}

	public void switchCajero() {
		while (true) {
			try {
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
						Vista.mostrarMensaje(clienteGlobal.verificarTarjetas());
						switchCajero();
						break;
					case 4:
						Vista.mostrarMensaje(clienteGlobal.verificarInteres());
						switchCajero();
						break;
					case 5:
						Vista.mostrarMensaje("Cerrando menú cajero...");
						switchCliente();
						break;
					default:
						Vista.mostrarMensaje("Opción inválida.");
						opcion = Vista.menuCajero();
						break;
					}
				}
			} catch (InputMismatchException e) {
				Vista.mostrarMensaje("Digite un valor numerico por favor");
				Vista.limpiarScanner();
			}finally {
				Vista.mostrarMensaje("Proceso finalizado.");
			}
		}
	}

	public void realizarDeposito() {
		try {
			double monto = Double.parseDouble(Vista.pedirString("Ingrese el monto a depositar: "));
			clienteGlobal.depositarDinero(monto);
			Vista.mostrarMensaje("Depósito realizado exitosamente.");
		} catch (ArithmeticException e) {
			Vista.mostrarMensaje("Error en el depósito: " + e.getMessage());
		} catch (NumberFormatException e) {
			Vista.mostrarMensaje("Monto inválido, intente de nuevo.");
		} finally {
			Vista.mostrarMensaje("Depósito finalizado.");
		}
		switchCajero();
	}

	public void realizarRetiro() {
		try {
			double monto = Double.parseDouble(Vista.pedirString("Ingrese el monto a retirar: "));
			clienteGlobal.retirarDinero(monto);
			Vista.mostrarMensaje("Retiro realizado ecitosamente.");
		} catch (ArithmeticException e) {
			Vista.mostrarMensaje("Error en el retiro: " + e.getMessage());
		} catch (NumberFormatException e) {
			Vista.mostrarMensaje("Monto inválido, intente de nuevo.");
		} finally {
			Vista.mostrarMensaje("Retiro finalizado.");
		}
		switchCajero();
	}

}
