/*package principal.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import principal.modelo.Categoria;

@RequestMapping("/alumnos")
@Controller
public class AlumnoController {

	AlumnoDAO alumnoDAO = new AlumnoDAO();
	
	@GetMapping(value= {"","/"})
	String homealumnos(Model model) {
		
		// Salir a buscar a la BBDD
		
		ArrayList<Categoria> misalumnos = alumnoDAO.listarAlumnosJPA();
		model.addAttribute("listaAlumnos", misalumnos);
		model.addAttribute("alumnoaEditar", new Categoria());
		model.addAttribute("alumnoNuevo", new Categoria());
		
		
		//
		
		return "redirect:/alumnos";
	}
	
	@PostMapping("/edit/{id}")
	public String editarAlumno(@PathVariable Integer id, @ModelAttribute("alumnoaEditar") Categoria alumnoEditado, BindingResult bindingresult) {
		
		Categoria alumnoaeditar= alumnoDAO.buscarPorIdJPA(id);
		
		alumnoaeditar.setNombre(alumnoEditado.getNombre());
		
		alumnoDAO.modificarAlumnoJPA(alumnoaeditar);
		
		return "redirect:/alumnos";
	}
	
	@PostMapping("/add")
	public String addAlumno(@ModelAttribute("alumnoNuevo") Categoria alumnoNew, BindingResult bindingresult) {
		
		alumnoDAO.insertarAlumnoJPA(alumnoNew);
		
		return "redirect:/alumnos";
	}
	
	@GetMapping({"/{id}"})
	String idAlumno(Model model, @PathVariable Integer id) {
		
		Categoria alumnoMostrar = alumnoDAO.buscarPorIdJPA(id);
		model.addAttribute("alumnoMostrar",alumnoMostrar);
		
		return "alumno";
	}
	
	@GetMapping("/delete/{id}")
	String deleteAlumno(Model model, @PathVariable Integer id) {
		
		Categoria aeliminar = alumnoDAO.buscarPorIdJPA(id);
		alumnoDAO.eliminarAlumnoJPA(aeliminar);
		
		return "redirect:/alumnos";
	}
	
}
*/