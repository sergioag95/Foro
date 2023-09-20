package principal.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import principal.modelo.Categoria;
import principal.modelo.Hilo;

@Repository
public interface HiloRepositorio extends JpaRepository <Hilo, Long>{

	List<Hilo> findByCategoria(Categoria categoria);

}
