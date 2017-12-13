package com.zhicall.op.util.shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.util.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zhicall.op.entity.Cmd;

/**
 * This class provide interface to execute command on remote Linux.
 */

public class SSHCommandExecutor {
	private String ipAddress;

	private String username;

	private String password;

	public static final int DEFAULT_SSH_PORT = 22;

	private Vector<String> stdout;

	public SSHCommandExecutor(final String ipAddress, final String username, final String password) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		stdout = new Vector<String>();
	}

	public int execute(final String command) {
		int returnCode = 0;
		JSch jsch = new JSch();
		MyUserInfo userInfo = new MyUserInfo();

		try {
			// Create and connect session.
			Session session = jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
			session.setPassword(password);
			session.setUserInfo(userInfo);
			session.connect();

			// Create and connect channel.
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);
			BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));

			channel.connect();

			// Get the output of remote command.
			String line;
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
			input.close();

			// Get the return code only after the channel is closed.
			if (channel.isClosed()) {
				returnCode = channel.getExitStatus();
			}

			// Disconnect the channel and session.
			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnCode;
	}

	public Vector<String> getStandardOutput() {
		return stdout;
	}

	public static Cmd exec(String lastPath, String cmd, String ip, String name, String pwd) {
		Cmd result = new Cmd();
		SSHCommandExecutor sshExecutor = new SSHCommandExecutor(ip, name, pwd);
		StringBuilder builder = new StringBuilder();
		if (lastPath != null) {
			builder.append("cd ").append(lastPath).append(";");
		}
		builder.append(cmd).append(";").append("pwd");

		sshExecutor.execute(builder.toString());

		Vector<String> stdout = sshExecutor.getStandardOutput();
		builder.setLength(0);

		for (int i = 0; i < stdout.size(); i++) {
			if (i == stdout.size() - 1) {
				result.setPath(stdout.get(i));
			} else {
				builder.append(stdout.get(i)).append("<br/>");
			}
		}
		
		
		result.setResult(builder.toString());
		result.setRoot(name);
		result.setCmd(cmd);
		result.setLastPath(result.getPath());
		
		showls(sshExecutor, lastPath, result);
		return result;
	}
	
	public static Cmd execLocal(String lastPath, String name, String cmd) {
		Cmd result = new Cmd();
		if (!StringUtils.isEmpty(cmd)) {
			String results = ShellUtil.exec(cmd);
			result.setResult(results);
		}
		result.setPath(ShellUtil.exec("pwd"));
		
		result.setRoot(name);
		result.setCmd(cmd);
		result.setLastPath(result.getPath());
//		showls(sshExecutor, lastPath, result);
		return result;
	}
	
	private static void showls(SSHCommandExecutor sshExecutor,String lastPath,Cmd result) {
		if (lastPath == null) {
			return;
		}
		List<String> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append("cd ").append(lastPath).append(";").append("cd ").append(result.getPath()).append(";ls");
		sshExecutor.execute(builder.toString());

		Vector<String> stdout = sshExecutor.getStandardOutput();
		builder.setLength(0);
		
		for (String string : stdout) {
			list.add(string);
		}
		
		result.setFilename(list);
	}

	public static void main(final String[] args) {
		// SSHCommandExecutor sshExecutor = new
		// SSHCommandExecutor("172.16.10.174", "root", "Isd@2016");
		//
		// sshExecutor.execute("cd /home; pwd ; ls");
		//
		// Vector<String> stdout = sshExecutor.getStandardOutput();
		// for (String str : stdout) {
		// System.out.println(str);
		// }

	}
}