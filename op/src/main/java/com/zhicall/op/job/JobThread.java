//package com.zhicall.op.job;
//
//import java.io.IOException;
//import java.util.Date;
//
//import com.xxg.jdeploy.service.JavaDeployService;
//import com.xxg.jdeploy.util.MailUtil;
//import com.zhicall.op.entity.DeployJob;
//import com.zhicall.op.util.DateUtil;
//
///**
// * @author Administrator
// *
// */
//public class JobThread extends Thread{
//
//	private JavaDeployService javaDeployService;
//	
//	private JavaDeployService javaDeployService2;
//	
//	private DeployJob deployJob;
//
//	public JobThread(JavaDeployService javaDeployService, JavaDeployService javaDeployService2, DeployJob deployJob) {
//		super();
//		this.javaDeployService = javaDeployService;
//		this.javaDeployService2 = javaDeployService2;
//		this.deployJob = deployJob;
//	}
//	
//	@Override
//	public void run() {
//		StringBuilder builder = new StringBuilder();
//		String flagStr = null;
//		try {
//			String deploy = javaDeployService.deploy(deployJob.getUuid());
//			if (deploy.contains("启动") && deploy.contains("kill") && deploy.contains("部署") && deploy.contains("备份")) {
//				flagStr = "自动部署成功";
//			} else {
//				flagStr = "自动部署失败";
//			}
//			
//			String status = javaDeployService.getStatus(deployJob.getUuid());
//			boolean flag = Boolean.parseBoolean(status);
//			if (flag) {
//				flagStr += "运行中.....";
//			} else {
//				flagStr += "启动失败.....";
//			}
//			builder.append("<p>自动部署后tomcat运行状态：").append(flagStr).append("</p>");
//			builder.append("<p>自动部署日志：").append(deploy).append("</p>");
//		} catch (IOException e) {
//			builder.append("在部署是捕获到IO异常，请查看原因");
//		}
//		String[] toAddress = deployJob.getEmail().split(";");
//		
//		MailUtil.getInstance().sendAlertMail(builder.toString(), DateUtil.getCurrent(), toAddress);
//		
//		javaDeployService2.updateJob(flagStr, builder.toString(), new Date(), deployJob.getId());
//	}
//}
