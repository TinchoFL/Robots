package Vista;

import Modelo.Juego;
import Modelo.Entidad;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaTablero extends GridPane {

    private final Juego modelo;
    GridPane tablero;
    private int[] dimension;
    private static final int TAMANIO_BALDOZA = 15;

    public VistaTablero(Juego modelo, int numero_filas, int numero_columnas) {
        tablero = new GridPane();
        this.modelo = modelo;


        dimension = new int[]{numero_filas, numero_columnas};

        pintarTablero();

        actualizarPosiciones();

    }

    //Dibuja el Tablero
    public void pintarTablero(){
        Color color;
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[0]; j++) {
                if ((i + j) % 2 == 0) {
                    color = Color.web("#cbb7ae");
                } else {
                    color = Color.web("#716677");
                }
                Rectangle baldoza = new Rectangle(TAMANIO_BALDOZA, TAMANIO_BALDOZA, color);
                tablero.add(baldoza, i, j);
            }

        }
    }


    //Le asigna imagen a las entidades y las dibuja en su posicion.
    public void actualizarPosiciones () {
        Image imagenJugador = new Image("file:src/main/java/Vista/Images/pj1.png");
        ImageView jugadorImageView = new ImageView(imagenJugador);
        jugadorImageView.setFitWidth(TAMANIO_BALDOZA);
        jugadorImageView.setFitHeight(TAMANIO_BALDOZA);
        int jugadorX = modelo.jugador.getPosicionX();
        int jugadorY = modelo.jugador.getPosicionY();
        tablero.add(jugadorImageView, jugadorX, jugadorY);
        for (Entidad enemigos : modelo.posicionesOcupadas()){
            if (enemigos instanceof Modelo.RobotBasico){
                Image enemigoImage = new Image("file:src/main/java/Vista/Images/robot1.png");
                ImageView enemigoImageView = new ImageView(enemigoImage);
                enemigoImageView.setFitWidth(TAMANIO_BALDOZA);
                enemigoImageView.setFitHeight(TAMANIO_BALDOZA);
                int enemigoX = enemigos.getPosicionX();
                int enemigoY = enemigos.getPosicionY();
                tablero.add(enemigoImageView,enemigoX,enemigoY);
            }else if (enemigos instanceof Modelo.RobotAvanzado){
                Image enemigoImage = new Image("file:src/main/java/Vista/Images/robot2.png");
                ImageView enemigoImageView = new ImageView(enemigoImage);
                enemigoImageView.setFitWidth(TAMANIO_BALDOZA);
                enemigoImageView.setFitHeight(TAMANIO_BALDOZA);
                int enemigoX = enemigos.getPosicionX();
                int enemigoY = enemigos.getPosicionY();
                tablero.add(enemigoImageView,enemigoX,enemigoY);
            }else{
                Image enemigoImage = new Image("file:src/main/java/Vista/Images/explosion.png");
                ImageView enemigoImageView = new ImageView(enemigoImage);
                enemigoImageView.setFitWidth(TAMANIO_BALDOZA);
                enemigoImageView.setFitHeight(TAMANIO_BALDOZA);
                int enemigoX = enemigos.getPosicionX();
                int enemigoY = enemigos.getPosicionY();
                tablero.add(enemigoImageView,enemigoX,enemigoY);
            }

        }


    }

    //Borra el tablero y lo vuelve a imprimir con las nuevas posiciones
    public void actualizar(){
        tablero.getChildren().clear();
        pintarTablero();
        actualizarPosiciones();
    }


    public void reiniciar(){
        tablero.getChildren().clear();
        pintarTablero();
        modelo.reiniciar();
        actualizarPosiciones();
    }

    public void crearEvento(Eventos eventos){
        tablero.setOnMouseClicked(eventos.getClick());
    }

    public GridPane getTablero(){
        return tablero;
    }
}
