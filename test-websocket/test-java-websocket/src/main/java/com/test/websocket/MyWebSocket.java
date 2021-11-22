package com.test.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class MyWebSocket {

	private Session session;

	/**
	 * 连接建立后触发的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		WebSocketMapUtil.put(session.getId(), this);
		System.out.println("-------- onOpen: 当前在线人数 " + WebSocketMapUtil.getOnlineCount() + "，连接人 " + session.getId() + " --------");
	}

	/**
	 * 连接关闭后触发的方法
	 */
	@OnClose
	public void onClose() {
		// 从map中删除
		WebSocketMapUtil.remove(session.getId());
		System.out.println("-------- onClose: 当前在线人数 " + WebSocketMapUtil.getOnlineCount() + "，关闭人 " + session.getId() + " --------");
	}

	/**
	 * 接收到客户端消息时触发的方法
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		// 获取服务端到客户端的通道
		MyWebSocket myWebSocket = WebSocketMapUtil.get(session.getId());
		System.out.println("收到来自 " + session.getId() + " 的消息：" + message);
		
		// 返回消息给Web Socket客户端（浏览器）
		myWebSocket.sendMessageAll("服务端已收到消息：" + message);
	}

	/**
	 * 发生错误时触发的方法
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		// System.out.println(session.isOpen());
		System.out.println("-------- onError: 当前在线人数 " + WebSocketMapUtil.getOnlineCount() + "，连接发生错误 " + session.getId() + "-"+ error.getMessage() + " --------");
		// error.printStackTrace();
	}

	/**
	 * 给单个客户端发送消息
	 * @param message
	 * @param sessionId
	 * @throws IOException
	 */
	public void sendMessageSingle(String message, String sessionId) throws IOException {

		// session.getBasicRemote().sendText(message); 同步消息
		// session.getAsyncRemote().sendText(message); 异步消息

		MyWebSocket myWebSocket = WebSocketMapUtil.get(sessionId);
		if(myWebSocket != null) {
			myWebSocket.session.getBasicRemote().sendText(message);
		}
	}

	/**
	 * 给所有客户端发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageAll(String message) throws IOException {
		for (MyWebSocket item : WebSocketMapUtil.getValues() ) {
			item.session.getAsyncRemote().sendText(message);
		}
	}

}
