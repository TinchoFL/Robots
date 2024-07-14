package Vista;

import Modelo.Juego;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;



public class Botones {
  GridPane botonera;
  private static Button tpRandom;
  private static Button tpSeguro;
  private static Button esperar;
  private final Juego modelo;

  public Botones(Juego modelo) {
    botonera = new GridPane();
    this.modelo = modelo;
    //Crea 3 columnas de 33% de pantalla cada una
    ColumnConstraints columna1 = new ColumnConstraints();
    columna1.setPercentWidth(33.33);
    ColumnConstraints columna2 = new ColumnConstraints();
    columna2.setPercentWidth(33.33);
    ColumnConstraints columna3 = new ColumnConstraints();
    columna3.setPercentWidth(33.33);

    botonera.getColumnConstraints().addAll(columna1, columna2, columna3);

    tpRandom = new Button("Tp Random");
    tpSeguro = new Button("\tTP Seguro\n(Usos restantes: "+ modelo.getTpSeguros() +")");
    esperar = new Button("Esperar");

    tpRandom.setMinHeight(120);
    tpSeguro.setMinHeight(120);
    esperar.setMinHeight(120);
    tpRandom.setMaxWidth(Double.MAX_VALUE);
    tpSeguro.setMaxWidth(Double.MAX_VALUE);
    esperar.setMaxWidth(Double.MAX_VALUE);

    //mete los botones en la columna marcada
    botonera.add(tpRandom, 0, 0);
    botonera.add(tpSeguro, 1, 0);
    botonera.add(esperar, 2, 0);

  }

  public void actualizar(){
    tpSeguro.setText(("\tTP Seguro\n(Usos restantes: "+ modelo.getTpSeguros() +")"));
  }

  public void crearEvento(Eventos eventos){
    tpRandom.setOnAction(eventos.getTpRandom());
    tpSeguro.setOnAction(eventos.getTpSeguro());
    esperar.setOnAction(eventos.getEsperar());
  }
  public GridPane getBotonera(){
    return botonera;
  }

}
