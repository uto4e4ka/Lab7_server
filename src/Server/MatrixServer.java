package Server;

import client.Matrix;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MatrixServer {
    public MatrixServer(int port) throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (true){
                try (Socket socket = serverSocket.accept();
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())){
                    System.out.println("Клиент успешно подключен. "+socket.getInetAddress());
                    Matrix matrix = (Matrix) objectInputStream.readObject();
                    System.out.println("Получена матрица: "+matrix.getRows()+" на "+matrix.getColumns());
                    double d = Matrix.getOddSum(matrix);
                    dataOutputStream.writeDouble(d);
                    System.out.println("Отправлено: "+d);
                    dataOutputStream.flush();

                }catch (Exception e){
                    System.out.println(e);
                }
            }


        }catch(IOException e){
             System.out.println(e);
        }
    }
}
