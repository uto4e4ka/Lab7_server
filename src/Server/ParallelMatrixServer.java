package Server;

import client.Matrix;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ParallelMatrixServer {
    ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public ParallelMatrixServer(int port){
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (true){
                Socket socket =serverSocket.accept();
                threadPool.submit(()->userHandler(socket));
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void userHandler(Socket client){
        System.out.println("Клиент успешно подключен. "+client.getInetAddress());
        try(ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());)
        {
            while (!client.isClosed()) {
                Matrix matrix = (Matrix) objectInputStream.readObject();
                System.out.println("Получена матрица: "+matrix.getRows()+" на "+matrix.getColumns());

                System.out.println(matrix.toString());
                double d = Matrix.getOddSum(matrix);
                dataOutputStream.writeDouble(d);
                dataOutputStream.flush();
                System.out.println("Отправлено: "+d);
            }
            System.out.println("Клиент отключился");
            client.close();
        }catch (IOException e){
            System.out.println(e);
        }catch (ClassNotFoundException  e)
        {
            System.out.println(e);
        }
        finally {
            try {
                client.close();
                System.out.println("Клиент отключился.");
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии соединения с клиентом: " + e.getMessage());
            }
        }
    }
}
