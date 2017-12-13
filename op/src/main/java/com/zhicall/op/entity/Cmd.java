package com.zhicall.op.entity;

import java.util.List;

public class Cmd {

	private String root;
	
	private String cmd;
	
	private String path;
	
	private String result;
	
	private String lastPath;
	
	private List<String> filename;

	public String getRoot() {
		if (this.root != null) {
			return root.trim();
		}
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getCmd() {
		if (this.cmd != null) {
			return cmd.trim();
		}
			
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getPath() {
		if (this.path != null) {
			return path.trim();
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getResult() {
		if (this.result != null) {
			return result.trim();
		}
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLastPath() {
		if (this.lastPath != null) {
			return lastPath.trim();
		}
		return lastPath;
	}

	public void setLastPath(String lastPath) {
		this.lastPath = lastPath;
	}

	public List<String> getFilename() {
		return filename;
	}

	public void setFilename(List<String> filename) {
		this.filename = filename;
	}
	
}
