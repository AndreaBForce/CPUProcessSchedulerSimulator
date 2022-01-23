package Dialogs;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.util.ResourceBundle;

public class SimulationDialog extends Dialog {
    private String name;
    private String algorithm;

    private boolean isFirst = true;
    private boolean confirm = false;

    public SimulationDialog() {

        super(420, 100);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        getStage().setTitle(resourceBundle.getString("menuNewSim.text"));
        getStage().setAlwaysOnTop(true);

        //hbox
        HBox nameSim = new HBox();
        HBox dropdownAlgo = new HBox();

        VBox total = new VBox();

        //Label creazione simulazione
        Label nameSimulation = new Label();
        nameSimulation.setText(resourceBundle.getString("textInputDialog.text"));

        nameSimulation.setAlignment(Pos.CENTER);
        TextField textSimulationName = new TextField();

        nameSim.setSpacing(20);
        nameSim.setAlignment(Pos.CENTER);

        nameSim.getChildren().addAll(nameSimulation, textSimulationName);

        ComboBox<String> chooseAlgorithmCombo = new ComboBox<>();
        chooseAlgorithmCombo.getItems().addAll("FCFS",
                "SJF",
                "Round Robin",
                "Lottery",
                "RMA",
                "EDF");
        chooseAlgorithmCombo.setValue("FCFS");
        Label algoChooseLabel = new Label();
        algoChooseLabel.setText(resourceBundle.getString("textAlgori.text"));

        dropdownAlgo.getChildren().addAll(algoChooseLabel, chooseAlgorithmCombo);
        dropdownAlgo.setSpacing(20);
        dropdownAlgo.setAlignment(Pos.CENTER);

        total.getChildren().addAll(nameSim, dropdownAlgo);
        getBorderPane().setCenter(total);

        HBox buttonHBOX = new HBox();

        Button createBtn = new Button();
        Button exitBtn = new Button();
        createBtn.getStyleClass().add("btn");
        exitBtn.getStyleClass().add("btn");
        createBtn.setText(resourceBundle.getString("creaSimulation.text"));
        exitBtn.setText(resourceBundle.getString("annullaCreaSimulation.text"));

        buttonHBOX.getChildren().addAll(createBtn, exitBtn);
        buttonHBOX.setAlignment(Pos.CENTER);
        buttonHBOX.setSpacing(20);

        createBtn.setPrefSize(150, 30);
        exitBtn.setPrefSize(120, 30);

        getStage().initModality(Modality.APPLICATION_MODAL);

        getBorderPane().setBottom(buttonHBOX);

        getStage().setScene(getScene());

        createBtn.setOnAction(x -> {
            name = textSimulationName.getText();
            algorithm = chooseAlgorithmCombo.getValue().toString();
            confirm = true;
            getStage().close();
        });

        exitBtn.setOnAction(x -> {
            confirm = false;
            getStage().close();
        });

    }

    public String getName() {
        return name;
    }

    public String getAlgorithm() {
        return algorithm;
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
