package AccesoDatos;

import Aerolinea.Avion;
import Aerolinea.Empleados;
import Aerolinea.Pasajero;
import Aerolinea.Vuelos;

import java.io.*;
import java.util.ArrayList;

public class Serializer implements Serializable, ArchivesInterface
{


    @Override
    public void writeAvion(ArrayList<Avion> aviones)
    {
        Avion a;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream(("Aviones.ser"), false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Avion avion: aviones)
            {
                a = avion;
                objectOutputStream.writeObject(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
           try
           {
               if(fileOutputStream != null)
                   fileOutputStream.close();
               if(objectOutputStream != null)
                   objectOutputStream.close();

           }catch (IOException e)
           {
               System.err.println("Error IO");
           }

        }




    }

    @Override
    public void writeEmpleados(Empleados[] empleados)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream("Empleados.ser",false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Empleados empleado: empleados)
            {
                if(empleado != null)
                    objectOutputStream.writeObject(empleado);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fileOutputStream != null)
                    fileOutputStream.close();
                if(objectOutputStream != null)
                    objectOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void writeVuelos(ArrayList<Vuelos> vuelos)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream("Vuelos.ser",false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Vuelos vuelo: vuelos)
            {
                objectOutputStream.writeObject(vuelo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fileOutputStream != null)
                    fileOutputStream.close();
                if(objectOutputStream != null)
                    objectOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void writePasajeroMayorPuntaje(Pasajero pasajero)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream("PasajeroVIP.ser",false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(pasajero);

        }catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
