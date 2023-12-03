package principal.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import principal.modelo.Rol;
import principal.modelo.Usuario;
import principal.modelo.dto.UsuarioDTO;
import principal.repositorio.RolRepository;
import principal.servicio.UsuarioServiceImpl;

@RequestMapping("/usuarios")

@Controller
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl userDetailsService;
    
    @Autowired
    private RolRepository rolRepository;

    @GetMapping(value = { "", "/" })
    String homeusuarios(Model model) {
        ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) userDetailsService.listarUsuarios();
        ArrayList<UsuarioDTO> listaUsuariosDTO = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            listaUsuariosDTO.add(UsuarioDTO.fromUsuario(usuario));
        }

        model.addAttribute("listaUsuarios", listaUsuariosDTO);
        model.addAttribute("usuarioaEditar", new UsuarioDTO());
        model.addAttribute("usuarioNuevo", new UsuarioDTO());

        return "usuarios";
    }

    @PostMapping("/edit/{id}")
    public String editarUsuario(@PathVariable Long id, @RequestParam("rol") String rol, @ModelAttribute("usuarioaEditar") UsuarioDTO usuarioEditado, BindingResult bindingresult) {

        Usuario usuarioaeditar = userDetailsService.obtenerUsuarioPorID(id);

        usuarioaeditar.setNombre(usuarioEditado.getNombre());
        usuarioaeditar.setUsername(usuarioEditado.getUsername());
        usuarioaeditar.setEmail(usuarioEditado.getEmail());

        // Asignar roles según la selección en el formulario
//        String rolSeleccionado = rol;
        

        	usuarioaeditar.getRoles().clear();
        
    	   Rol Rol = new Rol(rol);

           usuarioaeditar.getRoles().add(Rol);
           
       

        userDetailsService.insertarUsuario(usuarioaeditar);    
        
        
        // Encriptar la contraseña solo si se ha proporcionado una nueva contraseña
        if (usuarioEditado.getPassword() != null && !usuarioEditado.getPassword().isEmpty()) {
            // Encriptar la contraseña solo si se ha proporcionado una nueva contraseña
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usuarioEditado.getPassword());
            usuarioaeditar.setPassword(encodedPassword);
        }

        userDetailsService.insertarUsuario(usuarioaeditar);

        return "redirect:/usuarios";
    }
    



    @PostMapping("/add")
    public String addUsuario(@ModelAttribute("usuarioNuevo") Usuario usuarioNew, BindingResult bindingresult) {

        userDetailsService.insertarUsuario(usuarioNew);

        return "redirect:/usuarios";
    }

    @GetMapping({"/{id}"})
    String idUsuario(Model model, @PathVariable Long id) {

        Usuario usuarioMostrar = userDetailsService.obtenerUsuarioPorID(id);
        model.addAttribute("usuarioMostrar", usuarioMostrar);

        return "usuario";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuario(Model model, @PathVariable("id") Long id) {
    	userDetailsService.eliminarUsuarioPorId(id);
        return "redirect:/usuarios";
    }


    private boolean elUsuarioLogueadoEsAdmin() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;

        if (principal instanceof UserDetails) {

            userDetails = (UserDetails) principal;

            Usuario u = userDetailsService.obtenerUsuarioPorNombre(userDetails.getUsername());

            for (Rol r : u.getRoles()) {

                if (r.getNombre().compareTo("ROLE_ADMIN") == 0) {

                    return true;

                }
            }
        }

        return false;
    }

    // ...

    @GetMapping("/registro")
    public String mostrarRegistro(Model model, @ModelAttribute("usuarioNuevo") UsuarioDTO usuarioNew, BindingResult bindingresult) {
        model.addAttribute("newUserDTO", new UsuarioDTO());
        return "registro";
    }

    @PostMapping("/addRegistro")
    public String addRegistro(@ModelAttribute("usuarioNuevo") UsuarioDTO usuarioNew, BindingResult bindingresult) {
    	Usuario usuario = new Usuario();
    	usuario.setNombre(usuarioNew.getNombre());
    	usuario.setUsername(usuarioNew.getUsername());
    	usuario.setEmail(usuarioNew.getEmail());
    	usuario.setFechaAlta(LocalDateTime.now());
    	usuario.setNumeroPosts(0);
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String encodedPassword = passwordEncoder.encode(usuarioNew.getPassword());
    	usuario.setPassword(encodedPassword);


    	 Set<Rol> roles = new HashSet<>();
    	    for (String rolNombre : usuarioNew.getRoles()) {
    	        Rol rol = userDetailsService.obtenerRolPorNombre(rolNombre);
    	        if (rol == null) {
    	            rol = new Rol(rolNombre);
    	        }
    	        roles.add(rol);
    	    }
    	    usuario.setRoles(roles);

        userDetailsService.insertarUsuario(usuario);

        return "login";
    }
    
    
    

    // ...
}