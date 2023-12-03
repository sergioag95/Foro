package principal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import principal.modelo.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
