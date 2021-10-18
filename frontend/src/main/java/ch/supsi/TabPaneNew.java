package ch.supsi;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class TabPaneNew {
    private TabPane tabpane;

    public TabPaneNew() {
        Platform.setImplicitExit(false);

        this.tabpane = new TabPane();
        Tab plus = new Tab("+");
        plus.setClosable(false);

        //Parte da spostare che permette la creazione di un nuovo tab
        plus.setOnSelectionChanged (e ->{
            if(plus.isSelected()){
                addTab(new TabView("Nome place"));
            }
        }
        );

        //Evento che gestisce i soci
        tabpane.getTabs().add(plus);
    }

    public void addTab(TabView tab){
        tabpane.getTabs().add(tab.getTab());
    }

    public TabPane getTabpane() {
        return tabpane;
    }

    public void setTabpane(TabPane tabpane) {
        this.tabpane = tabpane;
    }
}
