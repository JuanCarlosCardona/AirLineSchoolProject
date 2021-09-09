package AccesoDatos;

import Aerolinea.Avion;
import Aerolinea.Empleados;
import Aerolinea.Pasajero;
import Aerolinea.Vuelos;
import Excepciones.NullEmpleadoException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Writer implements ArchivesInterface
{


    public Writer() {}

    @Override
    public void writeAvion(ArrayList<Avion> aviones)
    {

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Aviones.txt", false);

            PrintWriter printWriter = new PrintWriter(fileOutputStream);

            for(Avion avion: aviones)
                printWriter.println(avion.toString());

            printWriter.close();


        }
        catch (FileNotFoundException e)
        {
            System.err.println("Archivo no encontrado!");
        }

    }

    @Override
    public void writeEmpleados(Empleados[] empleados)
    {

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Empleados.txt", false);

            PrintWriter printWriter = new PrintWriter(fileOutputStream);

            for(Empleados empleado: empleados)
            {
                if(empleado != null)
                    printWriter.println(empleado.toString());
            }
            //printWriter.println(empleados[empleadosIndex].toString());
            printWriter.close();

        }
        catch (FileNotFoundException e)
        {
            System.err.println("Archivo no encontrado!");
        }

    }

    @Override
    public void writeVuelos(ArrayList<Vuelos> vuelos)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Vuelos.txt", false);

            PrintWriter printWriter = new PrintWriter(fileOutputStream);

            for(Vuelos vuelo: vuelos)
                printWriter.println(vuelo.toString());
           // printWriter.println(vuelos.get(vuelosIndex).toString());
            printWriter.close();

        }
        catch (FileNotFoundException e)
        {
            System.err.println("Archivo no encontrado!");
        }
    }

    @Override
    public void writePasajeroMayorPuntaje(Pasajero pasajero)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("PasajeroVIP.txt",false);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(pasajero.toString());
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Pasajero searchMayorPuntaje(Pasajero[] pasajeros)
    {
        int puntaje = Integer.MIN_VALUE;
        Pasajero pasajero = null;
        Writer writer = new Writer();
        Serializer serializer = new Serializer();

        try
        {
                for (Pasajero p : pasajeros)
                {
                    if(p != null)
                    {
                        if (p.getPuntos() > puntaje) {
                            puntaje = p.getPuntos();
                            pasajero = p;
                        }
                    }
                }
            writer.writePasajeroMayorPuntaje(pasajero);
                serializer.writePasajeroMayorPuntaje(pasajero);
                return pasajero;
        } catch (NullPointerException e) {
            System.err.println("No se encontraron pasajeros registrados!");
            e.printStackTrace();
        }
        return pasajero;
    }

    public static void readMayorPuntaje()
    {
        try
        {
            Scanner scanner = new Scanner(new File("PasajeroVIP.txt"));
            String s = "";

            while(scanner.hasNext())
            {
                s += scanner.next();
            }
            scanner.close();
            System.out.println("Pasajero VIP: " + s);
        } catch (FileNotFoundException e) {
            System.err.println("No se encontro el archivo de PasajeroVIP.txt");
        }

    }



}
