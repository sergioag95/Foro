package principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import principal.modelo.Usuario;
import principal.servicio.UsuarioServicio;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioServicio usuarioService;  // Asegúrate de tener tu servicio de usuario

    @GetMapping("/{idUsuario}")
    public String mostrarPerfil(@PathVariable Long idUsuario, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorID(idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
        }

        model.addAttribute("usuario", usuario);

        return "perfil";
    }
}
