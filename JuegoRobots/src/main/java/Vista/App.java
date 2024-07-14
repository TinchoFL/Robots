package Vista;

import Modelo.Juego;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Juego juego;
    VistaTablero vistatablero;
    Botones botonera;
    Scene scene;

    @Override
    public void start(Stage primaryStage) {

        PedidorDeDatos pedidordedatos = new PedidorDeDatos();
        pedidordedatos.showAndWait();

        int numero_filas = pedidordedatos.getNumeroFilas();
        int numero_columnas = pedidordedatos.getNumeroColumnas();

        Juego juego = new Juego(numero_filas, numero_columnas);
        this.juego = juego;

        Eventos eventos = new Eventos();

        VBox ventana = new VBox();
        ventana.setAlignment(Pos.CENTER);

        vistatablero = new VistaTablero(juego, numero_filas, numero_columnas);
        ventana.getChildren().addAll(vistatablero.getTablero());

        botonera = new Botones(juego);
        ventana.getChildren().add(botonera.getBotonera());

        scene = new Scene(ventana);

        primaryStage.setScene(scene);

        primaryStage.setResizable(false);

        eventos.crearEventos(scene, juego,vistatablero, botonera);
        botonera.crearEvento(eventos);
        vistatablero.crearEvento(eventos);


        primaryStage.getIcons().add(new Image("file:src/main/java/Vista/Images/pj1.png"));
        primaryStage.setTitle("Robots");
        primaryStage.show();

    }


}