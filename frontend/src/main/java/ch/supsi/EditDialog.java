package ch.supsi;

import ch.supsi.menu.ProcessDetailsView;
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
    public EditDialog(ProcessDetailsView detailsView,Process process){
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
        //TODO INTERNALIZZAZIONE
        Label name = new Label();
        Label arrivalTime = new Label();
        Label burstTime = new Label();
        Label priority = new Label();

        name.setText("Process name: ");
        arrivalTime.setText("Arrival time:     ");
        burstTime.setText("Burst time:       ");
        priority.setText("Priority:            ");

        TextField pName = new TextField();
        TextField pArrival = new TextField();
        TextField pBurst = new TextField();
        TextField pPrio = new TextField();

        pName.setText(detailsView.getProcessName().getText());
        pArrival.setText(detailsView.getValueArrival().getText());
        pBurst.setText(detailsView.getValueBurst().getText());
        pPrio.setText(detailsView.getValuePriority().getText());

        //TODO: CHECK INPUT E FAI

        hb_p_name.getChildren().addAll(name,pName);
        hb_p_name.setSpacing(10);

        hb_arrival.getChildren().addAll(arrivalTime, pArrival);
        hb_arrival.setSpacing(10);

        hb_burst.getChildren().addAll(burstTime, pBurst);
        hb_burst.setSpacing(10);

        hb_priority.getChildren().addAll(priority, pPrio);
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
            detailsView.getProcessName().setText(pName.getText());
            detailsView.getValueArrival().setText(pArrival.getText());
            detailsView.getValueBurst().setText(pBurst.getText());
            detailsView.getValuePriority().setText(pPrio.getText());

            process.setArrivalTime(Float.parseFloat(pArrival.getText()));
            process.setName(pName.getText());
            process.setBurstTime(Integer.valueOf(pBurst.getText()));
            process.setPriority(Integer.valueOf(pPrio.getText()));


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
