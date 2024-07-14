package Modelo;

import java.util.*;

import static Modelo.Direccion.calcularDireccion;

public class Juego {
    private Entidad[][] tablero;
    public Jugador jugador;
    private HashSet<Entidad> enemigos;
    //private HashSet<ArrayList<Entidad>> celdasOcupadas;
    private int nivel;
    //private int cantidadEnemigos;
    private boolean termino;
    private boolean tpSeguroActivo;

    public Juego(int cantFilas, int cantColumnas) {
        this.enemigos = new HashSet<>();
        this.tablero = new Entidad[cantFilas][cantColumnas];
        //this.celdasOcupadas = new HashSet<>();
        int mitadFilas = cantFilas / 2;
        int mitadColumans = cantColumnas / 2;
        Jugador jugador = new Jugador(mitadFilas, mitadColumans);
        tablero[mitadFilas][mitadColumans] = jugador;
        //celdasOcupadas.add(tablero[mitadFilas][mitadColumans]);
        this.jugador = jugador;
        generarEnemigos(cantFilas, cantColumnas);
        tpSeguroActivo = false;
        termino = false;
        nivel = 0;
        jugador.usosRestantes = 2;
    }

    //TO DO
    public void reiniciar() {

        this.enemigos = new HashSet<>();
        this.tablero = new Entidad[tablero.length][tablero[0].length];
        int mitadFilas = tablero.length / 2;
        int mitadColumans = tablero[0].length / 2;
        Jugador jugador = new Jugador(mitadFilas, mitadColumans);
        tablero[mitadFilas][mitadColumans] = jugador;
        this.jugador = jugador;
        generarEnemigos(tablero.length, tablero[0].length);
        tpSeguroActivo = false;
        termino = false;
        nivel=0;
        jugador.usosRestantes = 2;

    }

    public int getJugadorX() {
        return jugador.getPosicionX();
    }

    public int getJugadorY() {
        return jugador.getPosicionY();
    }

    public int getTpSeguros() {
        return jugador.getTpSeguros();
    }


    private void generarEnemigos(int cantFilas, int cantColumnas) {
        int cantEnemigosBasicos = 3 + this.nivel;
        int cantEnemigosAvanzados = 2 + this.nivel;
        int enemigosTotales = cantEnemigosAvanzados + cantEnemigosBasicos;
        Random random = new Random();
        for (int i = 0; i < enemigosTotales; i++) {
            int posicionX = random.nextInt(cantFilas);
            int posicionY = random.nextInt(cantColumnas);
            if (cantEnemigosBasicos > 0) {
                RobotBasico basico = new RobotBasico(posicionX, posicionY);
                tablero[posicionX][posicionY] = basico;
                enemigos.add(basico);
                cantEnemigosBasicos--;
            } else if (cantEnemigosAvanzados > 0) {
                RobotAvanzado avanzado = new RobotAvanzado(posicionX, posicionY, jugador);
                tablero[posicionX][posicionY] = avanzado;
                enemigos.add(avanzado);
                cantEnemigosAvanzados--;
            }
        }
    }


    // Se encarga de hacer que las Entidades del tablero se muevan segun corresponda
    public void movimiento(int posicionX, int posicionY) {
        moverEntidad(jugador, posicionX, posicionY);
        moverEnemigos();
        tpSeguroActivo = false;
    }


    //Adquiere fila y columnas de la celda clickeada, calcula la diferencia entre la celda clickeada y la celda del jugador y a traves de matematicas magicas, se determina la celda adyacente mas cerca hacia la direccion del click

    public void celdaSeleccionada(int fila, int columna) {
        int posX = jugador.getPosicionX();
        int posY = jugador.getPosicionY();

        int deltaX = fila - posX;
        int deltaY = columna - posY;

        if (tpSeguroActivo) {
            tpSeguro(fila, columna);
        } else {
            //calcula la posicion mas cercana al jugador
            int nuevaPosX = posX + Integer.compare(deltaX, 0);
            int nuevaPosY = posY + Integer.compare(deltaY, 0);

            movimiento(nuevaPosX, nuevaPosY);

        }
        tpSeguroActivo = false;

    }

    public void activarTpSeguro() {
        if (jugador.usosRestantes > 0) {
            tpSeguroActivo = true;
        } else {
            System.out.println("no hay mas usos");
        }
    }

