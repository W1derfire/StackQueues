import java.io.EOFException;
import java.io.ObjectInputStream;

public class CommunicationIn implements Runnable {


    ObjectInputStream in;
    StackQueue inQueue;


    public CommunicationIn(ObjectInputStream in, StackQueue queue) {
        this.in = in;
        this.inQueue = queue;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {

                Object data = in.readObject();

                while (data == null) {
                    data = in.readObject();
                }

                //System.out.println("Hello there!");

                System.out.println(data);

                boolean didWork = inQueue.put(data);
                while (!didWork) {
                    Thread.currentThread().yield();
                    didWork = inQueue.put(data);
                }

            } catch (EOFException e) {
                //System.out.println("Oops.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
