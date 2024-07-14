package Modelo;

public abstract class Entidad {
    private int posicionX;
    private int posicionY;

    public Entidad(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    // Devuelve la posicion de la Modelo.Entidad
    public int getPosicionX() {
        return this.posicionX;
    }

    public int getPosicionY() {
        return this.posicionY;
    }

    // Cambia la posicion de la Modelo.Entidad
    public void setPosicion(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }


    // Recibe una direccion y se mueve correspondientemente
    protected void mover(Direccion direccion) {
            int[] posicionNueva = direccion.mover(posicionX, posicionY);
            posicionX = posicionNueva[0];
            posicionY = posicionNueva[1];
    }
}

