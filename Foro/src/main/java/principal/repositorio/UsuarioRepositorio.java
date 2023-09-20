package principal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import principal.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>{

}
