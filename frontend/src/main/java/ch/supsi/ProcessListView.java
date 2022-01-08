package ch.supsi;

import Dialogs.EditDialog;
import ch.supsi.menu.ProcessDetailsView;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ProcessListView {
    private final VBox container;
    private final ScrollPane scrollableList;
    private final VBox processBox;
    private final List<HBox> cells;
    private final Button addBtn;
    private long id = 0;
    private final List<Process> processList = new ArrayList<>();
    private String algortihm;

    public ProcessListView() {
        container = new VBox();
        processBox = new VBox();
        addBtn = new Button("Add new process");

        cells = new ArrayList<>();
        scrollableList = new ScrollPane();
        container.setSpacing(16);
        container.setPadding(new Insets(10));
        container.setStyle("-fx-background-color: red");
        container.getChildren().addAll(addBtn,scrollableList);
        container.setMinWidth(200);
        processBox.setMaxWidth(800);
        processBox.setSpacing(10);
        container.setAlignment(Pos.BASELINE_CENTER);

        addBtn.setOnMouseClicked(mouseEvent -> {
            //TODO METTERE QUA IN VASE ALL'AGORTIMO LE SCELTE POSSIBILI
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(5, 5, 5, 5));
            Scene secondScene = new Scene(gridPane, 320, 350);

            Stage newWindow = new Stage();
            newWindow.setTitle("Add new process");
            newWindow.setScene(secondScene);

            newWindow.setMaxWidth(350);
            newWindow.setMinWidth(350);
            newWindow.setMaxHeight(320);
            newWindow.setMinHeight(320);
            /*"FIFO" name, burst,arrival time,
                "SJF",
                "Round Robin"name burst arrival time,
                "Lottery" name burst arrival priority,

                "RMA" name, burst, period, priority,
                "EDF"*/
            //Li dichiaro tutti sopra, poi a seconda dello switch che ho li attivo/disattivo

            Text scenetitle = new Text("Add new process");
            gridPane.add(scenetitle, 0, 0, 2, 1);

            Label labelProcessName = new Label("Name:");
            gridPane.add(labelProcessName, 0, 1);
            TextField textProcessName = new TextField();
            gridPane.add(textProcessName, 1, 1);

            Label labelColor = new Label("Color:");
            gridPane.add(labelColor, 0, 2);
            ColorPicker colorPicker = new ColorPicker();
            gridPane.add(colorPicker,1,2);


            Label labelTmpArr = new Label("Arrival time:");
            TextField tmpArr = new TextField();
            gridPane.add(tmpArr, 1, 2);

            Label labelTmpBurst = new Label("Burst time:");
            TextField tmpBurst = new TextField();
            gridPane.add(tmpBurst, 1, 3);

            Label labelPriority = new Label("Priority:");
            TextField priority = new TextField();


            Button submitBtn = new Button("Add");
            submitBtn.disableProperty().bind(Bindings.isEmpty(textProcessName.textProperty())
                    .or(Bindings.isEmpty(tmpArr.textProperty()))
                    .or(Bindings.isEmpty(tmpBurst.textProperty()))
                    .or(Bindings.isEmpty(priority.textProperty())));
            gridPane.add(submitBtn, 1, 5);
            newWindow.show();

            submitBtn.setOnMouseClicked(mouseEvent1 -> {
                //TODO CHECK SE FLOAT
                add(new Process(textProcessName.getText(),Float.parseFloat(tmpBurst.getText()),Float.parseFloat(tmpArr.getText()),Integer.parseInt(priority.getText())));
                newWindow.close();
            });
        });
        scrollableList.setContent(processBox);
        scrollableList.setFitToWidth(true);
        scrollableList.setStyle("-fx-background: red; -fx-background-color: red");
        scrollableList.setMaxWidth(800);
        processBox.setPadding(new Insets(10));
    }

    public void add(Process process) {

        String hexColor = "#83C1DC";



        HBox cell = getCell(hexColor);
        ProcessDetailsView processDetails = new ProcessDetailsView(process);
        //Btn edit
        Button btnEdit = new Button();
        btnEdit.setText("Edit");
        btnEdit.setStyle("-fx-background-insets: 0; -fx-background-color: #f0f0f0; -fx-font-size: 10px;");
        btnEdit.setMaxHeight(10);
        HBox.setMargin(btnEdit, new Insets(5, 5, 5, 5));

        btnEdit.setOnMouseClicked(mouseEvent -> {
            EditDialog editDialog = new EditDialog(processDetails,process);
            editDialog.getEditStage().showAndWait();
        });

        Button button = getButton();


        /*//contrast settings
        if (isDark(hexColor)) {
            label.setStyle("-fx-text-fill: #ffffff;");
            tmpArrivalTime.setStyle("-fx-text-fill: #ffffff;");
            tmpBurstTime.setStyle("-fx-text-fill: #ffffff;");
            tmpPriority.setStyle("-fx-text-fill: #ffffff;");

            valueArrival.setStyle("-fx-text-fill: #ffffff;");
            valueBurst.setStyle("-fx-text-fill: #ffffff;");
            valuePriority.setStyle("-fx-text-fill: #ffffff;");
        } else {
            label.setStyle("-fx-text-fill: #000000;");
            tmpArrivalTime.setStyle("-fx-text-fill: #000000;");
            tmpBurstTime.setStyle("-fx-text-fill: #000000;");
            tmpPriority.setStyle("-fx-text-fill: #000000;");
            valueArrival.setStyle("-fx-text-fill: #000000;");
            valueBurst.setStyle("-fx-text-fill: #000000;");
            valuePriority.setStyle("-fx-text-fill: #000000;");
        }*/


        cell.getChildren().addAll(getSpace(),
                processDetails.getProcessName(),
                getSpace(),
                processDetails.getTmpArrivalTime(),processDetails.getValueArrival(),
                getSpace(),
                processDetails.getTmpBurstTime(), processDetails.getValueBurst(),
                getSpace(),
                processDetails.getTmpPriority(),processDetails.getValuePriority(),
                getSpace(),
                btnEdit, button);

        processBox.getChildren().add(cell);
    }

    public void removeCell(String id) {
        Node toRemove = container.lookup('#' + id);
        processBox.getChildren().remove(toRemove);
    }

    private HBox getCell(String hexColor) {
        HBox cell = new HBox();
        cell.setStyle("-fx-background-radius: 5; -fx-background-color: " + hexColor + ";");
        cell.setEffect(new DropShadow(10, Color.BLACK));
        cell.setPrefHeight(32);
        cell.setAlignment(Pos.CENTER);
        cell.setFillHeight(false);
        cell.setId("cell" + id);
        id++;
        return cell;
    }

    private Label getLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Futura"));
        label.setPrefWidth(150);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public Region getSpace() {
        Region space = new Region();
        space.setPrefWidth(50);
        space.setPrefHeight(30);
        HBox.setHgrow(space, Priority.ALWAYS);
        return space;
    }

    private Button getButton() {
        Button button = new Button();

        button.setText("Remove");
        button.setStyle("-fx-background-insets: 0; -fx-background-color: #f0f0f0; -fx-font-size: 10px;");
        button.setMaxHeight(10);
        HBox.setMargin(button, new Insets(5, 5, 5, 5));

        button.setOnMouseClicked(mouseEvent -> {
            removeCell(button.getParent().getId());
        });

        return button;
    }

    private boolean isDark(String hexColor) {
        double bright = Color.web(hexColor).getBrightness();
        return bright < 0.8;
    }

    public VBox getContainer() {
        return container;
    }
}
