package ch.supsi;

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


//        addBtn.setOnMouseClicked(mouseEvent -> {
//                    add(new Process("P" + id));
//                }
//
//        );

        addBtn.setOnMouseClicked(mouseEvent -> {
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

            Text scenetitle = new Text("Add new process");
            gridPane.add(scenetitle, 0, 0, 2, 1);

            Label labelProcessName = new Label("Name:");
            gridPane.add(labelProcessName, 0, 1);

            TextField textProcessName = new TextField();
            gridPane.add(textProcessName, 1, 1);

            Label labelTmpArr = new Label("Arrival time:");
            gridPane.add(labelTmpArr, 0, 2);
            TextField tmpArr = new TextField();
            gridPane.add(tmpArr, 1, 2);

            Label labelTmpBurst = new Label("Burst time:");
            gridPane.add(labelTmpBurst, 0, 3);
            TextField tmpBurst = new TextField();
            gridPane.add(tmpBurst, 1, 3);

            Label labelPriority = new Label("Priority:");
            gridPane.add(labelPriority, 0, 4);
            TextField priority = new TextField();
            gridPane.add(priority, 1, 4);


            Button submitBtn = new Button("Add");
            submitBtn.disableProperty().bind(Bindings.isEmpty(textProcessName.textProperty())
                    .or(Bindings.isEmpty(tmpArr.textProperty()))
                    .or(Bindings.isEmpty(tmpBurst.textProperty()))
                    .or(Bindings.isEmpty(priority.textProperty())));
            gridPane.add(submitBtn, 1, 5);
            newWindow.show();

            submitBtn.setOnMouseClicked(mouseEvent1 -> {
                add(new Process(textProcessName.getText()));
                newWindow.close();
            });
        });

//        container.heightProperty().addListener((observableValue, number, t1) -> System.out.println(observableValue));
    }

    public void add(Process process) {
        String hexColor = "#83C1DC";

        HBox cell = getCell(hexColor);
        Label label = getLabel(process.toString());
        Button button = getButton();
        Region space = getSpace();

        if (isDark(hexColor))
            label.setStyle("-fx-text-fill: #ffffff;");
        else
            label.setStyle("-fx-text-fill: #000000;");

        cell.getChildren().addAll(label, space, button);

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
        space.setPrefWidth(200);
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
