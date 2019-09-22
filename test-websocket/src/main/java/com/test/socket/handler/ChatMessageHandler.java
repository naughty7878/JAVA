package com.test.socket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.test.websocket.MyWebSocket;
import com.test.websocket.WebSocketMapUtil;

public class ChatMessageHandler extends TextWebSocketHandler {

	/**
	 * 连接成功时候，会触发UI上onopen方法
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		WebSocketHandlerUtil.put(session.getId(), session);
		System.out.println("-------- onOpen: 当前在线人数 " + WebSocketHandlerUtil.getOnlineCount() + "，连接人 "
				+ session.getId() + " --------");
	}
	
	/**
	 * 接收到客户端消息时触发的方法,调用websocket.close()时候
	 */
	@Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {  
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
		System.out.println("收到来自 " + session.getId() + " 的消息：" + message);

		// 返回消息给Web Socket客户端（浏览器）
		// myWebSocket.sendMessageAll("服务端已收到消息：" + message);
	}

	/**
	 *  发生错误时触发的方法
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		// users.remove(session);
		System.out.println("-------- onError: 当前在线人数 " + WebSocketHandlerUtil.getOnlineCount() + "，连接发生错误 " + session.getId() + "-"+ exception.getMessage() + " --------");
	}
	 
	
}
