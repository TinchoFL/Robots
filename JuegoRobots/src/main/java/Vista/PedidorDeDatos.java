package Vista;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class PedidorDeDatos extends Dialog<GridPane> {
    private TextField CampoFilas;
    private TextField CampoColumnas;


    public PedidorDeDatos(){
        this.setTitle("Tamaño del tablero");
        setHeaderText("Por favor, introduce el número de filas y columnas:");

        ButtonType BotonOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(BotonOk);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        CampoFilas = new TextField();
        CampoColumnas = new TextField();


        grid.add(new Label("Filas:"), 0, 0);
        grid.add(CampoFilas, 1, 0);
        grid.add(new Label("Columnas:"), 0, 1);
        grid.add(CampoColumnas, 1, 1);

        Image imagen = new Image("file:src/main/java/Vista/Images/pj4.png");
        ImageView imagenView = new ImageView(imagen);
        imagenView.setFitWidth(50);
        imagenView.setFitHeight(50);

        grid.add(imagenView,3, 1);

        getDialogPane().setContent(grid);

    }


    public int getNumeroFilas(){
        return Integer.parseInt(CampoFilas.getText());
    }

    public int getNumeroColumnas(){
        return Integer.parseInt(CampoColumnas.getText());
    }
}
