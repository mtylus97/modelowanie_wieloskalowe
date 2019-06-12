
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.awt.*;



import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML private Canvas canvas;
    @FXML private Button start;
    @FXML private Button stop;
    @FXML private TextField widthField;
    @FXML private TextField heightField;
    @FXML private ComboBox startStateBox;
    private int width, height;

    private DrawerTask task;
    protected String startState;


    @FXML
    private void handleRunBtnAction(){

        width = Integer.parseInt(widthField.getText());
        height = Integer.parseInt(heightField.getText());
        startState = (String)startStateBox.getSelectionModel().getSelectedItem();
        GraphicsContext  gc  =  canvas.getGraphicsContext2D();
        // BufferedImage bi= new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        System.out.println(startState);
        task = new DrawerTask(height,width, startState, gc);

        new Thread(task).start();

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

}
