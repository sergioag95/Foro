package principal.servicio;

import java.util.List;

import principal.modelo.dto.UsuarioDTO;
import principal.modelo.Post;
import principal.modelo.Rol;
import principal.modelo.Usuario;

public interface UsuarioServicio  {
	public Usuario insertarUsuario (Usuario user);
	public Usuario insertarusuarioDTO (UsuarioDTO userDTO);
	public List<Usuario> listarUsuarios();
	public Usuario obtenerUsuarioPorID (Long id);
	public Usuario obtenerUsuarioPorNombre (String nombre);
	public void eliminarUsuario(Usuario user);
	public void eliminarUsuarioPorId(Long id);
	public Rol obtenerRolPorNombre(String nombre);
    public boolean existeUsername(String username);
    public boolean existeEmail(String email);
    public void actualizarUsuario(Usuario usuario);
    public List<Post> obtenerPostsPorUsuario(Usuario usuario);
    public void seguirUsuario(String usernameSeguidor, Long userIdToFollow);
}
