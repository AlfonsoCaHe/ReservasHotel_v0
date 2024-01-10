package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    INSERTAR_HUESPED(1,"Insertar hu�sped."),
    BUSCAR_HUESPED(2, "Buscar hu�sped."),
    BORRAR_HUESPED(3,"Borrar hu�sped."),
    MOSTRAR_HUESPEDES(4,"Mostrar hu�spedes."),
    INSERTAR_HABITACION(5,"Insertar habitaci�n."),
    BUSCAR_HABITACION(6,"Buscar habitaci�n."),
    BORRAR_HABITACION(7,"Borrar habitaci�n."),
    MOSTRAR_HABITACIONES(8,"Mostrar habitaciones."),
    INSERTAR_RESERVA(9,"Insertar reserva."),
    ANULAR_RESERVA(10, "Anular reserva."),
    MOSTRAR_RESERVAS(11, "Mostrar reservas."),
    CONSULTAR_DISPONIBILIDAD(12, "Consultar disponibilidad."),
    SALIR(13,"Salir.");

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
