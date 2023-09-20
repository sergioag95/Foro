package principal.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import principal.modelo.Categoria;
import principal.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepositorio repositorio;

    @Autowired
    public CategoriaServicioImpl(CategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return repositorio.findAll();
    }

    @Override
    public void agregarCategoria(Categoria categoria) {
        repositorio.save(categoria);
    }

    @Override
    public Categoria obtenerCategoria(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        repositorio.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        repositorio.deleteById(id);
    }
}

