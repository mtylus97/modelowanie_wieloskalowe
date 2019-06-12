import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Board extends Task<Void> {
    private int columns;
    private int rows;
    private Cell[][] cellArray;
    private String startState;
    private GraphicsContext gc;
    private int jednorodnex, jednorodney, randomF, radius;

    public Board(int columns, int rows, String startState,int jednorodnex,int  jednorodney, int  randomF,int radius, GraphicsContext gc) throws InterruptedException {
        this.columns = columns;
        this.rows = rows;
        this.startState = startState;
        this.gc = gc;
        this.jednorodnex=jednorodnex;
        this.jednorodney=jednorodney;
        this.randomF=randomF;
        this.radius= radius;
    }

    public void createArray(Cell[][] array){
        for(int i=0; i<rows; ++i){
            for(int j=0; j<columns; ++j){
                array[i][j] = new Cell();
            }
        }

        Scanner in = new Scanner(System.in);

        if(startState.equals("Random")){
            // System.out.println("Ile wstawić? ");
            //int numberOfRandoms = in.nextInt();

            for(int i=1; i<=randomF; ++i){
                int column = new Random().nextInt(columns);
                int row = new Random().nextInt(rows);
                cellArray[column][row].setValue(i);
                cellArray[column][row].setColor(randomColor());
            }
        }

        if(startState.equals("Jednorodne")){
            //System.out.println("Ile w wierszu?");
            // int row = in.nextInt();
            // System.out.println("Ile w kolumnie?");
            // int column = in.nextInt();

            int inColumn = rows/jednorodnex;
            int inRow = columns/jednorodney;

            int iterator = 1;

            for(int i=inRow/2; i<rows; i+=inRow){
                for(int j=inColumn/2; j<columns; j+=inColumn){
                    cellArray[i][j].setValue(iterator);
                    cellArray[i][j].setColor(randomColor());
                    iterator++;
                }
            }
        }

        if(startState.equals("Z promieniem")) {
            //System.out.println("Ile wstawić?");
            //int numberOfRandoms = in.nextInt();
            // System.out.println("Jaki promień?");
            // int radius = in.nextInt();
            boolean bigChecker = false;
            for (int i = 1; i <= randomF; ++i) {
                int column = new Random().nextInt(columns);
                int row = new Random().nextInt(rows);
                boolean isInserted = false;
                while (isInserted == false) {
                   /* if(cellArray[column-radius][row-radius].getValue()==0){
                        cellArray[column][row].setValue(i);
                        isInserted=true;
                    }*/
                    System.out.println(i);
                    if(i!=1) {
                        for (int j = 0; j <= radius; ++j) {
                           // System.out.println("pierwszy");
                            if (row - j >= 0&& cellArray[column][row - j].getValue() != 0 && cellArray[column][row-j].getX()>=radius+cellArray[column][row].getX() && cellArray[column][row-j].getY()>=radius+cellArray[column][row].getY() ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        //System.out.println("drugi");
                        for (int j = 0; j <= radius; ++j) {
                            if (row + j < rows && cellArray[column][row + j].getValue() != 0 &&  cellArray[column][row+j].getX()>=radius+cellArray[column][row].getX() && cellArray[column][row+j].getY()>=radius+cellArray[column][row].getY() ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for (int j = 0; j <= radius; ++j) {
                           // System.out.println("trzeci");
                            if (column - j >= 0 && cellArray[column - j][row].getValue() != 0 &&  cellArray[column-j][row].getX()>=radius+cellArray[column][row].getX() && cellArray[column-j][row].getY()>=radius+cellArray[column][row].getY() ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for (int j = 0; j <= radius; ++j) {
                          // System.out.println("czwarty");
                            if (column + j < columns && cellArray[column + j][row].getValue() != 0 &&  cellArray[column+j][row].getX()>=radius+cellArray[column][row].getX() && cellArray[column+j][row].getY()>=radius+cellArray[column][row].getY()   ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for(int k=0;k<=radius; ++k) {
                            for (int j = 0; j <= radius; ++j) {

                               //System.out.println("piąty");
                                if (column - j >= 0 && cellArray[column - j][row - k].getValue() != 0 &&  cellArray[column-j][row-k].getX()>=radius+cellArray[column-j][row-k].getX() && cellArray[column-j][row-k].getY()>=radius+cellArray[column][row].getY()  && row - k >= 0 ) {
                                    bigChecker = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for(int k=0;k<=radius; ++k) {
                            for (int j = 0; j <= radius; ++j) {
                             //   System.out.println("szósty");
                                if (column + j < columns && row - k >= 0 && cellArray[column + j][row - k].getValue() != 0&&  cellArray[column+j][row-k].getX()>=radius+cellArray[column][row].getX() && cellArray[column+j][row-k].getY()>=radius+cellArray[column][row].getY() ) {
                                    bigChecker = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for(int k=0;k<=radius; ++k) {
                            for (int j = 0; j <= radius; ++j) {
                               // System.out.println("siódmy");
                                if (column - j >= 0 && row + k < rows && cellArray[column - j][row + k].getValue() != 0&&  cellArray[column-j][row+k].getX()>=radius+cellArray[column][row].getX() && cellArray[column-j][row+k].getY()>=radius+cellArray[column][row].getY() ) {
                                    bigChecker = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for(int k=0;k<=radius; ++k) {
                            for (int j = 0; j <= radius; ++j) {
                               // System.out.println("ósmy");
                                if (column + j < columns && row + k < rows && cellArray[column + j][row + k].getValue() != 0) {
                                    bigChecker = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!bigChecker) {
                        cellArray[column][row].setValue(i);
                        cellArray[column][row].setColor(randomColor());
                        cellArray[column][row].setX(ThreadLocalRandom.current().nextDouble(column, column+1));
                        cellArray[column][row].setY(ThreadLocalRandom.current().nextDouble(row, row+1));
                        System.out.println("RANDOM: kolumna: " + column + " wiersz: " + row);
                        System.out.println("Współrzędne: x: " + cellArray[column][row].getX()+ " y: "+ cellArray[column][row].getY());

                        isInserted = true;

                    } else {
                        column = (column+1)%columns;
                        row = (row+1)%rows;
                        bigChecker = false;
                    }

                //   printArray(cellArray);
                    //zrobić co by nie wyszło poza tablicę i dołożyć na 4 strony warunki
                }
                isInserted = false;
            }
            //printArray(cellArray);
        }
    }


    public Paint randomColor(){
        Random random = new Random();
        int r = (random.nextInt(255)+random.nextInt(255))%255;
        int g = (random.nextInt(255)+random.nextInt(255))%255;
        int b = (random.nextInt(255)+random.nextInt(255))%255;

        return Color.rgb(r,g,b);
    }

    public int findZeroes(Cell[][] array){
        int counter=0;
        for(int i=0; i<rows; ++i){
            for(int j=0; j<columns; ++j){
                if(array[i][j].getValue()==0) counter++;
            }
        }
        return counter;
    }

    public void printArray(Cell[][] array){
        for(int i=0; i<rows; ++i){
            for(int j=0; j<columns; ++j){
                System.out.print(array[i][j].getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void copyArray(Cell[][] to, Cell[][] from){
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                to[i][j].setValue(from[i][j].getValue());
                to[i][j].setColor(from[i][j].getColor());
            }
        }
    }

    @Override
    protected Void call() throws Exception {


        cellArray = new Cell[columns][rows];
        createArray(cellArray);

        float height = 600/rows;
        float width = 600/columns;
        Cell[][] array = new Cell[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                array[i][j] = new Cell();
            }
        }
        // printArray(cellArray);
        while(findZeroes(cellArray)>0) {

            copyArray(array, cellArray);
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    if (cellArray[i][j].getValue() != 0) {
                        if (cellArray[i][j].getValue() == 0) continue;
                        else {
                            if (j != 0) {
                                if (cellArray[i][j - 1].getValue() == 0/* && array[i][j - 1].getValue() == 0*/) {
                                    array[i][j - 1].setValue(cellArray[i][j].getValue());
                                    array[i][j - 1].setColor(cellArray[i][j].getColor());
                                }
                            }
                            if (j != cellArray[0].length - 1) {
                                if (cellArray[i][j + 1].getValue() == 0/* && array[i][j + 1].getValue() == 0*/) {
                                    array[i][j + 1].setValue(cellArray[i][j].getValue());
                                    array[i][j + 1].setColor(cellArray[i][j].getColor());
                                }
                            }
                            if (i != 0) {
                                if (cellArray[i - 1][j].getValue() == 0 /*&& array[i-1][j].getValue() == 0*/) {
                                    array[i - 1][j].setValue(cellArray[i][j].getValue());
                                    array[i - 1][j].setColor(cellArray[i][j].getColor());
                                }
                            }
                            if (i != cellArray.length - 1) {
                                if (cellArray[i + 1][j].getValue() == 0 /*&& array[i+1][j].getValue() == 0*/) {
                                    array[i + 1][j].setValue(cellArray[i][j].getValue());
                                    array[i + 1][j].setColor(cellArray[i][j].getColor());
                                }
                            }
                        }
                    }

                }
            }
            copyArray(cellArray, array);
            //printArray(cellArray);
            for(int i=0; i<rows; ++i){
                for (int j=0;j< columns; ++j){
                    gc.setFill(cellArray[i][j].getColor());
                    gc.fillRect(j*height, i*width, height, width);
                }
            }

            if(isCancelled()) {
                System.out.println("stop");
               // Thread.dumpStack();
                break;
            }

            Thread.sleep(100);

        }

        return null;
    }

    public void neighbourhood(){

    }

}
