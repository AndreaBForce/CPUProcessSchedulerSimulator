package ch.supsi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProcessListView {
    VBox container = new VBox();
    long id = 0;

    public ProcessListView() {
        container.setSpacing(16);
        container.setTranslateX(20);
        container.setTranslateY(20);
    }

    public void add(Process process){
        HBox cell = new HBox();
        Label label = new Label();
        Button button = new Button();
        Region space = new Region();
        String hexColor = "#83C1DC";

        space.setPrefWidth(200);
        space.setPrefHeight(30);
        HBox.setHgrow(space, Priority.ALWAYS);

        if(isDark(hexColor))
            label.setStyle("-fx-text-fill: #ffffff;");
        else
            label.setStyle("-fx-text-fill: #000000;");

        cell.setStyle("-fx-background-radius: 5; -fx-background-color: "+hexColor+";");
        cell.setEffect(new DropShadow(10, Color.BLACK));
        cell.setPrefHeight(32);
        cell.setAlignment(Pos.CENTER);
        cell.setFillHeight(false);
        cell.setId("cell"+id);
        id++;

        button.setText("Remove");
        button.setStyle("-fx-background-insets: 0; -fx-background-color: #f0f0f0; -fx-font-size: 10px;");
        button.setMaxHeight(10);
        HBox.setMargin(button, new Insets(5,5,5,5));

        label.setText(process.toString());
        label.setFont(Font.font("Futura"));
        label.setPrefWidth(150);
        label.setAlignment(Pos.CENTER);

        cell.getChildren().addAll(label,space,button);
        container.getChildren().add(cell);

        button.setOnMouseClicked(mouseEvent -> {
            removeCell(button.getParent().getId());
        });
    }

    public void removeCell(String id){
        Node toRemove =  container.lookup('#'+id);
        container.getChildren().remove(toRemove);
    }

    private boolean isDark(String hexColor){
        double bright = Color.web(hexColor).getBrightness();
        return bright < 0.8;
    }

}
