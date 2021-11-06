package ch.supsi;

import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ProcessChartView {
    private final StackedBarChart<Number, String> chart;
    private final XYChart.Series<Number, String> processList;

    public ProcessChartView() {
        CategoryAxis categoryAxis = new CategoryAxis();
        NumberAxis numberAxis = new NumberAxis();
        this.chart = new StackedBarChart<>(numberAxis, categoryAxis);
        this.processList = new XYChart.Series<>();

        categoryAxis.setTickMarkVisible(false);

        chart.setLegendVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);


        chart.setMinSize(250,100);
        chart.setMaxSize(1000, 120);
    }

    public StackedBarChart<Number, String> getChart() {
        return chart;
    }

    public void add(Process process, int length, String color){
        XYChart.Data<Number, String> data = new XYChart.Data<>(length,"");
        Label label = new Label(process.toString());
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: "+color);
        data.setNode(label);

        processList.getData().add(data);
    }

    public void addSpace(int pos, int length){
        XYChart.Data<Number, String> data = new XYChart.Data<>(length,"");
        Region space = new Region();
        space.setStyle("-fx-background-color: #e7e7e7");
        data.setNode(space);

        processList.getData().add(pos, data);
    }

    public void testChart(){
        add(new Process("P1"),1,"#f5e353");
        add(new Process("P2"), 2, "#8CF3F3");
        addSpace(1,3);
        add(new Process("P3"),1,"#F19797");
        addSpace(3,2);

        chart.getData().add(processList);
    }
}