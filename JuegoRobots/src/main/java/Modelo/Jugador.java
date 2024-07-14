package Modelo;

public class Jugador extends Entidad {

    public int usosRestantes;

    private final int posicionInicialX;
    private final int posicionInicialY;

    public Jugador(int posicionX, int posicionY) {
        super(posicionX, posicionY);
        this.posicionInicialX = posicionX;
        this.posicionInicialY = posicionY;
    }

    // Si quedan usos restantes, se teletransporta a la posicion deseada
    protected void teleportacionSegura(int posicionX, int posicionY) {
        if (usosRestantes > 0) {
            teleportacion(posicionX, posicionY);
            usosRestantes--;
        }
    }

    // Se teletransporta a una posicion aleatoria
    protected void teleportacion(int posicionX, int posicionY) {
        setPosicion(posicionX, posicionY);
    }

    protected void terminoNivel() {
        this.usosRestantes++; // Al comenzar cada nivel se le otorga un uso adicional.
        setPosicion(posicionInicialX, posicionInicialY);
    }

    public int getTpSeguros() {
        return usosRestantes;
    }
}

