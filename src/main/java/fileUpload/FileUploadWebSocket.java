package fileUpload;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/fileupload")
public class FileUploadWebSocket {
	
	
	@OnOpen
	public void handleOpen() {
		System.out.println("Client Connected");
	}
	
	@OnMessage
	public void handleMessage(Session session, ByteBuffer file) throws Exception{
		Charset charset = Charset.forName("ISO-8859-1");
		String text = charset.decode(file).toString();
		//System.out.println(text);
		
		session.getBasicRemote().sendText(text);
	}
	
	@OnClose
	public void handleClose() {
		System.out.println("Client Disconected");
	}

}
