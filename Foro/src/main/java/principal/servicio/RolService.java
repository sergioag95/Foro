package principal.servicio;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import principal.modelo.Rol;

@Service
public interface RolService extends UserDetailsService {
	
	public Rol obtenerRolPorNombre (String nombre);

	
}
