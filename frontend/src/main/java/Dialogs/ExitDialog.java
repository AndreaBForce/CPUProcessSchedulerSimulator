package Dialogs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class ExitDialog {
    private Stage exitStage;
    private Scene scena;
    private boolean end = false;

    //Da mettere resource bundle come parametro
    public ExitDialog() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        exitStage = new Stage();
        exitStage.setTitle(resourceBundle.getString("exitDialogTitle.text"));
        exitStage.setAlwaysOnTop(true);

        //borderpane padre
        BorderPane exitBorder = new BorderPane();
        //Label di sicurezza
        Label sicuro = new Label();
        sicuro.setText(resourceBundle.getString("exitDialogSure.text"));
        sicuro.setFont(new Font("Arial", 15));
        sicuro.setAlignment(Pos.CENTER);
        exitBorder.setTop(sicuro);
        HBox exitButtons = new HBox();
        Button exitAnnulla = new Button();
        Button exitEsci = new Button();
        exitEsci.setText(resourceBundle.getString("exitDialogClose.text"));
        exitAnnulla.setText(resourceBundle.getString("exitDialogCancel.text"));
        exitEsci.setStyle("-fx-background-color: #336699; ");
        exitStage.initModality(Modality.APPLICATION_MODAL);
        exitButtons.setSpacing(20);
        exitAnnulla.setPrefSize(70, 30);
        exitEsci.setPrefSize(70, 30);
        exitButtons.getChildren().addAll(exitEsci, exitAnnulla);
        exitButtons.setAlignment(Pos.CENTER);
        exitBorder.setCenter(exitButtons);
        //scena del meno di exit con annulla e socio
        scena = new Scene(exitBorder, 300, 100);
        exitStage.setScene(scena);

        exitAnnulla.setOnMouseClicked(x -> {
                exitStage.close();
                end = false;
        });

        exitEsci.setOnAction(x -> {
            exitStage.close();
            end = true;
        });

    }

    public boolean isEnd() {
        return end;
    }

    public Stage getExitStage() {
        return exitStage;
    }
}
