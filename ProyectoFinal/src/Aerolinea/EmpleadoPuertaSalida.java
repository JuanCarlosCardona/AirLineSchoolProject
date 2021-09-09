package Aerolinea;

import java.util.Date;
import java.util.Random;

public class EmpleadoPuertaSalida extends Empleados implements EmpleadosActividades
{
    public EmpleadoPuertaSalida(int ID, long telefono, Date fechaIngreso, int sueldo)
    {
        super(ID, telefono, fechaIngreso, sueldo);
    }

    public EmpleadoPuertaSalida() {}

    @Override
    public void revisarDocumentos()
    {
        System.out.println("Revisando documentos....");
        Random rand = new Random();
        int numrand = rand.nextInt(100);

        if(numrand <= 10 || numrand >= 50)
        {
            System.out.println("Documentos verificados!");
        }
        else
        {
            System.out.println("Documentos falsos o vencidos!");
            System.out.println("Proceda a llamar a seguridad o que el cliente se retire");
        }
    }


    public void revisarEquipaje()
    {
        System.out.println("Revisando equipaje...");
        Random rand = new Random();
        int numrand = rand.nextInt(100);

        if(numrand <= 50 && numrand >= 30)
        {
            System.out.println("Nada sospechoso en el equipaje.El pasajero puede continuar");
        }
        else
        {
            System.out.println("¡Objeto sospechoso identificado en el equipaje!");
        }
    }


    @Override
    public void atenderCliente()
    {
        System.out.println("¡Hola buenos dias! ¿En que lo puedo ayudar?");
        System.out.println("Resolviendo dudas...");
        System.out.println("Cliente atentido con exito!");
    }

    public void guiarPasajeros()
    {
        System.out.println("¡Hola! el camino hacia tu vuelo es por aqui.");
    }


    @Override
    public String toString() {
        return super.toString();
    }

}

