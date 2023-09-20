package principal.servicio;

import java.util.List;

import principal.modelo.Hilo;
import principal.modelo.Post;

public interface PostServicio {
    List<Post> listarPostsPorHilo(Hilo hilo);
    void agregarPost(Post post, Hilo hilo);
    List<Post> findByHilo(Hilo hilo);
    public Post createPost(Post post, Hilo hilo);
    List<Post> getAllPosts();
    public Hilo obtenerHiloDesdeAlgunLugar();
    public Post obtenerPostPorId(Long id);
    public void guardarPost(Post post);
    public void borrarPost(Long idPost);

}