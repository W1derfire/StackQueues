import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerConnection implements Runnable{

    HashMap<String, ConnectionInfo> ourConnect;
    StackQueue inQueue;

    public ServerConnection(HashMap<String, ConnectionInfo> h, StackQueue queue){
        ourConnect = h;
        inQueue = queue;
    }


    @Override
    public void run() {

        try {
            ServerSocket socket = new ServerSocket(900);
            System.out.println("Waiting for a client...");

            while(!Thread.interrupted()) {
                Socket clientMan = socket.accept();
                System.out.println("Yippee! Connected to " + clientMan.getInetAddress());

                ObjectInputStream in = new ObjectInputStream(clientMan.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientMan.getOutputStream());

                //CommunicationOut is ALREADY running.
                //Pass it to communicationOut through allConnections.
                ourConnect.put(clientMan.getInetAddress().toString(), new ConnectionInfo(clientMan.getInetAddress().getHostAddress(),
                        clientMan,out));

                CommunicationIn wynaut = new CommunicationIn(in,inQueue);
                Thread wobuffet = new Thread(wynaut,clientMan.getInetAddress().toString());
                wobuffet.start();


            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
