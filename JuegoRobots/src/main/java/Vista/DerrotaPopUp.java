package Vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class DerrotaPopUp extends Stage {
    public DerrotaPopUp() {
        this.setTitle("Game Over");
        this.getIcons().add(new Image("file:src/main/java/Vista/Images/caratriste.png"));

        Label texto = new Label("GAME OVER");

        texto.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Button reiniciar = new Button("Reiniciar Juego");
        reiniciar.setStyle("-fx-font-size: 14px;");
        reiniciar.setOnAction(e -> this.close());

        Button salir = new Button("Salir");
        salir.setStyle("-fx-font-size: 14px;");
        salir.setOnAction(e -> System.exit(0));

        VBox alerta = new VBox(10);
        alerta.getChildren().addAll(texto, reiniciar, salir);
        alerta.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(alerta, 300, 200);
        this.setScene(popupScene);
    }

    public void mostrarPopup() {
        this.showAndWait();
    }
}
