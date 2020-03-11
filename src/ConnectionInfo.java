import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionInfo {
    String inetAddress;
    Socket socket;
    ObjectOutputStream outer;

    public ConnectionInfo(String i, Socket s, ObjectOutputStream o){
        inetAddress = i;
        socket = s;
        outer = o;
    }

    public String getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(String inetAddress) {
        this.inetAddress = inetAddress;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOuter() {
        return outer;
    }

    public void setOuter(ObjectOutputStream outer) {
        this.outer = outer;
    }
}
