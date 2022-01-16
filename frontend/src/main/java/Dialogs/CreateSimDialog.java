package Dialogs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class CreateSimDialog {
    private Stage createStage;
    private Scene scene;
    private String name;
    private String algortihm;

    private boolean isFirst = true;
    private boolean confirm = false;

    public CreateSimDialog() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        createStage = new Stage();
        createStage.setTitle(resourceBundle.getString("menuNewSim.text"));
        createStage.setAlwaysOnTop(true);

        //borderpane padre
        BorderPane createSimulationBorder = new BorderPane();


        //hbox
        HBox nameSim = new HBox();
        HBox dropdownAlgo = new HBox();

        VBox total = new VBox();

        //Label creazione simulazione
        Label nameSimulation = new Label();
        nameSimulation.setText(resourceBundle.getString("textInputDialog.text"));
        nameSimulation.setFont(new Font("Arial", 15));
        nameSimulation.setAlignment(Pos.CENTER);
        TextField textSimulationName = new TextField();

        nameSim.setSpacing(20);
        nameSim.setAlignment(Pos.CENTER);

        nameSim.getChildren().addAll(nameSimulation, textSimulationName);

        ComboBox chooseAlgorithmCombo = new ComboBox();
        chooseAlgorithmCombo.getItems().addAll("FCFS",
                "SJF",
                "Round Robin",
                "Lottery",
                "RMA",
                "EDF");
        chooseAlgorithmCombo.setValue("FCFS");
        Label algoChooseLabel = new Label();
        algoChooseLabel.setText(resourceBundle.getString("textAlgori.text"));
        algoChooseLabel.setFont(new Font("Arial", 15));

        dropdownAlgo.getChildren().addAll(algoChooseLabel, chooseAlgorithmCombo);
        dropdownAlgo.setSpacing(20);
        dropdownAlgo.setAlignment(Pos.CENTER);

        total.getChildren().addAll(nameSim, dropdownAlgo);
        createSimulationBorder.setCenter(total);

        HBox buttonHBOX = new HBox();

        Button createSimulation = new Button();
        Button exitAnnulla = new Button();
        createSimulation.setText(resourceBundle.getString("creaSimulation.text"));
        exitAnnulla.setText(resourceBundle.getString("annullaCreaSimulation.text"));

        buttonHBOX.getChildren().addAll(createSimulation, exitAnnulla);
        buttonHBOX.setAlignment(Pos.CENTER);
        buttonHBOX.setSpacing(20);

        createSimulation.setPrefSize(120, 30);
        exitAnnulla.setPrefSize(120, 30);

        createStage.initModality(Modality.APPLICATION_MODAL);

        createSimulationBorder.setBottom(buttonHBOX);

        scene = new Scene(createSimulationBorder, 400, 100);
        createStage.setScene(scene);

        createSimulation.setOnAction(x -> {
            name = textSimulationName.getText();
            algortihm = chooseAlgorithmCombo.getValue().toString();
            confirm = true;
            createStage.close();
        });

        exitAnnulla.setOnAction(x -> {
            confirm = false;
            createStage.close();
        });

    }


    public Stage getCreateStage() {
        return createStage;
    }

    public String getName() {
        return name;
    }

    public String getAlgortihm() {
        return algortihm;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public boolean isFirst() {
        boolean temp = isFirst;
        isFirst = false;
        return temp;
    }
}
