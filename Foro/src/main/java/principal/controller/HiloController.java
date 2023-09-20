package principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import principal.modelo.Categoria;
import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.modelo.Usuario;
import principal.repositorio.CategoriaRepositorio;
import principal.repositorio.HiloRepositorio;
import principal.repositorio.UsuarioRepo;
import principal.servicio.HiloServicio;
import principal.servicio.PostServicio;

@Controller
public class HiloController {
    
    private final HiloServicio hiloService;
    private final PostServicio postService;
    private final CategoriaRepositorio categoriaRepository;
    private final HiloRepositorio hiloRepository;
    
    @Autowired
    private UsuarioRepo usuarioRepo;
    
    @Autowired
    public HiloController(HiloServicio hiloService, PostServicio postService,
                          CategoriaRepositorio categoriaRepository, HiloRepositorio hiloRepository) {
        this.hiloService = hiloService;
        this.postService = postService;
        this.categoriaRepository = categoriaRepository;
        this.hiloRepository = hiloRepository;
    }
    
    @GetMapping("/categorias/{idCategoria}/hilos")
    public String listarHilosPorCategoria(@PathVariable("idCategoria") Long idCategoria, Model model) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria));
        List<Hilo> hilos = hiloRepository.findByCategoria(categoria);
        
        // Cargar información del usuario para cada hilo
        for (Hilo hilo : hilos) {
            hilo.getUsuario().getUsername(); // Accede a la propiedad username para cargar el usuario
        }
        
        model.addAttribute("hilos", hilos);
        model.addAttribute("categoria", categoria);
        return "hilosCategoria";
    }

    

    @GetMapping("/categorias/{idCategoria}/hilos/nuevo")
    public String nuevoHilo(Model model, @PathVariable("idCategoria") Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria));
        Hilo hiloNuevo = new Hilo();
        hiloNuevo.setCategoria(categoria);
        model.addAttribute("hiloNuevo", hiloNuevo);
        return "nuevoHilo";
    }

    @PostMapping("/categorias/{idCategoria}/hilos/nuevo")
    public String agregarHilo(@ModelAttribute("hiloNuevo") Hilo hiloNuevo,
            @PathVariable("idCategoria") Long idCategoria) {
    	
    	  // Obtén el usuario autenticado
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
    	
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria));
        
     // Asigna el usuario al post
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        hiloNuevo.setUsuario(usuario);
        
        hiloNuevo.setCategoria(categoria);
        hiloNuevo.setUsuario(usuario);
        hiloRepository.save(hiloNuevo);
        return "redirect:/categorias/{idCategoria}/hilos";
    }


    @GetMapping("/hilos/{id}/eliminar")
    public String eliminarHilo(Model model, @PathVariable Long id) {
        Hilo hiloAEliminar = hiloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + id));
        Long idCategoria = hiloAEliminar.getCategoria().getId();
        hiloRepository.delete(hiloAEliminar);
        return "redirect:/categorias/" + idCategoria + "/hilos";
    }


    @GetMapping("/hilos/{id}/posts")
    public String verPostsHilo(Model model, @PathVariable Long id) {
        Hilo hilo = hiloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + id));

        List<Post> postHilo = postService.findByHilo(hilo);

        model.addAttribute("hilo", hilo);
        model.addAttribute("listaPosts", postHilo);

        return "postHilo";
    }
    
    @GetMapping("/hilos/{id}/editar")
    public String mostrarFormularioEditarHilo(@PathVariable("id") Long id, Model model) {
        Hilo hilo = hiloService.obtenerHiloPorId(id);
        model.addAttribute("hiloEditar", hilo);
        return "editarHilo";
    }

    // Método para procesar la solicitud de edición de un hilo
    @PostMapping("/categorias/{idCategoria}/hilos/{id}/editar")
    public String editarHilo(@PathVariable("id") Long id, @ModelAttribute("hiloEditar") Hilo hiloEditado,
                              @PathVariable("idCategoria") Long idCategoria) {
        Hilo hiloExistente = hiloService.obtenerHiloPorId(id);
        hiloExistente.setTitulo(hiloEditado.getTitulo());
        hiloService.guardarHilo(hiloExistente);
        return "redirect:/hilos/" + id;
    }



    @PostMapping("/categorias/{idCategoria}/hilos/{id}/actualizar")
    public String actualizarHilo(@PathVariable("id") Long id, @ModelAttribute("hiloEditar") Hilo hiloEditado,
                                  @PathVariable("idCategoria") Long idCategoria) {
        Hilo hiloExistente = hiloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + id));

        hiloExistente.setTitulo(hiloEditado.getTitulo());
        hiloRepository.save(hiloExistente);

        return "redirect:/categorias/{idCategoria}/hilos";
    }


}
