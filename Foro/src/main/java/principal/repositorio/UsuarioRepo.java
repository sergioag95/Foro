package principal.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import principal.modelo.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
}
