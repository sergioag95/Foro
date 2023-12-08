package principal.modelo;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @Column(name="username", unique = true)
	private String username;
    
    @Column(name="email", unique = true)
	private String email;
    
    @Column (name="password")
	private String password;
    
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    @Column(name = "numero_posts")
    private int numeroPosts;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Hilo> hilos;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
    
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(
			name="usuario_roles",
			joinColumns = {@JoinColumn(name="id_usuario")},
			inverseJoinColumns = @JoinColumn(name="id_rol")
			)
    
	@JsonIgnore
    @Column (name="rol")

	private Set<Rol> roles;
    
    
    
    @ManyToMany
    @JoinTable(
        name = "usuarios_seguidos",
        joinColumns = @JoinColumn(name = "seguidor_id"),
        inverseJoinColumns = @JoinColumn(name = "seguido_id")
    )
    private Set<Usuario> seguidos = new HashSet<>();

    @ManyToMany(mappedBy = "seguidos")
    private Set<Usuario> seguidores = new HashSet<>();
	
    public Usuario() {
        roles = new HashSet<>();
    }


	public Usuario(String nombre, String usuario, String pass, String email, LocalDateTime fechaAlta, int numeroPosts) {
		this.nombre = nombre;
		this.username = usuario;
		this.password = pass;
		this.email=email;
		this.fechaAlta = LocalDateTime.now(); // Establece la fecha de alta al momento de la creación del usuario
		this.numeroPosts = 0; 
		roles = new HashSet<Rol>();
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

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	 public Set<Usuario> getSeguidos() {
	        return seguidos;
	    }

    public Set<Usuario> getSeguidores() {
        return seguidores;
    }

    public void seguirUsuario(Usuario usuario) {
        seguidos.add(usuario);
        usuario.seguidores.add(this);
    }

    public void dejarDeSeguirUsuario(Usuario usuario) {
        seguidos.remove(usuario);
        usuario.seguidores.remove(this);
    }
	
    // Constructor, getters y setters
    
    
}
