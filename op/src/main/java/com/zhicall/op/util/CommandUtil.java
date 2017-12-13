package com.zhicall.op.util;

import java.io.File;

public class CommandUtil {

	public static String generateShellCommand(String root, String file, String... args) {
		StringBuilder builder = new StringBuilder();
		builder.append("sh ").append(root).append("/").append(file).append(" ");
		if (args.length > 0) {
			for (String string : args) {
				builder.append(string).append(" ");
			}
		}
		return builder.toString().trim();
	}
}
