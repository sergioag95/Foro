package principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import principal.modelo.Categoria;
import principal.modelo.Hilo;
import principal.modelo.Usuario;
import principal.repositorio.CategoriaRepositorio;
import principal.repositorio.HiloRepositorio;
import principal.repositorio.UsuarioRepo;
import principal.servicio.CategoriaServicio;


@RestController
@RequestMapping("/categoriasSwagger")
public class CategoriaControllerSwagger {

    private final CategoriaServicio categoriaServicio;
    private final CategoriaRepositorio categoriaRepository;
    private final HiloRepositorio hiloRepository;


    @Autowired
    private UsuarioRepo usuarioRepo;
    
    public CategoriaControllerSwagger(CategoriaServicio categoriaServicio, CategoriaRepositorio categoriaRepository, HiloRepositorio hiloRepository) {
        this.categoriaServicio = categoriaServicio;
        this.categoriaRepository = categoriaRepository;
        this.hiloRepository = hiloRepository;
    }

    @GetMapping(value = {"", "/"})
    public String homeCategorias(Model model) {
        List<Categoria> misCategorias = categoriaServicio.listarCategorias();
        model.addAttribute("listaCategorias", misCategorias);
        model.addAttribute("categoriaNuevo", new Categoria());
        return "categorias";
    }

    @PostMapping("/edit")
    public String editarCategoria(@ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult) {
        Categoria categoriaAEditar = categoriaServicio.obtenerCategoria(categoria.getId());
        if (categoriaAEditar != null) {
            categoriaAEditar.setNombre(categoria.getNombre());
            categoriaServicio.actualizarCategoria(categoriaAEditar);
        }
        return "redirect:/categorias";
    }

    @PostMapping("/add")
    public String addCategoria(@ModelAttribute("categoriaNuevo") Categoria categoria, BindingResult bindingResult) {

        // Obtén el usuario autenticado
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Obtén el usuario
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Asigna el usuario a la categoría
        categoria.setUsuario(usuario);

        // Guarda la categoría en la base de datos
        categoriaServicio.agregarCategoria(categoria);

        return "redirect:/categorias";
    }



    @GetMapping("/{id}")
    public String idCategoria(Model model, @PathVariable("id") Long id) {
        Categoria categoriaMostrar = categoriaServicio.obtenerCategoria(id);
        model.addAttribute("categoriaMostrar", categoriaMostrar);
        return "categoria";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategoria(Model model, @PathVariable("id") Long id) {
        categoriaServicio.eliminarCategoria(id);
        return "redirect:/categorias";
    }
    
   
    @GetMapping("/{id}/verHilos")
    public String verHilosPorCategoria(Model model, @PathVariable("id") Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + id));

        List<Hilo> hilosCategoria = hiloRepository.findByCategoria(categoria);

        model.addAttribute("categoria", categoria);
        model.addAttribute("listaHilos", hilosCategoria);

        return "hilosCategoria";
    }
}

