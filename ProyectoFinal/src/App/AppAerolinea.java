/*
Juan Carlos Cardona
20112050
 */
package App;

import Aerolinea.*;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppAerolinea
{
    public static void main(String[] args)
    {
        /*
        Implementacion de todos los metodos desarrollados en otras clases para el desarrollo del programa
        en la funcion main
         */

        boolean isRunning = true;
        byte op, op2;
        int numEmpleados = 0;
        Scanner scanner = new Scanner(System.in);
        Aerolinea aerolinea = new Aerolinea("AeroMexico", new File("Logo.jpg"));
        Avion avion = new Avion();
        Empleados empleados = new Empleados();
        EmpleadoMostrador empleadoMostrador = new EmpleadoMostrador();
        EmpleadoPuertaSalida empleadoPuertaSalida = new EmpleadoPuertaSalida();
        Vuelos vuelos = new Vuelos();
        Pasajero pasajero = new Pasajero();

        while (isRunning)
        {
            try
            {
                System.out.println(aerolinea.toString());
                System.out.println("***** MENU AEROLINEA *****");
                System.out.println("1.-Seccion Aviones" + "\n2.-Seccion Empleados" + "\n3.-Vuelos."
                        + "\n4.-Estadisticas de la aerolinea" + "\n5.-Otras opciones" + "\n6.-Salir del programa");
                System.out.println("Selecciona una opcion: ");
                op = scanner.nextByte();

                switch (op)
                {
                    case 1:
                        System.out.println("***** SECCION AVIONES *****");
                        System.out.println("1.-Agregar Aviones" + "\n2.-Consultar Aviones" + "\n3.-Despegar Avion"
                                 + "\n4.-Aterrizar Avion");
                        System.out.println("Selecciona una opcion: ");
                        op2 = scanner.nextByte();

                        switch (op2)
                        {
                            case 1 -> aerolinea.agregarAviones();
                            case 2 -> aerolinea.consultarAviones();
                            case 3 -> avion.despegar();
                            case 4 -> avion.aterrizar();
                        }
                        break;
                    case 2:
                        System.out.println("***** MENU EMPLEADOS *****");
                        System.out.println("1.-Añadir Empleados" + "\n2.-Quitar Empleado" +
                                "\n3.-Aumentar sueldo de empleado" + "\n4.-Cambiar numero de telefono de empleado");
                        System.out.println("Selecciona una opcion: ");
                        op2 = scanner.nextByte();

                        switch (op2)
                        {
                            case 1 -> numEmpleados = empleados.agregarEmpleado(numEmpleados);
                            case 2 -> numEmpleados = empleados.quitarEmpleado(numEmpleados);
                            case 3 -> empleados.aumentarSueldo();
                            case 4 -> empleados.cambiarNumeroTelefono();
                            default -> System.err.println("Selecciona una opcion valida");
                        }
                        break;
                    case 3:
                        System.out.println("***** MENU DE VUELOS *****");
                        System.out.println("1.- Reservar vuelo" + "\n2.- Añadir pasajeros." + "\n3.- Consultar pasajeros");
                        System.out.println("Selecciona una opcion: ");
                        op2 = scanner.nextByte();

                        switch (op2)
                        {
                            case 1 -> aerolinea.reservarVuelo();
                            case 2 -> vuelos.añadirPasajeros(aerolinea.getVuelos().size());
                            case 3 -> vuelos.consultarPasajeros();
                            default -> System.err.println("Selecciona una opcion valida!");
                        }

                        break;
                    case 4:
                        aerolinea.consultarEstatidisticas(numEmpleados);
                        break;
                    case 5:
                        System.out.println("1.-Revisar documentos" + "\n2.-Revisar equipaje"
                                + "\n3.-Atender cliente" + "\n4.-Guiar pasajeros" + "\n5.-Pesar equipaje");
                        System.out.println("Seleccione una opcion: ");
                        op2 = scanner.nextByte();

                        switch (op2) {
                            case 1 -> {
                                empleadoMostrador.revisarDocumentos();
                                empleadoPuertaSalida.revisarDocumentos();
                            }
                            case 2 -> empleadoPuertaSalida.revisarEquipaje();
                            case 3 -> {
                                empleadoPuertaSalida.atenderCliente();
                                empleadoMostrador.atenderCliente();
                            }
                            case 4 -> empleadoPuertaSalida.guiarPasajeros();
                            case 5 -> empleadoMostrador.pesarEquipaje();
                        }
                        break;
                    case 6:
                        isRunning = false;
                        break;
                    case 9:
                        System.out.println("MENU SECRETO DE PASAJEROS DESBLOQUEADO!");
                        System.out.println("1.-Entregar documentacion." + "\n2.- Subir a vuelo");
                        System.out.println("Selecciona una opcion:");
                        op2 = scanner.nextByte();

                        switch (op2)
                        {
                            case 1 -> pasajero.entregarDocumentacion();
                            case 2 -> pasajero.subirVuelo();
                            default -> System.err.println("Selecciona una opcion valida!");
                        }

                        break;
                }
            }
            catch (InputMismatchException e)
            {
                System.err.println("Introduce un tipo de dato valido!");
                scanner.nextLine();
            }

        }
    }
}
