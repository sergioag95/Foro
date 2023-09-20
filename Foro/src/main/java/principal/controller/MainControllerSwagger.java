package principal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainControllerSwagger {

	@GetMapping("/swagger")
	String home() {
		
		return "index";
		
	}
	
}
