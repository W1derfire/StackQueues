import java.util.HashMap;

public class ServerSmarts implements Runnable {

    StackQueue inQueue;
    StackQueue outQueue;
    HashMap allConnections;

    ServerSmarts(StackQueue in, StackQueue out, HashMap connections) {
        this.inQueue = in;
        this.outQueue = out;
        this.allConnections = connections;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            Object message = inQueue.get();
            while (message == Boolean.FALSE) {
                Thread.currentThread().yield();
                message = inQueue.get();
            }
            Message what = (Message) message;

            if (what.toID == MessageValue.SERVERID) {
                //INTERCEPTING MESSAGES

                if (what.getData() == (MessageValue.CONNECTDATA)) {
                    String clientID = what.fromID;
                    String clientIP = what.fromIP;

                    //We should save this info.
                    ConnectionInfo clientInfo = (ConnectionInfo) allConnections.get(clientIP);
                    if (clientInfo != null) {
                        allConnections.remove(clientIP);
                        allConnections.put(clientID, clientInfo);
                        continue;
                    } else {
                        System.out.println("Dammit, Hayden!\nClient missing: " + clientID + ", " + clientIP + "\nCheck out ServerSmarts!");
                    }
                }
                if (what.getData() == MessageValue.DISCONNECTDATA) {

                }
            } else {


                //System.out.println("LOGIC"  + message);

                boolean didWork = outQueue.put(message);
                while (!didWork) {
                    Thread.currentThread().yield();
                    didWork = outQueue.put(message);
                }
            }
        }
    }
}
