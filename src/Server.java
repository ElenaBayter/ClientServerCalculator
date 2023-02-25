

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started, waiting for connection....");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected to server");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {

                int number1 = Integer.parseInt(dataInputStream.readUTF());
                System.out.println("We received number: " + number1);
                if (number1 == 0){
                    break;
                }
                String action = dataInputStream.readUTF();
                System.out.println("We received operator: " + action);
                List<String> actions = new ArrayList<>();
                Collections.addAll(actions, "+", "-", "*", "/");
                if (actions.contains(action)){
                    int number2 = Integer.parseInt(dataInputStream.readUTF());
                    System.out.println("We received number: " + number2);
                    if (action.equals("+")){
                        dataOutputStream.writeUTF(number1 + " " + action + " " + number2 + " = " + (number1 + number2));
                    }
                    if (action.equals("-")){
                        dataOutputStream.writeUTF(number1 + " " + action + " " + number2 + " = " + (number1 - number2));
                    }
                    if (action.equals("*")){
                        dataOutputStream.writeUTF(number1 + " " + action + " " + number2 + " = " + (number1 * number2));
                    }
                    if (action.equals("/")){
                        dataOutputStream.writeUTF(number1 + " " + action + " " + number2 + " = " + (number1 / number2));
                    }
                } else {
                    System.out.println("Inputted incorrect operator");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}