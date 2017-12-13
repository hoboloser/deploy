package com.zhicall.op.socket;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.enums.BussinessType;
import com.zhicall.op.mapper.DeployMapper;
import com.zhicall.op.service.DeployServiceFactory;
import com.zhicall.op.util.FileUtil;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

/**
 * Created by wucao on 16-5-30.
 */
public class LogWebSocketHandle extends AbstractWebSocketHandler {

	private ConcurrentHashMap<String, LogThread> map = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired
	private DeployServiceFactory deployServiceFactory;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String uuid = QueryStringParser.parse(session.getUri().getQuery()).get("uuid");
		String file = QueryStringParser.parse(session.getUri().getQuery()).get("file");
		String cmd = QueryStringParser.parse(session.getUri().getQuery()).get("cmd");
		String type = QueryStringParser.parse(session.getUri().getQuery()).get("type");
		
		Map<String, Object> returnMap = new HashMap<>();
		
		InputStream in = deployServiceFactory.getServiceInstance(uuid).getInputStream(type, uuid, file, cmd, returnMap );
		
		LogThread thread = new LogThread(in, (Session) returnMap.get("session"), (Connection) returnMap.get("connection"), session);

		threadPoolTaskExecutor.execute(thread);
		
		map.put(session.getId(), thread);
		
		String key = uuid + getIp(session);
		sessionMap.put(key, session);
	}
	
	private String getIp(WebSocketSession session) {
		String ip = null;
		InetSocketAddress address = session.getRemoteAddress();
		if (address != null) {
			ip = address.getHostString();
			InetAddress iadd = address.getAddress();
			if (iadd != null && !StringUtils.isEmpty(iadd.getHostAddress())) {
				ip = iadd.getHostAddress();
			}
		}
		return ip;
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		LogThread thread = map.get(session.getId());
		map.remove(session.getId());
		thread.close();
		String uuid = QueryStringParser.parse(session.getUri().getQuery()).get("uuid");
		String key = uuid + getIp(session);
		sessionMap.remove(key);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		exception.printStackTrace();
	}
}