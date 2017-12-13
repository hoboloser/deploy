package com.zhicall.op.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;
import com.zhicall.op.entity.Cmd;
import com.zhicall.op.service.DeployService;
import com.zhicall.op.service.DeployServiceFactory;
import com.zhicall.op.service.OpConfigService;
import com.zhicall.op.util.shell.UploadUtil;

@Controller
@RequestMapping("/opc/deploy")
public class DeployController {

	@Autowired
	private DeployServiceFactory deployServiceFactory;
	
	@Autowired
	private OpConfigService opConfigService;
	
	
	private DeployService getInstance(String uuid) {
		return deployServiceFactory.getServiceInstance(uuid);
	}
	
	@ResponseBody
	@RequestMapping(value = "restart", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxRestart(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(getInstance(uuid).restart(uuid));
	}
	
	@ResponseBody
	@RequestMapping(value = "start", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStart(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(getInstance(uuid).start(uuid));
	}
	
	@ResponseBody
	@RequestMapping(value = "stop", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStop(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(getInstance(uuid).kill(uuid));
	}
	
	@ResponseBody
	@RequestMapping(value = "status", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStatus(String uuid) throws IOException {
		return getInstance(uuid).status(uuid);
	}
	
	@ResponseBody
	@RequestMapping(value = "deploy", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxDeploy(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(getInstance(uuid).deploy(uuid));
	}
	
	@ResponseBody
	@RequestMapping(value = "system/info", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String system(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(getInstance(uuid).systemInfo(uuid));
	}
	
	@ResponseBody
	@RequestMapping(value = "excute/cmd", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxExcute(String uuid,String cmd, String lastPath) throws IOException {
		String[] array = cmd.split("\\$");
		Cmd cmds = getInstance(uuid).excute(uuid,array[1],lastPath);
		return JSON.toJSONString(cmds);
	}
	
	@ResponseBody
	@RequestMapping(value = "cmd/login", method = RequestMethod.POST)
	public String cmdLogin(String uuid,String lastPath) throws IOException {
		Cmd cmds =  getInstance(uuid).excute(uuid, null, lastPath);
		return JSON.toJSONString(cmds);
	}
	
	@RequestMapping(value = "show/file/{uuid}", method = RequestMethod.GET)
	public ModelAndView showlogfile(@PathVariable String uuid) throws IOException {
		List list = getInstance(uuid).showFile(uuid);
		ModelAndView mv = new ModelAndView("deploy/logdetail");
		mv.addObject("details", list);
		
		mv.addObject("detailObj", opConfigService.getForObject(uuid));
		return mv;
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(String uuid, HttpServletRequest request) {
		String result = UploadUtil.upload(request);
		return "redirect:/opc/deploy/detail/"+uuid;
	}
	
}
