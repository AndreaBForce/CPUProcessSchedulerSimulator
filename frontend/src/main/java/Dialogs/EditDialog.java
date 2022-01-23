package Dialogs;

import ch.supsi.Process;
import ch.supsi.menu.ProcessDetailsView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class EditDialog extends Dialog {

    public EditDialog(ProcessDetailsView detailsView, Process process){
        super(300, 180);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        getStage().setTitle(resourceBundle.getString("editDialogTitle.text"));
        getStage().setAlwaysOnTop(true);

        VBox cushion = new VBox();

        HBox hb_p_name = new HBox();
        HBox hb_arrival = new HBox();
        HBox hb_burst = new HBox();
        HBox hb_priority = new HBox();
        HBox hb_btns = new HBox();

        Label name = new Label();
        Label arrivalTime = new Label();
        Label burstTime = new Label();
        Label priority = new Label();

        name.setText(resourceBundle.getString("labelEditName.text"));
        arrivalTime.setText(resourceBundle.getString("labelEditArrivalTime.text"));
        burstTime.setText(resourceBundle.getString("labelEditBurstTime.text"));
        priority.setText(resourceBundle.getString("labelEditPriority.text"));

        TextField pName = new TextField();
        pName.setId("pName");
        TextField pArrival = new TextField();
        pArrival.setId("pArrival");
        TextField pBurst = new TextField();
        pBurst.setId("pBurst");
        TextField pPrio = new TextField();
        pPrio.setId("pPrio");

        pName.setText(detailsView.getProcessName().getText());
        pArrival.setText(detailsView.getValueArrival().getText());
        pBurst.setText(detailsView.getValueBurst().getText());
        pPrio.setText(detailsView.getValuePriority().getText());

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

        btn_confirm.getStyleClass().add("btn");
        btn_close.getStyleClass().add("btn");
        btn_confirm.setText(resourceBundle.getString("editDialogConfirm.text"));
        btn_close.setText(resourceBundle.getString("editDialogAnnulla.text"));

        hb_btns.getChildren().addAll(btn_confirm,btn_close);
        hb_btns.setSpacing(10);
        hb_btns.setAlignment(Pos.CENTER);

        cushion.setSpacing(10);
        cushion.setAlignment(Pos.CENTER);
        cushion.getChildren().addAll(hb_p_name,hb_arrival,hb_burst,hb_priority,hb_btns);
        getBorderPane().setCenter(cushion);

        getStage().setScene(getScene());

        btn_confirm.setOnMouseClicked(x -> {
            detailsView.getProcessName().setText(pName.getText());
            detailsView.getValueArrival().setText(pArrival.getText());
            detailsView.getValueBurst().setText(pBurst.getText());
            detailsView.getValuePriority().setText(pPrio.getText());

            process.setArrivalTime(Double.parseDouble(pArrival.getText()));
            process.setName(pName.getText());
            process.setBurstTime(Double.parseDouble(pBurst.getText()));
            process.setPriority(Integer.parseInt(pPrio.getText()));

            getStage().close();
        });

        btn_close.setOnAction(x -> {
            getStage().close();
        });
    }
}
