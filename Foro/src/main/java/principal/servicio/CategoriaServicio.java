package principal.servicio;

import java.util.List;

import principal.modelo.Categoria;

public interface CategoriaServicio {
    List<Categoria> listarCategorias();
    void agregarCategoria(Categoria categoria);
    Categoria obtenerCategoria(Long id);
    void actualizarCategoria(Categoria categoria);
    void eliminarCategoria(Long id);
}
