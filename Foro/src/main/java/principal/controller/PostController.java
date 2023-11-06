package principal.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.modelo.Usuario;
import principal.repositorio.CategoriaRepositorio;
import principal.repositorio.HiloRepositorio;
import principal.repositorio.UsuarioRepo;
import principal.servicio.HiloServicio;
import principal.servicio.PostServicio;



@Controller
public class PostController {

    private final HiloServicio hiloService;
    private final PostServicio postService;
    private final CategoriaRepositorio categoriaRepository;
    private final HiloRepositorio hiloRepository;
    
    @Autowired
    private UsuarioRepo usuarioRepo;
    
    @Autowired
    public PostController(HiloServicio hiloService, PostServicio postService,
                          CategoriaRepositorio categoriaRepository, HiloRepositorio hiloRepository) {
        this.hiloService = hiloService;
        this.postService = postService;
        this.categoriaRepository = categoriaRepository;
        this.hiloRepository = hiloRepository;
    }

    @GetMapping("/hilos/{idHilo}/verPosts")
    public String listarPostsPorHilo(@PathVariable("idHilo") Long idHilo, Model model) {
        Hilo hilo = hiloRepository.findById(idHilo)
                .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + idHilo));
        List<Post> posts = postService.listarPostsPorHilo(hilo);
        model.addAttribute("hilo", hilo);
        model.addAttribute("listaPosts", posts);
        model.addAttribute("nuevoPost", new Post()); // Agrega esto para el formulario de crear post
        return "postHilo";
    }

    @PostMapping("/hilo/crearPost")
    public String crearPost(@ModelAttribute("nuevoPost") Post nuevoPost,
                            @RequestParam("idHilo") Long idHilo) {
        Hilo hilo = hiloRepository.findById(idHilo)
                .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + idHilo));

        // Obtén el usuario autenticado
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Asigna el usuario al post
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        nuevoPost.setUsuario(usuario);
        usuario.setNumeroPosts(usuario.getNumeroPosts()+1);

        postService.agregarPost(nuevoPost, hilo);
        return "redirect:/hilos/" + idHilo + "/verPosts";
    }
    @GetMapping("/editarPost")
    public String editarPost(@RequestParam("idPost") Long idPost, Model model,@ModelAttribute("mismoPost") Post mismoPost) {
    	// Obtén el usuario autenticado
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Asigna el usuario al post
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        mismoPost.setUsuario(usuario);
        
        Post post = postService.obtenerPostPorId(idPost);
        if (post == null) {
            return "redirect:/hilo/lista";
        }
        model.addAttribute("post", post);
        model.addAttribute("modoEdicion", true);
        return "editarPost";
    }

    @PostMapping("/guardarPost")
    public String guardarPost(@ModelAttribute("post") Post post) {
        postService.guardarPost(post);
        return "redirect:/hilo/lista";
    }

    @GetMapping("/hilo/editarPost")
    public String mostrarFormularioEdicion(@RequestParam("idPost") Long idPost, Model model) {
        Post post = postService.obtenerPostPorId(idPost);
        if (post == null) {
            return "redirect:/hilo/lista";
        }
        model.addAttribute("post", post);
        return "editarPost";
    }
    
    @PostMapping("/hilo/guardarPost/{id}")
    public String guardarPostEditado(@PathVariable("id") Long id, @ModelAttribute("post") Post post,
                                     @RequestParam("idHilo") Long idHilo) {
        Hilo hilo = hiloRepository.findById(idHilo)
                .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + idHilo));
        post.setId(id); // Establecer el ID del post existente
        post.setHilo(hilo);
        post.setFecha(LocalDateTime.now());
        postService.guardarPost(post);
        return "redirect:/hilos/" + idHilo + "/verPosts";
    }

    @PostMapping("/borrarPost")
    public String borrarPost(@RequestParam("idPost") Long idPost, Model model) {
        Post post = postService.obtenerPostPorId(idPost);

        if (post == null) {
            return "redirect:/hilo/lista";
        }

        Hilo hilo = post.getHilo();
        postService.borrarPost(idPost);

        return "redirect:/hilos/" + hilo.getId() + "/verPosts";
    }



    // Otros métodos según tus necesidades...
}
