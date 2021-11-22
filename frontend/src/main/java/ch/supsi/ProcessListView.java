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
    ScrollPane scrollableList;
    VBox processBox;
    List<HBox> cells;
    Button addBtn;
    long id = 0;

    public ProcessListView() {
        container = new VBox();
        processBox = new VBox();
        addBtn = new Button("Add new process");
        cells = new ArrayList<>();
        scrollableList = new ScrollPane();
        container.setSpacing(16);
        container.setPadding(new Insets(10));
        container.setStyle("-fx-background-color: red");
        container.getChildren().addAll(addBtn, scrollableList);
        container.setMinWidth(200);
//        container.setMaxWidth(600);
        processBox.setMaxWidth(800);
        processBox.setSpacing(10);
        container.setAlignment(Pos.BASELINE_CENTER);
//        container.setMinHeight(200);
//        container.setMaxHeight(400);

        add(new Process("TEST"));
        add(new Process("TEST"));
        add(new Process("TEST"));
        add(new Process("TEST"));

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
        
        v_arrival.setText(String.valueOf(process.getArrivalTime()));
        v_burst.setText(String.valueOf(process.getBurstTime()));
        v_priority.setText(String.valueOf(process.getPriority()));

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
