package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

public class Huespedes {

    private int capacidad;
    private int tamano;
    private Huesped[] huespedes;

    /*Crea el constructor con par�metros que crear� una lista de la capacidad indicada en el par�metro e inicializar� los atributos
    de la clase a los valores correspondientes.
    */
    public Huespedes(int capacidad){
        try{
                if(capacidad > 0) {
                    this.tamano = 0;
                    this.capacidad = capacidad;
                    huespedes = new Huesped[capacidad];
                    for (int i = 0; i < capacidad; i++) {
                        huespedes[i] = null;
                    }
                }else{
                    throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
                }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La capacidad no puede ser nula.");
        }

    }

    /*El m�todo get devolver� una copia profunda de la colecci�n haciendo uso del m�todo copiaProfundaHuespedes.*/
    public Huesped[] get(){
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes(){
        Huesped[] copiaHuespedes = new Huesped[tamano];
        for(int i = 0; i < tamano; i++){
            copiaHuespedes[i] = new Huesped(huespedes[i].getNombre(), huespedes[i].getDni(), huespedes[i].getCorreo(), huespedes[i].getTelefono(), huespedes[i].getFechaNacimiento());
        }
        return copiaHuespedes;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    /*Se permitir�n insertar hu�spedes no nulos al final de la colecci�n sin admitir repetidos.*/
    public void insertar(Huesped huesped){
        try{
            if(huesped != null) {
                if (tamano > 0) {//Si tamano es cero, significa que todav�a no hay ning�n hu�sped, por lo que se insertar� directamente
                    if (buscar(huesped) == null) {
                        if (tamano < capacidad) {//Si queda espacio en la capacidad
                            huespedes[tamano] = huesped;//Insertamos al final del array
                            tamano++;//Incrementamos el tama�o actual del array
                        } else {
                            throw new IllegalArgumentException("ERROR: No se aceptan m�s hu�spedes.");
                        }
                    } else {
                        throw new IllegalArgumentException("ERROR: Ya existe un hu�sped con ese dni.");
                    }
                } else {
                    huespedes[tamano] = huesped;
                    tamano++;
                }
            }else{
                throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }
    }

    private int buscarIndice(Huesped huesped){
        if(huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");
        int indice = -1;//Establecemos el valor de �ndice en -1 para evaluar si existe un huesped con dicho �ndice
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if (huespedes[i].equals(huesped)) {
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
        boolean capacidadSuperada = (tamano < capacidad);
        return capacidadSuperada;
    }

    /*El m�todo buscar devolver� un hu�sped si �ste se encuentra en la colecci�n y null en caso contrario.*/
    public Huesped buscar(Huesped huesped){
        if(huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");
        Huesped huespedEncontrado = null;
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if(huespedes[i].equals(huesped)) {
                encontrado = true;
                huespedEncontrado = new Huesped(huesped.getNombre(), huesped.getDni(), huesped.getCorreo(), huesped.getTelefono(), huesped.getFechaNacimiento());
            }
        }
        return huespedEncontrado;
    }

    /*El m�todo borrar, si el hu�sped se encuentra en la colecci�n, lo borrar� y desplazar� los elementos hacia la izquierda para
    dejar el array compactado.*/
    public void borrar(Huesped huesped){
        try{
            int indice = buscarIndice(huesped);
            if(indice != -1){
                huespedes[indice] = null;//Borramos al hu�sped
                desplazarUnaPosicionHaciaIzquierda(indice);//Compactamos el array desde la posici�n del hu�sped borrado
            }else{
                throw new IllegalArgumentException("ERROR: No existe ning�n hu�sped como el indicado.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        while(indice < tamano - 1){
            huespedes[indice] = huespedes [indice + 1];
            indice++;
        }
        huespedes[indice] = null;//Se borra el �ltimo registro de hu�spede para evitar duplicados
        tamano--;//Se reduce el n�mero de hu�spedes
    }
}
