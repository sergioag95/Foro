package principal.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import principal.modelo.Categoria;
import principal.modelo.Hilo;
import principal.repositorio.CategoriaRepositorio;
import principal.repositorio.HiloRepositorio;

@Service
public class HiloServicioImpl implements HiloServicio {

    private final HiloRepositorio hiloRepository;
    private final CategoriaRepositorio categoriaRepository;

    @Autowired
    public HiloServicioImpl(HiloRepositorio hiloRepository, CategoriaRepositorio categoriaRepository) {
        this.hiloRepository = hiloRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Hilo> listarHilosPorCategoria(Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow();
        return hiloRepository.findByCategoria(categoria);
    }

    @Override
    public void agregarHilo(Hilo hilo, Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow();
        hilo.setCategoria(categoria);
        hiloRepository.save(hilo);
    }

	@Override
	public List<Hilo> listarTodosLosHilos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Hilo obtenerHiloPorId(Long id) {
	    return hiloRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Hilo no encontrado con ID: " + id));
	}
	
	public void guardarHilo(Hilo hilo) {
        hiloRepository.save(hilo);
    }

}
