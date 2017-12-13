package com.zhicall.op.util;  
  
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;  
  
public class FtpsFileList {  
	
	public static boolean download(String host, int port, String username, final String password, final String sourcePath, 
			final String file, BufferedOutputStream out) {
		 ChannelSftp sftp = null;  
	        Channel channel = null;  
	        Session sshSession = null;  
	        InputStream in = null;
	        try {  
	            JSch jsch = new JSch();  
	            jsch.getSession(username, host, port);  
	            sshSession = jsch.getSession(username, host, port);  
	            sshSession.setPassword(password);  
	            Properties sshConfig = new Properties();  
	            sshConfig.put("StrictHostKeyChecking", "no");  
	            sshSession.setConfig(sshConfig);  
	            sshSession.connect();  
	            channel = sshSession.openChannel("sftp");  
	            channel.connect();  
	            sftp = (ChannelSftp) channel;  
	            
	            sftp.cd(sourcePath);
	            in = sftp.get(file);
	            byte[] buffer = new byte[1024];    
	            while (in.read(buffer) != -1) {    
	                out.write(buffer);    
	                buffer = new byte[1024];    
	            }    
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return false;
	        } finally {
	        	if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	            closeChannel(sftp);  
	            closeChannel(channel);  
	            closeSession(sshSession);  
	        }  
	        return true;
	}
  
    public static boolean upload(String host, int port, String username, final String password, String sourceFile, 
    		String targetPath, String targetFileName) {  
        ChannelSftp sftp = null;  
        Channel channel = null;  
        Session sshSession = null;  
        try {  
            JSch jsch = new JSch();  
            jsch.getSession(username, host, port);  
            sshSession = jsch.getSession(username, host, port);  
            sshSession.setPassword(password);  
            Properties sshConfig = new Properties();  
            sshConfig.put("StrictHostKeyChecking", "no");  
            sshSession.setConfig(sshConfig);  
            sshSession.connect();  
            channel = sshSession.openChannel("sftp");  
            channel.connect();  
            sftp = (ChannelSftp) channel;  
            
            File imagefile = new File(sourceFile);
            InputStream in = new FileInputStream(imagefile);
            sftp.put(in, targetPath + File.separator + targetFileName + ".war");
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;
        } finally {  
            closeChannel(sftp);  
            closeChannel(channel);  
            closeSession(sshSession);  
        }  
        return true;
    }  
  
    private static void closeChannel(Channel channel) {  
        if (channel != null) {  
            if (channel.isConnected()) {  
                channel.disconnect();  
            }  
        }  
    }  
  
    private static void closeSession(Session session) {  
        if (session != null) {  
            if (session.isConnected()) {  
                session.disconnect();  
            }  
        }  
    }  
}  