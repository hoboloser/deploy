package com.zhicall.op.util.shell;
import com.jcraft.jsch.UserInfo;  
   
/** 
 * This class provide interface to feedback information to the user. 
 */ 
public class MyUserInfo implements UserInfo {  
    private String password;  
   
    private String passphrase;  
   
    @Override 
    public String getPassphrase() {  
        return null;  
    }  
   
    @Override 
    public String getPassword() {  
        return null;  
    }  
   
    @Override 
    public boolean promptPassphrase(final String arg0) {  
        return false;  
    }  
   
    @Override 
    public boolean promptPassword(final String arg0) {  
        return false;  
    }  
   
    @Override 
    public boolean promptYesNo(final String arg0) {  
        if (arg0.contains("The authenticity of host")) {  
            return true;  
        }  
        return false;  
    }  
   
    @Override 
    public void showMessage(final String arg0) {  
        System.out.println("MyUserInfo.showMessage()"); 
        System.out.println(arg0);
    }  
}