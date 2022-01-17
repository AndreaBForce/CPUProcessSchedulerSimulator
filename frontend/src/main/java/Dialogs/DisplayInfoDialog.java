package Dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;

import java.util.ResourceBundle;

public class DisplayInfoDialog extends Dialog {


    public DisplayInfoDialog(){
        super(500, 300);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        getStage().setTitle(resourceBundle.getString("menuNewSim.text"));
        getStage().setAlwaysOnTop(true);

        HBox dropdownAlgo = new HBox();
        VBox total = new VBox();

        VBox.setMargin(dropdownAlgo, new Insets(10,16,10,16));

        ComboBox<String> chooseAlgorithmCombo = new ComboBox<>();
        chooseAlgorithmCombo.getItems().addAll("FIFO",
                "SJF",
                "Round Robin",
                "Lottery",
                "RMA",
                "EDF");
        chooseAlgorithmCombo.setValue("FIFO");

        Label algoChooseLabel = new Label();
        algoChooseLabel.setText(resourceBundle.getString("selezioneDescrizione.text"));

        dropdownAlgo.getChildren().addAll(algoChooseLabel,chooseAlgorithmCombo);
        dropdownAlgo.setSpacing(20);
        dropdownAlgo.setAlignment(Pos.CENTER);

        HBox showBorder = new HBox();
        showBorder.setAlignment(Pos.CENTER);

        total.getChildren().addAll(dropdownAlgo,showBorder);
        getBorderPane().setTop(total);

        Label algorithmDescription = new Label();
        algorithmDescription.setTextAlignment(TextAlignment.JUSTIFY);
        algorithmDescription.setWrapText(true);
        algorithmDescription.setMaxHeight(200);
        algorithmDescription.setMinWidth(200);

        algorithmDescription.setText(resourceBundle.getString("FIFO.text"));
        getBorderPane().setCenter(algorithmDescription);
        HBox buttonHBOX = new HBox();

        Button exitAnnulla = new Button();
        exitAnnulla.getStyleClass().add("btn");
        exitAnnulla.setText(resourceBundle.getString("closeDescription.text"));


        buttonHBOX.getChildren().addAll(exitAnnulla);
        buttonHBOX.setAlignment(Pos.CENTER);
        buttonHBOX.setSpacing(20);

        exitAnnulla.setPrefSize(120, 30);

        getStage().initModality(Modality.APPLICATION_MODAL);

        getBorderPane().setBottom(buttonHBOX);

        getStage().setScene(getScene());


        exitAnnulla.setOnAction(x -> {
            getStage().close();
        });

        chooseAlgorithmCombo.setOnAction(x -> {
            switch (chooseAlgorithmCombo.getValue().toString()) {
                case "FIFO" -> algorithmDescription.setText(resourceBundle.getString("FIFO.text"));
                case "SJF" -> algorithmDescription.setText(resourceBundle.getString("SJF.text"));
                case "Round Robin" -> algorithmDescription.setText(resourceBundle.getString("ROUND.text"));
                case "Lottery" -> algorithmDescription.setText(resourceBundle.getString("LOTTERY.text"));
                case "RMA" -> algorithmDescription.setText(resourceBundle.getString("RMA.text"));
                case "EDF" -> algorithmDescription.setText(resourceBundle.getString("EDF.text"));
                default -> {
                }
            }
        });

    }
}
