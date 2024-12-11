package client;

import Exeptions.MatrixSizeOutOfBound;
import Exeptions.MatrixWrongSize;

import java.io.Serializable;
import java.util.Random;

public class Matrix implements Serializable {
    private final double[][] matrix;
    public Matrix (int rows,int columns){
        if(rows<0||columns<0||rows>100||columns>100)throw new MatrixWrongSize(rows+" "+columns);
        this.matrix = new double[rows][columns];
        fillRandom();
    }
    public int getRows(){
        return matrix.length;
    }
    public int getColumns(){
        return matrix[0].length;
    }
    public void set(int row,int column,double value){
        if(isRightIndex(row, column))matrix[row][column] = value;
        else throw new MatrixSizeOutOfBound(row+" "+column);
    }
    public double get(int row, int column){
        if(isRightIndex(row, column)) return matrix[row][column];
        else throw new MatrixSizeOutOfBound(row+" "+column);
    }
    private boolean isRightIndex(int row,int column){
        return row <= getRows() - 1 && column <= getColumns() - 1;
    }
    private void fillRandom(){
        Random random = new Random();
        for (int i=0;i<getRows();i++){
            for (int k=0;k<getColumns();k++){
                matrix[i][k] = Math.round(Math.abs(random.nextDouble() * 100));
            }
        }
    }
    public static double getOddSum(Matrix matrix){
        double sum = 0;
        for (int i=0;i<matrix.getRows();i++){
            for (int k=0;k<matrix.getColumns();k++){
                double num = matrix.get(i,k);
                if(num%2!=0) sum+=num;
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < getRows(); i++) {
            for (int k = 0; k < getColumns(); k++) {
                str.append(matrix[i][k]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
