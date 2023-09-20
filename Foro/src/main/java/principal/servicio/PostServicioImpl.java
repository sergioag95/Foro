package principal.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.repositorio.PostRepositorio;

@Service
public class PostServicioImpl implements PostServicio {

    private final PostRepositorio postRepositorio;
    private final HiloServicio hiloServicio;

    @Autowired
    public PostServicioImpl(PostRepositorio postRepositorio, HiloServicio hiloServicio) {
        this.postRepositorio = postRepositorio;
        this.hiloServicio = hiloServicio;
    }

    @Override
    public List<Post> listarPostsPorHilo(Hilo hilo) {
        return postRepositorio.findByHilo(hilo);
    }

    @Override
    public void agregarPost(Post post, Hilo hilo) {
        post.setHilo(hilo);
        postRepositorio.save(post);
    }

    @Override
    public List<Post> findByHilo(Hilo hilo) {
        return postRepositorio.findByHilo(hilo);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepositorio.findAll();
    }

    @Override
    public Post createPost(Post post, Hilo hilo) {
        post.setHilo(hilo);
        return postRepositorio.save(post);
    }

    @Override
    public Hilo obtenerHiloDesdeAlgunLugar() {
        // Por ejemplo, supongamos que obtienes el hilo a través de su ID
        Long hiloId = 1L; // ID del hilo que deseas obtener

        return hiloServicio.obtenerHiloPorId(hiloId);
    }
    
    @Override
    public Post obtenerPostPorId(Long id) {
        // Utiliza el repositorio de posts para obtener el post por su ID
        Optional<Post> optionalPost = postRepositorio.findById(id);
        
        // Verificar si se encontró el post y devolverlo
        if (optionalPost.isPresent()) {
            return optionalPost.get();
        }
        
        // En caso contrario, devolver null o lanzar una excepción, según tu caso de uso
        return null;
    }
    
    @Override
    public void guardarPost(Post post) {
    	postRepositorio.save(post);
    }
    
    public void borrarPost(Long idPost) {
        postRepositorio.deleteById(idPost);
    }


}
