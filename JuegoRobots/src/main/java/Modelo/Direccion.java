package Modelo;

public enum Direccion {
        ARRIBA(0,-1),
        ABAJO(0,1),
        IZQ(-1,0),
        DER(1,0),
        ARRIBA_IZQ(-1,-1),
        ARRIBA_DER(1,-1),
        ABAJO_IZQ(-1,1),
        ABAJO_DER(1,1);

        private final int movimientoFila;
        private final int movimientoColumna;
        Direccion(int movimientoFila, int movimientoColumna) {
            this.movimientoFila = movimientoFila;
            this.movimientoColumna = movimientoColumna;
        }

        // Recibe la cordenada actual y la coordenada objetivo y se mueve segun corresponda
    public static Direccion calcularDireccion(int posActualX,int posActualY, int posObjetivoX, int posObjetivoY) {
            if (posActualX > posObjetivoX) {
                    if (posActualY > posObjetivoY) {
                            return Direccion.ARRIBA_IZQ;
                    } else if (posActualY < posObjetivoY) {
                            return Direccion.ABAJO_IZQ;
                    } else {
                            return Direccion.IZQ;
                    }
            } else if (posActualX < posObjetivoX) {
                    if (posActualY > posObjetivoY) {
                            return Direccion.ARRIBA_DER;
                    } else if (posActualY < posObjetivoY) {
                            return Direccion.ABAJO_DER;
                    } else {
                            return Direccion.DER;
                    }
            } else {
                    if (posActualY > posObjetivoY) {
                            return Direccion.ARRIBA;
                    } else {
                            return Direccion.ABAJO;
                    }
            }
    }

    public int[] mover(int posicionX, int posicionY) {
        return new int[]{posicionX+ this.movimientoFila, posicionY+ this.movimientoColumna};
        }

    public boolean noEstaEnLosLimites(int posicionX,int posicionY,int cantFilas, int cantColumnas) {
        boolean sePuedeMoverEnX = true;
        boolean sePuedeMoverEnY = true;
        if (posicionX + movimientoFila < -1 || posicionX + this.movimientoFila > cantFilas) { sePuedeMoverEnX=false;}
        if (posicionY + movimientoColumna < -1 || posicionY + this.movimientoColumna > cantColumnas) { sePuedeMoverEnY=false;}
        return sePuedeMoverEnX && sePuedeMoverEnY;
    }
}