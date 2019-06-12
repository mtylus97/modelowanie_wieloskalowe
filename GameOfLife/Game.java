import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel{

    private int width, height;
    private String startState;
    private int span = 10;
    public Cell[][] board;
    Game(int width, int height, String startState) {
        this.width = width;
        this.height = height;
        this.startState = startState;

        Cell[][] board = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                board[i][j] = new Cell();
            }
        }

        startState(board, startState);

        for (int k=0;k<100;++k) {
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    //repaint();
                    //System.out.print(board[i][j].isAlive() + " ");
                    if(board[i][j].isAlive()==1) System.out.print("■");
                    else System.out.print("□");
                }
                System.out.println();
            }
             System.out.println();
             System.out.println();

            board = countNextStep(board, height, width);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   /* public void paint(Graphics g){
        for(int i=0; i<board.length-1; i++){
            for(int j=0; j<board[i].length-1; ++j){
                if(board[i][j].isAlive()==0)
                    g.drawRect(j+span*j, i+span*i, span, span );
                else g.fillRect(j+span*j,i+span*i, span, span);
            }
        }
    }*/

    public void startState(Cell[][] board, String state){
        int height = board.length;
        int width = board[0].length;
        int midWidth = width / 2 ;
        int midHeight = height / 2 - 1;

        if(state == "oscylator"){
            for (int i = 0; i < 3; ++i) {
                board[midHeight + i][midWidth].setAlive(true);
            }
        }
        if(state=="glider"){
            board[midHeight][midWidth].setAlive(true);
            board[midHeight][midWidth-1].setAlive(true);
            board[midHeight-1][midWidth].setAlive(true);
            board[midHeight-1][midWidth+1].setAlive(true);
            board[midHeight+1][midWidth+1].setAlive(true);
        }
        if(state == "niezmienny"){
            board[midHeight][midWidth-2].setAlive(true);
            board[midHeight][midWidth+1].setAlive(true);
            board[midHeight-1][midWidth].setAlive(true);
            board[midHeight-1][midWidth-1].setAlive(true);
            board[midHeight+1][midWidth].setAlive(true);
            board[midHeight+1][midWidth-1].setAlive(true);
        }
        if(state == "losowy"){
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
}
