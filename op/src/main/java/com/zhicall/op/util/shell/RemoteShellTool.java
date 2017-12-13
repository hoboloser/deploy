package com.zhicall.op.util.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhicall.op.entity.Cmd;
import com.zhicall.op.entity.LogFile;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class RemoteShellTool {

	private Connection conn;
	private String ipAddr;
	private String charset = Charset.defaultCharset().toString();
	private String userName;
	private String password;

	public RemoteShellTool(String ipAddr, String userName, String password, String charset) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	public boolean login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	public String exec(String cmds) {
		InputStream in = null;
		String result = "";
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);

				in = session.getStdout();
				result = this.processStdout(in, this.charset);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public InputStream execWithInputStream(String cmd, Map<String, Object> param) {
		InputStream in = null;
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmd);
	
				in = session.getStdout();
				
				param.put("session", session);
				param.put("connection", conn);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return in;
	}
	
	public Cmd execWithPWD(String cmds) {
		InputStream in = null;
		String result = "";
		Cmd cmd = new Cmd();
		try {
			if (this.login()) {
				if (cmds != null) {
					Session session = conn.openSession(); // 打开一个会话
					session.execCommand(cmds);
					
					in = session.getStdout();
					result = this.processStdout(in, this.charset);
					cmd.setCmd(cmds);
					cmd.setResult(result);
					session.close();
				}
				
				Session session2 = conn.openSession(); 
				session2.execCommand("pwd");
				
				in = session2.getStdout();
				result = this.processStdout(in, this.charset);
				cmd.setPath(result);
				
				cmd.setRoot(userName);
				
				session2.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return cmd;
	}

	public List<LogFile> execList(String cmds) {
		InputStream in = null;
		List<LogFile> list = new ArrayList<>();
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);

				in = session.getStdout();
				parse(in, list);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public void parse(InputStream in, List<LogFile> list) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] array = line.split(" ");
				if (array.length > 2) {
					list.add(new LogFile(array[array.length-1], array[array.length-2]));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream execs(String cmds) throws IOException {
		InputStream in = null;
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);

				in = session.getStdout();

				session.close();
			}
		} finally {
			conn.close();
		}
		return in;
	}

	public String processStdout(InputStream in, String charset) {

		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
