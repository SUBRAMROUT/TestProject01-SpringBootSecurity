package com.nt.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOperationsController {
	
	@GetMapping("/")
	public String showHomePage() {
		//return LVN
		return "welcome";
	}
	
	@GetMapping("/balance")
	public String showBalancePage(Map<String, Object> map) {
		int amount = new Random().nextInt(1000000);
		map.put("amount", amount);
		//return LVN
		return "show_balance";
	}
	
	@GetMapping("/offers")
	public String showOffers() {
		//return LVN
		return "offers";
	}
	
	@GetMapping("/loanApprove")
	public String approveLoan(Map<String, Object> map) {
		int amount = new Random().nextInt(1000000);
		map.put("amount", amount);
		//return LVN
		return "loan";
	}
	
	@GetMapping("/denied")
	public String showAccessDeniedPage() {
		//return LVN
		return "authorization_failed";
	}

}
