import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CommunicationOut implements Runnable {

    StackQueue outQueue;
    boolean isServer;

    //Server-only
    HashMap<String,ConnectionInfo> allConnections;

    //Client-only
    ConnectionInfo serverConnection;


    //Server constructor
    public CommunicationOut(HashMap connections, StackQueue queue, boolean isServer){
        this.outQueue = queue;
        this.isServer = isServer;
        this.allConnections = connections;
    }


    //Client constructor
    public CommunicationOut(ConnectionInfo server, StackQueue queue){
        serverConnection = server;
        allConnections = null;
        outQueue = queue;
        isServer = false;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {

                Object latest = outQueue.get();
                while(latest == Boolean.FALSE){
                    Thread.currentThread().yield();
                    latest = outQueue.get();
                }
                //System.out.println("We've recieved!");

                //System.out.

                final Message data = (Message)latest;
                ConnectionInfo target;
                if(isServer) {
                    target = allConnections.get(data.toID);
                }else{
                    target = serverConnection;
                }
                if(target != null){
                    try {
                        target.getOuter().writeObject(data);
                    }catch(Exception e){

                    }
                }else{
                    if(!isServer){
                        System.out.println("Dammit, Hayden!\nThe client's trying to use allConnections! BAD!\nCheck out CommunicationOut!");
                    }
                    allConnections.forEach((id, connectionInfo) -> {
                        try {
                            //System.out.println("This just in!");
                            if(!connectionInfo.getInetAddress().equals(data.fromIP)) {
                                connectionInfo.getOuter().writeObject(data);
                            }
                        } catch (Exception e) {

                        }

                    });
                }


                //if(data.getToID().equals(Message.allId)) {
                    //System.out.println("Someone sent something!");


                //}else{
                    /*ConnectionInfo oneInfo = allConnections.get(data.toID);
                    oneInfo.getOuter().writeObject(data);*/
                //}
                //out.writeObject(latest);
            }catch(Exception e){

            }
        }
    }
}
