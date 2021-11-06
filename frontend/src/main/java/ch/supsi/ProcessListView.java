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

    public void add(Process process){
        String hexColor = "#83C1DC";

        HBox cell = getCell(hexColor);
        Label label = getLabel(process.toString());
        Button button = getButton();
        Region space = getSpace();

        if(isDark(hexColor))
            label.setStyle("-fx-text-fill: #ffffff;");
        else
            label.setStyle("-fx-text-fill: #000000;");

        cell.getChildren().addAll(label,space,button);

        processBox.getChildren().add(cell);
    }

    public void removeCell(String id){
        Node toRemove =  container.lookup('#'+id);
        processBox.getChildren().remove(toRemove);
    }

    private HBox getCell(String hexColor){
        HBox cell = new HBox();
        cell.setStyle("-fx-background-radius: 5; -fx-background-color: "+hexColor+";");
        cell.setEffect(new DropShadow(10, Color.BLACK));
        cell.setPrefHeight(32);
        cell.setAlignment(Pos.CENTER);
        cell.setFillHeight(false);
        cell.setId("cell"+id);
        id++;
        return cell;
    }

    private Label getLabel(String text){
        Label label = new Label(text);
        label.setFont(Font.font("Futura"));
        label.setPrefWidth(150);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public Region getSpace(){
        Region space = new Region();
        space.setPrefWidth(200);
        space.setPrefHeight(30);
        HBox.setHgrow(space, Priority.ALWAYS);
        return space;
    }

    private Button getButton(){
        Button button = new Button();

        button.setText("Remove");
        button.setStyle("-fx-background-insets: 0; -fx-background-color: #f0f0f0; -fx-font-size: 10px;");
        button.setMaxHeight(10);
        HBox.setMargin(button, new Insets(5,5,5,5));

        button.setOnMouseClicked(mouseEvent -> {
            removeCell(button.getParent().getId());
        });

        return button;
    }

    private boolean isDark(String hexColor){
        double bright = Color.web(hexColor).getBrightness();
        return bright < 0.8;
    }

}
