package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/login")
	public String loginPrincipal() {
		return "/loginPrincipal";
	}
}
