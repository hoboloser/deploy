//package com.zhicall.op.job;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.xxg.jdeploy.domain.DeployJob;
//import com.xxg.jdeploy.service.JavaDeployService;
//
//@Component
//public class DeployJobTask {
//
//	ExecutorService exector = Executors.newScheduledThreadPool(4);
//	
//	@Autowired
//	private JavaDeployService javaDeployService;
//	
//	@Scheduled(fixedRate=60000)
//	public void excute() {
//		System.out.println("job");
//		List<DeployJob> list = javaDeployService.queryJob(null);
//		if (list == null || list.size() == 0) {
//			return;
//		}
//		List<DeployJob> needExcute = new ArrayList<>();
//		long current = System.currentTimeMillis();
//		for (DeployJob deployJob : list) {
//			if (deployJob.getJobTimeDate().getTime() <= current) {
//				needExcute.add(deployJob);
//			}
//		}
//		
//		
//		for (DeployJob deployJob : needExcute) {
//			exector.execute(new JobThread(javaDeployService, javaDeployService, deployJob));
//		}
//	}
//}
