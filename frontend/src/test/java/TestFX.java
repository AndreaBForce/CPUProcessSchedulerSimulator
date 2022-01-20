import ch.supsi.MainApplication;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class TestFX extends ApplicationTest{

    @Before
    public void setUpClass() throws Exception {
        ApplicationTest.launch(MainApplication.class);
    }

    @Override
    public void start(Stage stage) {
        stage.show();
    }

    @Test
    @Order(1)
    public void clickOnFileMenu(){
        clickOn("#fileMenu");
        verifyThat("#exitItem", isVisible());
        verifyThat("#importSimItem", isVisible());
    }

    @Test
    @Order(2)
    public void clickOnExitMenu(){
        clickOn("#fileMenu");
        clickOn("#exitItem");
        verifyThat("Cancel", isVisible());
        clickOn("Cancel");
    }

    @Test
    @Order(3)
    public void clickOnImportSimMenu() {
        clickOn("#fileMenu");
        clickOn("#importSimItem");
        write("sim");
        verifyThat("OK", isVisible());
        clickOn("OK");
    }

    @Test
    @Order(4)
    public void clickOnEditMenu(){
        clickOn("#editMenu");
        verifyThat("#newSimItem", isVisible());
        verifyThat("#randomSimItem", isVisible());
    }

    @Test
    @Order(5)
    public void clickOnNewSimMenu(){
        clickOn("#editMenu");
        clickOn("#newSimItem");
        write("ciao");
        verifyThat("FCFS", isVisible());
        clickOn("FCFS");
        clickOn("Round Robin");
        verifyThat("Create simulation", isVisible());
        verifyThat("Cancel", isVisible());
        clickOn("Create simulation");
        clickOn("ciao");
        clickOn("ciao");
    }

    @Test
    @Order(6)
    public void clickOnNewRandomSimMenu(){
        clickOn("#editMenu");
        clickOn("#randomSimItem");
        verifyThat("random", isVisible());
        clickOn("random");
        clickOn("random");
    }

    @Test
    @Order(7)
    public void clickOnHelpMenu(){
        clickOn("#helpMenu");
        verifyThat("#aboutItem", isVisible());
        verifyThat("#infoAlgoItem", isVisible());
    }

    @Test
    @Order(8)
    public void clickOnAboutMenu(){
        clickOn("#helpMenu");
        clickOn("#aboutItem");
        verifyThat("OK", isVisible());
        clickOn("OK");
    }

    @Test
    @Order(9)
    public void clickOnInfoAlgoMenu(){
        clickOn("#helpMenu");
        clickOn("#infoAlgoItem");
        verifyThat("#comboBoxItem", isVisible());
        clickOn("#comboBoxItem");
        clickOn("Round Robin");
        clickOn("#comboBoxItem");
        clickOn("SJF");
        clickOn("#comboBoxItem");
        clickOn("Lottery");
        clickOn("#comboBoxItem");
        clickOn("RMA");
        clickOn("#comboBoxItem");
        clickOn("EDF");
        verifyThat("Close", isVisible());
        clickOn("Close");
    }

    @Test
    @Order(10)
    public void testAddProcess(){
        clickOn("#editMenu");
        clickOn("#newSimItem");
        write("ciao");
        clickOn("Create simulation");
        clickOn("ciao");
        clickOn("ciao");
        verifyThat("Add new process", isVisible());
        clickOn("Add new process");
        verifyThat("Add", Node::isDisable);
        write("p1");
        clickOn("#tmpArr");
        write("2");
        clickOn("#tmpBurst");
        write("3");
        verifyThat("Add", isVisible());
        clickOn("Add");
    }

    @Test
    @Order(11)
    public void testButtonSimulation(){
        testAddProcess();
        verifyThat("Export simulation", Node::isDisable);
        verifyThat("Export graph", Node::isDisable);
        clickOn("Start");
        write("0.2");
        verifyThat("OK", isVisible());
        clickOn("OK");
        verifyThat("Export simulation", isEnabled());
        verifyThat("Export graph", isEnabled());
    }

    @Test
    @Order(12)
    public void testExportSim(){
        testButtonSimulation();
        clickOn("Export simulation");
        write("sim1");
        clickOn("OK");
    }

    @Test
    @Order(13)
    public void testRemoveProcess(){
        testAddProcess();
        verifyThat("Remove", isVisible());
        verifyThat("Remove", isEnabled());
        clickOn("Remove");
    }

    @Test
    @Order(14)
    public void testEditProcess(){
        testAddProcess();
        verifyThat("#btnEdit", isVisible());
        verifyThat("#btnEdit", isEnabled());
        clickOn("#btnEdit");
        write("pcs1");
        verifyThat("Confirm", isVisible());
        verifyThat("Confirm", isEnabled());
        verifyThat("Cancel", isVisible());
        verifyThat("Cancel", isEnabled());
        clickOn("Confirm");
        clickOn("#btnEdit");
        clickOn("Cancel");
    }
}