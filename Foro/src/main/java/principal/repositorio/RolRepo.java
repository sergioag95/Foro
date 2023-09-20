package principal.repositorio;



import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import principal.modelo.Rol;

public interface RolRepo extends JpaRepository<Rol,Integer> {

	public Rol findByNombre(String nombre);
	
    List<Rol> findByNombreIn(Set<String> nombres);

	
}
