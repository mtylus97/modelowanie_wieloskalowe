import javax.swing.*;
import java.awt.*;

public class Automat {

    private int[][] array;
    private int[] rule;
    public int numberOfSteps, length;

    public Automat(int numberOfSteps, int length, int rule) {

        this.numberOfSteps = numberOfSteps;
        this.length = length;
        this.array = new int[numberOfSteps][length];

        array[0][array[0].length / 2] = 1;

        this.rule = generateRule(rule);
        applyRule();
      //  printArray();
    }

    private void applyRule(){


        int prev, actual, next;

        for(int i=1;i<array.length;++i){
            for(int j=0; j<array[i].length; ++j){

                prev = array[i-1][(((j-1)%array[i].length)+array[i].length)%array[i].length];
                actual = array[i-1][j];
                next = array[i-1][(((j+1)%array[i].length)+array[i].length)%array[i].length];

                     if(prev==1 && actual==1 && next==1) array[i][j]=rule[0];
                else if(prev==1 && actual==1 && next==0) array[i][j]=rule[1];
                else if(prev==1 && actual==0 && next==1) array[i][j]=rule[2];
                else if(prev==1 && actual==0 && next==0) array[i][j]=rule[3];
                else if(prev==0 && actual==1 && next==1) array[i][j]=rule[4];
                else if(prev==0 && actual==1 && next==0) array[i][j]=rule[5];
                else if(prev==0 && actual==0 && next==1) array[i][j]=rule[6];
                else if(prev==0 && actual==0 && next==0) array[i][j]=rule[7];
            }
        }
    }

    public int[] generateRule(int rule){
        int[] ruleArray = new int[8];

        String binaryRule = Integer.toBinaryString(rule);
        int div = 8 - binaryRule.length();

        for(int i=0; i<binaryRule.length(); ++i) {
            ruleArray[i+div] = (binaryRule.charAt(i)=='1') ? 1 : 0;
        }

        return ruleArray;
    }

    public void printArray() {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                System.out.printf(array[i][j] + " ");
               // if(array[i][j]==1) System.out.print("■");
               // else System.out.print("□");
            }
            System.out.println();
        }
    }

    public int[][] getArray() {
        return array;
    }
}
