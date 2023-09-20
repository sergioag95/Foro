package principal.servicio;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import principal.modelo.Rol;
import principal.modelo.Usuario;
import principal.modelo.dto.UsuarioDTO;
import principal.repositorio.RolRepo;
import principal.repositorio.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioServicio {
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@Autowired
	private RolRepo rolRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Usuario> usuario = usuarioRepo.findByUsername(username);
		
		if(usuario.isPresent()) {
			
			Usuario springUsermio = usuario.get();
			return springUsermio;
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		
	}

	@Override
	public Usuario insertarUsuario(Usuario user) {
		usuarioRepo.save(user);
		return null;
	}

	@Override
	public Usuario insertarusuarioDTO(UsuarioDTO userDTO) {
	    Usuario nuevoUsuario = new Usuario(userDTO.getUsername(), userDTO.getNombre(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail(), userDTO.getFechaAlta(), userDTO.getNumeroPosts());
	    
	    // Verificar si el rol existe en la base de datos
	    Rol rol = rolRepo.findByNombre(userDTO.getRoles().iterator().next());
	    if (rol != null) {
	        nuevoUsuario.getRoles().add(rol);
	    }

	    return usuarioRepo.save(nuevoUsuario);
	}


	@Override
	public List<Usuario> listarUsuarios() {
		
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	public Usuario obtenerUsuarioPorID(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepo.findById(id).get();
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuarioRepo.findByUsername(nombre).get();
	}

	@Override
	public void eliminarUsuario(Usuario user) {
		usuarioRepo.delete(user);
	}

	@Override
	public void eliminarUsuarioPorId(Long id) {
		usuarioRepo.delete(usuarioRepo.findById(id).get());
		
	}
	
	public Rol obtenerRolPorNombre(String nombre) {
	    return rolRepo.findByNombre(nombre);
	}
	
	@Override
    public boolean existeEmail(String email) {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
        return usuario != null;
    }

    @Override
    public boolean existeUsername(String username) {
        Optional<Usuario> usuario = usuarioRepo.findByUsername(username);
        return usuario != null;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
    	usuarioRepo.save(usuario);
    }
	

}

