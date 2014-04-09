package server;

/**SocketClientInterface interface, contains openConnection, handleSession, closeSession function*/
public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
