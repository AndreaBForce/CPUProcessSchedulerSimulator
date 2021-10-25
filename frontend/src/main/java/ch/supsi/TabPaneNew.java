package ch.supsi;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class TabPaneNew {
    private TabPane tabpane;

    public TabPaneNew() {
        Platform.setImplicitExit(false);
        tabpane = new TabPane();

        Tab plus = new Tab("+");
        plus.setClosable(false);

        //Parte da spostare che permette la creazione di un nuovo tab
        plus.setOnSelectionChanged (e ->{
            if(plus.isSelected()){
                TabView aggiunta = new TabView("ProcessoN");
                ProcessListView lista = new ProcessListView(aggiunta);

                aggiunta.getTab().setContent(lista.container);

                addTab(aggiunta);
            }
        }
        );

        tabpane.setLayoutY(25);
        //Evento che gestisce i soci
        tabpane.getTabs().add(plus);
    }

    public void addTab(TabView tab){
        tabpane.getTabs().add(tab.getTab());
    }

    public TabPane getTabpane() {
        return tabpane;
    }

    public void setTabpane(Scheduler schedulder) {

        schedulder.getVboxMenu().getChildren().add(getTabpane());
    }


}
