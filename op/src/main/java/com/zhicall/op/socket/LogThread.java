package com.zhicall.op.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

/**
 * Created by wucao on 16-5-30.
 */
public class LogThread implements Runnable {


    private BufferedReader reader;
    private WebSocketSession session;
    
    private Session csession;
    private Connection conn;

    public LogThread(InputStream process, Session csession, Connection conn, WebSocketSession session) throws UnsupportedEncodingException {
        this.reader = new BufferedReader(new InputStreamReader(process, "UTF-8"));
        this.session = session;
        this.csession = csession;
        this.conn = conn;
    }

    public LogThread(InputStream process, WebSocketSession session) throws UnsupportedEncodingException {
    	 this.reader = new BufferedReader(new InputStreamReader(process, "UTF-8"));
    	 this.session = session;
    }
    
    public void close() throws IOException {
        try {
            reader.close();
            csession.close();
            conn.close();
        } finally {
        	
        }
    }

    @Override
    public void run() {
        String line;
        try {
            while((line = reader.readLine()) != null) {
                // 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
                session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(line) + "<br>"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
