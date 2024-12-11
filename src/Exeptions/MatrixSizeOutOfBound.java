package Exeptions;

public class MatrixSizeOutOfBound extends RuntimeException{
    public MatrixSizeOutOfBound(String message){
        super("Индексы за пределом матрицы "+message);
    }
}
