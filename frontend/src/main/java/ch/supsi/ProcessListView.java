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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProcessListView {
    private final VBox container;
    private final ScrollPane scrollableList;
    private final VBox processBox;
    private final List<HBox> cells;
    private final Button addBtn;
    private long id = 0;
    private final List<Process> processList = new ArrayList<>();
    private String algorithm;

    public ProcessListView() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        container = new VBox();
        processBox = new VBox();
        addBtn = new Button(resourceBundle.getString("labelAddProcess.text"));
        addBtn.getStyleClass().add("btn");
        cells = new ArrayList<>();
        scrollableList = new ScrollPane();
    }

    public void initList(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        container.setSpacing(16);
        container.setPadding(new Insets(10));
        container.getChildren().addAll(addBtn, scrollableList);
        container.setMinWidth(200);
        processBox.setMaxWidth(800);
        processBox.setSpacing(10);
        container.setAlignment(Pos.BASELINE_CENTER);

        addBtn.setOnMouseClicked(mouseEvent -> {
            GridPane gridPane = new GridPane();
            gridPane.getStylesheets().add("Style.css");
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(5, 5, 5, 5));
            Scene secondScene = new Scene(gridPane, 320, 350);

            Stage newWindow = new Stage();
            newWindow.setTitle(resourceBundle.getString("labelAddProcess.text"));
            newWindow.setScene(secondScene);

            newWindow.setMaxWidth(350);
            newWindow.setMinWidth(350);
            newWindow.setMaxHeight(320);
            newWindow.setMinHeight(320);

            Text scenetitle = new Text(resourceBundle.getString("labelAddProcess.text"));
            gridPane.add(scenetitle, 0, 0, 2, 1);

            Label labelProcessName = new Label(resourceBundle.getString("labelEditName.text"));
            gridPane.add(labelProcessName, 0, 1);
            TextField textProcessName = new TextField();
            gridPane.add(textProcessName, 1, 1);

            Label labelColor = new Label(resourceBundle.getString("labelColorProcess.text"));
            gridPane.add(labelColor, 0, 2);
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.setId("colorPicker");
            gridPane.add(colorPicker, 1, 2);


            Label labelTmpArr = new Label(resourceBundle.getString("labelEditArrivalTime.text"));
            TextField tmpArr = new TextField();
            tmpArr.setId("tmpArr");
            tmpArr.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                if (!newValue) {
                    if (!tmpArr.getText().matches("^(?:1?\\d(?:\\.\\d{1,2})?|20(?:\\.0?)?)$")) {
                        tmpArr.setText("");
                    }
                }
            });

            Label labelTmpBurst = new Label(resourceBundle.getString("labelEditBurstTime.text"));
            TextField tmpBurst = new TextField();
            tmpBurst.setId("tmpBurst");
            tmpBurst.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                if (!newValue) {
                    if (!tmpBurst.getText().matches("^(?:1?\\d(?:\\.\\d{1,2})?|20(?:\\.0?)?)$")) {
                        tmpBurst.setText("");
                    }
                }
            });

            Label labelPriority = new Label(resourceBundle.getString("labelEditPriority.text"));
            TextField priority = new TextField();
            priority.setId("priority");
            priority.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                if (!newValue) {
                    if (!priority.getText().matches("^(?:1?\\d(?:\\.\\d{1,2})?|20(?:\\.0?)?)$")) {
                        priority.setText("");
                    }
                }
            });

            Button submitBtn = new Button(resourceBundle.getString("labelAdd.text"));

            switch (algorithm) {
                case "FCFS", "SJF" -> {
                    //Arrival time
                    gridPane.add(labelTmpArr, 0, 3);
                    gridPane.add(tmpArr, 1, 3);
                    //Burst time
                    gridPane.add(labelTmpBurst, 0, 4);
                    gridPane.add(tmpBurst, 1, 4);
                    priority.setText("4");
                    submitBtn.disableProperty().bind(Bindings.isEmpty(textProcessName.textProperty())
                            .or(Bindings.isEmpty(tmpArr.textProperty()))
                            .or(Bindings.isEmpty(tmpBurst.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpBurst.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpArr.textProperty()))
                    );
                    gridPane.add(submitBtn, 1, 5);
                }
                case "Round Robin" -> {
                    //Arrival time
                    priority.setText("4");
                    gridPane.add(labelTmpArr, 0, 3);
                    gridPane.add(tmpArr, 1, 3);
                    //Burst time
                    gridPane.add(labelTmpBurst, 0, 4);
                    gridPane.add(tmpBurst, 1, 4);
                    submitBtn.disableProperty().bind(Bindings.isEmpty(textProcessName.textProperty())
                            .or(Bindings.isEmpty(tmpArr.textProperty()))
                            .or(Bindings.isEmpty(tmpBurst.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpBurst.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpArr.textProperty()))
                    );
                    gridPane.add(submitBtn, 1, 6);
                }
                case "Lottery" -> {
                    //Arrival time
                    gridPane.add(labelTmpArr, 0, 3);
                    gridPane.add(tmpArr, 1, 3);
                    //Burst time
                    gridPane.add(labelTmpBurst, 0, 4);
                    gridPane.add(tmpBurst, 1, 4);
                    //Priority
                    gridPane.add(labelPriority, 0, 5);
                    gridPane.add(priority, 1, 5);
                    submitBtn.disableProperty().bind(Bindings.isEmpty(textProcessName.textProperty())
                            .or(Bindings.isEmpty(tmpArr.textProperty()))
                            .or(Bindings.isEmpty(tmpBurst.textProperty()))
                            .or(Bindings.isEmpty(priority.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), priority.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpBurst.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpArr.textProperty()))
                    );
                    gridPane.add(submitBtn, 1, 6);
                }
                case "RMA", "EDF" -> {
                    tmpArr.setText("4");
                    gridPane.add(labelTmpBurst, 0, 3);
                    gridPane.add(tmpBurst, 1, 3);
                    labelPriority.setText(resourceBundle.getString("labelPeriod.text"));
                    gridPane.add(labelPriority, 0, 4);
                    gridPane.add(priority, 1, 4);
                    submitBtn.disableProperty().bind(Bindings.isEmpty(textProcessName.textProperty())
                            .or(Bindings.isEmpty(tmpBurst.textProperty()))
                            .or(Bindings.isEmpty(priority.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), priority.textProperty()))
                            .or(Bindings.greaterThan(String.valueOf(0.1), tmpBurst.textProperty()))
                    );
                    gridPane.add(submitBtn, 1, 5);
                }
            }

            newWindow.show();

            submitBtn.setOnMouseClicked(mouseEvent1 -> {
                add(new Process(textProcessName.getText(), roundAvoid(Double.parseDouble(tmpBurst.getText()), 1), roundAvoid(Double.parseDouble(tmpArr.getText()), 1), Integer.parseInt(priority.getText()), colorPicker.getValue()));
                newWindow.close();
            });
        });
        scrollableList.setContent(processBox);
        scrollableList.setFitToWidth(true);
        scrollableList.setMaxWidth(800);
        processBox.setPadding(new Insets(10));
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }

    public void add(Process process) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        String hexColor = toHexString(process.getColor());
        processList.add(process);

        HBox cell = getCell(hexColor);
        ProcessDetailsView processDetails = new ProcessDetailsView(process);

        Button btnEdit = getButton(resourceBundle.getString("labelEdit.text"));

        btnEdit.setId("btnEdit");

        btnEdit.setOnMouseClicked(mouseEvent -> {
            EditDialog editDialog = new EditDialog(processDetails, process);
            editDialog.getStage().showAndWait();
        });

        Button btnRemove = getButton(resourceBundle.getString("labelRemove.text"));
        btnRemove.setOnMouseClicked(mouseEvent -> {
            removeCell(btnRemove.getParent().getId());
        });

        switch (algorithm) {
            case "FCFS", "SJF" -> cell.getChildren().addAll(getSpace(),
                    processDetails.getProcessName(),
                    getSpace(),
                    processDetails.getTmpArrivalTime(), processDetails.getValueArrival(),
                    getSpace(),
                    processDetails.getTmpBurstTime(), processDetails.getValueBurst(),
                    getSpace(),
                    btnEdit, btnRemove);
            case "Round Robin" -> cell.getChildren().addAll(getSpace(),
                    processDetails.getProcessName(),
                    getSpace(),
                    processDetails.getTmpArrivalTime(), processDetails.getValueArrival(),
                    getSpace(),
                    processDetails.getTmpBurstTime(), processDetails.getValueBurst(),
                    getSpace(),
                    processDetails.getTmpPriority(),
                    getSpace(),
                    btnEdit, btnRemove);
            case "Lottery" -> cell.getChildren().addAll(getSpace(),
                    processDetails.getProcessName(),
                    getSpace(),
                    processDetails.getTmpArrivalTime(), processDetails.getValueArrival(),
                    getSpace(),
                    processDetails.getTmpBurstTime(), processDetails.getValueBurst(),
                    getSpace(),
                    processDetails.getTmpPriority(), processDetails.getValuePriority(),
                    getSpace(),
                    btnEdit, btnRemove);
            case "RMA", "EDF" -> {
                processDetails.getTmpPriority().setText(resourceBundle.getString("labelPeriod.text"));
                cell.getChildren().addAll(getSpace(),
                        processDetails.getProcessName(),
                        getSpace(),
                        processDetails.getTmpBurstTime(), processDetails.getValueBurst(),
                        getSpace(),
                        processDetails.getTmpPriority(), processDetails.getValuePriority(),
                        getSpace(),
                        btnEdit, btnRemove);
            }
        }
        processBox.getChildren().add(cell);
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void removeCell(String id) {
        Node toRemove = container.lookup('#' + id);
        processBox.getChildren().remove(toRemove);
        HBox node = (HBox) toRemove;
        Label label = (Label) node.getChildren().get(1);
        processList.removeIf(process -> process.getName().equals(label.getText()));
    }

    private HBox getCell(String hexColor) {
        HBox cell = new HBox();
        cell.setStyle("-fx-background-radius: 5; -fx-background-color: " + hexColor + ";");
        cell.setEffect(new DropShadow(4, Color.BLACK));
        cell.setPrefHeight(32);
        cell.setAlignment(Pos.CENTER);
        cell.setFillHeight(false);
        cell.setId("cell" + id);
        id++;
        return cell;
    }

    public Region getSpace() {
        Region space = new Region();
        space.setPrefWidth(50);
        space.setPrefHeight(30);
        HBox.setHgrow(space, Priority.ALWAYS);
        return space;
    }

    private Button getButton(String text) {
        Button button = new Button();
        button.getStyleClass().add("btn");
        button.setText(text);
        button.setStyle("-fx-effect: none; -fx-font-size: 10px;");
        button.setMaxHeight(10);
        HBox.setMargin(button, new Insets(5, 5, 5, 5));

        return button;
    }

    public List<Process> getProcessList() {
        return processList;
    }

    private boolean isDark(String hexColor) {
        double bright = Color.web(hexColor).getBrightness();
        return bright < 0.8;
    }

    public VBox getContainer() {
        return container;
    }
}
