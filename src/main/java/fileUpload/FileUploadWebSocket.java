package fileUpload;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/fileupload")
public class FileUploadWebSocket {
	
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("Client Connected");
		users.add(session);
	}
	
	@OnMessage
	public void handleMessage(ByteBuffer file) throws Exception{
		Charset charset = Charset.forName("ISO-8859-1");
		String text = charset.decode(file).toString();
		//System.out.println(text);
		for(Session s: users) {
			s.getBasicRemote().sendText(text);	
		}
		
	}
	
	@OnClose
	public void handleClose(Session session) {
		System.out.println("Client Disconected");
		users.remove(session);
	}

}
