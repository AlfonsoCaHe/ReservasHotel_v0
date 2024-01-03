package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR(1,"Salir."),
    INSERTAR_HUESPED(2,"Insertar huésped."),
    BUSCAR_HUESPED(3, "Buscar huésped."),
    BORRAR_HUESPED(4,"Borrar huésped."),
    MOSTRAR_HUESPEDES(5,"Mostrar huéspedes."),
    INSERTAR_HABITACION(6,"Insertar habitación."),
    BUSCAR_HABITACION(7,"Buscar habitación."),
    BORRAR_HABITACION(8,"Borrar habitación."),
    MOSTRAR_HABITACIONES(9,"Mostrar habitaciones."),
    INSERTAR_RESERVA(10,"Insertar reserva."),
    ANULAR_RESERVA(11, "Anular reserva."),
    MOSTRAR_RESERVAS(12, "Mostrar reservas."),
    CONSULTAR_DISPONIBILIDAD(12, "Consultar disponibilidad.");

    private int cardinalOpcion;
    private String cadenaAMostrar;
    private Opcion(int cardinalOpcion, String cadenaAMostrar){
        this.cardinalOpcion = cardinalOpcion;
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cardinalOpcion+".- "+cadenaAMostrar;
    }
}
