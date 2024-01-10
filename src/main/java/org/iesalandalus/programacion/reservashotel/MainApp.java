package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class MainApp {
    public static int CAPACIDAD = 10;
    private static Huespedes huespedes;
    private static Habitaciones habitaciones;
    private static Reservas reservas;

    /*Crea el m�todo main que nos mostrar� el men� de la aplicaci�n, nos pedir� una opci�n y la ejecutar� mientras no elijamos
    la opci�n salir. En caso de salir, la aplicaci�n mostrar� un mensaje de despedida.*/
    public static void main(String[] args) throws OperationNotSupportedException {
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
        System.out.println("************************************************************");
        System.out.println("Bienvenido al sistema de gesti�n de nuestra cadena hotelera.");
        System.out.println("************************************************************\n");
        int opcion = 0;
        do {
            try {
                opcion = Consola.elegirOpcion();
                ejecutarOpcion(opcion);
                System.out.println("\n************************************************************\n");
            }catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e){
                System.out.println("ERROR: Operaci�n no permitida. "+e.getMessage());
            }
        } while (opcion != 13);

        System.out.println("\t\tFIN DE LA APLICACI�N. Les esperamos pronto.\n");
        System.out.println("************************************************************");
    }

    private static void ejecutarOpcion(int opcion) throws OperationNotSupportedException {
        switch(opcion){
            case 1:
                System.out.println("\n************************************************************");
                System.out.println("Insertar hu�sped");
                System.out.println("************************************************************\n");
                insertarHuesped();
                break;
            case 2:
                System.out.println("\n************************************************************");
                System.out.println("Buscar hu�sped");
                System.out.println("************************************************************\n");
                buscarHuesped();
                break;
            case 3:
                System.out.println("\n************************************************************");
                System.out.println("Borrar hu�sped");
                System.out.println("************************************************************\n");
                borrarHuesped();
                break;
            case 4:
                System.out.println("\n************************************************************");
                System.out.println("Mostrar hu�spedes");
                System.out.println("************************************************************\n");
                mostrarHuespedes();
                break;
            case 5:
                System.out.println("\n************************************************************");
                System.out.println("Insertar habitaci�n");
                System.out.println("************************************************************\n");
                insertarHabitacion();
                break;
            case 6:
                System.out.println("\n************************************************************");
                System.out.println("Buscar habitaci�n");
                System.out.println("************************************************************\n");
                buscarHabitacion();
                break;
            case 7:
                System.out.println("\n************************************************************");
                System.out.println("Borrar habitaci�n");
                System.out.println("************************************************************\n");
                borrarHabitacion();
                break;
            case 8:
                System.out.println("\n************************************************************");
                System.out.println("Mostrar habitaciones");
                System.out.println("************************************************************\n");
                mostrarHabitaciones();
                break;
            case 9:
                System.out.println("\n************************************************************");
                System.out.println("Insertar Reserva");
                System.out.println("************************************************************\n");
                insertarReserva();
                break;
            case 10:
                System.out.println("\n************************************************************");
                System.out.println("Anular reserva");
                System.out.println("************************************************************\n");
                anularReserva();
                break;
            case 11:
                System.out.println("\n************************************************************");
                System.out.println("Mostrar Reservas");
                System.out.println("************************************************************\n");
                mostrarReservas();
                break;
            case 12:
                System.out.println("\n************************************************************");
                System.out.println("Comprobar habitaciones disponibles");
                System.out.println("************************************************************\n");
                TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                System.out.println("Introduzca la fecha de inicio de reserva:");
                LocalDate fechaInicioReserva = Consola.leerFecha();
                System.out.println("Introduzca la fecha de fin de reserva:");
                LocalDate fechaFinReserva = Consola.leerFecha();
                Habitacion habitacion = consultarDisponibilidad(tipoHabitacion, fechaInicioReserva, fechaFinReserva);
                if(habitacion == null){
                    System.out.println("\nNo existen habitaciones del tipo "+tipoHabitacion.toString()+" en las fechas escogidas.");
                }else{
                    System.out.println("\n"+habitacion.toString());
                }
        }
    }

    /*Crea el m�todo insertarHuesped que nos pedir� los datos de un hu�sped, haciendo uso de la clase Consola, y lo insertar� en
    la colecci�n correspondiente si es posible o informar� del problema en caso contrario.*/
    private static void insertarHuesped(){
        Huesped huesped = null;
        try{
            huesped = Consola.leerHuesped();
            huespedes.insertar(huesped);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /*Crea el m�todo buscarHuesped que nos pedir� el dni de un hu�sped, haciendo uso de la clase Consola, mostr�ndonos a dicho
    huesped o informar� de que no existe o informar� del problema en caso acontecido.*/
    private static void buscarHuesped(){
        try{
            if(huespedes.getTamano() != 0) {
                System.out.print("Introduzca el dni que desea buscar: ");
                String dni = Entrada.cadena();
                System.out.println(" ");
                Huesped huesped = Consola.leerClientePorDni(dni);
                boolean encontrado = false;
                Huesped[] huespedesBusqueda = huespedes.get();
                for (int i = 0; i < huespedes.getTamano(); i++) {
                    if (huespedesBusqueda[i].equals(huesped)) {
                        encontrado = true;
                        huesped = huespedesBusqueda[i];
                    }
                }
                if(encontrado) {
                    System.out.println(huesped.toString());
                } else {
                    System.out.println("No se ha encontrado un huesped con ese dni.");
                }
            }else{
                System.out.println("No existen registros de hu�spedes.");
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /*Crea el m�todo borrarHuesped que nos pedir� el dni de un hu�sped, haciendo uso de la clase Consola, borr�ndolo si es
    posible o informar� del problema en caso contrario.*/
    private static void borrarHuesped(){
        try{
            if(huespedes.getTamano() != 0) {
                System.out.print("Introduzca el dni que desea buscar: ");
                String dni = Entrada.cadena();
                System.out.println(" ");
                Huesped huesped = Consola.leerClientePorDni(dni);
                boolean encontrado = false;
                Huesped[] huespedesBusqueda = huespedes.get();
                if(huespedes.getTamano() == 1){
                    if (huespedesBusqueda[0].equals(huesped)) {
                        Reserva[] reservasAnulables = getReservasAnulables(reservas.getReservas(huesped));
                        if(reservasAnulables.length == 0) {
                            huespedes.borrar(huesped);
                            System.out.println("Huesped borrado correctamente.");
                        }else{
                            System.out.println("No se puede borrar un huesped con reservas pendientes.");
                        }
                    }else{
                        System.out.println("No se ha encontrado un huesped con ese dni.");
                    }
                }else {
                    for (int i = 0; i < huespedes.getTamano() && !encontrado; i++) {
                        if (huespedesBusqueda[i].equals(huesped)) {
                            encontrado = true;
                            huesped = huespedesBusqueda[i];
                        }
                    }
                    if (encontrado) {
                        Reserva[] reservasAnulables = getReservasAnulables(reservas.getReservas(huesped));
                        if(reservasAnulables.length == 0) {
                            huespedes.borrar(huesped);
                            System.out.println("Huesped borrado correctamente.");
                        }else{
                            System.out.println("No se puede borrar un huesped con reservas pendientes.");
                        }
                    } else {
                        System.out.println("No se ha encontrado un huesped con ese dni.");
                    }
                }
            }else{
                System.out.println("No existen registros de hu�spedes.");
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /*Crea el m�todo mostrarHuespedes que mostrar� todos los hu�spedes almacenados si es que hay o si no, nos informar� de que
    no hay hu�spedes.*/
    private static void mostrarHuespedes(){
        if(huespedes.getTamano()==0){
            System.out.println("No existen registros de hu�spedes.");
        }else{
            for(Huesped h: huespedes.get()){
                System.out.println(h.toString());
            }
        }
    }

    /*Crea el m�todo insertarHabitacion que nos pedir� los datos de una habitaci�n, haciendo uso de la clase Consola, y lo
    insertar� en la colecci�n correspondiente si es posible o informar� del problema en caso contrario.*/
    private static void insertarHabitacion(){
        Habitacion habitacion = null;
        try{
            habitacion = Consola.leerHabitacion();
            habitaciones.insertar(habitacion);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /*Crea el m�todo buscarHabitacion que nos pedir� el n�mero de puerta y el n�mero de planta de una habitaci�n, haciendo uso
    de la clase Consola, mostr�ndonos a dicha habitaci�n o informar� de que no existe o informar� del problema en caso
    acontecido.*/
    private static void buscarHabitacion(){
        try {
            if(habitaciones.getTamano() != 0) {//Si existen habitaciones pasaremos a buscar
                System.out.print("Introduzca el n�mero de planta: ");
                int plantaBuscada = Entrada.entero();
                System.out.println(" ");
                System.out.print("Introduzca el n�mero de habitaci�n: ");
                int puertaBuscada = Entrada.entero();
                System.out.println(" ");
                Habitacion habitacion = new Habitacion(plantaBuscada, puertaBuscada, Habitacion.MIN_PRECIO_HABITACION);
                Habitacion[] habitacionesBusqueda = habitaciones.get();
                boolean encontrado = false;
                if(habitaciones.getTamano() == 1){//Si solo hay una habitaci�n
                    if (habitacionesBusqueda[0].equals(habitacion)) {
                        System.out.println(habitacionesBusqueda[0].toString());
                    }else{
                        System.out.println("No se ha encontrado una habitaci�n con el identificador "+plantaBuscada+puertaBuscada+".");
                    }
                }else {
                    for (int i = 0; i < habitaciones.getTamano(); i++) {
                        if (habitacionesBusqueda[i].equals(habitacion)) {
                            encontrado = true;
                            System.out.println(habitacionesBusqueda[i].toString());
                        }
                    }
                    if(!encontrado){
                        System.out.println("No se ha encontrado una habitaci�n con el identificador "+plantaBuscada+puertaBuscada+".");
                    }
                }
            }else{
                System.out.println("No existen registros de habitaciones.");
            }
        }catch(IllegalArgumentException e){
            System.out.println("ERROR NO ESPERADO.");
        }
    }

    /*Crea el m�todo borrarHabitacion que nos pedir� el n�mero de puerta y el n�mero de planta de una habitaci�n, haciendo uso
    de la clase Consola, borr�ndolo si es posible o informar� del problema en caso contrario.*/
    private static void borrarHabitacion(){
        try{
            if(habitaciones.getTamano() != 0) {
                System.out.print("Introduzca el n�mero de planta: ");
                int plantaBuscada = Entrada.entero();
                System.out.println(" ");
                System.out.print("Introduzca el n�mero de habitaci�n: ");
                int puertaBuscada = Entrada.entero();
                System.out.println(" ");
                Habitacion habitacion = new Habitacion(plantaBuscada, puertaBuscada, Habitacion.MIN_PRECIO_HABITACION);
                Habitacion[] habitacionesBusqueda = habitaciones.get();
                boolean encontrado = false;
                if(reservas.getReservasFuturas(habitacion).length == 0) {
                    if (habitaciones.getTamano() == 1) {
                        if (habitacionesBusqueda[0].equals(habitacion)) {
                            habitaciones.borrar(habitacion);
                            System.out.println("Habitaci�n borrada correctamente.");
                        } else {
                            System.out.println("No se ha encontrado una habitaci�n con el identificador " + plantaBuscada + puertaBuscada + ".");
                        }
                    } else {
                        for (int i = 0; i < habitaciones.getTamano(); i++) {
                            if (habitacionesBusqueda[i].equals(habitacion)) {
                                encontrado = true;
                                habitacion = habitacionesBusqueda[i];
                            }
                        }
                        if (encontrado) {
                            habitaciones.borrar(habitacion);
                            System.out.println("Habitaci�n borrada correctamente.");
                        } else {
                            System.out.println("No se ha encontrado una habitaci�n con el identificador " + plantaBuscada + puertaBuscada + ".");
                        }
                    }
                }else{
                    System.out.println("No se puede borrar una habitaci�n con reservas pendientes.");
                }
            }else{
                System.out.println("No existen registros de habitaciones.");
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /*Crea el m�todo mostrarHabitaciones que mostrar� todos las habitaciones almacenadas si es que hay o si no, nos informar�
    de que no hay habitaciones.*/
    private static void mostrarHabitaciones(){
        if(habitaciones.getTamano()==0){
            System.out.println("No existen registros de habitaciones.");
        }else{
            for(Habitacion h: habitaciones.get()){
                System.out.println(h.toString());
            }
        }
    }

    /*Crea el m�todo insertarReserva que nos pedir� los datos de una reserva, haciendo uso de la clase Consola, y lo insertar�
    en la colecci�n correspondiente si es posible o informar� del problema en caso contrario. Debe tenerse en cuenta que para
    poder insertar una reserva debe haber disponibilidad del tipo de habitaci�n deseada por el hu�sped en el periodo indicado.*/
    private static void insertarReserva(){
        try{
            Reserva r = Consola.leerReserva();
            Habitacion h = habitaciones.buscar(r.getHabitacion());
            reservas.insertar(new Reserva(r.getHuesped(), h, r.getRegimen(), r.getFechaInicioReserva(), r.getFechaFinReserva(), r.getNumeroPersonas()));
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    /*Crea el m�todo listarReservas que est� sobrecargado, mostrando todas las reservas almacenadas (si es que hay) del hu�sped,
    del tipo de habitaci�n o de la fecha indicada como par�metro. Si no hay, nos informar� de que no hay reservas.*/
    private static void listarReservas(Huesped huesped){
        Reserva[] reservasHuesped = reservas.getReservas(huesped);
        for(int i = 0; i < reservasHuesped.length; i++){
            System.out.println(reservasHuesped[i].toString());
        }
    }

    private static void listarReservas(TipoHabitacion tipoHabitacion){
        Reserva[] reservasTipoHabitacion = reservas.getReservas(tipoHabitacion);
        for(int i = 0; i < reservasTipoHabitacion.length; i++){
            System.out.println(reservasTipoHabitacion[i].toString());
        }
    }

    private static void listarReservas(LocalDate fecha){
        Reserva[] reservasFecha = reservas.get();
        for(int i = 0; i < reservasFecha.length; i++){
            if(reservasFecha[i].getFechaInicioReserva().isBefore(fecha) && reservasFecha[i].getFechaFinReserva().isAfter(fecha)){
                System.out.println(reservasFecha[i].toString());
            }else{
                if(reservasFecha[i].getFechaInicioReserva().isEqual(fecha) || reservasFecha[i].getFechaFinReserva().isEqual(fecha)){
                    System.out.println(reservasFecha[i].toString());
                }
            }
        }
    }

    /*Crea el m�todo getReservasAnulables que de la colecci�n de reservas recibida como par�metro, devolver� aquellas que sean
    anulables, es decir, cuya fecha de inicio de la reserva a�n no haya llegado.*/
    private static Reserva[] getReservasAnulables(Reserva[] reservasAAnular){
        Reserva[] reserva = null;
        try {
            if(reservasAAnular != null) {
                int contador = 0;//Variable que determinar� el tama�o del nuevo array a devolver.
                for (int i = 0; i < reservasAAnular.length; i++) {//Si la fecha de inicio de la reserva es posterior, todav�a se puede anular.
                    if (reservasAAnular[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                        contador++;
                    }
                }
                reserva = new Reserva[contador];//Establecer el tama�o total del array a devolver
                contador = 0;//Reiniciamos el contador para poder utilizarlo en las posiciones del nuevo array
                for (int i = 0; i < reservasAAnular.length; i++) {
                    if (reservasAAnular[i].getFechaInicioReserva().isAfter(LocalDate.now())) {//Si la fecha de inicio es posterior, se a�ade al array a devolver.
                        reserva[contador] = reservasAAnular[i];
                        contador++;
                    }
                }
            }else{
                throw new IllegalArgumentException("ERROR: el array reservasAAnular es nulo.");
            }
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println("ERROR INESPERADO.");
        }
        return reserva;
    }

    /*Crea el m�todo anularReserva que pedir� el dni del hu�sped del que se desea anular una reserva (haciendo uso de la clase
    Consola), obteniendo de todas las reservas de dicho hu�sped aquellas que sean anulables. En el caso de que no tenga ninguna
    anulable deber� de informarse de dicha circunstancia. Si solo tiene una reserva anulable deber� confirmarse de que realmente
    se quiere anular. Y por �ltimo, en el caso de que el hu�sped tenga m�s de una reserva anulable, deber�n ser listadas
    precedidas por un n�mero para que el usuario elija la reserva que desea anular. */
    private static void anularReserva(){
        System.out.print("Introduzca el dni del cliente: ");
        Huesped huesped = huespedes.buscar(Consola.leerClientePorDni(Entrada.cadena()));
        System.out.println(" ");
        Reserva[] reservaHuesped = getReservasAnulables(reservas.getReservas(huesped));
        if(reservaHuesped.length != 0){
            int opcion;
            if(reservaHuesped.length == 1){
                System.out.println("El hu�sped solo dispone de 1 reserva anulable: ");
                System.out.println(reservaHuesped[0].toString());
                System.out.println("�Desea realmente anularla?\n\t1. S�\n\t2. No");
                do{
                    opcion = Entrada.entero();
                }while(opcion < 1 || opcion > 2);
                if(opcion == 1){
                    reservas.borrar(reservaHuesped[0]);
                    System.out.println("Reserva anulada correctamente.");
                }else{
                    System.out.println("No se ha borrado ninguna reserva.");
                }
            }else{
                for(int i = 0; i < reservaHuesped.length; i++){
                    System.out.println(""+(i+1)+". "+reservaHuesped[i].toString());
                }
                do{
                    System.out.print("\nSeleccione una reserva para anularla o escoja 0 para cancelar: ");
                    opcion = Entrada.entero();
                }while(opcion < 0 || opcion > reservaHuesped.length);
                if(opcion != 0){
                    reservas.borrar(reservaHuesped[opcion - 1]);
                    System.out.println("Reserva anulada correctamente.");
                }else{
                    System.out.println("No se ha borrado ninguna reserva.");
                }
            }
        }else{
            System.out.println("No existen reservas anulables para el huesped.");
        }
    }

    /*Crea el m�todo mostrarReservas que mostrar� todas las reservas almacenadas si es que hay o si no, nos informar� de que no
    hay reservas. */
    private static void mostrarReservas(){
        int opcion = 0;
        if(reservas.getTamano()==0){
            System.out.println("No existen registros de reservas.");
        }else{
            do{
                System.out.println("1. Mostrar todas las reservas.");
                System.out.println("2. Mostrar todas las reservas de un huesped.");
                System.out.println("3. Mostrar todas las reservas de un tipo de habitaci�n.");
                System.out.println("4. Mostrar todas las reservas en una fecha.");
                System.out.println("0. Cancelar.");
                System.out.print("Escoja una opci�n: ");
                opcion = Entrada.entero();
                System.out.println(" ");
            } while(opcion < 0 || opcion > 4);
            switch(opcion){
                case 1:
                    for(Reserva r: reservas.get()){//Mostrar todas las reservas
                        System.out.println(r.toString());
                    }
                    break;
                case 2:
                    System.out.print("Introduzca el dni del hu�sped: ");
                    String dni = Entrada.cadena();
                    System.out.print(" ");
                    Huesped huesped = Consola.leerClientePorDni(dni);
                    boolean encontrado = false;
                    Huesped[] huespedesBusqueda = huespedes.get();
                    for (int i = 0; i < huespedes.getTamano(); i++) {
                        if (huespedesBusqueda[i].equals(huesped)) {
                            encontrado = true;
                            huesped = huespedesBusqueda[i];
                        }
                    }
                    if(encontrado) {
                        if(reservas.getReservas(huesped).length != 0) {
                            listarReservas(huesped);
                        }else{
                            System.out.println("No se han encontrado reservas del huesped.");
                        }
                    } else {
                        System.out.println("No se ha encontrado un huesped con ese dni.");
                    }
                    break;
                case 3:
                    TipoHabitacion tipo = Consola.leerTipoHabitacion();
                    listarReservas(tipo);
                    break;
                case 4:
                    LocalDate fecha = Consola.leerFecha();
                    listarReservas(fecha);
                    break;
                default:
            }
        }
    }

    /*Crea el m�todo consultarDisponibilidad que devuelve una habitaci�n del tipo indicado por par�metro y que est� disponible
    entre las fechas de inicio y fin de la reserva, tambi�n indicados por par�metro.*/
    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva){
        Habitacion habitacion = null;

        try{
            Habitacion[] habitacionesDelTipo = habitaciones.get(tipoHabitacion);//Creamos una copia profunda de las habitaciones del hotel que sean del tipo solicitado.
            Reserva[] reservasTipoHabitacion = reservas.getReservas(tipoHabitacion);//Creamos una copia profunda de las reservas que tienen por tipo de habitaci�n el solicitado.

            Habitaciones habitacionesDisponibles = new Habitaciones(habitacionesDelTipo.length);//Creamos un objeto de la clase habitaciones que contendr� las habitaciones del tipo.
            for(int i = 0; i < habitacionesDelTipo.length; i++){//Realizamos una copia profunda de las habitaciones insert�ndolas en el array de habitaciones disponibles.
                habitacionesDisponibles.insertar(new Habitacion(habitacionesDelTipo[i].getPlanta(),habitacionesDelTipo[i].getPuerta(),habitacionesDelTipo[i].getPrecio(), habitacionesDelTipo[i].getTipoHabitacion()));
            }

            for(int i = 0; i < reservasTipoHabitacion.length; i++){//Recorreremos el array de reservas del tipo y eliminaremos del array de habitaciones disponibles las que no lo est�n.
                if(fechaInicioReserva.isBefore(reservasTipoHabitacion[i].getFechaFinReserva()) && fechaFinReserva.isAfter(reservasTipoHabitacion[i].getFechaInicioReserva())){//Si la fecha de inicio de la reserva es anterior a la fecha de la reserva y la fecha de fin de la reserva es mayor que la de inicio. La habitaci�n estar� ocupada. Por tanto se borra.
                    if(habitacionesDisponibles.buscar(reservasTipoHabitacion[i].getHabitacion()) != null) {//Nos aseguramos que la habitaci�n no haya sido borrada ya
                        habitacionesDisponibles.borrar(reservasTipoHabitacion[i].getHabitacion());
                    }
                }else{
                    if(fechaInicioReserva.equals(reservasTipoHabitacion[i].getFechaInicioReserva())){//Si las fechas de inicio coindicen, la habitaci�n no estar� disponible. Por tanto se borra.
                        if(habitacionesDisponibles.buscar(reservasTipoHabitacion[i].getHabitacion()) != null) {//Nos aseguramos que la habitaci�n no haya sido borrada ya
                            habitacionesDisponibles.borrar(reservasTipoHabitacion[i].getHabitacion());
                        }
                    }else{//Solo queda que la fecha de inicio fuera posterior a la fecha de inicio de la reserva
                        if(fechaInicioReserva.isAfter(reservasTipoHabitacion[i].getFechaInicioReserva()) && fechaInicioReserva.isBefore(reservasTipoHabitacion[i].getFechaFinReserva())){//Si la fecha de inicio es posterior a la de inicio de la reserva y anterior de a la de fin de la reserva, no estar� disponible. Por tanto se borra.
                            if(habitacionesDisponibles.buscar(reservasTipoHabitacion[i].getHabitacion()) != null) {//Nos aseguramos que la habitaci�n no haya sido borrada ya
                                habitacionesDisponibles.borrar(reservasTipoHabitacion[i].getHabitacion());
                            }
                        }
                    }
                }
            }

            if(habitacionesDisponibles.getTamano() > 0) {//Si nos queda alguna habitaci�n del tipo disponible entre las fechas.
                Habitacion[] temporal = habitacionesDisponibles.get();
                habitacion = temporal[0];//Nos quedamos con la primera habitaci�n disponible
            }

        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return habitacion;
    }

    /*Crea el m�todo getNumElementosNoNulos que devolver� el n�mero de elementos del array que no son nulos.*/
    private static int getNumElementosNoNulos(){
        int numero = 0;
        return numero;
    }
}
