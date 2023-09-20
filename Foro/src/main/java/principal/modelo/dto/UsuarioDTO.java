package principal.modelo.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import principal.modelo.Hilo;
import principal.modelo.Post;
import principal.modelo.Rol;
import principal.modelo.Usuario;
import principal.servicio.UsuarioServiceImpl;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String username;
    private List<Hilo> hilos;
    private List<Post> posts;
    private Set<String> roles;
    private String password;
    private String email;
    private LocalDateTime fechaAlta;
    private int numeroPosts;

    public UsuarioDTO() {
    }
    
    @Autowired
    private UsuarioServiceImpl userDetailsService;

    public UsuarioDTO(Long id, String nombre, String username, List<Hilo> hilos, List<Post> posts, Set<String> roles, String password, String email, LocalDateTime fechaAlta, int numeroPosts) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.hilos = hilos;
        this.posts = posts;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.numeroPosts = numeroPosts;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getNumeroPosts() {
        return numeroPosts;
    }

    public void setNumeroPosts(int numeroPosts) {
        this.numeroPosts = numeroPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Hilo> getHilos() {
        return hilos;
    }

    public void setHilos(List<Hilo> hilos) {
        this.hilos = hilos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static UsuarioDTO fromUsuario(Usuario usuario) {
        Set<String> roles = usuario.getRoles().stream()
                .map(Rol::getNombre)
                .map(nombreRol -> {
                    if (nombreRol.equals("ROL_ADMIN")) {
                        return "Administrador";
                    } else {
                        // Otros roles
                        return nombreRol;
                    }
                })
                .collect(Collectors.toSet());

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getHilos(),
                usuario.getPosts(),
                roles,
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getFechaAlta(),
                usuario.getNumeroPosts()
        );
    }
	
	public Usuario toUsuario() {
	    Usuario usuario = new Usuario();
	    usuario.setNombre(this.getNombre());
	    usuario.setUsername(this.getUsername());
	    usuario.setEmail(this.getEmail());
	    usuario.setFechaAlta(this.getFechaAlta());
	    usuario.setNumeroPosts(this.getNumeroPosts());

	    Set<Rol> roles = new HashSet<>();
	    for (String rolNombre : this.getRoles()) {
	        Rol rol = userDetailsService.obtenerRolPorNombre(rolNombre);
	        if (rol == null) {
	            rol = new Rol(rolNombre);
	        }
	        roles.add(rol);
	    }
	    usuario.setRoles(roles);

	    return usuario;
	}


}

