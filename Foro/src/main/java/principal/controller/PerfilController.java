package principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import principal.modelo.Post;
import principal.modelo.Usuario;
import principal.servicio.PostServicio;
import principal.servicio.UsuarioServicio;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioServicio usuarioService;  // Asegúrate de tener tu servicio de usuario
    
    @Autowired
    private PostServicio postServicio;
    
    @GetMapping("/{idUsuario}")
    public String mostrarPerfil(@PathVariable Long idUsuario, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorID(idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
        }

        // Agrega los posts del usuario al modelo
        List<Post> postsUsuario = usuarioService.obtenerPostsPorUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("postsUsuario", postsUsuario);
        
        return "perfil";
    }
}
