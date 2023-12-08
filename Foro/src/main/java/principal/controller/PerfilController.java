package principal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import principal.modelo.Post;
import principal.modelo.Usuario;
import principal.servicio.UsuarioServicio;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioServicio usuarioService;  // Asegúrate de tener tu servicio de usuario
    
    
    
    @GetMapping("/{idUsuario}")
    public String mostrarPerfil(@PathVariable Long idUsuario, Model model, Principal principal) {
        Usuario usuario = usuarioService.obtenerUsuarioPorID(idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
        }

        boolean isSiguiendo = usuarioService.isUserFollowing(principal.getName(), usuario.getId());

        // Agrega los posts del usuario al modelo
        List<Post> postsUsuario = usuarioService.obtenerPostsPorUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("postsUsuario", postsUsuario);
        model.addAttribute("isSiguiendo", isSiguiendo);

        return "perfil";
    }
    
    @PostMapping("/{idUsuario}/follow")
    public String followUser(@RequestParam("userIdToFollow") Long userIdToFollow, Principal principal) {
        String username = principal.getName();
        usuarioService.seguirUsuario(username, userIdToFollow);
        // Redirige a la página del perfil o a donde desees después de seguir al usuario
        return "redirect:/perfil/" + userIdToFollow;
    }
    
    @PostMapping("/{idUsuario}/unfollow")
    public String unfollowUser(@RequestParam("userIdToUnfollow") Long userIdToUnfollow, Principal principal) {
        String username = principal.getName();
        usuarioService.unfollowUsuario(username, userIdToUnfollow);
        // Redirige a la página del perfil o a donde desees después de seguir al usuario
        return "redirect:/perfil/" + userIdToUnfollow;
    }
}
