package com.packt.webstore.controller;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/logon", method = RequestMethod.GET)
	public String logon() {
		return "logon";
	}
	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	public String processlogon() {
		return "logon";
	}
	@RequestMapping(value = "/logonfailed", method = RequestMethod.GET)
	public String logonerror(Model model) {
		model.addAttribute("error", "true");
		return "logon";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "logon";
	}
}
