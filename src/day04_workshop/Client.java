package day04_workshop;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // Connect to the server listening on port 3000
        Socket conn = new Socket("localhost", 3000);
        System.out.println("Connected to server on localhost: 3000");

        try {
            while (true) {
                // Console
                Console cons = System.console();
                String eq = cons.readLine("Enter your equation in format: Operand1 Operater Operand2: ");

                if (eq.toLowerCase().contentEquals("exit")) {
                    // close connection
                    conn.close();
                }
                // write to server
                OutputStream os = conn.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);

                oos.writeUTF(eq);
                oos.flush();

                // read from server
                InputStream is = conn.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);

                String ans = ois.readUTF();

                // output the answer
                System.out.printf("Answer is: %s.\n", ans);
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e);
        }
    }
}
