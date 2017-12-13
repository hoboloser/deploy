package com.zhicall.op.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.entity.DeployJob;
import com.zhicall.op.service.DeployServiceFactory;
import com.zhicall.op.service.OpConfigService;
import com.zhicall.op.util.DateUtils;

@Controller
@RequestMapping
public class PageController {

	@Value("${op.config.account}")
	private String accountConfig;
	
	private Map<String, String> map = new HashMap<>();
	
	private void init() {
		if (accountConfig == null) {
			return;
		}
		if (map.size() > 0) {
			return;
		}
		String[] array = accountConfig.split(";");
		for (String arrayStr : array) {
			String[] accountArray = arrayStr.split(",");
			map.put(accountArray[0], accountArray[1]);
		}
	}
	
	@Autowired
	private OpConfigService opConfigService;
	
	@Autowired
	private DeployServiceFactory deployServiceFactory;
	
	@RequestMapping("/")
	public String hello() {
		return "redirect:/lg";
	}

	@RequestMapping("/lg")
	public ModelAndView lg() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping("/opc/new/config")
	public String newOP(DeployInfo deployInfo) {
		opConfigService.insert(deployInfo);
		return "redirect:/opc/index";
	}
	
	@RequestMapping("/opc/update/config")
	public String updateOP(DeployInfo deployInfo) {
		opConfigService.update(deployInfo);
		return "redirect:/opc/detail/" + deployInfo.getUuid();
	}
	
	@RequestMapping("/opc/login")
	public ModelAndView login(String username, String password) {
		init();
		String pwd = map.get(username);
		ModelAndView mv = new ModelAndView();
		if (!pwd.equals(password)) {
			mv.setViewName("login");
			mv.addObject("errorMsg","用户名或密码错误");
			return mv;
		}
		mv.setViewName("index");
		mv.addObject("deployList", opConfigService.listConfigs());
		
		return mv;
	}

	@RequestMapping("/opc/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("deployList", opConfigService.listConfigs());
		return mv;
	}
	
	@RequestMapping("/opc/new")
	public ModelAndView newService() {
		ModelAndView mv = new ModelAndView("deploy/new");
		return mv;
	}
	
	@RequestMapping("/opc/update/{uuid}")
	public ModelAndView update(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("deploy/update");
		mv.addObject("detail", opConfigService.getForObject(uuid));
		return mv;
	}
	
	@RequestMapping("/opc/detail/{id}")
	public ModelAndView detail(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("deploy/detail");
		mv.addObject("detail", opConfigService.getForObject(id));
		return mv;
	}
	
	@RequestMapping("/opc/delete/{id}")
	public String delete(@PathVariable String id) {
		opConfigService.delete(id);
		return "redirect:/opc/index";
	}
	
	@RequestMapping("/opc/cmd/{id}")
	public ModelAndView cmd(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("deploy/cmd");
		mv.addObject("detail", opConfigService.getForObject(id));
		return mv;
	}
	
	@RequestMapping(value = "/opc/newjob/{uuid}", method = RequestMethod.GET)
	public ModelAndView newJob(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("deploy/job");
		mv.addObject("detail", uuid);
		return mv;
	}
	
	@RequestMapping(value = "/opc/oldjob/{uuid}", method = RequestMethod.GET)
	public ModelAndView oldJob(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("deploy/oldjobs");
		List list = opConfigService.queryJob(uuid);
		mv.addObject("detail", uuid);
		mv.addObject("jobList", list);
		return mv;
	}

	@RequestMapping(value = "/opc/insertjob", method = RequestMethod.POST)
	public String insertJob(Long uuid, String email, String mobile, String jobTime) {
		Date date = DateUtils.convertDate(jobTime);
		
		DeployJob deployJob = new DeployJob();
		deployJob.setEmail(email);
		deployJob.setMobile(mobile);
		deployJob.setUuid(uuid);
		deployJob.setJobTime(date);
		deployJob.setFlag(1);
		opConfigService.insertJob(deployJob);
		return "redirect:/opc/oldjob/"+deployJob.getUuid();
	}
	
	@RequestMapping(value = "/opc/upload/{uuid}", method = RequestMethod.GET)
	public ModelAndView uploadUU(@PathVariable String uuid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("deploy/upload");
		
		mv.addObject("detail", opConfigService.getForObject(uuid));
		return mv;
	}
}
