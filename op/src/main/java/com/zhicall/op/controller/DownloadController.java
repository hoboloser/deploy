package com.zhicall.op.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.service.OpConfigService;
import com.zhicall.op.util.FtpsFileList;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

@Controller
@RequestMapping("/opc")
public class DownloadController {
	
	@RequestMapping("/download2")
	public void download2(String uuid, String file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + file);
		DeployInfo info = opConfigService.getForObject(uuid);
		if (info == null) {
			return;
		}
		BufferedOutputStream out = null;
	    try {
	    	out = new BufferedOutputStream(response.getOutputStream());    
	    	FtpsFileList.download(info.getIp(), 22, info.getLname(), info.getPassword(), info.getLogpath(), file, out);
	    } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            IOUtils.closeQuietly(out);  
        }  
	}

	@RequestMapping("/download")
	public void download(String uuid, String file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + file);
		InputStream in = null;
		BufferedOutputStream out = null;
		String remoteUrl = getRemoteUrl(uuid, file);
		if (remoteUrl == null) {
			return;
		}
        try {    
            SmbFile remoteFile = new SmbFile(remoteUrl);    
            if (remoteFile != null && remoteFile.exists()) {    
                in = new BufferedInputStream(new SmbFileInputStream(remoteFile));    
                out = new BufferedOutputStream(response.getOutputStream());    
                byte[] buffer = new byte[1024];    
                while (in.read(buffer) != -1) {    
                    out.write(buffer);    
                    buffer = new byte[1024];    
                }    
            } else {  
            }  
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
              
            try {  
                out.close();  
                out = null;  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                  
                try {  
                    in.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }    
	}
	
	@Autowired
	private OpConfigService opConfigService;
	
	private String getRemoteUrl(String uuid, String file) {
		DeployInfo info = opConfigService.getForObject(uuid);
		if (info == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("smb://").append(info.getLname()).append(":").append(info.getPassword()).append("@")
			.append(info.getIp()).append(info.getLogpath()).append("/").append(file);
		
		return builder.toString();
	}
	
}
