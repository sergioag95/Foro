package principal.controller;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import principal.modelo.Categoria;
import principal.modelo.Post;
import principal.modelo.Hilo;
import principal.modelo.Usuario;


@Controller
public class MainController {

	@GetMapping("/")
	String home() {
		
		return "index";
		
	}
	
}
