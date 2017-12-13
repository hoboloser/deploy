package com.zhicall.op.util.shell;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class DownloadUtil {

	public static void smbGet(String remoteUrl, String localDir) {    
        InputStream in = null;    
        OutputStream out = null;    
        try {    
            SmbFile remoteFile = new SmbFile(remoteUrl);    
            if (remoteFile != null && remoteFile.exists()) {    
                String fileName = remoteFile.getName();    
                File localFile = new File(localDir + File.separator + fileName);    
                in = new BufferedInputStream(new SmbFileInputStream(remoteFile));    
                out = new BufferedOutputStream(new FileOutputStream(localFile));    
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
}
