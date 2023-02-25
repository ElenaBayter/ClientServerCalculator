


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 1234)) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Input first number: ");
                String first = scanner.nextLine();
                dataOutputStream.writeUTF(first);
                if (first.equals("0")) {
                    System.out.println("End");
                    break;
                } else{
                    System.out.println("Input action: ");
                    String action = scanner.nextLine();
                    dataOutputStream.writeUTF(action);
                    List<String> actions = new ArrayList<>();
                    Collections.addAll(actions, "+", "-", "*", "/");
                    if (actions.contains(action)) {
                        System.out.println("Input second number: ");
                        String second = scanner.nextLine();
                        dataOutputStream.writeUTF(second);

                        System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());
                    } else {
                        System.out.println("Try to use correct operator");
                    }
                }

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}