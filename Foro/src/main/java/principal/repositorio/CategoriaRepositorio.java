package principal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import principal.modelo.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository <Categoria, Long>{

}
