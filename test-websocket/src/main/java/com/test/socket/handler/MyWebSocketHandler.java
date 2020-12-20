package com.test.socket.handler;

import java.io.IOException;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;


public class MyWebSocketHandler extends AbstractWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		WebSocketHandlerUtil.put(session.getId(), session);
		System.out.println("-------- onOpen: 当前在线人数 " + WebSocketHandlerUtil.getOnlineCount() + "，连接人 "
				+ session.getId() + " --------");
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
		// 从map中删除
        WebSocketHandlerUtil.remove(session.getId());
 		System.out.println("-------- onClose: 当前在线人数 " + WebSocketHandlerUtil.getOnlineCount() + "，关闭人 " + session.getId() + " --------");
	}

	
	/**
	 * 在UI在用js调用websocket.send()时候，会调用该方法
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// super.handleTextMessage(session, message);
		// 获取服务端到客户端的通道
		System.out.println("收到来自 " + session.getId() + " 的消息：" + message.getPayload());

		// 返回消息给Web Socket客户端（浏览器）
		sendMessageAll("服务端已收到消息：" + message);
	}
  
	/**
	 *  发生错误时触发的方法
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		System.out.println("-------- onError: 当前在线人数 " + WebSocketHandlerUtil.getOnlineCount() + "，连接发生错误 " + session.getId() + "-"+ exception.getMessage() + " --------");
	}
	
	/**
	 * 给单个客户端发送消息
	 * @param message
	 * @param sessionId
	 * @throws IOException
	 */
	public void sendMessageSingle(String message, String sessionId) throws IOException {

		WebSocketSession session = WebSocketHandlerUtil.get(sessionId);
		if(session != null) {
			session.sendMessage(new TextMessage(message));
		}
	}

	/**
	 * 给所有客户端发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageAll(String message) throws IOException {
		for (WebSocketSession session : WebSocketHandlerUtil.getValues() ) {
			
			session.sendMessage(new TextMessage(message));
		}
	}
	
}
