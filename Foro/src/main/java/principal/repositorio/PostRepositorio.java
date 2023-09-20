package principal.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import principal.modelo.Hilo;
import principal.modelo.Post;

@Repository
public interface PostRepositorio extends JpaRepository<Post, Long> {
    List<Post> findByHilo(Hilo hilo);
    


}

