package Aerolinea;

import AccesoDatos.Serializer;
import Excepciones.BoletosOutOfBounceException;
import Excepciones.NullAvionesException;
import AccesoDatos.Writer;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Aerolinea
{
    protected int numEmpleados = 0;
    protected int numAviones = 0;
    private ArrayList<Avion> aviones = new ArrayList<Avion>();
    private ArrayList<Vuelos> vuelos = new ArrayList<Vuelos>();
    private String nombre;
    private File logo;

    public Aerolinea(String nombre, File logo)
    {
        this.nombre = nombre;
        this.logo = logo;
    }

    public Aerolinea() {}


    public void agregarAviones()
    {
        /*
        Funcion que permite añadir elemenos de la clase Avion al ArrayList aviones
         */
        String resp, modelo;
        int ID;
        Writer writer = new Writer();
        Serializer serializer = new Serializer();

        Scanner scanner = new Scanner(System.in);

        try
        {
            do {
                System.out.println("Introduce el ID del avion: ");
                ID = scanner.nextInt();
                System.out.println("Introduce el modelo del avion: ");
                scanner.nextLine();
                modelo = scanner.nextLine();
                this.aviones.add(new Avion(ID,modelo));
                writer.writeAvion(aviones);
                serializer.writeAvion(aviones);


                System.out.println("¿Desea agregar otro avion?  Si/No");
                resp = scanner.nextLine();

                this.numAviones++;
            }while(resp.equalsIgnoreCase("si"));

            System.out.println("¡Avion/es agregado con exito!");
        }
        catch (InputMismatchException e)
        {
            System.err.println("Introduce los datos de manera correcta!");
        }

    }

    public void reservarVuelo()
    {
        /*
        Esta funcion se encarga de agregar objetos de la clase Vuelos al ArrayList Vuelos
        a traves de la captura de datos por parte del usuario
        Si el numero de boletos a comprar es mayor a 50 se lanza una BoletoOutOfBouncesException
        Si no se encuentran aviones registrados se lanza una NullAvionesException
        y no se podra reservar el vuelo
         */

        String destino, horavuelo, resp, fecha,origen;
        Scanner scanner = new Scanner(System.in);
        int boletos, randAvion;
        Random rand = new Random();
        Aerolinea aerolinea = new Aerolinea();
        Avion avion;
        Writer writer = new Writer();
        Serializer serializer = new Serializer();

        try
        {
            do {
                System.out.println("*** Reservacion de vuelos ***");
                System.out.println("Introduce el origen del vuelo: ");
                origen = scanner.nextLine();
                System.out.println("Introduce el destino: ");
                destino = scanner.nextLine();
                System.out.println("Introduce la fecha del vuelo: dd/MM/yy");
                fecha = scanner.nextLine();

                Date date = new SimpleDateFormat("dd/MM/yy").parse(fecha);

                System.out.println("Introduce la hora del vuelo: ");
                //scanner.nextLine();
                horavuelo = scanner.nextLine();
                System.out.println("Cantidad de boletos: ");
                boletos = scanner.nextInt();

                if(boletos > 50)
                    throw new BoletosOutOfBounceException("No se pueden comprar mas de 50 boletos");

                System.out.println("Reserva de vuelo exitosa!");
                for(int i = 0; i < boletos; i++)
                {
                    int j = rand.nextInt(49 + 1);
                    System.out.println("Se te asigno el asiento " + j);
                }


                if(aviones.size() == 0)
                    throw new NullAvionesException();
                else
                {
                    randAvion = rand.nextInt(aviones.size());
                    avion = aviones.get(randAvion);
                }

                System.out.println("Se te asigno el avion " + avion.toString());

                vuelos.add(new Vuelos(origen,destino,horavuelo,boletos,date,avion));
                writer.writeVuelos(vuelos);
                serializer.writeVuelos(vuelos);

                System.out.println("Agregar otro vuelo? Si/No");
                scanner.nextLine();
                resp = scanner.nextLine();

            }while(resp.equalsIgnoreCase("si"));
            System.out.println(aerolinea.getVuelos().size());
        }
        catch (BoletosOutOfBounceException e)
        {
            e.printStackTrace();
            scanner.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.err.println("Introduce un tipo de dato valido!");
            scanner.nextLine();
        }
        catch (ParseException e)
        {
            System.err.println("No se pudo hacer la conversion!");
        }
        catch (NullAvionesException e)
        {
            System.err.println("No hay aviones registrados para asignar en el vuelo!");
        }
    }

    public void consultarAviones()
    {
        try
        {
            if(aviones.size() == 0)
                throw new NullAvionesException();

            for (Avion aviones : aviones)
                System.out.println(aviones);

        }catch (NullAvionesException e)
        {
            System.err.println("No hay aviones registrados!");
        }
    }

    public void consultarEstatidisticas(int numEmpleados)
    {
        this.numEmpleados = numEmpleados;
        System.out.println("***** MENU ESTADISTICAS *****");
        System.out.printf("\nNumero de empleados: %d\nNumero de aviones: %d\n", this.numEmpleados, this.numAviones);
        Writer.readMayorPuntaje();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(ArrayList<Avion> aviones) {
        this.aviones = aviones;
    }


    @Override
    public String toString() {
        return "Aerolinea{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public ArrayList<Vuelos> getVuelos() {
        return vuelos;
    }
}

