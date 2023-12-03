package principal.servicio;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import principal.modelo.Rol;
import principal.repositorio.RolRepo;

@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
	private RolRepo rolRepo;
	


	@Override
	public Rol obtenerRolPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return rolRepo.findByNombre(nombre);
	}

	 public List<Rol> obtenerTodosLosRoles() {
	        return rolRepo.findAll();
	    }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