    // Mueve una entidad a su nueva posicion segun la posicion final del jugador
    private void moverEntidad(Entidad entidad, int posicionFinalJugadorX, int posicionFinalJugadorY) {
        int posicionAnteriorX = entidad.getPosicionX();
        int posicionAnteriorY = entidad.getPosicionY();
        Direccion direccion = calcularDireccion(posicionAnteriorX,posicionAnteriorY,posicionFinalJugadorX, posicionFinalJugadorY);
        if (!noEstaEnLosLimites(new int[]{posicionFinalJugadorX,posicionFinalJugadorY},direccion)){
            return;
        }
        tablero[posicionAnteriorX][posicionAnteriorY] =null;
        entidad.mover(direccion);
        if (entidad instanceof RobotAvanzado){
            int[] posicionIntermedia = ((RobotAvanzado) entidad).getPosicionAnterior();
            if (tablero[posicionIntermedia[0]][posicionIntermedia[1]] instanceof Explosion){
                explosion(tablero[posicionIntermedia[0]][posicionIntermedia[1]],entidad);
                return;
            }
        }
        int posActualX = entidad.getPosicionX();
        int posActualY = entidad.getPosicionY();
        Entidad entidadColisionada = tablero[posActualX][posActualY];
        if (entidadColisionada!=null) {
            if (entidadColisionada == jugador){
                termino = true;
            }else {
                explosion(entidad, tablero[posActualX][posActualY]);

            }
        }else{
            tablero[posActualX][posActualY] = entidad;
        }
    }


    // Se encarga de mover a todos los enemigos del tablero a la posicion del jugador
    private void moverEnemigos() {
        HashSet<Entidad> copiaIterable = new HashSet<>(enemigos);
        for (Entidad entidad : copiaIterable) {
            moverEntidad(entidad, jugador.getPosicionX(), jugador.getPosicionY());
        }
    }


    // Teletransporta al jugador a una posicion aleatoria del tablero
    public void tpRandom() {
        int cantFilas = this.tablero.length;
        int cantColumnas = this.tablero[0].length;
        Random random = new Random();
        int posicionX = random.nextInt(cantFilas);
        int posicionY = random.nextInt(cantColumnas);
        tablero[getJugadorX()][getJugadorY()] = null;
        jugador.teleportacion(posicionX, posicionY);
        tablero[posicionX][posicionY] = jugador;
        moverEnemigos();
    }

    // Teletransporta al jugador a una posicion desocupada del tablero
    public void tpSeguro(int posicionX, int posicionY) {
        if (tablero[posicionX][posicionY] == null) {
            tablero[posicionX][posicionY] = jugador;
            jugador.teleportacionSegura(posicionX, posicionY);
            moverEnemigos();
        }
    }

    // Mueve a los enemigos luego de que el jugador se quede en el lugar
    public void esperar() {
        moverEnemigos();
    }

    // Verifica el estado del tablero al final del turno y actua segun corresponda
    public void validarEstado() {
        terminarNivel();
    }

    public boolean reiniciarJuego() {
        termino = false;
        return termino;
    }


    public boolean terminarJuego() {
        return termino;
    }


    // Añade una explosion al tablero
    private void explosion(Entidad e1, Entidad e2) {
        int posicionX = e1.getPosicionX();
        int posicionY = e1.getPosicionY();
        Explosion explosion = new Explosion(posicionX, posicionY);
        tablero[posicionX][posicionY] = explosion;
        this.enemigos.remove(e1);
        this.enemigos.remove(e2);
        this.enemigos.add(explosion);
    }

    // Si el tablero no tiene enemeigos, se termino el nivel
    private void terminarNivel() {
        if (soloQuedanExplosiones()) {
            this.jugador.terminoNivel();
            this.nivel++;
            this.tablero = new Entidad[tablero.length][tablero[0].length];
            tablero[tablero.length / 2][tablero[0].length / 2] = jugador;
            enemigos.clear();
            generarEnemigos(tablero.length, tablero[0].length);
        }
    }

    private boolean soloQuedanExplosiones(){
        for (Entidad e : enemigos){
            if (!(e instanceof Explosion)){
                return false;
            }
        }
        return true;
    }

    // Verifica que el movimiento a ejecutar no se salga del tablero
    private boolean noEstaEnLosLimites(int[] coordenadas, Direccion direccion) {
        return direccion.noEstaEnLosLimites(coordenadas[0], coordenadas[1], this.tablero.length, this.tablero[0].length);
    }

    public HashSet<Entidad> posicionesOcupadas() {
        return this.enemigos;
    }
}