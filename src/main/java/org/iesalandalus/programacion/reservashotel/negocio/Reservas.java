package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.*;

import java.time.LocalDateTime;

public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] reservas;

    /*Crea el constructor con par�metros que crear� una lista de la capacidad indicada en el par�metro e inicializar� los atributos de la clase a los valores correspondientes.*/
    public Reservas(int capacidad){
        try {
            if(capacidad > 0){//Si la capacidad es al menos 1
                reservas = new Reserva[capacidad];
                for (int i = 0; i < capacidad; i++) {
                    reservas[i] = null;
                }
                this.tamano = 0;
                this.capacidad = capacidad;
            }else{
                throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La capacidad no puede ser nula.");
        }
    }

    /*Crea el m�todo get que devolver� una copia profunda de la colecci�n haciendo uso del m�todo copiaProfundaReservas.*/
    public Reserva[] get(){
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas(){
        Reserva[] copiaReservas = new Reserva[tamano];
        for(int i = 0; i < tamano; i++){
            copiaReservas[i] = new Reserva(reservas[i].getHuesped(), reservas[i].getHabitacion(), reservas[i].getRegimen(), reservas[i].getFechaInicioReserva(), reservas[i].getFechaFinReserva(), reservas[i].getNumeroPersonas());
        }
        return copiaReservas;
    }

    public int getTamano() {
        return this.tamano;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    /*Se permitir�n insertar reservas no nulas al final de la colecci�n sin admitir repetidos.*/
    public void insertar(Reserva reserva){
        try{
            if(reserva != null) {
                if (tamano > 0) {//Si tamano es cero, significa que todav�a no hay ning�n hu�sped, por lo que se insertar� directamente
                    if (buscar(reserva) == null) {
                        if (tamano < capacidad) {//Si queda espacio en la capacidad
                            reservas[tamano] = reserva;//Insertamos al final del array
                            tamano++;//Incrementamos el tama�o actual del array
                        } else {
                            throw new IllegalArgumentException("ERROR: No se aceptan m�s reservas.");
                        }
                    } else {
                        throw new IllegalArgumentException("ERROR: Ya existe una reserva igual.");
                    }
                } else {
                    reservas[tamano] = reserva;
                    tamano++;
                }
            }else{
                throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
    }

    private int buscarIndice(Reserva reserva){
        if(reserva == null)
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        int indice = -1;//Establecemos el valor de �ndice en -1 para evaluar si existe una reserva con dicho �ndice
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if (reservas[i].equals(reserva)) {
                encontrado = true;
                indice = i;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice){
        return true;
    }

    private boolean capacidadSuperada(int indice){
        return true;
    }

    /*El m�todo buscar devolver� una reserva si �sta se encuentra en la colecci�n y null en caso contrario.*/
    public Reserva buscar(Reserva reserva){
        if(reserva == null)
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        Reserva reservaEncontrada = null;
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if(reservas[i].equals(reserva)) {
                encontrado = true;
                reservaEncontrada = new Reserva(reserva.getHuesped(), reserva.getHabitacion(), reserva.getRegimen(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), reserva.getNumeroPersonas());
            }
        }
        return reservaEncontrada;
    }

    /*El m�todo borrar, si la reserva se encuentra en la colecci�n, la borrar� y desplazar� los elementos hacia la izquierda
    para dejar el array compactado.
    */
    public void borrar(Reserva reserva){
        try{
            int indice = buscarIndice(reserva);
            if(indice != -1){
                reservas[indice] = null;//Borramos la reserva
                desplazarUnaPosicionHaciaIzquierda(indice);//Compactamos el array desde la posici�n de la reserva borrada
            }else{
                throw new IllegalArgumentException("ERROR: No existe ninguna reserva como la indicada.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        while(indice < tamano - 1){
            reservas[indice] = reservas[indice + 1];
            indice++;
        }
        reservas[indice] = null;//Se borra el �ltimo registro de una reserva para evitar duplicados
        tamano--;//Se reduce el n�mero de reservas
    }

    /*El m�todo getReservas que est� sobrecargado y devolver� una colecci�n de las reservas realizadas por el hu�sped pasado
    por par�metro o una colecci�n de las reservas realizadas para el tipo de habitaci�n indicada como par�metro.
    */
    public Reserva[] getReservas(Huesped huesped){
        if(huesped == null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
        int reservasHuesped = 0;
        for(int i = 0; i < tamano; i++){//Calculamos el tama�o del array que vamos a devolver
            if(reservas[i].getHuesped().equals(huesped)){
                reservasHuesped++;
            }
        }

        Reserva[] copiaReservas = new Reserva[reservasHuesped];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el �ndice del array que devolveremos
        for(int i = 0; i < tamano; i++){
            if(reservas[i].getHuesped().equals(huesped)){
                copiaReservas[j] = new Reserva(reservas[i].getHuesped(), reservas[i].getHabitacion(), reservas[i].getRegimen(), reservas[i].getFechaInicioReserva(), reservas[i].getFechaFinReserva(), reservas[i].getNumeroPersonas());
                j++;//Desplazamos el �ndice del array que devolveremos
            }
        }
        return copiaReservas;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        if(tipoHabitacion == null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitaci�n nula.");
        }
        int reservasDelTipo = 0;
        for(int i = 0; i < tamano; i++){//Calculamos el tama�o del array que vamos a devolver
            if(reservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)){
                reservasDelTipo++;
            }
        }

        Reserva[] copiaReservas = new Reserva[reservasDelTipo];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el �ndice del array que devolveremos
        for(int i = 0; i < tamano; i++){
            if(reservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)){
                copiaReservas[j] = new Reserva(reservas[i].getHuesped(), reservas[i].getHabitacion(), reservas[i].getRegimen(), reservas[i].getFechaInicioReserva(), reservas[i].getFechaFinReserva(), reservas[i].getNumeroPersonas());
                j++;//Desplazamos el �ndice del array que devolveremos
            }
        }
        return copiaReservas;
    }


    /*El m�todo getReservasFuturas que devolver� una colecci�n de las reservas realizadas para la habitaci�n indicada como
    par�metro y que sean posteriores a la fecha de hoy.
    */
    public Reserva[] getReservasFuturas(Habitacion habitacion){
        if(habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitaci�n nula.");
        }
        int reservasHabitacion = 0;
        for (int i = 0; i < tamano; i++) {//Calculamos el tama�o del array que vamos a devolver
            if (reservas[i].getHabitacion().equals(habitacion)) {//Si la habitaci�n coincide
                if (reservas[i].getFechaInicioReserva().atStartOfDay().isAfter(LocalDateTime.now())) {//Si la fecha es posterior a hoy
                    reservasHabitacion++;
                }
            }
        }

        Reserva[] copiaReservas = new Reserva[reservasHabitacion];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el �ndice del array que devolveremos
        for (int i = 0; i < tamano; i++) {
            if (reservas[i].getHabitacion().equals(habitacion)) {
                if (reservas[i].getFechaInicioReserva().atStartOfDay().isAfter(LocalDateTime.now())) {
                    copiaReservas[j] = new Reserva(reservas[i].getHuesped(), reservas[i].getHabitacion(), reservas[i].getRegimen(), reservas[i].getFechaInicioReserva(), reservas[i].getFechaFinReserva(), reservas[i].getNumeroPersonas());
                    j++;//Desplazamos el �ndice del array que devolveremos
                }
            }
        }
        return copiaReservas;
    }
}
