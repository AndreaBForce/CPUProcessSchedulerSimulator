package Dialogs;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

import java.util.ResourceBundle;

public class ExitDialog extends Dialog {
    private boolean end = false;

    //Da mettere resource bundle come parametro
    public ExitDialog() {
        super(300, 100);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        getStage().setTitle(resourceBundle.getString("exitDialogTitle.text"));
        getStage().setAlwaysOnTop(true);

        //Label di sicurezza
        Label sicuro = new Label();
        sicuro.setText(resourceBundle.getString("exitDialogSure.text"));
        sicuro.setAlignment(Pos.CENTER);
        getBorderPane().setTop(sicuro);
        HBox exitButtons = new HBox();
        Button exitAnnulla = new Button();
        Button exitEsci = new Button();

        exitAnnulla.getStyleClass().add("btn");
        exitEsci.getStyleClass().add("btn");
        exitEsci.setText(resourceBundle.getString("exitDialogClose.text"));
        exitAnnulla.setText(resourceBundle.getString("exitDialogCancel.text"));
        getStage().initModality(Modality.APPLICATION_MODAL);
        exitButtons.setSpacing(20);
        exitAnnulla.setPrefSize(70, 30);
        exitEsci.setPrefSize(70, 30);
        exitButtons.getChildren().addAll(exitEsci, exitAnnulla);
        exitButtons.setAlignment(Pos.CENTER);
        getBorderPane().setCenter(exitButtons);

        getStage().setScene(getScene());

        exitAnnulla.setOnMouseClicked(x -> {
                getStage().close();
                end = false;
        });

        exitEsci.setOnAction(x -> {
            getStage().close();
            end = true;
        });

    }

    public boolean isEnd() {
        return end;
    }

}
