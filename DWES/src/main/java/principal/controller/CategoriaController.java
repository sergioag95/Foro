package principal.controller;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import principal.persistencia.CategoriaDAO;
import principal.modelo.Categoria;

@RequestMapping("/Categorias")
@Controller
public class CategoriaController {

	CategoriaDAO CategoriaDAO = new CategoriaDAO();
	
	@GetMapping(value= {"","/"})
	String homeCategorias(Model model) {
		
		// Salir a buscar a la BBDD
		
		ArrayList<Categoria> misCategorias = CategoriaDAO.listarCategoriasJPA();
		model.addAttribute("listaCategorias", misCategorias);
		model.addAttribute("CategoriaaEditar", new Categoria());
		model.addAttribute("CategoriaNuevo", new Categoria());
		
		
		//
		
		return "redirect:/Categorias";
	}
	
	@PostMapping("/edit/{id}")
	public String editarCategoria(@PathVariable Integer id, @ModelAttribute("CategoriaaEditar") Categoria CategoriaEditado, BindingResult bindingresult) {
		
		Categoria Categoriaaeditar= CategoriaDAO.buscarPorIdJPA(id);
		
		Categoriaaeditar.setNombre(CategoriaEditado.getNombre());
		
		CategoriaDAO.modificarCategoriaJPA(Categoriaaeditar);
		
		return "redirect:/Categorias";
	}
	
	@PostMapping("/add")
	public String addCategoria(@ModelAttribute("CategoriaNuevo") Categoria CategoriaNew, BindingResult bindingresult) {
		
		CategoriaDAO.insertarCategoriaJPA(CategoriaNew);
		
		return "redirect:/Categorias";
	}
	
	@GetMapping({"/{id}"})
	String idCategoria(Model model, @PathVariable Integer id) {
		
		Categoria CategoriaMostrar = CategoriaDAO.buscarPorIdJPA(id);
		model.addAttribute("CategoriaMostrar",CategoriaMostrar);
		
		return "Categoria";
	}
	
	@GetMapping("/delete/{id}")
	String deleteCategoria(Model model, @PathVariable Integer id) {
		
		Categoria aeliminar = CategoriaDAO.buscarPorIdJPA(id);
		CategoriaDAO.eliminarCategoriaJPA(aeliminar);
		
		return "redirect:/Categorias";
	}
	
}
