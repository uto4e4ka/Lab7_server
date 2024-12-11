package Exeptions;

public class MatrixWrongSize extends RuntimeException{
    public MatrixWrongSize(String message){
        super("Неверный размер матрицы: "+message);
    }
}
