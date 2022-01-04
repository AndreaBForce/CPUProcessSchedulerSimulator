package Dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class DisplayInfoDialog {
    private Stage displayStage;
    private Scene scene;


    public DisplayInfoDialog(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        displayStage = new Stage();
        displayStage.setTitle(resourceBundle.getString("menuNewSim.text"));
        displayStage.setAlwaysOnTop(true);

        BorderPane displaySimulationBorder = new BorderPane();
        HBox dropdownAlgo = new HBox();
        VBox total = new VBox();

        VBox.setMargin(dropdownAlgo, new Insets(10,16,10,16));

        ComboBox chooseAlgorithmCombo = new ComboBox();
        chooseAlgorithmCombo.getItems().addAll("FIFO",
                "SJF",
                "Round Robin",
                "Lottery",
                "RMA",
                "EDF");
        chooseAlgorithmCombo.setValue("FIFO");

        Label algoChooseLabel = new Label();
        algoChooseLabel.setText(resourceBundle.getString("selezioneDescrizione.text"));
        algoChooseLabel.setFont(new Font("Arial", 15));

        dropdownAlgo.getChildren().addAll(algoChooseLabel,chooseAlgorithmCombo);
        dropdownAlgo.setSpacing(20);
        dropdownAlgo.setAlignment(Pos.CENTER);

        HBox showBorder = new HBox();
        showBorder.setAlignment(Pos.CENTER);

        total.getChildren().addAll(dropdownAlgo,showBorder);
        displaySimulationBorder.setTop(total);

        Label algoritmhDescription = new Label();
        algoritmhDescription.setTextAlignment(TextAlignment.JUSTIFY);
        algoritmhDescription.setWrapText(true);
        algoritmhDescription.setMaxHeight(200);
        algoritmhDescription.setMinWidth(200);

        algoritmhDescription.setText(resourceBundle.getString("FIFO.text"));
        displaySimulationBorder.setCenter(algoritmhDescription);
        HBox buttonHBOX = new HBox();

        Button exitAnnulla = new Button();
        exitAnnulla.setText(resourceBundle.getString("closeDescription.text"));


        buttonHBOX.getChildren().addAll(exitAnnulla);
        buttonHBOX.setAlignment(Pos.CENTER);
        buttonHBOX.setSpacing(20);

        exitAnnulla.setPrefSize(120, 30);

        displayStage.initModality(Modality.APPLICATION_MODAL);

        displaySimulationBorder.setBottom(buttonHBOX);

        scene = new Scene(displaySimulationBorder, 500, 300);
        displayStage.setScene(scene);


        exitAnnulla.setOnAction(x -> {
            displayStage.close();
        });

        chooseAlgorithmCombo.setOnAction(x -> {
            switch (chooseAlgorithmCombo.getValue().toString()){
                case "FIFO":
                    algoritmhDescription.setText(resourceBundle.getString("FIFO.text"));
                    break;
                case "SJF":
                    algoritmhDescription.setText(resourceBundle.getString("SJF.text"));
                    break;
                case "Round Robin":
                    algoritmhDescription.setText(resourceBundle.getString("ROUND.text"));
                    break;
                case "Lottery":
                    algoritmhDescription.setText(resourceBundle.getString("LOTTERY.text"));
                    break;
                case "RMA":
                    algoritmhDescription.setText(resourceBundle.getString("RMA.text"));
                    break;
                case "EDF":
                    algoritmhDescription.setText(resourceBundle.getString("EDF.text"));
                    break;
                default:
                    break;
            }
        });

    }


    public Stage getDisplayStage() {
        return displayStage;
    }
}
