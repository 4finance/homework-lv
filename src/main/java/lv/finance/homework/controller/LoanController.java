package lv.finance.homework.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

	@GetMapping("/")
	public String home() {
		return "Hello 4finance";
	}
}