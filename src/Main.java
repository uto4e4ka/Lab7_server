import Server.MatrixServer;
import Server.ParallelMatrixServer;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       new MatrixServer(7777);
        //new ParallelMatrixServer(7777);
    }
}