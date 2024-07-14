package Modelo;

import static Modelo.Direccion.calcularDireccion;

public class RobotAvanzado extends Entidad {

    private final Jugador objetivo;
    private int[] posicionAnterior;

    public RobotAvanzado(int posicionX, int posicionY, Jugador objetivo) {
        super(posicionX, posicionY);
        this.objetivo = objetivo;
    }

    //Se mueve dos veces en la direccion correspondiente
    @Override
    public void mover(Direccion direccion) {
        super.mover(direccion);
        posicionAnterior = new int[]{super.getPosicionX(),super.getPosicionY()};
        Direccion direccionNueva = calcularDireccion(super.getPosicionX(), super.getPosicionY(), objetivo.getPosicionX(), objetivo.getPosicionY());
        super.mover(direccionNueva);
    }

    public int[] getPosicionAnterior(){
        return posicionAnterior;
    }
}
