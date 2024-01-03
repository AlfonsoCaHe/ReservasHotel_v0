package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR(1,"Salir."),
    INSERTAR_HUESPED(2,"Insertar hu�sped."),
    BUSCAR_HUESPED(3, "Buscar hu�sped."),
    BORRAR_HUESPED(4,"Borrar hu�sped."),
    MOSTRAR_HUESPEDES(5,"Mostrar hu�spedes."),
    INSERTAR_HABITACION(6,"Insertar habitaci�n."),
    BUSCAR_HABITACION(7,"Buscar habitaci�n."),
    BORRAR_HABITACION(8,"Borrar habitaci�n."),
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
