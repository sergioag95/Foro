package principal.servicio;

import java.util.List;

import principal.modelo.Categoria;
import principal.modelo.Hilo;

public interface HiloServicio  {
	public List <Hilo> listarTodosLosHilos();

	public List<Hilo> listarHilosPorCategoria(Long categoria);

	public void agregarHilo(Hilo hiloNuevo, Long idCategoria);
	
	public Hilo obtenerHiloPorId(Long id);
	
	public void guardarHilo(Hilo hilo);
}
