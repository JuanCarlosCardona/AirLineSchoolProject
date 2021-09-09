package Aerolinea;

import AccesoDatos.Writer;
import Excepciones.BoletosOutOfBounceException;
import Excepciones.NullPasajeroException;
import Excepciones.NullVuelosException;
import Excepciones.PesoEquipajePassed;

import java.io.Serializable;
import java.util.*;

public class Vuelos implements Serializable
{
    private String origen;
    private String destino;
    private String horaVuelo;
    private int boletos;
    private Avion avion;
    private Date fecha;
    private transient final int maxPasajeros = 50;
    private transient Pasajero[] pasajeros = new Pasajero[maxPasajeros];

    transient Scanner scanner = new Scanner(System.in);

    public Vuelos(String origen,String destino, String horaVuelo, int boletos, Date fecha, Avion avion)
    {
        this.origen = origen;
        this.destino = destino;
        this.horaVuelo = horaVuelo;
        this.boletos = boletos;
        this.fecha = fecha;
        this.avion = avion;
    }

    public Vuelos() {}

    public void añadirPasajeros(int vuelos)
    {
        /*
        Esta funcion se encarga de agregar objetos de Pasajero al array pasajeros a traves de la captura por el usuario
        En caso de que no haya vuelos registrados se lanzara una NullVuelosException impidiendo reservar uno
        Si el equipaje es mayor a 15 se lanzara una PesoEquipajePassed
        Si el num de asiento es mayor a 50 se lanzara una BoletosOutOfBouncesException
        Si se llega al limite de pasajeros se lanza un ArrayOutOfIndex
         */

        int ID, numAsiento;
        float pesoEquipaje;
        String nombre, opcion;
        int puntos = 100, agregarPuntos = 0;

        for(int i = 0; i < pasajeros.length; i++)
        {
            try
            {
                if(vuelos == 0) throw new NullVuelosException();

                if(pasajeros[i] == null)
                {
                    System.out.println("Introduce el ID del pasajero: ");
                    ID = scanner.nextInt();
                    System.out.println("Nombre del pasajero: ");
                    scanner.nextLine();
                    nombre = scanner.nextLine();
                    System.out.println("Peso del equipaje: ");
                    pesoEquipaje = scanner.nextFloat();
                    System.out.println("Numero de asiento: ");
                    numAsiento = scanner.nextInt();

                    if(numAsiento > 50) throw new BoletosOutOfBounceException(" ");
                    if(pesoEquipaje > 15.00) throw new PesoEquipajePassed();

                    this.pasajeros[i] = new Pasajero(ID, nombre, numAsiento, pesoEquipaje, puntos);
                    System.out.println("La operacion resulto exitosa!");

                    //Busqueda de pasajero que se encuentre mas de una vez
                    for(Pasajero p: pasajeros)
                    {
                        if(p != null)
                        {
                            if(nombre.equalsIgnoreCase(p.getNombre()))
                            {
                                agregarPuntos = puntos + p.getPuntos();
                                p.setPuntos(agregarPuntos);
                            }

                            if(p.getPuntos() > 500)
                                System.out.println("Se realizara un descuento del 15% al pasajero " + p.getNombre() + " por tener mas de 500 puntos");
                        }
                    }

                    Writer.searchMayorPuntaje(pasajeros);

                    System.out.println("¿Agregar otro pasajero? Si/No");
                    scanner.nextLine();
                    opcion = scanner.nextLine();

                    if(opcion.equalsIgnoreCase("no"))
                    {
                        break;
                    }
                    else {
                        if (!opcion.equalsIgnoreCase("si")) {
                            System.out.println("Selecciona una opcion valida");
                            System.out.println("¿Agregar otro paajero? Si/No");
                            opcion = scanner.nextLine();
                        }
                    }
                }
                else if(i == maxPasajeros - 1)
                    throw new ArrayIndexOutOfBoundsException();
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.err.println("Se ha superado el limite de pasajeros!");
                break;
            }
            catch (BoletosOutOfBounceException e)
            {
                System.err.println("No hay mas de 50 asientos en un vuelo. Verifique el num de asiento!");
                scanner.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.err.println("Introduce los datos correctamente");
                scanner.nextLine();
            }
            catch (PesoEquipajePassed e)
            {
                System.err.println("La carga maxima de equipaje es de 15 kilogramos!");
                scanner.nextLine();
            }
            catch (NullVuelosException e)
            {
                System.err.println("No hay vuelos registrados!");
                break;
            }
        }
    }

    public void consultarPasajeros()
    {
        /*
        A traves de un for loop consulta todos los pasajeros registrado en el array pasajeros
        Si no hay pasajeros o no se encuentran mas, se lanzara una NullPasajeroException
         */
        for(int i = 0; i < maxPasajeros; i++)
        {
               try
               {
                   if(this.pasajeros[i] != null)
                       System.out.println(this.pasajeros[i].toString());
                   else
                        throw new NullPasajeroException(" ");

               }
               catch (NullPasajeroException e)
               {
                   System.err.println("No se encontraron mas pasajeros registrados!");
                   scanner.nextLine();
                   break;
               }
        }
    }


    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public String getHoraVuelo() { return horaVuelo; }
    public void setHoraVuelo(String horaVuelo) { this.horaVuelo = horaVuelo; }
    public int getBoletos() { return boletos; }
    public void setBoletos(int boletos) { this.boletos = boletos; }
    public Pasajero[] getPasajeros() { return pasajeros; }

    @Override
    public String toString() {
        return "Vuelos{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", horaVuelo='" + horaVuelo + '\'' +
                ", boletos=" + boletos +
                ", avion=" + avion +
                ", fecha=" + fecha +
                '}';
    }
}

