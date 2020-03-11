import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            Thread.currentThread().setName("Nicco");

            Socket socket = new Socket("10.37.154.198", 155);
            System.out.println("Connected to " + socket.getInetAddress() + "!");



            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            StackQueue beQueue = new StackQueue("Queue", "Dummy");

            //CommunicationOut goAway = new CommunicationOut(Server,"")

            CommunicationIn input = new CommunicationIn(in, beQueue);
            Thread inThread = new Thread(input);
            inThread.start();


            StackQueue outQueue = new StackQueue("Queue", "Out!");
            ConnectionInfo serverConnection = new ConnectionInfo(socket.getInetAddress().getHostAddress(),socket,out);
            CommunicationOut goodbye = new CommunicationOut(serverConnection,outQueue);

            Thread outThread = new Thread(goodbye,"WhatThread");
            outThread.start();


            Message message = new Message(MessageValue.CONNECTDATA, "Nicco", InetAddress.getLocalHost().getHostAddress(), MessageValue.SERVERID);
            outQueue.put(message);

            //out.writeObject(message);
            //out.flush();

            Scanner scanner = new Scanner(System.in);


            while (!Thread.interrupted()) {//in.available() <= 0) {

                String whatevs = scanner.nextLine();
                Message m = new Message(whatevs, "Nicco", InetAddress.getLocalHost().getHostAddress(), "All");
                outQueue.put(m);
                //out.writeObject(m);
                //out.flush();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
