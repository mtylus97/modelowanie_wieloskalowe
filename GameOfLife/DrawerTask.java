import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawerTask extends Task<Void> {

    private int width, height;
    private String startState;
    public Cell[][] board;

    private GraphicsContext gc;

    private BufferedImage bi;

    DrawerTask(int width, int height, String startState, GraphicsContext gc) {
        this.width = width;
        this.height = height;
        this.startState = startState;
        this.gc = gc;
        System.out.println("start State " + startState);
    }


    public void startState(Cell[][] board, String state){
        int height = board.length;
        int width = board[0].length;
        int midWidth = width / 2 ;
        int midHeight = height / 2 - 1;

        if(state.equals("oscylator")){
            for (int i = 0; i < 3; ++i) {
                board[midHeight + i][midWidth].setAlive(true);
            }
        }
        if(state.equals("glider")){
            board[midHeight][midWidth].setAlive(true);
            board[midHeight][midWidth-1].setAlive(true);
            board[midHeight-1][midWidth].setAlive(true);
            board[midHeight-1][midWidth+1].setAlive(true);
            board[midHeight+1][midWidth+1].setAlive(true);
        }
        if(state.equals("niezmienny")){
            board[midHeight][midWidth-2].setAlive(true);
            board[midHeight][midWidth+1].setAlive(true);
            board[midHeight-1][midWidth].setAlive(true);
            board[midHeight-1][midWidth-1].setAlive(true);
            board[midHeight+1][midWidth].setAlive(true);
            board[midHeight+1][midWidth-1].setAlive(true);
        }
        if(state.equals("losowy")){
            Random generator = new Random();
            for(int i=0;i<board.length; ++i){
                for(int j=0; j< board[0].length; ++j){
                    board[i][j].setAlive(generator.nextBoolean());
                }
            }
        }
    }


    public  Cell[][] countNextStep(Cell[][] board, int height, int width){

        Cell[][] nextBoard = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                nextBoard[i][j] = new Cell();
            }
        }


        int LU, U, RU;
        int L, R;
        int LD, D, RD;

        int prevHeight;
        int nextHeight;
        int prevWidth;
        int nextWidth;

        for(int i=0; i<height; ++i){
            int numberOfLivingNeighbours ;
            for(int j=0; j< width; ++j){

                prevHeight = (((i-1)%height)+height)%height;
                nextHeight = (((i+1)%height)+height)%height;
                prevWidth = (((j-1)%width)+width)%width;
                nextWidth = (((j+1)%width)+width)%width;


                LU = board[prevHeight][prevWidth].isAlive();
                U = board[prevHeight][((j%width)+width)%width].isAlive();
                RU = board[prevHeight][nextWidth].isAlive();

                L = board[i][prevWidth].isAlive();
                R = board[i][nextWidth].isAlive();


                LD = board[nextHeight][prevWidth].isAlive();
                D  = board[nextHeight][((j%width)+width)%width].isAlive();
                RD  = board[nextHeight][nextWidth].isAlive();

                numberOfLivingNeighbours = LU + U + RU + L + R + LD + D + RD;

                if(board[i][j].isAlive()==0 && numberOfLivingNeighbours==3) nextBoard[i][j].setAlive(true);
                if(board[i][j].isAlive()==1 && (numberOfLivingNeighbours==2 || numberOfLivingNeighbours ==3)) nextBoard[i][j].setAlive(true);
                if(board[i][j].isAlive()==1 && numberOfLivingNeighbours>3) nextBoard[i][j].setAlive(false);
                if(board[i][j].isAlive()==1 && numberOfLivingNeighbours<2) nextBoard[i][j].setAlive(false);

            }
        }

        return nextBoard;
    }

    @Override
    protected Void call() throws Exception {

       // bi= new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);


        Cell[][] board = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                board[i][j] = new Cell();
            }
        }

        startState(board, startState);
        System.out.println("start");

        while(true) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {

                    if (board[i][j].isAlive()==1) {
                        //bi.setRGB(i, 390 - j, java.awt.Color.BLACK.getRGB());
                        gc.setFill(Color.BLACK);
                       gc.fillRect(i*600/width, j*400/height, 600/width, 400/height);

                        //gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
                    }
                    else {
                       // System.out.println("drugi if");

                              //bi.setRGB(i+k, 390-j+l, java.awt.Color.WHITE.getRGB());
                        gc.setFill(Color.WHITE);
                        gc.fillRect(i*600/width, j*400/height, 600/width, 400/height);
                    }
                     //if(i==height-1)  gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0, 0);
                }

            }

            if(isCancelled()) {
                System.out.println("stop");
                Thread.dumpStack();
                break;
            }
            Thread.sleep(500);
            board = countNextStep(board, height, width);
        }
        return null;
    }
}
