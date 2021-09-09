/*
Juan Carlos Cardona
20112050
 */
package Aerolinea;

import AccesoDatos.Serializer;
import AccesoDatos.Writer;
import Excepciones.EmpleadoOutOfBouncesException;
import Excepciones.NullEmpleadoException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Empleados extends Aerolinea implements Serializable
{
    final transient int maxEmpleados = 300;
    private transient Empleados[] empleados = new Empleados[maxEmpleados];
    private int ID;
    private long telefono;
    private Date fechaIngreso;
    private int sueldo;

    transient Scanner scanner = new Scanner(System.in);

    public Empleados(){} //Default constructor

    public Empleados(int ID, long telefono, Date fechaIngreso, int sueldo)
    {
        this.ID = ID;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.sueldo = sueldo;
    }


    public int agregarEmpleado(int numEmpleados)
    {
        /*
        Esta funcion de encarga de agregar objetos de Empleados al arreglo de empleados
        a traves de captura de datos en un ciclo for
        Si se llega a superar el limite de empleados se lanzara la excepcion EmpleadoOutOfBouncesExcepcion
         */

        String op, fechaIngreso;
        int ID, sueldo;
        long telefono;
        Writer writer = new Writer();
        Serializer serializer = new Serializer();

        try
        {
            for(int i = 0; i < empleados.length; i++)
            {
                if(empleados[i] == null)
                {
                    System.out.println("***** Captura de Datos del Nuevo Empleado *****");

                    System.out.println("Introduce su nuevo ID: ");
                    ID = scanner.nextInt();
                    System.out.println("Introduce su numero de telefono:");
                    telefono = scanner.nextLong();
                    System.out.println("Fecha de Ingreso: " + " dd/MM/yy");
                    scanner.nextLine();
                    fechaIngreso = scanner.nextLine();

                    Date date = new SimpleDateFormat("dd/MM/yy").parse(fechaIngreso);

                    System.out.println("Sueldo que recibira: ");
                    sueldo = scanner.nextInt();

                    this.empleados[i] = new Empleados(ID, telefono, date, sueldo);
                    writer.writeEmpleados(empleados);
                    serializer.writeEmpleados(empleados);
                    System.out.println("Nuevo empleado agregado con exito!");
                    numEmpleados++;

                    System.out.println("¿Agregar otro empleado? Si/No");
                    scanner.nextLine();
                    op = scanner.nextLine();

                    if(op.equalsIgnoreCase("no"))
                    {
                        break;
                    }
                    else {
                        if (!op.equalsIgnoreCase("si")) {
                            System.out.println("Selecciona una opcion valida");
                            System.out.println("¿Agregar otro empleado? Si/No");
                            scanner.nextLine();
                            op = scanner.nextLine();
                        }
                    }
                }
                else if(i >= maxEmpleados - 1)
                {
                    EmpleadoOutOfBouncesException e = new EmpleadoOutOfBouncesException("Limite de empleados superado");
                    throw e;
                }

            }
        }
        catch (EmpleadoOutOfBouncesException e)
        {
            System.err.println("Se ha superado el limite de empleados de la aerolinea");
            scanner.nextLine();
        }
        catch (ParseException e)
        {
            System.err.println("No se ha podido hacer la conversion o el formato de fecha no fue introducido correctamente");
        }
        catch (InputMismatchException e)
        {
            System.err.println("Introduce los datos correctamente");
            scanner.nextLine();
        }

        return numEmpleados;
    }

    public int quitarEmpleado(int numEmpleados)
    {
        /*
        Esta funcion se encarga de eliminar al empleado que ingresa el usuario
        Si se encuentra registrado el ID, el index de donde esta guardado ese objeto sera igual a null
        De lo contrario se lanzara una NullEmpleadoException
         */

        int ID;
        Writer writer = new Writer();
        System.out.println("Introduce el ID del empleado para darlo de baja: ");
        ID = scanner.nextInt();

        for(int i = 0; i < empleados.length; i++)
        {
           try
           {
               if(empleados[i] != null)
               {
                   if(empleados[i].ID == ID)
                   {
                       empleados[i] = null;
                       numEmpleados--;
                       System.out.println("Se elimino al empleado " + ID + " con exito");
                       writer.writeEmpleados(empleados);
                       break;
                   }
               }
               else
               {
                   NullEmpleadoException e = new NullEmpleadoException(" ");
                   throw e;
               }
           }
           catch (NullEmpleadoException e)
           {
               System.err.printf("\nEmpleado %d no encontrado\n", ID);
               break;
           }


        }
        return numEmpleados;
    }

    public void aumentarSueldo()
    {

         /*
        Esta funcion se encarga de modificar el sueldo del empleado que se introduce
        Si se encuentra registrado el ID, se pedira el sueldo nuevo que tendra el empleado
        De lo contrario se lanzara una NullEmpleadoException
         */

        int ID, nuevoSueldo;
        System.out.println("Introduce el ID del empleado que deseas aumentar su sueldo: ");
        ID = scanner.nextInt();
        Writer writer = new Writer();

        for(int i = 0; i < empleados.length; i++)
        {
           try
           {
               if(empleados[i] != null)
               {
                   if(empleados[i].ID == ID)
                   {
                       System.out.println(empleados[i]);
                       System.out.println("\nIntroduce el nuevo sueldo del empleado " + ID + " :");
                       nuevoSueldo = scanner.nextInt();
                       this.empleados[i].sueldo = nuevoSueldo;
                       System.out.println("Aumento de sueldo del empleado " + ID + " resulto exitoso.");
                       writer.writeEmpleados(empleados);
                       break;
                   }
               }
               else
               {
                   NullEmpleadoException e = new NullEmpleadoException(" ");
                   throw e;
               }
           }
           catch (NullEmpleadoException e)
           {
               System.err.printf("\nEmpleado %d no encontrado!\n", ID);
               break;
           }
        }
    }

    public void cambiarNumeroTelefono()
    {

        /*
        Esta funcion se encarga de modificar el numero de telefono del empleado que introduce
        Si se encuentra registrado el ID, se pedira el num de telefono nuevo que tendra el empleado
        De lo contrario se lanzara una NullEmpleadoException
         */

        int ID;
        long nuevoTel;
        System.out.println("Introduce el ID del empleado que deseas cambiar su numero de telefono: ");
        ID = scanner.nextInt();
        Writer writer = new Writer();

        for(int i = 0; i < empleados.length; i++)
        {
            try
            {
                if(empleados[i] != null)
                {
                    if(empleados[i].ID == ID)
                    {
                        System.out.println(empleados[i]);
                        System.out.println("\nIntroduce el nuevo numero de telefono: ");
                        nuevoTel = scanner.nextLong();
                        this.empleados[i].telefono = nuevoTel;
                        System.out.println("Numero de telefono cambiado con exito!");
                        writer.writeEmpleados(empleados);
                        break;
                    }
                }
                else
                {
                    NullEmpleadoException e = new NullEmpleadoException(" ");
                    throw e;
                }
            }
            catch (NullEmpleadoException e)
            {
                System.err.printf("\nEmpleado %d no encontrado!\n", ID);
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "ID=" + ID +
                ", telefono=" + telefono +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}

