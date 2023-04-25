package principal;

   

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import principal.persistencia.*;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		TablasBBDD t = new TablasBBDD();
		t.crearTablas();
		
		SpringApplication.run(Main.class, args);
		



	}
}
