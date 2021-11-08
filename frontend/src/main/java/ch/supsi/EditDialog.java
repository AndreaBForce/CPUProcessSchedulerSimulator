package ch.supsi;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class EditDialog {
    private Stage editStage;
    private Scene scene;

    //TODO RIMPIAZZA CON OGGETTO PROCESSO
    public EditDialog(Label p_name,Label p_arr_t,Label p_burst,Label p_prio){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        editStage = new Stage();
        editStage.setTitle(resourceBundle.getString("editDialogTitle.text"));
        editStage.setAlwaysOnTop(true);

        BorderPane editBorder = new BorderPane();
        VBox cushion = new VBox();

        HBox hb_p_name = new HBox();
        HBox hb_arrival = new HBox();
        HBox hb_burst = new HBox();
        HBox hb_priority = new HBox();
        HBox hb_btns = new HBox();

        //Label di modifica parametri
        Label t_p_name = new Label();
        Label t_arrival_time = new Label();
        Label t_burst_time = new Label();
        Label t_priority = new Label();

        t_p_name.setText("Process name: ");
        t_arrival_time.setText("Arrival time:     ");
        t_burst_time.setText("Burst time:       ");
        t_priority.setText("Priority:            ");

        TextField v_p_name = new TextField();
        TextField v_arrival = new TextField();
        TextField v_burst = new TextField();
        TextField v_priority = new TextField();

        v_p_name.setText(p_name.getText());
        v_arrival.setText(p_arr_t.getText());
        v_burst.setText(p_burst.getText());
        v_priority.setText(p_prio.getText());

        //TODO: CHECK INPUT E FAI

        hb_p_name.getChildren().addAll(t_p_name,v_p_name);
        hb_p_name.setSpacing(10);

        hb_arrival.getChildren().addAll(t_arrival_time, v_arrival);
        hb_arrival.setSpacing(10);

        hb_burst.getChildren().addAll(t_burst_time, v_burst);
        hb_burst.setSpacing(10);

        hb_priority.getChildren().addAll(t_priority, v_priority);
        hb_priority.setSpacing(10);


        hb_arrival.setAlignment(Pos.CENTER);
        hb_burst.setAlignment(Pos.CENTER);
        hb_p_name.setAlignment(Pos.CENTER);
        hb_priority.setAlignment(Pos.CENTER);

        Button btn_confirm = new Button();
        Button btn_close = new Button();

        btn_confirm.setText(resourceBundle.getString("editDialogConfirm.text"));
        btn_close.setText(resourceBundle.getString("editDialogAnnulla.text"));

        hb_btns.getChildren().addAll(btn_confirm,btn_close);
        hb_btns.setSpacing(10);
        hb_btns.setAlignment(Pos.CENTER);

        cushion.setSpacing(10);
        cushion.setAlignment(Pos.CENTER);
        cushion.getChildren().addAll(hb_p_name,hb_arrival,hb_burst,hb_priority,hb_btns);
        editBorder.setCenter(cushion);

        scene = new Scene(editBorder, 300, 180);
        editStage.setScene(scene);

        btn_confirm.setOnMouseClicked(x -> {
            p_name.setText(v_p_name.getText());
            p_arr_t.setText(v_arrival.getText());
            p_burst.setText(v_burst.getText());
            p_prio.setText(v_priority.getText());
            editStage.close();
        });

        btn_close.setOnAction(x -> {
            editStage.close();
        });
    }

    public Stage getEditStage() {
        return editStage;
    }
}
