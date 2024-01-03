package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

public class Habitaciones {
    private int capacidad;
    private int tamano;

    private Habitacion[] habitaciones;

    /*Crea el constructor con par�metros que crear� una lista de la capacidad indicada en el par�metro e inicializar� los atributos
    de la clase a los valores correspondientes. */
    public Habitaciones(int capacidad){
        try {
            if(capacidad > 0){//Si la capacidad es al menos 1
                habitaciones = new Habitacion[capacidad];
                for (int i = 0; i < capacidad; i++) {
                    habitaciones[i] = null;
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

    /*Crea el m�todo get que est� sobrecargado y devolver�
    El m�todo sin par�metros, una copia profunda de la colecci�n haciendo uso del m�todo copiaProfundaHabitaciones.
    */
    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    public int getTamano() {
        return this.tamano;
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    private Habitacion[] copiaProfundaHabitaciones(){
        Habitacion[] copiaHabitaciones = new Habitacion[tamano];
        for(int i = 0; i < tamano; i++){
            copiaHabitaciones[i] = new Habitacion(habitaciones[i].getPlanta(),habitaciones[i].getPuerta(),habitaciones[i].getPrecio(), habitaciones[i].getTipoHabitacion());
        }
        return copiaHabitaciones;
    }

    /*
    El m�todo con el par�metro TipoHabitacion, un copia profunda de la colecci�n pero de solo aquellas habitaciones cuyo tipo sea
    el indicado como par�metro.
     */

    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        int habitacionesDelTipo = 0;
        for(int i = 0; i < tamano; i++){//Calculamos el tama�o del array que vamos a devolver
            if(habitaciones[i].getTipoHabitacion().equals(tipoHabitacion)){
                habitacionesDelTipo++;
            }
        }

        Habitacion[] copiaHabitaciones = new Habitacion[habitacionesDelTipo];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el �ndice del array que devolveremos
        for(int i = 0; i < tamano; i++){
            if(habitaciones[i].getTipoHabitacion().equals(tipoHabitacion)){
                copiaHabitaciones[j] = new Habitacion(habitaciones[i].getPlanta(),habitaciones[i].getPuerta(),habitaciones[i].getPrecio(), habitaciones[i].getTipoHabitacion());
                j++;//Desplazamos el �ndice del array que devolveremos
            }
        }
        return copiaHabitaciones;
    }

    /*Se permitir�n insertar habitaciones no nulas al final de la colecci�n sin admitir repetidos.*/
    public void insertar(Habitacion habitacion){
        try{
            if(habitacion != null) {
                if (tamano > 0) {//Si tamano es cero, significa que todav�a no hay ninguna habitaci�n, por lo que se insertar� directamente
                    if (buscar(habitacion) == null) {
                        if (tamano < capacidad) {//Si queda espacio en la capacidad
                            habitaciones[tamano] = habitacion;//Insertamos al final del array
                            tamano++;//Incrementamos el tama�o actual del array
                        } else {
                            throw new IllegalArgumentException("ERROR: No se aceptan m�s habitaciones.");
                        }
                    } else {
                        throw new IllegalArgumentException("ERROR: Ya existe una habitaci�n con ese identificador.");
                    }
                } else {
                    habitaciones[tamano] = habitacion;
                    tamano++;
                }
            }else{
                throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
        }
    }

    private int buscarIndice(Habitacion habitacion){
        if(habitacion == null)
            throw new NullPointerException("ERROR: No se puede buscar una habitaci�n nula.");
        int indice = -1;//Establecemos el valor de �ndice en -1 para evaluar si existe una habitacion con dicho �ndice
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if (habitaciones[i].equals(habitacion)) {
                encontrado = true;
                indice = i;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice){
        return true;
    }

    private boolean CapacidadSuperada(int indice){
        return true;
    }

    /*El m�todo buscar devolver� una habitaci�n si �sta se encuentra en la colecci�n y null en caso contrario.*/
    public Habitacion buscar(Habitacion habitacion){
        if(habitacion == null)
            throw new NullPointerException("ERROR: No se puede buscar una habitaci�n nula.");
        Habitacion habitacionEncontrada = null;
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if(habitaciones[i].equals(habitacion)) {
                encontrado = true;
                habitacionEncontrada = new Habitacion(habitacion.getPlanta(),habitacion.getPuerta(),habitacion.getPrecio(), habitacion.getTipoHabitacion());
            }
        }
        return habitacionEncontrada;
    }

    /*El m�todo borrar, si la habitaci�n se encuentra en la colecci�n, la borrar� y desplazar� los elementos hacia la izquierda para dejar el array compactado.*/
    public void borrar(Habitacion habitacion){
        try{
            int indice = buscarIndice(habitacion);
            if(indice != -1){
                habitaciones[indice] = null;//Borramos la habitaci�n
                desplazarUnaPosicionHaciaIzquierda(indice);//Compactamos el array desde la posici�n de la habitaci�n borrada
            }else{
                throw new IllegalArgumentException("ERROR: No existe ninguna habitaci�n como la indicada.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");
        }

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        while(indice < tamano - 1){
            habitaciones[indice] = habitaciones [indice + 1];
            indice++;
        }
        habitaciones[indice] = null;//Se borra el �ltimo registro de habitaciones para evitar duplicados
        tamano--;//Se reduce el n�mero de habitaciones
    }
}
