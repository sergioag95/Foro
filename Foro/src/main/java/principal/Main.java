package principal;

   

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import principal.persistencia.TablasBBDD;

@SpringBootApplication
@ComponentScan(basePackages = "principal")
public class Main {

	public static void main(String[] args) {
		TablasBBDD t = new TablasBBDD();
		//t.crearTablas();
		
		SpringApplication.run(Main.class, args);
	
	}

}
