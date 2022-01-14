package ch.supsi;

import ch.supsi.utility.ImageExport;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import service.process.Process;

import java.io.File;

public class ProcessChartView {
    private final StackedBarChart<Number, String> chart;
    private final XYChart.Series<Number, String> processList;
    private ImageExport imageExport;

    public ProcessChartView() {
        CategoryAxis categoryAxis = new CategoryAxis();
        NumberAxis numberAxis = new NumberAxis();
        this.chart = new StackedBarChart<>(numberAxis, categoryAxis);
        this.processList = new XYChart.Series<>();

        testChart();
        categoryAxis.setTickMarkVisible(false);

        chart.setLegendVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);


        chart.setMinSize(250,100);
        chart.setMaxSize(1000, 120);
    }

    public void exportImage(File file){
        imageExport = new ImageExport(chart, file);
    }

    public StackedBarChart<Number, String> getChart() {
        return chart;
    }

    public void add(Process process, double length, String color){
        XYChart.Data<Number, String> data = new XYChart.Data<>(length,"");
        Label label = new Label(process.getName());
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

        chart.getData().add(processList);
    }

    public XYChart.Series<Number, String> getProcessList() {
        return processList;
    }
}