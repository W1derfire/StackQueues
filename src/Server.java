import java.util.HashMap;

public class Server {
    public static void main(String[] args){
        try {

            //This is where EVERYTHING goes.
            HashMap<String,ConnectionInfo> allConnections = new HashMap<String, ConnectionInfo>();

            StackQueue inputQueue = new StackQueue("Queue","InQueue");
            StackQueue outputQueue = new StackQueue("Queue","OutQueue");

            CommunicationOut mimeJr = new CommunicationOut(allConnections,outputQueue,true);
            Thread mrMime = new Thread(mimeJr);
            mrMime.start();

            ServerSmarts smarts = new ServerSmarts(inputQueue,outputQueue,allConnections);


            ServerConnection connectionThread = new ServerConnection(allConnections, inputQueue);
            Thread rocket = new Thread(connectionThread);
            rocket.start();




            System.out.println("We're mobile!");


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
