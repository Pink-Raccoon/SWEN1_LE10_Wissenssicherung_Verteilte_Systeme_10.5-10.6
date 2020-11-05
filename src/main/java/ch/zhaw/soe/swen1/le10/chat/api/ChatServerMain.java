package ch.zhaw.soe.swen1.le10.chat.api;

import org.apache.catalina.Context;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http2.Http2Protocol;

import ch.zhaw.soe.swen1.le10.chat.api.common.ChatServiceLocator;
import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatServerImpl;

import java.io.File;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The server for starting an RMI chat Server and  a webserver (embedded Tomcat) serving web clients.
 * 
 * Attention: For a real use it is absolutely necessary to work with TLS for encrypted data transmission.
 * For test purposes the web server with unencrypted data transmission is used.  
 */
public class ChatServerMain {
	public static void main(String[] args) throws Exception {
		int port = 8080;
		String contextPath = "/ws";
		String docBase = "web";	
		final String CHAT_SERVER = "ChatServer";	
		//String keystoreFile = "keystore.jks";
		//String keystorePass = "secret";

		final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.INFO);
		
		// Start the RMI chat server.
		try {
            LocateRegistry.createRegistry(1099);
            ChatServerImpl serverStub = new ChatServerImpl(ChatServiceLocator.getInstance());
            Naming.rebind(CHAT_SERVER, serverStub);
            String host = (System.getProperty("java.rmi.server.hostname") == null)
                    ? InetAddress.getLocalHost().getHostAddress()
                    : System.getProperty("java.rmi.server.hostname");
            logger.info(String.format("RMI ChatServer for chat %s started at ", ChatServiceLocator.getInstance().getName()) + host + " ...");
        } catch (Exception e) {
            logger.severe("RMI ChatServer could not be startet.");
            e.printStackTrace();
        }
		
		// Start embedded Tomcat.
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
		Context ctx = tomcat.addWebapp(contextPath, new File(docBase).getAbsolutePath());

		Connector con = new Connector();
		con.setPort(port);
		//con.setScheme("https");
		//con.setSecure(true);
		//con.addUpgradeProtocol(new Http2Protocol());
		//con.setProperty("protocol", "HTTP/1.1");
		//con.setProperty("SSLEnabled", "true");
		//con.setProperty("maxThreads", "150");
		//con.setProperty("clientAuth", "false");
		//con.setProperty("sslProtocol", "TLS");
		//con.setProperty("keystoreFile", new File(keystoreFile).getAbsolutePath());
		//con.setProperty("keystorePass", keystorePass);

		Service service = tomcat.getService();
		service.addConnector(con);

		tomcat.start();

		logger.info("DocBase: " + ctx.getDocBase());
		String url = con.getScheme() + "://" + InetAddress.getLocalHost().getHostAddress() + ":" +
				con.getPort() + ctx.getPath();
		logger.info("URL: " + url);
		logger.info("Press ENTER and CTRL + C to stop the server.");

		System.in.read();
		tomcat.stop();
		tomcat.destroy();
	}
}

