
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML private Canvas canvas;
    @FXML private Button start;
    @FXML private Button stop;
    @FXML private TextField widthField;
    @FXML private TextField heightField;
    @FXML private TextField jednorodneX;
    @FXML private TextField jednorodneY;
    @FXML private TextField randomField;
    @FXML private TextField radiusField;
    @FXML private TextField iField;
    @FXML private ComboBox startStateBox;
    private int width, height, jednorodnex, jednorodney, random, radius, iter;

    private Board task;
    protected String startState;
    private Thread thread;


    @FXML
    private void handleRunBtnAction() throws InterruptedException {


        width = Integer.parseInt(widthField.getText());
        height = Integer.parseInt(heightField.getText());
        jednorodnex = Integer.parseInt(jednorodneX.getText());
        jednorodney = Integer.parseInt(jednorodneY.getText());
        random= Integer.parseInt(randomField.getText());
        startState = (String)startStateBox.getSelectionModel().getSelectedItem();
        radius = Integer.parseInt(radiusField.getText());
        iter  = Integer.parseInt(iField.getText());
        GraphicsContext  gc  =  canvas.getGraphicsContext2D();
        System.out.println(startState);
        task = new Board(height,width, startState,jednorodnex, jednorodney, random, radius, iter, gc);

        thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void handleStopBtnAction(){
        task.cancel();
        task=null;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private  void  drawShapes(GraphicsContext gc)  {
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
    }

    public void handleEnBtnAction(ActionEvent actionEvent) {
        task.clearScreen();
        task.drawDislocation();
    }

    public void handleretAction(ActionEvent actionEvent) {
        task.draw();
        task.drawDislocation();
    }


}
