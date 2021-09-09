package Aerolinea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Avion extends Aerolinea implements Serializable
{
    private int ID;
    private String modelo;
    transient Scanner scanner = new Scanner(System.in);


    public Avion() { }

    public Avion(int ID, String modelo)
    {
        this.ID = ID;
        this.modelo = modelo;
    }

    public void despegar()
    {
        System.out.println("Despegando...");
        System.out.println("Se ha despegado con exito!");
    }

    public void aterrizar()
    {
        System.out.println("Aterrizando...");
        System.out.println("Se ha aterrizado con exito!");
    }


    @Override
    public String toString() {
        return "Avion{" +
                "ID=" + ID +
                ", modelo='" + modelo + '\'' +
                '}';
    }

}

