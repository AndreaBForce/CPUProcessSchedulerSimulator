package ch.supsi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class ProcessListView {
    VBox container;
    VBox processBox;
    List<HBox> cells;
    Button addBtn;
    long id = 0;

    public ProcessListView() {
        container = new VBox();
        processBox = new VBox();
        addBtn = new Button("Add new process");
        cells = new ArrayList<>();
        container.setSpacing(16);
        container.setPadding(new Insets(10));
        container.setStyle("-fx-background-color: red");
        container.getChildren().addAll(addBtn, processBox);
        container.setMinWidth(200);
//        container.setMaxWidth(600);
        processBox.setMaxWidth(800);
        processBox.setSpacing(10);
        container.setAlignment(Pos.BASELINE_CENTER);
//        container.setMinHeight(200);
//        container.setMaxHeight(400);
        addBtn.setOnMouseClicked(mouseEvent -> {
                    add(new Process("P" + id));
                }
        );

//        container.heightProperty().addListener((observableValue, number, t1) -> System.out.println(observableValue));
    }

    public void add(Process process) {
        String hexColor = "#83C1DC";

        HBox cell = getCell(hexColor);
        Label label = getLabel(process.toString());


        //t = text
        Label t_arrival_time = new Label();
        Label t_burst_time = new Label();
        Label t_priority = new Label();

        //v = value
        Label v_arrival = new Label();
        Label v_burst = new Label();
        Label v_priority = new Label();

        t_arrival_time.setText("Arrival time: ");
        t_burst_time.setText("Burst time: ");
        t_priority.setText("Priority: ");
        
        v_arrival.setText("5");
        v_burst.setText("5");
        v_priority.setText("5");

        //Btn edit
        Button btn_edit = new Button();

        btn_edit.setText("Edit");
        btn_edit.setStyle("-fx-background-insets: 0; -fx-background-color: #f0f0f0; -fx-font-size: 10px;");
        btn_edit.setMaxHeight(10);
        HBox.setMargin(btn_edit, new Insets(5, 5, 5, 5));

        btn_edit.setOnMouseClicked(mouseEvent -> {
            EditDialog editDialog = new EditDialog(label,v_arrival,v_burst,v_priority);
            editDialog.getEditStage().showAndWait();
        });


        Button button = getButton();




        //contrast settings
        if (isDark(hexColor)) {
            label.setStyle("-fx-text-fill: #ffffff;");
            t_arrival_time.setStyle("-fx-text-fill: #ffffff;");
            t_burst_time.setStyle("-fx-text-fill: #ffffff;");
            t_priority.setStyle("-fx-text-fill: #ffffff;");

            v_arrival.setStyle("-fx-text-fill: #ffffff;");
            v_burst.setStyle("-fx-text-fill: #ffffff;");
            v_priority.setStyle("-fx-text-fill: #ffffff;");
        } else {
            label.setStyle("-fx-text-fill: #000000;");
            t_arrival_time.setStyle("-fx-text-fill: #000000;");
            t_burst_time.setStyle("-fx-text-fill: #000000;");
            t_priority.setStyle("-fx-text-fill: #000000;");

            v_arrival.setStyle("-fx-text-fill: #000000;");
            v_burst.setStyle("-fx-text-fill: #000000;");
            v_priority.setStyle("-fx-text-fill: #000000;");
        }

        //TODO: ADD LABEL: ARRIVAL TIME
        //TODO: ADD LABEL: BURST TIME
        //TODO: ADD LABEL: PRIORITY
        //TODO: ADD BUTTON: EDIT
        //TODO: ADD DIALOG TO EDI TO EDIT LABEL
        cell.getChildren().addAll(label, t_arrival_time,v_arrival,getSpace(),t_burst_time,v_burst,getSpace(),t_priority,v_priority,getSpace(),btn_edit, button);


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

}
