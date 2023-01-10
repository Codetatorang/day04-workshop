package day04_workshop;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[]args) throws IOException{
         int port = 3000; //default port number
         if (args.length >0){
            port = Integer.parseInt(args[0]);
         }

        //Create a server socket and listen to a specific port
        ServerSocket server = new ServerSocket(port);

        while (true){
            //wait for a connection
            System.out.println("Waiting for connection.");

            Socket conn = server.accept();
            System.out.println("Connection established.");

            //inputstream
            InputStream is = conn.getInputStream();
            ObjectInputStream ois  = new ObjectInputStream(is);

            String input = ois.readUTF();
            System.out.printf("%s\n", input);

            //do calculations
            String[] eq = input.split(" ", -1);
            float ans = 0f;


            switch(eq[1]){
                case "+":
                ans = Float.parseFloat(eq[0]) + Float.parseFloat(eq[2]);
                break;
                case "-":
                ans = Float.parseFloat(eq[0]) - Float.parseFloat(eq[2]);
                break;
                case "*":
                ans = Float.parseFloat(eq[0]) * Float.parseFloat(eq[2]);
                break;
                case "/":
                ans = Float.parseFloat(eq[0]) / Float.parseFloat(eq[2]);
                break;
            }

            //write back to client
            OutputStream os = conn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeUTF(Float.toString(ans));
            oos.flush();
            //close the conection
            conn.close();
        }



    }


}