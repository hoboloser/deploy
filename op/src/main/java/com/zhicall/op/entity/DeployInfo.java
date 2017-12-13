package com.zhicall.op.entity;

public class DeployInfo {

    private String uuid;
    
    private String name;
    
    /**
     * linux服务器ip
     */
    private String ip;
    
    /**
     * linux服务器登录账户
     */
    private String lname;
    
    /**
     * linux服务器密码
     */
    private String password;
    
    /**
     * tomcat名字
     */
    private String tname;
    
    /**
     * shell文件地址
     */
    private String sdir;
    
    /**
     * war包名字
     */
    private String wname;
    
    /**
     * tomcat根目录
     */
    private String tdir;
    
    /**
     * 备份目录
     */
    private String bdir;
    
    /**
     * war包所处目录
     */
    private String wdir;
    
    /**
     * 日志所处路径
     */
    private String logpath;
    
    private String javaHomePath;
    
    private String zhicallConfig;
    
    private String type;

    public String getLogpath() {
		return logpath;
	}

	public void setLogpath(String logpath) {
		this.logpath = logpath;
	}

//    public String getModule() {
//        return module;
//    }
//
//    public void setModule(String module) {
//        this.module = module;
//    }
//
//    public String getProfile() {
//		return profile;
//	}
//
//	public void setProfile(String profile) {
//		this.profile = profile;
//	}

	public String getUuid() {
        return uuid;
    }

	public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getSdir() {
		return sdir;
	}

	public void setSdir(String sdir) {
		this.sdir = sdir;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getTdir() {
		return tdir;
	}

	public void setTdir(String tdir) {
		this.tdir = tdir;
	}

	public String getBdir() {
		return bdir;
	}

	public void setBdir(String bdir) {
		this.bdir = bdir;
	}

	public String getWdir() {
		return wdir;
	}

	public void setWdir(String wdir) {
		this.wdir = wdir;
	}

	public String getJavaHomePath() {
		return javaHomePath;
	}

	public void setJavaHomePath(String javaHomePath) {
		this.javaHomePath = javaHomePath;
	}

	public String getZhicallConfig() {
		return zhicallConfig;
	}

	public void setZhicallConfig(String zhicallConfig) {
		this.zhicallConfig = zhicallConfig;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
