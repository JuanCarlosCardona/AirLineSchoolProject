package Aerolinea;

import java.io.Serializable;

public class Pasajero implements Serializable
{
    private int IDvuelo;
    private String nombre;
    private int numAsiento;
    private float pesoEquipaje;
    private int puntos;

    public Pasajero(int IDvuelo, String nombre, int numAsiento, float pesoEquipaje, int puntos)
    {
        this.setIDvuelo(IDvuelo);
        this.setNombre(nombre);
        this.numAsiento = numAsiento;
        this.pesoEquipaje = pesoEquipaje;
        this.puntos = puntos;
    }

    public Pasajero() {} // Defualt constructor

    public void entregarDocumentacion()
    {
        System.out.println("Entregando documentos...");
        System.out.println("Documentos entregados!");
    }

    public void subirVuelo()
    {
        System.out.println("Subiendo al vuelo " + getIDvuelo() + "..." );
        System.out.println("Se ha entrado al vuelo");
    }


    public int getIDvuelo() {
        return IDvuelo;
    }
    public void setIDvuelo(int IDvuelo) {
        this.IDvuelo = IDvuelo;
    }
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getNumAsiento() {return numAsiento;}
    public void setNumAsiento(int numAsiento) {this.numAsiento = numAsiento;}
    public float getPesoEquipaje() {return pesoEquipaje;}
    public void setPesoEquipaje(float pesoEquipaje) {this.pesoEquipaje = pesoEquipaje;}

    @Override
    public String toString() {
        return "Pasajero{" +
                "IDvuelo=" + IDvuelo +
                ", nombre='" + nombre + '\'' +
                ", numAsiento=" + numAsiento +
                ", pesoEquipaje=" + pesoEquipaje +
                ", puntos=" + puntos +
                '}';
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}

