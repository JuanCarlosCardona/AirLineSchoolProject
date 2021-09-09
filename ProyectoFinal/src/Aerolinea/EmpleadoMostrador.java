package Aerolinea;

import java.util.Date;
import java.util.Random;

public class EmpleadoMostrador extends Empleados implements  EmpleadosActividades
{
    EmpleadoMostrador(int ID, long telefono, Date fechaIngreso, int sueldo)
    {
        super(ID, telefono, fechaIngreso, sueldo);
    }

    public EmpleadoMostrador ()  {}

    @Override
    public void atenderCliente()
    {
        System.out.println("¡Hola buenos dias! ¿En que lo puedo ayudar?");
        System.out.println("Resolviendo dudas...");
        System.out.println("Cliente atentido con exito!");
    }

    @Override
    public void revisarDocumentos()
    {
        System.out.println("Revisando documentos....");
        Random rand = new Random();

        if(rand.nextInt(100) <= 10 || rand.nextInt(100) >= 50)
        {
            System.out.println("Documentos verificados!");
        }
        else
        {
            System.out.println("Documentos falsos o vencidos!");
            System.out.println("Proceda a llamar a seguridad o que el cliente se retire");
        }
    }

    public void pesarEquipaje()
    {
        Random random = new Random();
        float pesoEquipaje = random.nextFloat() * (19 - 1);
        Vuelos vuelos = new Vuelos();
        int pasajero = random.nextInt(50 + 1);


        try
        {
            if(vuelos.getPasajeros()[pasajero] != null)
            {
                System.out.println("El peso del equipaje del pasajero: " + vuelos.getPasajeros()[pasajero].getNombre() +
                        "\nEs de: " + pesoEquipaje + " kilogramos.");
                vuelos.getPasajeros()[pasajero].setPesoEquipaje(pesoEquipaje);
            }
            else
                throw new ArrayIndexOutOfBoundsException();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("No se encontro registro del pasajero.");
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}

