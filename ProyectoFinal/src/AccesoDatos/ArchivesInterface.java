package AccesoDatos;

import java.util.ArrayList;
import Aerolinea.Avion;
import Aerolinea.Empleados;
import Aerolinea.Pasajero;
import Aerolinea.Vuelos;

public interface ArchivesInterface
{
    void writeAvion(ArrayList<Avion> aviones);
    void writeEmpleados(Empleados[] empleados);
    void writeVuelos(ArrayList<Vuelos> vuelos);
    void writePasajeroMayorPuntaje(Pasajero pasajero);
}
