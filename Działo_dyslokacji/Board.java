import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Board extends Task<Void> {
    private int columns;
    private int rows;
    private Cell[][] cellArray;
    private String startState;
    private GraphicsContext gc;
    private int jednorodnex, jednorodney, randomF, radius, iter;
    private double roPrev, roNext;
    private final double A = 86710969050178.5, B =9.41268203527779;
    private float t = 0;
    private double sredniaWartoscDyslokacji;
    private ArrayList<Cell> listEdge, listInside;
    private int numberOfValues;


    public Board(int columns, int rows, String startState,int jednorodnex,int  jednorodney, int  randomF,int radius,int iter, GraphicsContext gc) throws InterruptedException {
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
                array[i][j].setX(ThreadLocalRandom.current().nextDouble(j, j+1));
                array[i][j].setY(ThreadLocalRandom.current().nextDouble(i, i+1));
            }
        }

        Scanner in = new Scanner(System.in);

        if(startState.equals("Random")){
            numberOfValues=randomF;

            for(int i=1; i<=randomF; ++i){
                int column = new Random().nextInt(columns);
                int row = new Random().nextInt(rows);
                cellArray[column][row].setValue(i);
                cellArray[column][row].setColor(randomColor());
            }
        }

        if(startState.equals("Jednorodne")){
            numberOfValues=jednorodnex*jednorodney;
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
            boolean bigChecker = false;
            for (int i = 1; i <= randomF; ++i) {
                int column = new Random().nextInt(columns);
                int row = new Random().nextInt(rows);
                boolean isInserted = false;
                while (isInserted == false) {
                    System.out.println(i);
                    if(i!=1) {
                        for (int j = 0; j <= radius; ++j) {
                            if (row - j >= 0&& cellArray[column][row - j].getValue() != 0 && cellArray[column][row-j].getX()>=radius+cellArray[column][row].getX() && cellArray[column][row-j].getY()>=radius+cellArray[column][row].getY() ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for (int j = 0; j <= radius; ++j) {
                            if (row + j < rows-1 && cellArray[column][row + j].getValue() != 0 &&  cellArray[column][row+j].getX()>=radius+cellArray[column][row].getX() && cellArray[column][row+j].getY()>=radius+cellArray[column][row].getY() ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for (int j = 0; j <= radius; ++j) {
                            if (column - j >= 0 && cellArray[column - j][row].getValue() != 0 &&  cellArray[column-j][row].getX()>=radius+cellArray[column][row].getX() && cellArray[column-j][row].getY()>=radius+cellArray[column][row].getY() ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for (int j = 0; j <= radius; ++j) {
                            if (column + j < columns-1 && cellArray[column + j][row].getValue() != 0 &&  cellArray[column+j][row].getX()>=radius+cellArray[column][row].getX() && cellArray[column+j][row].getY()>=radius+cellArray[column][row].getY()   ) {
                                bigChecker = true;
                                break;
                            }
                        }
                    }
                    if (i!=1 && bigChecker == false) {
                        for(int k=0;k<=radius; ++k) {
                            for (int j = 0; j <= radius; ++j) {
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
                        isInserted = true;

                    } else {
                        column = (column+1)%columns;
                        row = (row+1)%rows;
                        bigChecker = false;
                    }

                }
                isInserted = false;
            }
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
                to[i][j].setEnergy(from[i][j].getEnergy());
            }
        }
    }


    public void countEnergy(Cell[][] cellArray){


        for(int i=0; i<rows; ++i){
            for(int j=0; j<columns; ++j){
               /* if(i>0 && j>0 && i<rows && j<columns){
                    if(array[i-1][j].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i+1][j].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i-1][j-1].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i+1][j+1].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i-1][j+1].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i+1][j-1].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i][j+1].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                    if(array[i][j-1].getValue()!=array[i][j].getValue()) array[i][j].incrementEnergy();
                }*/
                if(i==0){
                    if(j==0){
                        if(cellArray[i+1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i+1][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    }
                    else if(j<columns-1){
                        if(cellArray[i+1][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i+1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i+1][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    }
                    else{
                        if(cellArray[i][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i+1][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i+1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    }
                }
                else if(i==rows-1){
                    if(j==0){
                        if(cellArray[i-1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i-1][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    }
                    else if(j==columns-1){
                        if(cellArray[i][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i-1][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i-1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    }
                    else{
                        if(cellArray[i][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i-1][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i-1][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                        if(cellArray[i-1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    }
                }
                else{
                    if(cellArray[i-1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(cellArray[i+1][j].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(j!=0 && cellArray[i-1][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(j<columns-1 && cellArray[i+1][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(j<columns-1 && cellArray[i-1][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(j!=0 && cellArray[i+1][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(j<columns-1 && cellArray[i][j+1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                    if(j!=0 && cellArray[i][j-1].getValue()!=cellArray[i][j].getValue()) cellArray[i][j].incrementEnergy();
                }

            }
        }


    }
    public void calculateDelta(Cell[][] cellArray, Cell[][] deltaArray){

        int randomI, randomJ;
        // System.out.println("przed petlo");
        for(int i=0; i<rows; ++i){
            for(int j=0;j<columns; ++j){
                //   System.out.println("w petli");
                //System.out.println(deltaArray[i][j].getEnergy());
                if(i>0 &&j>0 &&i<rows-1 && j<columns-1 && deltaArray[i][j].getEnergy()>0) {
                    // System.out.println("w ifie");
                    randomI = new Random().nextInt((1 + 1) + 1) - 1;
                    randomJ = new Random().nextInt((1 + 1) + 1) - 1;

                    // System.out.println("ranI: " + randomI + " ranJ: "+ randomJ);
                    //  deltaArray[i][j]=deltaArray[i+randomI][j+randomJ];
                    deltaArray[i][j].setColor(deltaArray[i + randomI][j + randomJ].getColor());
                    deltaArray[i][j].setValue(deltaArray[i + randomI][j + randomJ].getValue());
                    deltaArray[i][j].setEnergy(deltaArray[i + randomI][j + randomJ].getEnergy());
                }
            }
        }

        countEnergy(deltaArray);

        int numE =0, numI=0;
        for(int i=0; i<rows; ++i){
            for(int j=0; j< columns; ++j){
                if(cellArray[i][j].getEnergy()>0)numE++;
                else numI ++;
                if(deltaArray[i][j].getEnergy()-cellArray[i][j].getEnergy()<=0){
                    cellArray[i][j].setColor(deltaArray[i][j].getColor());
                    cellArray[i][j].setValue(deltaArray[i][j].getValue());
                    cellArray[i][j].setEnergy(deltaArray[i][j].getEnergy());
                }
            }
        }
        System.out.println("NUMI: "+ numI + " NUME: " + numE);
        listEdge = new ArrayList<>(numE);
        listInside = new ArrayList<>(numI);
        for(int i=0; i<rows; ++i){
            for(int j=0; j< columns; ++j){
                if(cellArray[i][j].getEnergy()>0){
                    listEdge.add(cellArray[i][j]);
                }
                else listInside.add(cellArray[i][j]);
            }
        }


        int sumDelta =0, sum = 0;
        for(int i=0; i<rows; ++i){
            for(int j=0; j< columns; ++j){
                sumDelta+=deltaArray[i][j].getEnergy();
                sum+=cellArray[i][j].getEnergy();
            }
        }
        System.out.println("Sumdelta" + sumDelta);
        System.out.println("sum"+ sum);
        System.out.println("Różnica:" + (sumDelta-sum));
    }

    @Override
    protected Void call() throws Exception {

        Cell[][] deltaArray = new Cell[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                deltaArray[i][j] = new Cell();
            }
        }

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

            draw();


            if(isCancelled()) {
                System.out.println("stop");
                // Thread.dumpStack();
                break;
            }

            Thread.sleep(10);

        }

        double rocritical = 4.21584E+12;

        countEnergy(cellArray);
        copyArray(deltaArray, cellArray);
        calculateDelta(cellArray,deltaArray);

        Thread.sleep(10);
        draw();


        roPrev = A/B * 1-(A/B) * Math.exp(-B*t);

        System.out.println("RO: "+ roPrev);

        PrintWriter writer = new PrintWriter("ro.txt");

        while(t<=0.201){

            writer.println(roPrev);
            t+=0.001;
            roNext = A/B * 1-(A/B) * Math.exp(-B*t);
            sredniaWartoscDyslokacji =(roNext-roPrev)/(columns*rows);

            for(int i=0; i< rows; ++i){
                for(int j=0; j<columns; ++j){
                    cellArray[i][j].zwiekszGestosc(0.2*sredniaWartoscDyslokacji);
                    roNext-=(0.2*sredniaWartoscDyslokacji);
                }
            }
            Random rand = new Random();
            int random;
            System.out.println("ron: " + roNext);
            while(roNext>1){
                random = new Random().nextInt(5);
                if(random<4){
                    listEdge.get(rand.nextInt(listEdge.size())).zwiekszGestosc(0.3*roNext);
                    if(listEdge.get(rand.nextInt(listEdge.size())).getGestoscDyslokacji()>rocritical){
                        listEdge.get(rand.nextInt(listEdge.size())).setCzyZrekrystalizowany(true);
                        listEdge.get(rand.nextInt(listEdge.size())).setGestoscDyslokacji(0);
                        listEdge.get(rand.nextInt(listEdge.size())).setValue(++numberOfValues);
                        listEdge.get(rand.nextInt(listEdge.size())).setColor(randomColor());
                    }
                }
                else{
                    listInside.get(rand.nextInt(listInside.size())).zwiekszGestosc(0.3*roNext);
                    if( listInside.get(rand.nextInt(listInside.size())).getGestoscDyslokacji()>rocritical){
                        listInside.get(rand.nextInt(listInside.size())).setCzyZrekrystalizowany(true);
                        listInside.get(rand.nextInt(listInside.size())).setGestoscDyslokacji(0);
                        listInside.get(rand.nextInt(listInside.size())).setValue(++numberOfValues);
                        listInside.get(rand.nextInt(listInside.size())).setColor(randomColor());
                    }

                }

                boolean ifNeighbour = false;
                for(int i=0;i<rows; ++i){
                    for(int j=0;j<columns; ++j){
                        if(j!=0 && cellArray[i][j-1].isCzyZrekrystalizowany()) ifNeighbour=true;
                        if(j != columns-1 && cellArray[i][j+1].isCzyZrekrystalizowany()) ifNeighbour=true;
                        if(i!=0 && cellArray[i-1][j].isCzyZrekrystalizowany()) ifNeighbour=true;
                        if(i!=rows-1 && cellArray[i+1][j].isCzyZrekrystalizowany()) ifNeighbour=true;

                        if(ifNeighbour){
                            if(cellArray[i][j].getGestoscDyslokacji()>cellArray[i][j-1].getGestoscDyslokacji() &&
                                    cellArray[i][j].getGestoscDyslokacji()>cellArray[i][j+1].getGestoscDyslokacji() &&
                                    cellArray[i][j].getGestoscDyslokacji()>cellArray[i-1][j].getGestoscDyslokacji() &&
                                    cellArray[i][j].getGestoscDyslokacji()>cellArray[i+1][j].getGestoscDyslokacji()){
                                cellArray[i][j].setCzyZrekrystalizowany(true);
                                cellArray[i][j].setGestoscDyslokacji(0);
                                cellArray[i][j].setValue(++numberOfValues);
                                cellArray[i][j].setColor(randomColor());
                            }
                        }
                        ifNeighbour=false;
                    }
                }

                roNext-=0.3*roNext;
            }

          roPrev= A/B * 1-(A/B) * Math.exp(-B*t);
        }
        writer.close();

        draw();

        drawDislocation();

        return null;
    }

    public void drawDislocation(){
        float height = 600/rows;
        float width = 600/columns;

        double cos = 1000000000000.0;
        for(int i=0; i<rows; ++i){
            for (int j=0;j< columns; ++j){
                    gc.setFill(Color.RED);
                    gc.fillOval(cellArray[i][j].getX()*height, cellArray[i][j].getY()*width, cellArray[i][j].getGestoscDyslokacji()/cos, cellArray[i][j].getGestoscDyslokacji()/cos);
            }
        }
    }
    public void clearScreen(){
        float height = 600/rows;
        float width = 600/columns;
        for(int i=0; i<rows; ++i){
            for (int j=0;j< columns; ++j){
                gc.setFill(Color.WHITE);
                gc.fillRect(j * height, i * width, height, width);
            }
        }
    }
    public void draw() {
        float height = 600/rows;
        float width = 600/columns;
        for(int i=0; i<rows; ++i){
            for (int j=0;j< columns; ++j){
                gc.setFill(cellArray[i][j].getColor());
                gc.fillRect(j * height, i * width, height, width);
            }
        }
    }
}