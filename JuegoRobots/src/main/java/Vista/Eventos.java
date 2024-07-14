package Vista;

import Modelo.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class Eventos {
    Botones botonera;
    VistaTablero vistaTablero;
    Juego juego;
    Scene scene;

    private EventHandler<ActionEvent> tpRandom;
    private EventHandler<ActionEvent> tpSeguro;
    private EventHandler<ActionEvent> esperar;
    private EventHandler<KeyEvent> teclado;
    private EventHandler<MouseEvent> click;


    public EventHandler<ActionEvent> getTpRandom(){
        return tpRandom;
    }
    public void setTpRandom(EventHandler<ActionEvent> tpRandom){
        this.tpRandom = tpRandom;
    }

    public EventHandler<ActionEvent> getTpSeguro(){
        return tpSeguro;
    }

    public void setTpSeguro(EventHandler<ActionEvent> tpSeguro){
        this.tpSeguro = tpSeguro;
    }

    public EventHandler<ActionEvent> getEsperar(){
        return esperar;
    }

    public void setEsperar(EventHandler<ActionEvent> esperar){
        this.esperar = esperar;
    }

    public EventHandler<KeyEvent> getTeclado(){
        return teclado;
    }

    public void setTeclado(EventHandler<KeyEvent> teclado){
        this.teclado = teclado;
    }

    public EventHandler<MouseEvent> getClick(){
        return click;
    }

    public void setClick(EventHandler<MouseEvent> click){
        this.click = click;
    }

    public void crearEventos(Scene scene, Juego juego, VistaTablero vistaTablero, Botones botonera){
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.botonera = botonera;

        setTpRandom(hola -> {
            juego.tpRandom();
            juego.validarEstado();
            actualizar();
        });

        setTpSeguro(hola -> {
            juego.activarTpSeguro();
            juego.validarEstado();
            actualizar();
        });

        setEsperar(hola -> {
            juego.esperar();
            juego.validarEstado();
            actualizar();
        });

        setTeclado(this::eventoTeclado);
        scene.setOnKeyReleased(getTeclado());

        setClick(this::eventoClick);
    }

    public void mensajeDerrota(){
        DerrotaPopUp alerta = new DerrotaPopUp();
        alerta.mostrarPopup();
    }

    public void actualizar(){
        vistaTablero.actualizar();
        botonera.actualizar();
        if (juego.terminarJuego()){
            mensajeDerrota();
            reiniciar();
        }
    }

    public void reiniciar(){
        vistaTablero.reiniciar();
        juego.validarEstado();
        juego.reiniciarJuego();
    }

    public void eventoTeclado(KeyEvent event){
        KeyCode tecla = event.getCode();
        switch (tecla){
            //Arriba izq
            case Q:
                juego.movimiento(juego.getJugadorX() -1, juego.getJugadorY() - 1);
                break;
            //Arriba
            case W:
                juego.movimiento(juego.getJugadorX(), juego.getJugadorY() - 1);
                break;
            //Arriba der
            case E:
                juego.movimiento(juego.getJugadorX() +1, juego.getJugadorY() - 1);
                break;
            //Izq
            case A:
                juego.movimiento(juego.getJugadorX() -1, juego.getJugadorY());
                break;
            //Derecha
            case D:
                juego.movimiento(juego.getJugadorX() + 1, juego.getJugadorY());
                break;
            //Abajo
            case S:
                juego.movimiento(juego.getJugadorX(), juego.getJugadorY() +1);
                break;
            //Abajo izq
            case Z:
                juego.movimiento(juego.getJugadorX() - 1, juego.getJugadorY() + 1);
                break;
            //Abajo der
            case X:
                juego.movimiento(juego.getJugadorX() + 1, juego.getJugadorY() + 1);
                break;
            case R:
                reiniciar();
                break;


        }
        juego.validarEstado();
        actualizar();
    }

    public void eventoClick(MouseEvent click){

        //Divide el las coordenadas del click por el tamaño del tablero para obtener la celda exacta
        int columna = (int) (click.getY() / 15);
        int fila = (int) (click.getX() / 15);

        juego.celdaSeleccionada(fila, columna);
        juego.validarEstado();
        actualizar();
    }

}


