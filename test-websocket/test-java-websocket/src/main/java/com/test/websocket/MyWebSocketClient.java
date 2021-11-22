package com.test.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient extends WebSocketClient {

	public MyWebSocketClient(URI serverUri) {
		super(serverUri);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		System.out.println("------ MyWebSocket onOpen ------");
		for (Iterator<String> it = handshakedata.iterateHttpFields(); it.hasNext();) {
			String key = it.next();
			System.out.println(key + ":" + handshakedata.getFieldValue(key));
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		System.out.println("------ MyWebSocket onClose ------");
	}

	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		System.out.println("------ MyWebSocket onError ------");
	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("-------- 接收到服务端数据： " + message + "--------");
	}

	public static void main(String[] arg0) throws URISyntaxException, InterruptedException {
		MyWebSocketClient myClient = new MyWebSocketClient(new URI("ws://localhost:8080/test-websocket/myWebSocket"));
		// 连接
		myClient.connect();

		while (!myClient.getReadyState().equals(ReadyState.OPEN)) {
			System.out.println("MyWebSocketClient 连接中 ...");
		}

		// 往websocket服务端发送数据
		myClient.send("MyWebSocketClient Message");
		
		// 调用此方法保持住连接
		myClient.sendPing();

		Thread.sleep(3000);
		myClient.close();
	}
}
